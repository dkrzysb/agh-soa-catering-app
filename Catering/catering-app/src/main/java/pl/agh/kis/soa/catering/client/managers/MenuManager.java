package pl.agh.kis.soa.catering.client.managers;

import org.jboss.ejb3.annotation.SecurityDomain;
import pl.agh.kis.soa.catering.client.services.MenuCategoryService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.client.services.OfferOfTheDayService;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@SecurityDomain("postgresqldomain")
@ManagedBean(name = "MenuManager")
@SessionScoped
public class MenuManager {
    private Long menuCategoryId = 1L;
    private MenuCategory menuCategory;
    private MenuItem menuItem;
    private OperationType operationType;

    @ManagedProperty(value="#{menuItemService}")
    private MenuItemService menuItemService;
    @ManagedProperty(value="#{menuCategoryService}")
    private MenuCategoryService menuCategoryService;
    @ManagedProperty(value="#{offerOfTheDayService}")
    private OfferOfTheDayService offerOfTheDayService;

    public Long getMenuCategoryId() { return menuCategoryId; }

    public MenuCategory getMenuCategory() { return menuCategory; }

    public MenuItem getMenuItem() { return menuItem; }

    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }

    public void setMenuCategoryService(MenuCategoryService menuCategoryService) { this.menuCategoryService = menuCategoryService; }

    public void setOfferOfTheDayService(OfferOfTheDayService offerOfTheDayService) { this.offerOfTheDayService = offerOfTheDayService; }

    public void setMenuCategoryId(Long menuCategoryId) { this.menuCategoryId = menuCategoryId; }

    public List<MenuCategory> getAllMenuCategories() {
        return menuCategoryService.getAllMenuCategories();
    }

    public List<MenuItem> getAllAcceptedMenuCategoryItems() { return menuItemService.getAllAcceptedMenuCategoryItems(menuCategoryId); }

    public List<MenuItem> getAllMenuCategoryItems() { return menuItemService.getAllMenuCategoryItems(menuCategoryId); }

    public List<MenuItem> getAllMenuCategoryItemsForClient(MenuCategory menuCategory) {
        List<MenuItem> allMenuCategoryItems = menuItemService.getAllAcceptedMenuCategoryItems(menuCategory.getId());

        for (MenuItem allMenuCategoryItem : allMenuCategoryItems) {
            if (offerOfTheDayService.isMenuItemInOffersOfTheDay(allMenuCategoryItem.getId()))
                allMenuCategoryItem.setPrice(offerOfTheDayService.getOfferOfTheDay(allMenuCategoryItem.getId()).getPrice());
        }

        return allMenuCategoryItems;
    }

    @RolesAllowed({"Admin","Manager"})
    public String addMenuCategory() {
        this.menuCategory = new MenuCategory();
        operationType = OperationType.Add;

        return "add-update-menu-category";
    }
    @RolesAllowed({"Admin","Manager"})
    public String updateMenuCategory() {
        this.menuCategory = menuCategoryService.getMenuCategoryById(menuCategoryId);
        operationType = OperationType.Update;

        return "add-update-menu-category";
    }

    @RolesAllowed({"Admin","Manager"})
    public String deleteMenuCategory() {
        menuCategoryService.deleteMenuCategory(menuCategoryId);

        return "manager-panel";
    }

    @RolesAllowed({"Admin","Manager"})
    public String addMenuItem() {
        this.menuItem = new MenuItem();
        operationType = OperationType.Add;

        return "add-update-menu-item";
    }

    @RolesAllowed({"Admin","Manager"})
    public String updateMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        operationType = OperationType.Update;

        return "add-update-menu-item";
    }

    @RolesAllowed({"Admin","Manager"})
    public String deleteMenuItem(MenuItem menuItem) {
        menuItemService.deleteMenuItem(menuItem.getId());

        return "manager-panel";
    }

    @RolesAllowed({"Admin","Manager"})
    public String addOrUpdateMenuCategory() {
        switch(operationType) {
            case Add:
                menuCategoryService.addMenuCategory(menuCategory.getName());
                break;
            case Update:
                menuCategoryService.updateMenuCategory(menuCategory.getId(), menuCategory.getName());
                break;
        }

        return "manager-panel";
    }

    @RolesAllowed({"Admin","Manager"})
    public String addOrUpdateMenuItem() {
        switch(operationType) {
            case Add:
                menuItemService.addMenuItem(menuItem.getName(), menuItem.getServingSize(), menuItem.getPrice(), menuCategoryId);
                break;
            case Update:
                menuItemService.updateMenuItem(menuItem.getId(), menuItem.getName(), menuItem.getServingSize(), menuItem.getPrice(), menuCategoryId);
                break;
        }

        return "manager-panel";
    }


    @RolesAllowed({"Admin","Manager"})
    public void download() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        ec.setResponseContentType("text"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ExternalContext#getMimeType() for auto-detection based on filename.
//        ec.setResponseContentLength(contentLength); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + "menu-" + Instant.now().truncatedTo(ChronoUnit.SECONDS) + ".csv" + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.
        OutputStream output = ec.getResponseOutputStream();

        List<MenuCategory> categories = getAllMenuCategories();


        PrintWriter pw = new PrintWriter(output);


        for (MenuCategory menuCategory : categories){
            pw.println(menuCategory.getName());

            for (MenuItem menuItem : menuCategory.getItems()){
                pw.println(menuItem);
            }
        }

        pw.close();

        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
    }

    @RolesAllowed({"Admin","Manager"})
    public void acceptItem(MenuItem menuItem) {
        menuItemService.acceptItem(menuItem.getId());
    }
}

enum OperationType {
    Add,
    Update
}
