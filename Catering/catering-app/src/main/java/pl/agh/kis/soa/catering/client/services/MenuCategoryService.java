package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IMenuCategoryRepository;
import pl.agh.kis.soa.catering.server.model.MenuCategory;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "menuCategoryService", eager = true)
@ApplicationScoped
public class MenuCategoryService {
    @EJB(lookup = "java:global/catering-business-logic/MenuCategoryRepository")
    IMenuCategoryRepository menuCategoryRepository;

    public MenuCategory getMenuCategoryById(Long id) {
        return menuCategoryRepository.getMenuCategoryById(id);
    }

    public List<MenuCategory> getAllMenuCategories() {
        return menuCategoryRepository.getAllMenuCategories();
    }

    public void addMenuCategory(String name) {
        menuCategoryRepository.addMenuCategory(name);
    }

    public void updateMenuCategory(Long id, String name) {
        menuCategoryRepository.updateMenuCategory(id, name);
    }

    public void deleteMenuCategory(Long id) {
        menuCategoryRepository.deleteMenuCategory(id);
    }
}
