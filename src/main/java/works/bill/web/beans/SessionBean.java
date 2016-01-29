package works.bill.web.beans;

import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.User;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Bill on 23/01/2016.
 */
@Component
@Scope("session")
public class SessionBean {

    private User currentUser;

    private String desired = "/";

    public User getCurrentUser() {
        return currentUser;
    }

    public void initiateSession(User user) {
        this.currentUser = user;
    }

    public String endSession() {
        this.currentUser = null;
        this.desired = "/";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged Out", "You have successfully been logged out."));
        return "/index.xhtml";
    }

    public Boolean isLoggedIn() {
        return (currentUser != null);
    }

    public String getDesired() {
        return desired;
    }

    public void clearDesired() {
        desired = "/";
    }

    public void setDesired() {
        HttpRewriteWrappedRequest request = (HttpRewriteWrappedRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        desired = request.getAttribute("javax.servlet.forward.request_uri").toString();
    }

}
