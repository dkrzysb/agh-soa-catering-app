package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.Order;

import java.util.List;

public interface IShippingOrderRepository {
    void addShippingOrder(Long orderId);
    void deleteShippingOrder(Long orderId);
    List<Order> getAllOrdersToShip();
}
