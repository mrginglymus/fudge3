package works.bill.web.beans;

import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by Bill on 25/01/2016.
 */
public abstract class ProtectedBean {

    @Autowired
    protected SessionBean sessionBean;

    protected String handlePermissionDenied() {
        if (sessionBean.isLoggedIn()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Permission Denied", "Go away"));
            return "index.xhtml";
        } else {
            HttpRewriteWrappedRequest request = (HttpRewriteWrappedRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            sessionBean.setDesired(request.getAttribute("javax.servlet.forward.request_uri").toString());
            return "login.xhtml";
        }
    }

}
