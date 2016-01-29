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
import works.bill.entities.User;
import works.bill.service.ThingManager;
import works.bill.service.UserManager;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 *
 * @author bill
 */
@Component
@Scope("application")
public class SpinUpBean {

    @Autowired
    private ThingManager thingManager;
    
    @Autowired
    private UserManager userManager;

    @Autowired
    private SessionBean sessionBean;
    
    @PostConstruct
    private void init() {
        User user1 = userManager.createUser("user1", "user1");
        User user2 = userManager.createUser("user2", "user2");

        thingManager.createThing(user1, "This is thing one");
        thingManager.createThing(user2, "This is thing two");
        thingManager.createThing(user1, "This is thing three");
    }

    public List<Thing> getThings() { return thingManager.findAll(); }
    
}
