package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IOrderRepository;
import pl.agh.kis.soa.catering.server.model.*;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Remote(IOrderRepository.class)
@Stateless
public class OrderRepository implements IOrderRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public void addOrder(Long clientId, Order order) {
        EntityManager em = factory.createEntityManager();
        Client client = em.find(Client.class, clientId);
        order.setClient(client);
        order.setConfirmed(false);
        order.setShipPending(false);
        order.setShipped(false);
        client.getOrders().add(order);

        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public List<Order> getAllClientOrders(Long clientId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Order> query = em.createQuery("select order from Order order where order.client.id = :clientId", Order.class)
                .setParameter("clientId", clientId);

        return query.getResultList();
    }

    public List<Order> getClientOrdersBetweenDates(Long clientId, Date fromDate, Date toDate) {
        EntityManager em = factory.createEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
        Root<Order> order = criteria.from(Order.class);
        criteria.select(order);
        criteria.where(cb.between(order.<Date>get("date"), fromDate, toDate));
        criteria.orderBy(cb.asc(order.<Date>get("date")));
        TypedQuery<Order> query = em.createQuery(criteria);

        return query.getResultList();
    }

    public List<Order> getAllUnconfirmedOrders() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Order> query = em.createQuery("select order from Order order where order.confirmed = false", Order.class);

        return query.getResultList();
    }

    public List<Order> getAllConfirmedAndNotShipPendingOrders() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Order> query = em.createQuery("select order from Order order where order.confirmed = true and order.shipPending = false", Order.class);

        return query.getResultList();
    }

    public void confirmOrder(Long orderId) {
        EntityManager em = factory.createEntityManager();
        Order order = em.find(Order.class, orderId);

        em.getTransaction().begin();
        order.setConfirmed(true);
        em.getTransaction().commit();
    }

    public void enqueueOrderShipping(Long orderId) {
        EntityManager em = factory.createEntityManager();
        Order order = em.find(Order.class, orderId);

        em.getTransaction().begin();
        order.setShipPending(true);
        em.getTransaction().commit();
    }

    public void shipOrder(Long orderId) {
        EntityManager em = factory.createEntityManager();
        Order order = em.find(Order.class, orderId);

        em.getTransaction().begin();
        order.setShipped(true);
        em.getTransaction().commit();
    }

    public Order getOrderById(Long orderId) {
        EntityManager em = factory.createEntityManager();
        Order order = em.find(Order.class, orderId);

        return order;
    }

    @Override
    public void deleteOrder(Long id) {
        EntityManager em = factory.createEntityManager();
        Order order = em.find(Order.class, id);
        em.getTransaction().begin();
        em.remove(order);
        em.getTransaction().commit();
    }

    @Override
    public List<Order> getAllOrders() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("select order from Order order", Order.class);
        return query.getResultList();
    }
}
