package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IClientRepository;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.Order;
import pl.agh.kis.soa.catering.server.model.Subscription;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "clientService", eager = true)
@RequestScoped
public class ClientService {
    @EJB(lookup = "java:global/catering-business-logic/ClientRepository")
    IClientRepository clientRepository;

    public Client getClientById(Long clientId) {
        return clientRepository.getClientById(clientId);
    }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public List<Subscription> getAllClientSubscriptions(Long clientId) {
        return clientRepository.getAllClientSubscriptions(clientId);
    }

    public void addClient(Client client){
        clientRepository.addClient(client);
    }
}
