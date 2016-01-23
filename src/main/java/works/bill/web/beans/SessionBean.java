package works.bill.web.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.Thing;
import works.bill.entities.User;

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

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean userCanAccessThing(Thing thing) {
        return thing.getOwner().equals(currentUser);
    }

}
