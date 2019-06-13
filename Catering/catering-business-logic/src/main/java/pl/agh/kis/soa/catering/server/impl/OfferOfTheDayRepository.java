package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IOfferOfTheDayRepository;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.OfferOfTheDay;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

@Remote(IOfferOfTheDayRepository.class)
@Stateless
public class OfferOfTheDayRepository implements IOfferOfTheDayRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public void addOfferOfTheDay(Long menuItemId, BigDecimal price) {
        EntityManager em = factory.createEntityManager();
        MenuItem menuItem = em.find(MenuItem.class, menuItemId);
        OfferOfTheDay offerOfTheDay = new OfferOfTheDay();
        offerOfTheDay.setMenuItem(menuItem);
        offerOfTheDay.setPrice(price);

        em.getTransaction().begin();
        em.persist(offerOfTheDay);
        em.getTransaction().commit();
    }

    public OfferOfTheDay getOfferOfTheDay(Long menuItemId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<OfferOfTheDay> query = em.createQuery("select offerOfTheDay from OfferOfTheDay offerOfTheDay where offerOfTheDay.menuItem.id = :menuItemId", OfferOfTheDay.class)
                .setParameter("menuItemId", menuItemId);

        return query.getSingleResult();
    }

    public void deleteOfferOfTheDay(Long offerOfTheDayId) {
        EntityManager em = factory.createEntityManager();
        OfferOfTheDay offerOfTheDay = em.find(OfferOfTheDay.class, offerOfTheDayId);

        em.getTransaction().begin();
        em.remove(offerOfTheDay);
        em.getTransaction().commit();
    }

    public List<OfferOfTheDay> getAllOffersOfTheDay() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<OfferOfTheDay> query = em.createQuery("select offerOfTheDay from OfferOfTheDay offerOfTheDay", OfferOfTheDay.class);

        return query.getResultList();
    }

    public boolean isMenuItemInOffersOfTheDay(Long menuItemId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<OfferOfTheDay> query = em.createQuery("select offerOfTheDay from OfferOfTheDay offerOfTheDay where offerOfTheDay.menuItem.id = :menuItemId", OfferOfTheDay.class)
                .setParameter("menuItemId", menuItemId);

        return query.getResultList().size() != 0;
    }
}
