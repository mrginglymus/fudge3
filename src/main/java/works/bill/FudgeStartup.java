package works.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import works.bill.entities.User;
import works.bill.service.ThingManager;
import works.bill.service.UserManager;

/**
 * Created by bill on 30/01/2016.
 */
@Component
public class FudgeStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ThingManager thingManager;

    @Autowired
    private UserManager userManager;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        User user1 = userManager.createUser("user1", "user1");
        User user2 = userManager.createUser("user2", "user2");

        thingManager.createThing(user1, "This is thing one");
        thingManager.createThing(user2, "This is thing two");
        thingManager.createThing(user1, "This is thing three");
    }
}
