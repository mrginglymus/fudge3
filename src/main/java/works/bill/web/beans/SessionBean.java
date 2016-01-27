package works.bill.web.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.Thing;
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

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean userCanAccessThing(Thing thing) {
        if (currentUser == null) {
            return false;
        }
        return thing.getOwner().equals(currentUser);
    }

    public void initiateSession(User user) {
        this.currentUser = user;
    }

    public String endSession() {
        this.currentUser = null;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged Out", "You have successfully been logged out."));
        return "/index.xhtml";
    }

    public Boolean isLoggedIn() {
        return (currentUser != null);
    }

}
