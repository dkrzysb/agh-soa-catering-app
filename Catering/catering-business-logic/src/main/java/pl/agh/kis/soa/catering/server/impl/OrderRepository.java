package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IOrderRepository;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Order;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    public void addOrder(Long clientId, List<MenuItem> menuItems, Date date, BigDecimal price) {
        EntityManager em = factory.createEntityManager();
        Order order = new Order();
        Client client = em.find(Client.class, clientId);
        order.setClient(client);
        order.setMenuItems(menuItems);
        order.setDate(date);
        order.setPrice(price);
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
}
