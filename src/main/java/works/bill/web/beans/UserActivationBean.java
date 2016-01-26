package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.service.UserManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by bill on 26/01/2016.
 */
@Component
@Scope("request")
public class UserActivationBean {

    @Autowired
    private UserManager userManager;

    private String username;

    private String activationHash;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getActivationHash() {
        return activationHash;
    }

    public void setActivationHash(String activationHash) {
        this.activationHash = activationHash;
    }

    public String activate() {
        if (userManager.activateUser(username, activationHash)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Activated", "Account Activated"));
            return "/login.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Could not activate account", "Could not activate account"));
            return "/index.xhtml";
        }
    }
}
