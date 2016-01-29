/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import works.bill.entities.Thing;
import works.bill.entities.User;
import works.bill.repositories.ThingRepository;

/**
 *
 * @author bill
 */
@Service
public class ThingManager {
    
    @Autowired
    ThingRepository thingRepository;
    
    public List<Thing> findAll() {
        return thingRepository.findAll();
    }
    
    public Thing findById(Long id) {
        return thingRepository.findOne(id);
    }

    public List<Thing> findThingsByUser(User user) {
        return thingRepository.findAllByOwner(user);
    }
    
    public Thing createThing(User user, String secret) {
        Thing thing = new Thing();
        thing.setOwner(user);
        thing.setSecretText(secret);
        return thingRepository.save(thing);
    }

    public Thing incrementThing(long thingID) {
        Thing thing = findById(thingID);
        thing.setCount(thing.getCount() + 1);
        return thingRepository.save(thing);
    }
    
}
