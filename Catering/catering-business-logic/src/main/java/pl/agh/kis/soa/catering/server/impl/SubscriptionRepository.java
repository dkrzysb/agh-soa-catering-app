package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.ISubscriptionRepository;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.Subscription;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Remote(ISubscriptionRepository.class)
@Stateless
public class SubscriptionRepository implements ISubscriptionRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public List<Subscription> getAllClientSubscriptions(Long clientId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Subscription> query = em.createQuery("select subscription from Subscription subscription where subscription.client.id = :clientId", Subscription.class)
                .setParameter("clientId", clientId);

        return query.getResultList();
    }

    public void addSubscription(List<MenuItem> menuItems, Long clientId, String daysOfTheWeek) {
        EntityManager em = factory.createEntityManager();
        Client client = em.find(Client.class, clientId);
        Subscription subscription = new Subscription();
        subscription.setMenuItems(menuItems);
        subscription.setClient(client);
        subscription.setDaysOfTheWeek(daysOfTheWeek);

        em.getTransaction().begin();
        em.persist(subscription);
        em.getTransaction().commit();
    }

    public void deleteSubscription(Long subscriptionId) {
        EntityManager em = factory.createEntityManager();
        Subscription subscription = em.find(Subscription.class, subscriptionId);

        em.getTransaction().begin();
        em.remove(subscription);
        em.getTransaction().commit();
    }
}
