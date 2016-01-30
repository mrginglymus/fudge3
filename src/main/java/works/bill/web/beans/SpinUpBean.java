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

    public List<Thing> getThings() { return thingManager.findAll(); }
    
}
