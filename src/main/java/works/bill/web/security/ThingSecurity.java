package works.bill.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.client.HttpClientErrorException;
import works.bill.entities.Thing;
import works.bill.service.ThingManager;
import works.bill.web.beans.SessionBean;

import java.util.List;

/**
 * Created by bill on 29/01/2016.
 */
@Service
@Scope("session")
public class ThingSecurity {

    @Autowired
    private SessionBean sessionBean;

    @Autowired
    private ThingManager thingManager;

    public Thing findById(long thingId) throws HttpSessionRequiredException {
        Thing thing = thingManager.findById(thingId);
        if (sessionBean.getCurrentUser() == null) {
            sessionBean.setDesired();
            throw new HttpSessionRequiredException("Please log in");
        } else if (thing != null && thing.getOwner().equals(sessionBean.getCurrentUser())) {
            return thing;
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }
    }

    public List<Thing> findAll() throws HttpSessionRequiredException {
        if (sessionBean.getCurrentUser() == null) {
            sessionBean.setDesired();
            throw new HttpSessionRequiredException("Please log in");
        } else {
            return thingManager.findThingsByUser(sessionBean.getCurrentUser());
        }
    }



}
