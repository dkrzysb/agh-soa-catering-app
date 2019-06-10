package pl.agh.kis.soa.catering.server.api;

import pl.agh.kis.soa.catering.server.model.*;

import java.util.Date;
import java.util.List;

public interface IClientRepository {
    Client getClientById(Long clientId);
    Client getClientByUsername(String username);
    List<Client> getAllClients();
    List<Subscription> getAllClientSubscriptions(Long clientId);
    void addClient(Client client);
    UserRole getUserRole(String role);
    List<UserAccount> getAllUsers();
    void removeUser(UserAccount userAccount);
    boolean checkUserPassword(String login, String hashedOldPassword);
    void changeUserPassword(String login, String hashPassword);
}
