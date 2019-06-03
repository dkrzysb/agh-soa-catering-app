package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "OrdersManager")
@RequestScoped
public class OrdersManager {
    private Map<Long, Boolean> menuItemsToOrder = new HashMap<Long, Boolean>();
    private List<MenuItem> orderedMenuItems;
    private int subscriptionDays;

    public Map<Long, Boolean> getMenuItemsToOrder() {
        return menuItemsToOrder;
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

    public String order() {

        return "order-summary";
    }

    public String subscribe() {

        return "subscription-details";
    }
}
