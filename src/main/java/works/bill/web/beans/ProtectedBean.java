/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import works.bill.entities.User;
import works.bill.service.UserManager;

import javax.servlet.http.HttpSession;

/**
 *
 * @author bill
 */
public abstract class ProtectedBean  {
    
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserManager ownerUserManager;
    
    public User getCurrentUser() {
        return ownerUserManager.findByUsername(httpSession.getAttribute("user").toString());
    }
}
