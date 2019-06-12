package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IMenuItemRepository;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.math.BigDecimal;
import java.util.List;

@ManagedBean(name = "menuItemService", eager = true)
@ApplicationScoped
public class MenuItemService {
    @EJB(lookup = "java:global/catering-business-logic/MenuItemRepository")
    IMenuItemRepository menuItemRepository;

    public MenuItem getMenuItemById(Long id) {
        return menuItemRepository.getMenuItemById(id);
    }

    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.getAllMenuItems();
    }

    public List<MenuItem> getAllMenuCategoryItems(Long menuCategoryId) { return menuItemRepository.getAllMenuCategoryItems(menuCategoryId); }


    public List<MenuItem> getAllAcceptedMenuCategoryItems(Long menuCategoryId) { return menuItemRepository.getAllAcceptedMenuCategoryItems(menuCategoryId); }

    public void addMenuItem(String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        menuItemRepository.addMenuItem(name, servingSize, price, menuCategoryId, true);
    }

    public void updateMenuItem(Long id, String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        menuItemRepository.updateMenuItem(id, name, servingSize, price, menuCategoryId);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteMenuItem(id);
    }

    public List<MenuItem> topMeals() {
        return menuItemRepository.topMeals();
    }

    public void acceptItem(Long menuItemId) {
        menuItemRepository.acceptItem(menuItemId);
    }
}
