package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IOrderRepository;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "orderService")
@ApplicationScoped
public class OrderService {
    @EJB(lookup = "java:global/catering-business-logic/OrderRepository")
    IOrderRepository orderRepository;

    public void addOrder(Long clientId, List<MenuItem> menuItems, Date date, BigDecimal price) {
        orderRepository.addOrder(clientId, menuItems, date, price);
    }

    public List<Order> getAllClientOrders(Long clientId) {
        return orderRepository.getAllClientOrders(clientId);
    }

    public List<Order> getClientOrdersBetweenDates(Long clientId, Date fromDate, Date toDate) {
        return orderRepository.getClientOrdersBetweenDates(clientId, fromDate, toDate);
    }

    public List<Order> getAllUnconfirmedOrders() {
        return orderRepository.getAllUnconfirmedOrders();
    }

    public List<Order> getAllConfirmedAndNotShipPendingOrders() { return orderRepository.getAllConfirmedAndNotShipPendingOrders(); }

    public void confirmOrder(Long orderId) {
        orderRepository.confirmOrder(orderId);
    }

    public void enqueueOrderShipping(Long orderId) { orderRepository.enqueueOrderShipping(orderId); }

    public Order getOrderById(Long orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public void deleteOrder(Long id) { orderRepository.deleteOrder(id);}
}
