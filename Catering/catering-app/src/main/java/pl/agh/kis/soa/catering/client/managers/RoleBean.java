package pl.agh.kis.soa.catering.client.managers;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "RoleBean")
@SessionScoped
public class RoleBean {

    public boolean isUserAdmin() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return origRequest.isUserInRole("Admin");
    }


    public boolean isUserManager() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return (origRequest.isUserInRole("Manager") || origRequest.isUserInRole("Admin"));
    }


    public boolean isUserStaff() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return (origRequest.isUserInRole("Staff") || origRequest.isUserInRole("Admin") || origRequest.isUserInRole("Manager"));
    }

    public boolean isUserClient() {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return (origRequest.isUserInRole("Client"));
    }

}
