/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import works.bill.entities.Thing;
import works.bill.service.ThingManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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

    public String loadThing() {
        Thing thing = thingManager.findById(thingID);
        if (thing==null || !thing.getOwner().equals(sessionBean.getCurrentUser())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Permission Denied", "Go away"));
            HttpRewriteWrappedRequest request = (HttpRewriteWrappedRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            String desired = UriComponentsBuilder.fromPath(request.getRequestURI()).queryParam("thingID", thingID).queryParam("faces-redirect", true).build().toString();
            sessionBean.setDesired(desired);
            return "login.xhtml";
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
}
