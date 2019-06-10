package pl.agh.kis.soa.catering.app.managers;

import pl.agh.kis.soa.catering.app.services.ShippingOrderService;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;

@ManagedBean(name = "ShippingOrderManager")
@ApplicationScoped
public class ShippingOrderManager {
    @ManagedProperty(value="#{shippingOrderService}")
    private ShippingOrderService shippingOrderService;

    public void setShippingOrderService(ShippingOrderService shippingOrderService) { this.shippingOrderService = shippingOrderService; }

    public List<Order> getAllOrdersToShip() {
        return shippingOrderService.getAllOrdersToShip();
    }

    public String shipOrder(Long orderId) {
        shippingOrderService.shipOrder(orderId);

        return "index";
    }
}
