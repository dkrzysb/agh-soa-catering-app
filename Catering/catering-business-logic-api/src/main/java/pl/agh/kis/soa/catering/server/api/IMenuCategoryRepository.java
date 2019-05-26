package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.MenuCategory;

import java.util.List;

public interface IMenuCategoryRepository {
    MenuCategory getMenuCategoryById(Long id);
    List<MenuCategory> getAllMenuCategories();
    void addMenuCategory(String name);
    void updateMenuCategory(Long id, String name);
    void deleteMenuCategory(Long id);
}
