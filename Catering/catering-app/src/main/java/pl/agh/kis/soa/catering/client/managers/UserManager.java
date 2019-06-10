package pl.agh.kis.soa.catering.client.managers;

import org.apache.commons.codec.binary.Base64;
import pl.agh.kis.soa.catering.client.services.ClientService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@ManagedBean(name = "UserManager")
@RequestScoped
public class UserManager {
    @ManagedProperty(value="#{clientService}")
    private ClientService clientService;

    public void setClientService(ClientService clientService){
        this.clientService = clientService;
    }
    public ClientService getClientService(){
        return this.clientService;
    }


    private String oldPassword;
    private String password;




    public void setOldPassword(String oldPassword){
        this.oldPassword = oldPassword;
    }

    public String getOldPassword(){
        return this.oldPassword;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }

    public String updateUserPassword(){
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        String login = principal.getName();

        String hashedOldPassword = hashPassword(this.oldPassword);
        if(clientService.checkUserPassword(login, hashedOldPassword)){
            clientService.changeUserPassword(login, hashPassword(this.password));
            this.password = "";
            this.oldPassword = "";
        }



        return "user-panel";
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
