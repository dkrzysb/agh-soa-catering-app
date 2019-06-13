package pl.agh.kis.soa.catering.client.managers;


import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "ToplistManager")
@SessionScoped
public class ToplistManager {
    @ManagedProperty(value="#{menuItemService}")
    private MenuItemService menuItemService;
    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }

    public List<MenuItem> getTopMeals() {
        return menuItemService.topMeals();
    }
}
