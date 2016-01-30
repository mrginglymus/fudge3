package works.bill.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import works.bill.entities.Thing;
import works.bill.service.ThingManager;
import works.bill.web.beans.SessionBean;
import works.bill.web.exceptions.NotFoundException;
import works.bill.web.exceptions.NotLoggedInException;

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

    public Thing findById(Long thingId) {
        if (thingId == null) {
            throw new NotFoundException();
        }
        Thing thing = thingManager.findById(thingId);
        if (sessionBean.getCurrentUser() == null) {
            sessionBean.setDesired();
            throw new NotLoggedInException();
        } else if (thing != null && thing.getOwner().equals(sessionBean.getCurrentUser())) {
            return thing;
        } else {
            throw new NotFoundException();
        }
    }

    public List<Thing> findAll()  {
        if (sessionBean.getCurrentUser() == null) {
            sessionBean.setDesired();
            throw new NotLoggedInException();
        } else {
            return thingManager.findThingsByUser(sessionBean.getCurrentUser());
        }
    }



}
