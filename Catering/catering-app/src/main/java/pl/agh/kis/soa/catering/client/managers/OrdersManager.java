package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.client.services.OrderService;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    public BigDecimal getTotalPriceOfAllClientOrders() {
        Long clientId = 1l;
        BigDecimal totalPrice = new BigDecimal(0);
        List<Order> allClientOrders = orderService.getAllClientOrders(clientId);

        for(Order order : allClientOrders)
            for(MenuItem menuItem : order.getMenuItems())
                totalPrice = totalPrice.add(menuItem.getPrice());

        return totalPrice;
    }

    public List<Order> getAllClientOrders() {
        Long clientId = 1l;

        return orderService.getAllClientOrders(clientId);
    }

    public int getLastMonthNumberOfMeals() {
        Long clientId = 1l;
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, -30);

        return orderService.getClientOrdersBetweenDates(clientId, calendar.getTime(), now).size();
    }

    public BigDecimal getLastMonthClientOrdersTotalPrice() {
        Long clientId = 1l;
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, -30);

        List<Order> clientOrders = orderService.getClientOrdersBetweenDates(clientId, calendar.getTime(), now);
        BigDecimal totalPrice = new BigDecimal(0);

        for(Order order :  clientOrders)
            totalPrice = totalPrice.add(order.getPrice());

        return totalPrice;
    }
    public int getSubscriptionDays() { return subscriptionDays; }

    public void setSubscriptionDays(int subscriptionDays) { this.subscriptionDays = subscriptionDays; }

    public void setMenuItemService(MenuItemService menuItemService) { this.menuItemService = menuItemService; }

    public void setOrderService(OrderService orderService) { this.orderService = orderService; }

    public String subscriptionDetails() {

        return "subscription-details";
    }

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
