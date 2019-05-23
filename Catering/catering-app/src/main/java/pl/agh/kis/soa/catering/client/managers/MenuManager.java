package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.MenuCategoryService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.server.api.IMenuCategoryRepository;
import pl.agh.kis.soa.catering.server.api.IMenuItemRepository;
import pl.agh.kis.soa.catering.server.model.MenuCategory;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean(name = "MenuManager")
@ApplicationScoped
public class MenuManager {
    private Long menuCategoryId;

    @ManagedProperty(value="#{menuItemService}")
    private MenuItemService menuItemService;
    @ManagedProperty(value="#{menuCategoryService}")
    private MenuCategoryService menuCategoryService;

    public Long getMenuCategoryId() { return menuCategoryId; }
    public void setMenuCategoryId(Long menuCategoryId) { this.menuCategoryId = menuCategoryId; }

    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }
    public void setMenuCategoryService(MenuCategoryService menuCategoryService) { this.menuCategoryService = menuCategoryService; }

    public List<MenuCategory> getAllMenuCategories() {
        return menuCategoryService.getAllMenuCategories();
    }
}
