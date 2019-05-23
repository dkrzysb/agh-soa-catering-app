package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public interface IMenuItemRepository {
    MenuItem getMenuItemById(Long id);
    List<MenuItem> getAllMenuItems();
    boolean addMenuItem(String name, int servingSize, BigDecimal price, Long menuCategoryId);
    boolean updateMenuItem(Long id, String name, int servingSize, BigDecimal price, Long menuCategoryId);
    void deleteMenuItem(Long id);
}
