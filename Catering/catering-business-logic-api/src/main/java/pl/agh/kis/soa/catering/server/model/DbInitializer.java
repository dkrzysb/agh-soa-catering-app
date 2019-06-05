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
        seedClientTable(em);
    }

    private void seedMenuCategoryTable(EntityManager em) {
        MenuCategory menuCategory1 = new MenuCategory();
        menuCategory1.setName("śniadania");
        MenuCategory menuCategory2 = new MenuCategory();
        menuCategory2.setName("dania mięsne");
        MenuCategory menuCategory3 = new MenuCategory();
        menuCategory3.setName("dania jarskie");
        MenuCategory menuCategory4 = new MenuCategory();
        menuCategory4.setName("desery");
        MenuCategory menuCategory5 = new MenuCategory();
        menuCategory5.setName("dania dnia");

        em.getTransaction().begin();
        em.persist(menuCategory1);
        em.persist(menuCategory2);
        em.persist(menuCategory3);
        em.persist(menuCategory4);
        em.getTransaction().commit();
    }

    private void seedClientTable(EntityManager em) {
        Client client1 = new Client();
        client1.setName("Mariusz");
        client1.setSurname("Pudzianowski");
        client1.setUsername("mpudzianowski");
        client1.setPassword("pudzian123");
        Client client2 = new Client();
        client2.setName("Robert");
        client2.setSurname("Lewandowski");
        client2.setUsername("rlewandowski");
        client2.setPassword("lewy123");

        em.getTransaction().begin();
        em.persist(client1);
        em.persist(client2);
        em.getTransaction().commit();
    }
}
