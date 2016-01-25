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

import javax.faces.context.FacesContext;
import java.io.IOException;

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
            setThing(getThingOrRedirect(thingID));
        }
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    private Thing getThingOrRedirect(Long thingID) {
        Thing gotThing = thingManager.findById(thingID);
        if (!gotThing.getOwner().equals(sessionBean.getCurrentUser())) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/login/");
            } catch (IOException e) {
            } finally {
                return null;
            }
        } else {
            return gotThing;
        }
    }

    public Long getThingID() {
        return thingID;
    }

    public void setThingID(Long thingID) {
        this.thingID = thingID;
    }
}
