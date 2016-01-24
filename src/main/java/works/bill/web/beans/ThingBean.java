/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import works.bill.entities.Thing;
import works.bill.service.ThingManager;

/**
 *
 * @author bill
 */
@Component
@Scope("request")
public class ThingBean {

    @Autowired
    private ThingManager thingManager;

    @Autowired
    private SessionBean sessionBean;

    private Long thingID;

    private Thing thing;

    public Thing getThing() {
        if (thing==null) {
            setThing(thingManager.findById(thingID));
        }
        return thing;
    }

    public void setThing(Thing thing) {
        if (sessionBean.userCanAccessThing(thing)) {
            this.thing = thing;
        } else {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Forbidden");
        }
    }

    public Long getThingID() {
        return thingID;
    }

    public void setThingID(Long thingID) {
        this.thingID = thingID;
    }
}
