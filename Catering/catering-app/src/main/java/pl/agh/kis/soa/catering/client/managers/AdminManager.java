package pl.agh.kis.soa.catering.client.managers;

import org.apache.commons.codec.binary.Base64;
import pl.agh.kis.soa.catering.client.services.ClientService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.UserAccount;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.xml.registry.infomodel.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.jboss.ejb3.annotation.SecurityDomain;

@RolesAllowed("Admin")
@SecurityDomain("postgresqldomain")
@ManagedBean(name = "AdminManager")
@SessionScoped
public class AdminManager {
    @ManagedProperty(value="#{clientService}")
    private ClientService clientService;
    public void setClientService(ClientService clientService){
        this.clientService = clientService;
    }
    public ClientService getClientService(){
        return this.clientService;
    }

    private UserAccount userAccount;
    private String password;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAccount> getAllUserAccounts() {
        return clientService.getAllUsers();
    }

    private boolean showPopup = false;


    public boolean isShowPopup(){
        return showPopup;
    }
    public void setShowPopup(boolean showPopup){
        this.showPopup = showPopup;
    }

    public String deleteUser(UserAccount userAccount){
        clientService.removeUser(userAccount);

        return "admin-panel";
    }

    public void showUpdatePassword(UserAccount userAccount) {
        this.userAccount = userAccount;
        this.showPopup = true;
    }
    public void hideUpdatePassword(){
        this.password = "";
        this.showPopup = false;
    }

    public void changePassword() {
        clientService.changeUserPassword(userAccount.getUsername(), hashPassword(this.password));
        this.password = "";
        this.showPopup = false;
    }

    private String hashPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordBytes = password.getBytes();
        byte[] hash = md.digest(passwordBytes);
        return Base64.encodeBase64String(hash);
    }
}
