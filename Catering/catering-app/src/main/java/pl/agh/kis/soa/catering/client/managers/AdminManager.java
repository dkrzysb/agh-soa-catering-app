package pl.agh.kis.soa.catering.client.managers;

import pl.agh.kis.soa.catering.client.services.ClientService;
import pl.agh.kis.soa.catering.client.services.MenuItemService;
import pl.agh.kis.soa.catering.server.model.Client;
import pl.agh.kis.soa.catering.server.model.MenuCategory;
import pl.agh.kis.soa.catering.server.model.UserAccount;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.xml.registry.infomodel.User;
import java.util.List;

@ManagedBean(name = "AdminManager")
@RequestScoped
public class AdminManager {
    @ManagedProperty(value="#{clientService}")
    private ClientService clientService;

    public void setClientService(ClientService clientService){
        this.clientService = clientService;
    }

    public ClientService getClientService(){
        return this.clientService;
    }


    public List<UserAccount> getAllUserAccounts() {
        return clientService.getAllUsers();
    }

    private boolean showPopup = false;

    public void show(){
        this.showPopup = true;
    }
    public void hide(){
        this.showPopup = false;
    }
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


}
