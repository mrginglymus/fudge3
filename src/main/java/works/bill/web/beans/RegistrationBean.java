package works.bill.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import works.bill.entities.User;
import works.bill.entities.dto.UserRegistration;
import works.bill.service.UserManager;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 * Created by bill on 26/01/2016.
 */
@Component
@Scope("request")
public class RegistrationBean {

    private UserRegistration userRegistration;

    @Autowired
    private UserManager userManager;

    @PostConstruct
    public void init() {
        userRegistration = userManager.createUserRegistration();
    }

    public UserRegistration getUserRegistration() {
        return userRegistration;
    }

    public void validateUsername(FacesContext context, UIComponent component, Object value) {
        String username = (String) value;
        if (userManager.isUsernameInUse(username)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Email address in use", "Email address in use"));
        }
    }

    public String register() {
        User user = userManager.createUser(userRegistration);
        String link = String.format("http://localhost:8080/activate/?username=%s&key=%s", user.getUsername(), user.getActivationHash());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Account Created", "Please check your emails"));
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "DEBUG", "goto <a href=\"" + link + "\">here</a>"));
        return "/index.xhtml";
    }

}
