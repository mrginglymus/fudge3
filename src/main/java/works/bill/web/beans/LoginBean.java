package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import works.bill.entities.User;
import works.bill.service.UserManager;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Bill on 25/01/2016.
 */
@Component
@Scope("request")
public class LoginBean {

    @Autowired
    private SessionBean sessionBean;

    @Autowired
    private UserManager userManager;

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        User user = userManager.findByUsername(username);
        if(user!=null && user.getActivated() && new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "You have been succesfully logged in."));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return sessionBean.initiateSession(user);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid Username or Password", "Your username and password were not recognised."));
            return null;
        }
    }
}
