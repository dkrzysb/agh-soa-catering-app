package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IMenuCategoryRepository;
import pl.agh.kis.soa.catering.server.model.MenuCategory;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
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

    public boolean addMenuCategory(String name) {
        return menuCategoryRepository.addMenuCategory(name);
    }

    public boolean updateMenuCategory(Long id, String name) {
        return menuCategoryRepository.updateMenuCategory(id, name);
    }

    public void deleteMenuCategory(Long id) {
        menuCategoryRepository.deleteMenuCategory(id);
    }
}
