/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.Thing;
import works.bill.entities.User;
import works.bill.service.ThingManager;

/**
 *
 * @author bill
 */
@Component
@Scope("request")
public class ThingBean extends ProtectedBean {
  
    @Autowired
    private ThingManager thingManager;
    
    @Value("#{request.getParameter('thingID')}")
    private Long thingID;
    
    private Thing thing;
    
    
    public Thing getThing() {
        if (thing==null) {
            thing = thingManager.findById(thingID);
        }
        return thing;
    }

}
