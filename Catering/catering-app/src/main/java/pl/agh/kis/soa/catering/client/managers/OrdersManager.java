package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.client.services.OrderService;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.*;

@ManagedBean(name = "OrdersManager")
@SessionScoped
public class OrdersManager {
    private Map<Long, Boolean> orderCheckboxes = new HashMap<Long, Boolean>();
    private List<MenuItem> orderedMenuItems;
    private int subscriptionDays;

    @ManagedProperty(value="#{menuItemService}")
    private MenuItemService menuItemService;
    @ManagedProperty(value="#{orderService}")
    private OrderService orderService;

    public Map<Long, Boolean> getOrderCheckboxes() {
        return orderCheckboxes;
    }

    public List<MenuItem> getOrderedMenuItems() {
        return orderedMenuItems;
    }

    public BigDecimal getOrderPrice() {
        BigDecimal orderPrice = new BigDecimal(0);

        for(MenuItem menuItem : orderedMenuItems)
            orderPrice = orderPrice.add(menuItem.getPrice());

        return orderPrice;
    }

    public int getSubscriptionDays() { return subscriptionDays; }

    public void setSubscriptionDays(int subscriptionDays) { this.subscriptionDays = subscriptionDays; }

    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }

    public void setOrderService(OrderService orderService) { this.orderService = orderService; }

    public String order() {
        orderedMenuItems = new ArrayList<MenuItem>();

        for(Long id : orderCheckboxes.keySet()) {
            boolean checked = orderCheckboxes.get(id);

            if(checked)
                orderedMenuItems.add(menuItemService.getMenuItemById(id));
        }

        return "order-summary";
    }

    public String confirm() {
        // TODO: replace this mock with client got from SessionContext
        Long clientId = 1l;

        orderService.addOrder(clientId, orderedMenuItems, new Date(), getOrderPrice());
        orderedMenuItems = new ArrayList<MenuItem>();

        return "client-panel";
    }

    public String cancel() {
        orderedMenuItems = new ArrayList<MenuItem>();

        return "client-panel";
    }

    public String subscribe() {

        return "subscription-details";
    }
}
