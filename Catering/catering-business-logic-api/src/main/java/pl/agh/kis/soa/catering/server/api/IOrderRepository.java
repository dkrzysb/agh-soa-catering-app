package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface IOrderRepository {
    void addOrder(Long clientId, List<MenuItem> menuItems, Date date, BigDecimal price);
    List<Order> getAllClientOrders(Long clientId);
    List<Order> getClientOrdersBetweenDates(Long clientId, Date fromDate, Date toDate);
}
