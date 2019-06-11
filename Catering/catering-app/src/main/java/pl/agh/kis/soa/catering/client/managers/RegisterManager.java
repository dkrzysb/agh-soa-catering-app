package pl.agh.kis.soa.catering.client.managers;

import org.apache.commons.codec.binary.Base64;
import pl.agh.kis.soa.catering.client.services.ClientService;
import pl.agh.kis.soa.catering.client.services.OrderService;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.MenuItem;
import pl.agh.kis.soa.catering.server.model.UserAccount;
import pl.agh.kis.soa.catering.server.model.UserRole;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.xml.registry.infomodel.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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

        String password = userAccountL.getPassword();
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordBytes = password.getBytes();
        byte[] hash = md.digest(passwordBytes);
        String base64String = Base64.encodeBase64String(hash);
//        System.out.println("skrót hasła: " + base64String);

        userAccountL.setPassword(base64String);
        UserRole userRole = clientService.getUserRole("Client");
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
