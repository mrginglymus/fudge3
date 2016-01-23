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
    
    private User user1;
    private User user2;
    
    private Thing thing1;
    private Thing thing2;
    
    @PostConstruct
    private void init() {
        user1 = userManager.createUser("user1", "user1");
        user2 = userManager.createUser("user2", "user2");

        thing1 = thingManager.createThing(user1, "This is thing one");
        thing2 = thingManager.createThing(user2, "This is thing two");

        user1 = userManager.findByUsername("user1");
        user2 = userManager.findByUsername("user2");

        sessionBean.setCurrentUser(user1);
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public Thing getThing1() {
        return thing1;
    }

    public void setThing1(Thing thing1) {
        this.thing1 = thing1;
    }

    public Thing getThing2() {
        return thing2;
    }

    public void setThing2(Thing thing2) {
        this.thing2 = thing2;
    }
    
    public List<Thing> getThings() {
        return thingManager.findAll();
    }
    
}
