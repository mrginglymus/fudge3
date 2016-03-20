/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.DatedThing;
import works.bill.entities.MyEnum;
import works.bill.entities.Thing;
import works.bill.service.DateThingGroupSet;
import works.bill.service.ThingManager;

import java.time.LocalDate;
import java.util.EnumSet;
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

    public DateThingGroupSet getDatedThings() {
        DateThingGroupSet dateThingGroupSet = new DateThingGroupSet();

        DatedThing thing1 = new DatedThing("Foo", LocalDate.now(), EnumSet.allOf(MyEnum.class));
        DatedThing thing5 = new DatedThing("Bar", LocalDate.now(), EnumSet.allOf(MyEnum.class));
        DatedThing thing2= new DatedThing("Woo", LocalDate.now(), EnumSet.of(MyEnum.FIRST, MyEnum.SECOND));
        DatedThing thing3= new DatedThing("Yay", LocalDate.now(), EnumSet.of(MyEnum.SECOND, MyEnum.FIRST));
        DatedThing thing4= new DatedThing("Hoopla", LocalDate.now().plusDays(3), EnumSet.allOf(MyEnum.class));

        dateThingGroupSet.add(thing1);
        dateThingGroupSet.add(thing2);
        dateThingGroupSet.add(thing3);
        dateThingGroupSet.add(thing4);
        dateThingGroupSet.add(thing5);

        return dateThingGroupSet;
    }
    
}
