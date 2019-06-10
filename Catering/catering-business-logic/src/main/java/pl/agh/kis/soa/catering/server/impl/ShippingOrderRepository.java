package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IShippingOrderRepository;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.ShippingOrder;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Remote(IShippingOrderRepository.class)
@Stateless
public class ShippingOrderRepository implements IShippingOrderRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public void addShippingOrder(Long orderId) {
        EntityManager em = factory.createEntityManager();
        Order order = em.find(Order.class, orderId);
        ShippingOrder shippingOrder = new ShippingOrder();
        shippingOrder.setOrder(order);

        em.getTransaction().begin();
        em.persist(shippingOrder);
        em.getTransaction().commit();
    }

    public void deleteShippingOrder(Long orderId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<ShippingOrder> query = em.createQuery("select shippingOrder from ShippingOrder shippingOrder where shippingOrder.order.id = :orderId", ShippingOrder.class)
                .setParameter("orderId", orderId);
        ShippingOrder shippingOrder = query.getSingleResult();

        em.getTransaction().begin();
        em.remove(shippingOrder);
        em.getTransaction().commit();
    }

    public List<Order> getAllOrdersToShip() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<ShippingOrder> query = em.createQuery("select shippingOrder from ShippingOrder shippingOrder", ShippingOrder.class);
        List<ShippingOrder> shippingOrders = query.getResultList();
        List<Order> orders = new ArrayList<>();

        for(ShippingOrder shippingOrder : shippingOrders)
            orders.add(shippingOrder.getOrder());

        return orders;
    }
}
