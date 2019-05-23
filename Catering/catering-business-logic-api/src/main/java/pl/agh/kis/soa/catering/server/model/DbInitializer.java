package pl.agh.kis.soa.catering.server.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbInitializer {
    private static DbInitializer _instance = null;
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("Catering");

    private DbInitializer() {}

    public static DbInitializer getInstance() {
        if(_instance == null) {
            _instance = new DbInitializer();
            _instance.seed();
        }

        return _instance;
    }

    public EntityManagerFactory getEntityManagerFactory() { return factory; }

    private void seed() {
        EntityManager em = factory.createEntityManager();

        seedMenuCategoryTable(em);
    }

    private void seedMenuCategoryTable(EntityManager em) {
        MenuCategory menuCategory1 = new MenuCategory();
        menuCategory1.setName("śniadania");
        MenuCategory menuCategory2 = new MenuCategory();
        menuCategory2.setName("dania mięsne");
        MenuCategory menuCategory3 = new MenuCategory();
        menuCategory3.setName("dania jarskie");
        MenuCategory menuCategory4 = new MenuCategory();
        menuCategory4.setName("dania dnia");

        em.getTransaction().begin();
        em.persist(menuCategory1);
        em.persist(menuCategory2);
        em.persist(menuCategory3);
        em.persist(menuCategory4);
        em.getTransaction().commit();
    }
}
