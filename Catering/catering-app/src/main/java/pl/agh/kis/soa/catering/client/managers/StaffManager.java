package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.OrderService;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

@ManagedBean(name = "StaffManager")
@ApplicationScoped
public class StaffManager {
    private Order order;

    @ManagedProperty(value="#{orderService}")
    private OrderService orderService;

    public Order getOrder() { return order; }
    
    public String confirmOrder(Long orderId) {
        orderService.confirmOrder(orderId);

        return "staff-panel";
    }

    public String generateReceipt(Long orderId) {
        order = orderService.getOrderById(orderId);

        return "order-receipt";
    }

    public void setOrderService(OrderService orderService) { this.orderService = orderService; }
}
