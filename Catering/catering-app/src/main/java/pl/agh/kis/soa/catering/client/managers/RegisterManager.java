package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.ClientService;
import pl.agh.kis.soa.catering.client.services.OrderService;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.UserAccount;
import pl.agh.kis.soa.catering.server.model.UserRole;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.xml.registry.infomodel.User;


@ManagedBean(name = "RegisterManager")
@RequestScoped
public class RegisterManager {

    @ManagedProperty(value="#{clientService}")
    private ClientService clientService;

    private Client client;
    private UserAccount userAccountL;

    public UserAccount getUserAccountL(){
        return this.userAccountL;
    }
    public void setUserAccountL(UserAccount userAccount){
        this.userAccountL = userAccount;
    }

    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }

    public String registerUser(){
        UserRole userRole = clientService.getUserRole("Manager");
        userAccountL.setUserRole(userRole);
        client.setUserAccount(userAccountL);
        clientService.addClient(client);
        return "login";
    }

    public void setClientService(ClientService clientService) { this.clientService = clientService; }

    @PostConstruct
    public void init() {
        client = new Client();
        userAccountL = new UserAccount();
    }


}
