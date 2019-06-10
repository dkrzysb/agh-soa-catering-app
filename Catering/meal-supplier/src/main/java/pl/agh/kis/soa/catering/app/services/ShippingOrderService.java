package pl.agh.kis.soa.catering.app.services;

import pl.agh.kis.soa.catering.server.api.IShippingOrderRepository;
import pl.agh.kis.soa.catering.server.api.IOrderRepository;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "shippingOrderService")
@ApplicationScoped
public class ShippingOrderService {
    @EJB(lookup = "java:global/catering-business-logic/ShippingOrderRepository")
    IShippingOrderRepository shippingOrderRepository;
    @EJB(lookup = "java:global/catering-business-logic/OrderRepository")
    IOrderRepository orderRepository;

    public List<Order> getAllOrdersToShip() {
        return shippingOrderRepository.getAllOrdersToShip();
    }

    public void shipOrder(Long orderId ) {
        shippingOrderRepository.deleteShippingOrder(orderId);
        orderRepository.shipOrder(orderId);
    }
}
