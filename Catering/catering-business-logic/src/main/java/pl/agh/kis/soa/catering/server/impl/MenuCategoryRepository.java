package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IMenuCategoryRepository;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.MenuCategory;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

@Remote(IMenuCategoryRepository.class)
@Stateless
public class MenuCategoryRepository implements IMenuCategoryRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public MenuCategory getMenuCategoryById(Long id) {
        EntityManager em = factory.createEntityManager();

        return em.find(MenuCategory.class, id);
    }

    public List<MenuCategory> getAllMenuCategories() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("select menuCategory from MenuCategory menuCategory", MenuCategory.class);

        return query.getResultList();
    }

    public void addMenuCategory(String name) {
        EntityManager em = factory.createEntityManager();

        if(!sameMenuCategoryExists(em, name)) {
            MenuCategory menuCategory = new MenuCategory();
            menuCategory.setName(name);

            em.getTransaction().begin();
            em.persist(menuCategory);
            em.getTransaction().commit();
        }
    }

    public void updateMenuCategory(Long id, String name) {
        EntityManager em = factory.createEntityManager();
        MenuCategory menuCategory = em.find(MenuCategory.class, id);

        em.getTransaction().begin();
        menuCategory.setName(name);
        em.getTransaction().commit();
    }

    public void deleteMenuCategory(Long id) {
        EntityManager em = factory.createEntityManager();
        MenuCategory menuCategory = em.find(MenuCategory.class, id);

        if(menuCategory != null) {
            em.getTransaction().begin();
            em.remove(menuCategory);
            em.getTransaction().commit();
        }
    }

    private boolean sameMenuCategoryExists(EntityManager em, String name) {
        Query query = em.createQuery("select menuCategory from MenuCategory menuCategory where menuCategory.name = :name")
                .setParameter("name", name);

        return query.getResultList().size() != 0;
    }
}
