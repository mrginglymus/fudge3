package works.bill.security;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import works.bill.entities.OwnedThing;
import works.bill.web.beans.ProtectedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bill
 */
@Aspect
@Component
public class ProtectedThingBeanAspect {
    
    @AfterReturning(
        pointcut="execution(works.bill.entities.OwnedThing+ works.bill.web.beans.ProtectedBean+.*(..))",
        returning="thing"
    )
    public void protectThing(JoinPoint joinPoint, OwnedThing thing) {
        ProtectedBean protectedBean = (ProtectedBean) joinPoint.getTarget();
        if (!thing.validOwner(protectedBean.getCurrentUser())) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "Permission Denied");
        }
    }

}
