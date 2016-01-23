/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import works.bill.entities.Thing;
import works.bill.web.beans.ThingBean;

/**
 *
 * @author bill
 */
@Aspect
@Component
public class ProtectedThing {
    
    @AfterReturning(
            pointcut="execution(* works.bill.web.beans.ThingBean.getThing())",
            returning="thing"
    )
    public void protectThing(JoinPoint joinPoint, Thing thing) {
        ThingBean thingBean = (ThingBean) joinPoint.getTarget();
        if (!thing.getOwner().getUsername().equals(thingBean.getUser())) {
          throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Permission Denied");
        }
    }
    
}