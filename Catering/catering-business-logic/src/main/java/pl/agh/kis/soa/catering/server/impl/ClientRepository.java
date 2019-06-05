package pl.agh.kis.soa.catering.server.impl;

import pl.agh.kis.soa.catering.server.api.IClientRepository;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.DbInitializer;
import pl.agh.kis.soa.catering.server.model.Subscription;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
}
