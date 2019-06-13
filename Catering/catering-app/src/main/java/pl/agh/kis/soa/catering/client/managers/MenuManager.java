package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.MenuCategoryService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.client.services.OfferOfTheDayService;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

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

    public List<MenuItem> getAllMenuCategoryItems() { return menuItemService.getAllMenuCategoryItems(menuCategoryId); }

    public List<MenuItem> getAllMenuCategoryItemsForClient(MenuCategory menuCategory) {
        List<MenuItem> allMenuCategoryItems = menuItemService.getAllMenuCategoryItems(menuCategory.getId());

        for (MenuItem allMenuCategoryItem : allMenuCategoryItems) {
            if (offerOfTheDayService.isMenuItemInOffersOfTheDay(allMenuCategoryItem.getId()))
                allMenuCategoryItem.setPrice(offerOfTheDayService.getOfferOfTheDay(allMenuCategoryItem.getId()).getPrice());
        }

        return allMenuCategoryItems;
    }

    public String addMenuCategory() {
        this.menuCategory = new MenuCategory();
        operationType = OperationType.Add;

        return "add-update-menu-category";
    }

    public String updateMenuCategory() {
        this.menuCategory = menuCategoryService.getMenuCategoryById(menuCategoryId);
        operationType = OperationType.Update;

        return "add-update-menu-category";
    }

    public String deleteMenuCategory() {
        menuCategoryService.deleteMenuCategory(menuCategoryId);

        return "manager-panel";
    }

    public String addMenuItem() {
        this.menuItem = new MenuItem();
        operationType = OperationType.Add;

        return "add-update-menu-item";
    }

    public String updateMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        operationType = OperationType.Update;

        return "add-update-menu-item";
    }

    public String deleteMenuItem(MenuItem menuItem) {
        menuItemService.deleteMenuItem(menuItem.getId());

        return "manager-panel";
    }

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
}

enum OperationType {
    Add,
    Update
}
