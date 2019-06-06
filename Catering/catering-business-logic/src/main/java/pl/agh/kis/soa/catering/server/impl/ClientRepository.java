package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IClientRepository;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.Subscription;
import pl.agh.kis.soa.catering.server.model.UserRole;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.xml.registry.infomodel.User;
import java.util.List;

@Remote(IClientRepository.class)
@Stateless
public class ClientRepository implements IClientRepository {
    private EntityManagerFactory factory = DbInitializer.getInstance().getEntityManagerFactory();

    public Client getClientById(Long clientId) {
        EntityManager em = factory.createEntityManager();

        return em.find(Client.class, clientId);
    }

    public List<Client> getAllClients() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Client> query = em.createQuery("select client from Client client", Client.class);

        return query.getResultList();
    }

    public List<Subscription> getAllClientSubscriptions(Long clientId) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Subscription> query = em.createQuery("select subscription from Subscription subscription where subscription.client.id = :clientId", Subscription.class)
                .setParameter("clientId", clientId);
        return query.getResultList();
    }

    public void addClient(Client client){
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    public UserRole getUserRole(String role) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<UserRole> query = em.createQuery("select userRole from UserRole userRole where userRole.role = :role", UserRole.class).setParameter("role", role);;
        try {
            UserRole userRole = query.getSingleResult();
            return userRole;

        }
        catch (NoResultException e){
            UserRole userRole = new UserRole(role);
            em.getTransaction().begin();
            em.persist(userRole);
            em.getTransaction().commit();
            return userRole;
        }
    }

}
