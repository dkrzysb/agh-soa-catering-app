package pl.agh.kis.soa.catering.client.managers;

import org.jboss.ejb3.annotation.SecurityDomain;
import pl.agh.kis.soa.catering.client.services.MenuCategoryService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.client.services.OfferOfTheDayService;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.OfferOfTheDay;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@SecurityDomain("postgresqldomain")
@ManagedBean(name = "OfferOfTheDayManager")
@SessionScoped
public class OfferOfTheDayManager {
    private Long selectedOfferOfTheDay;
    private int discount;

    @ManagedProperty(value="#{menuItemService}")
    private MenuItemService menuItemService;
    @ManagedProperty(value="#{menuCategoryService}")
    private MenuCategoryService menuCategoryService;
    @ManagedProperty(value="#{offerOfTheDayService}")
    private OfferOfTheDayService offerOfTheDayService;

    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }

    public void setMenuCategoryService(MenuCategoryService menuCategoryService) { this.menuCategoryService = menuCategoryService; }

    public void setOfferOfTheDayService(OfferOfTheDayService offerOfTheDayService) { this.offerOfTheDayService = offerOfTheDayService; }

    public Long getSelectedOfferOfTheDay() {
        return selectedOfferOfTheDay;
    }

    public int getDiscount() {
        return discount;
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> allMenuItems = new ArrayList<MenuItem>();

        for(MenuCategory menuCategory : menuCategoryService.getAllMenuCategories()) {
            allMenuItems.addAll(menuItemService.getAllMenuCategoryItems(menuCategory.getId()));
        }

        return allMenuItems;
    }

    public List<OfferOfTheDay> getAllOffersOfTheDay() {
        return offerOfTheDayService.getAllOffersOfTheDay();
    }

    public void setSelectedOfferOfTheDay(Long selectedOfferOfTheDay) {
        this.selectedOfferOfTheDay = selectedOfferOfTheDay;
    }

    @RolesAllowed({"Admin","Manager"})
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @RolesAllowed({"Admin","Manager"})
    public String setOfferOfTheDay() {
        MenuItem menuItem = menuItemService.getMenuItemById(selectedOfferOfTheDay);

        offerOfTheDayService.addOfferOfTheDay(selectedOfferOfTheDay, new BigDecimal(menuItem.getPrice().doubleValue() * discount / 100.0));

        return "manager-panel";
    }

    @RolesAllowed({"Admin","Manager"})
    public String deleteOfferOfTheDay(Long menuItemId) {
        offerOfTheDayService.deleteOfferOfTheDay(menuItemId);

        return "manager-panel";
    }
}
