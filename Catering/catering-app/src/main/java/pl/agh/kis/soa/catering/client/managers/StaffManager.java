package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.OrderService;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

@ManagedBean(name = "StaffManager")
@ApplicationScoped
public class StaffManager {
    private Order order;
    @Resource(lookup = "java:/jms/queue/CateringQueue")
    private Queue messagesQueue;
    @Inject
    private JMSContext context;

    @ManagedProperty(value="#{orderService}")
    private OrderService orderService;

    public void setOrderService(OrderService orderService) { this.orderService = orderService; }

    public Order getOrder() { return order; }
    
    public String confirmOrder(Long orderId) {
        orderService.confirmOrder(orderId);

        return "staff-panel";
    }

    public String generateReceipt(Long orderId) {
        order = orderService.getOrderById(orderId);

        return "order-receipt";
    }

    public String orderShipping(Long orderId) {
        orderService.enqueueOrderShipping(orderId);
        sendMessage(orderId.toString());

        return "staff-panel";
    }

    public void sendMessage(String message) {
        try {
            context.createProducer().send(messagesQueue, message);
        }
        catch(Exception ex) {
            System.err.println("Error in sending message: " + ex.getMessage());
        }
    }
}
