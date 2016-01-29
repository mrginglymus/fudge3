/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.Thing;
import works.bill.service.ThingManager;

/**
 *
 * @author bill
 */
@Component
@Scope("view")
public class ThingBean extends ProtectedBean {

    @Autowired
    private ThingManager thingManager;

    public String loadThing() {
        Thing thing = thingManager.findById(thingID);
        if (thing==null || !thing.getOwner().equals(sessionBean.getCurrentUser())) {
            return handlePermissionDenied();
        } else {
            this.thing = thing;
            return null;
        }
    }

    private Long thingID;

    private Thing thing;

    public Thing getThing() {
        return thing;
    }

    public Long getThingID() {
        return thingID;
    }

    public void setThingID(Long thingID) {
        this.thingID = thingID;
    }

    public void incrementThing() {
        this.thing = thingManager.incrementThing(thingID);
    }
}
