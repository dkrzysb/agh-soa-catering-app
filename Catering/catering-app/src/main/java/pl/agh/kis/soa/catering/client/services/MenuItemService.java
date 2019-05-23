package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IMenuItemRepository;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
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

    public boolean addMenuItem(String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        return menuItemRepository.addMenuItem(name, servingSize, price, menuCategoryId);
    }

    public boolean updateMenuItem(Long id, String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        return menuItemRepository.updateMenuItem(id, name, servingSize, price, menuCategoryId);
    }

    public void deleteMenuItem(Long id) {
        menuItemRepository.deleteMenuItem(id);
    }
}
