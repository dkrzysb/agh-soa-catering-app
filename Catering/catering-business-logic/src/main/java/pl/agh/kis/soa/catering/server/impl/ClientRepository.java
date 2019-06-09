package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IClientRepository;
import pl.agh.kis.soa.catering.server.model.*;

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

    public Client getClientByUsername(String username) {
        EntityManager em = factory.createEntityManager();
        TypedQuery<Client> query = em.createQuery("select client from Client client where client.userAccount.username = :username", Client.class)
                .setParameter("username", username);

        return query.getSingleResult();
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

    public List<UserAccount> getAllUsers() {
        EntityManager em = factory.createEntityManager();
        TypedQuery<UserAccount> query = em.createQuery("select userAccount from UserAccount userAccount", UserAccount.class);
        return query.getResultList();
    }


    public void removeUser(UserAccount userAccount) {
        EntityManager em = factory.createEntityManager();
        UserAccount userAccount1 = em.find(UserAccount.class, userAccount.getId());

        if(userAccount1 != null) {
            em.getTransaction().begin();
            em.remove(userAccount1);
            em.getTransaction().commit();
        }
    }

}
