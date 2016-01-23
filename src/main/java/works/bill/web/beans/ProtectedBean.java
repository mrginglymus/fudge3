/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package works.bill.web.beans;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author bill
 */
public abstract class ProtectedBean  {
    
    @Autowired
    private HttpSession httpSession;
    
    public String getUsername() {
        return httpSession.getAttribute("user").toString();
    }
    
    //public abstract User loadSecurely();
    
}
