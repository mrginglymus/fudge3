package works.bill.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.client.HttpClientErrorException;
import works.bill.entities.Thing;
import works.bill.web.beans.SessionBean;

/**
 * Created by bill on 29/01/2016.
 */
@Service
@Scope("session")
public class ThingSecurity {

    @Autowired
    private SessionBean sessionBean;

    public Thing check(Thing thing) throws HttpSessionRequiredException {
        if (sessionBean.getCurrentUser() == null) {
            sessionBean.setDesired();
            throw new HttpSessionRequiredException("Please log in");
        } else if (thing != null && thing.getOwner().equals(sessionBean.getCurrentUser())) {
            return thing;
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }



}
