package works.bill.web.beans;

import org.ocpsoft.rewrite.servlet.impl.HttpRewriteWrappedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Map;

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
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(request.getRequestURI()).queryParam("faces-redirect", true);
            for (Map.Entry<String, String[]> param : request.getParameterMap().entrySet()) {
                uriComponentsBuilder.queryParam(param.getKey(), param.getValue().length > 1 ? param.getValue()[0] : null);
            }
            String desired = uriComponentsBuilder.build().toString();
            sessionBean.setDesired(desired);
            return "login.xhtml";
        }
    }

}
