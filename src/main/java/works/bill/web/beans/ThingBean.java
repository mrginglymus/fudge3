/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpSessionRequiredException;
import works.bill.entities.Thing;
import works.bill.service.ThingManager;
import works.bill.web.security.ThingSecurity;

/**
 *
 * @author bill
 */
@Component
@Scope("view")
public class ThingBean {

    @Autowired
    private ThingManager thingManager;

    @Autowired
    private ThingSecurity thingSecurity;

    private Long thingID;

    private Thing thing;

    public Thing getThing() throws HttpSessionRequiredException {
        if (thing == null && thingID != null) {
            thing = thingSecurity.check(thingManager.findById(thingID));
        }
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
