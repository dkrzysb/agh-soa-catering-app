package pl.agh.kis.soa.catering.client.services;

import pl.agh.kis.soa.catering.server.api.IClientRepository;
import pl.agh.kis.soa.catering.server.model.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "clientService", eager = true)
@SessionScoped
public class ClientService {
    @EJB(lookup = "java:global/catering-business-logic/ClientRepository")
    IClientRepository clientRepository;

    public Client getClientById(Long clientId) {
        return clientRepository.getClientById(clientId);
    }

    public Client getClientByUsername(String username) { return clientRepository.getClientByUsername(username); }

    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }

    public List<Subscription> getAllClientSubscriptions(Long clientId) {
        return clientRepository.getAllClientSubscriptions(clientId);
    }

    public void addClient(Client client){
        clientRepository.addClient(client);
    }

    public UserRole getUserRole(String role) {
        return clientRepository.getUserRole(role);
    }

    public List<UserAccount> getAllUsers() {
        return clientRepository.getAllUsers();
    }

    public void removeUser(UserAccount userAccount) {
        clientRepository.removeUser(userAccount);
    }
}
