package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.MenuCategoryService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "OfferOfTheDayManager")
@SessionScoped
public class OfferOfTheDayManager {
    private Long[] selectedOffersOfTheDay;
    private int discount;

    @ManagedProperty(value="#{menuItemService}")
    private MenuItemService menuItemService;
    @ManagedProperty(value="#{menuCategoryService}")
    private MenuCategoryService menuCategoryService;

    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }

    public void setMenuCategoryService(MenuCategoryService menuCategoryService) { this.menuCategoryService = menuCategoryService; }

    public Long[] getSelectedOffersOfTheDay() {
        return selectedOffersOfTheDay;
    }
    public void setSelectedOffersOfTheDay(Long[] selectedOffersOfTheDay) {
        this.selectedOffersOfTheDay = selectedOffersOfTheDay;
    }

    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> allMenuItems = new ArrayList<MenuItem>();

        for(MenuCategory menuCategory : menuCategoryService.getAllMenuCategories()) {
            allMenuItems.addAll(menuItemService.getAllMenuCategoryItems(menuCategory.getId()));
        }

        return allMenuItems;
    }

    public String setDiscount() {

        return "manager-panel";
    }
}
