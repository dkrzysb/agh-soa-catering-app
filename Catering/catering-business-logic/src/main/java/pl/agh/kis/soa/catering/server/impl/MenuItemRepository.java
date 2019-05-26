package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IMenuItemRepository;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.MenuItem;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Remote(IMenuItemRepository.class)
@Stateless
public class MenuItemRepository implements IMenuItemRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public MenuItem getMenuItemById(Long id) {
        EntityManager em = factory.createEntityManager();

        return em.find(MenuItem.class, id);
    }

    public List<MenuItem> getAllMenuItems() {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("select menuItem from MenuItem menuItem");

        return query.getResultList();
    }

    public List<MenuItem> getAllMenuCategoryItems(Long menuCategoryId) {
        EntityManager em = factory.createEntityManager();
        Query query = em.createQuery("select menuItem from MenuItem menuItem where menuItem.menuCategory.id = :menuCategoryId")
                .setParameter("menuCategoryId", menuCategoryId);

        return query.getResultList();
    }

    public void addMenuItem(String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        EntityManager em = factory.createEntityManager();

        if(!sameMenuItemExistsInMenuCategory(em, name, servingSize, menuCategoryId)) {
            MenuCategory menuCategory = em.find(MenuCategory.class, menuCategoryId);
            MenuItem menuItem = new MenuItem();
            menuItem.setName(name);
            menuItem.setServingSize(servingSize);
            menuItem.setPrice(price);
            menuItem.setMenuCategory(menuCategory);

            em.getTransaction().begin();
            em.persist(menuItem);
            em.getTransaction().commit();
        }
    }

    public void updateMenuItem(Long id, String name, int servingSize, BigDecimal price, Long menuCategoryId) {
        EntityManager em = factory.createEntityManager();

        MenuCategory newMenuCategory = em.find(MenuCategory.class, menuCategoryId);
        MenuItem menuItem = em.find(MenuItem.class, id);

        em.getTransaction().begin();
        menuItem.setName(name);
        menuItem.setServingSize(servingSize);
        menuItem.setPrice(price);
        menuItem.setMenuCategory(newMenuCategory);
        em.getTransaction().commit();
    }

    public void deleteMenuItem(Long id) {
        EntityManager em = factory.createEntityManager();
        MenuItem menuItem = em.find(MenuItem.class, id);
        MenuCategory menuCategory = em.find(MenuCategory.class, menuItem.getMenuCategory().getId());

        em.getTransaction().begin();
        em.remove(menuItem);
        em.getTransaction().commit();
    }

    private boolean sameMenuItemExistsInMenuCategory(EntityManager em, String name, int servingSize, Long menuCategoryId) {
        Query query = em.createQuery("select menuItem from MenuItem menuItem where menuItem.name = :name and menuItem.servingSize = :servingSize and menuItem.menuCategory.id = :menuCategoryId")
                .setParameter("name", name)
                .setParameter("servingSize", servingSize)
                .setParameter("menuCategoryId", menuCategoryId);
        return query.getResultList().size() != 0;
    }
}
