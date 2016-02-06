package works.bill.web;

import com.sun.faces.application.view.MultiViewHandler;

import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by wac26 on 03/02/2016.
 */
public class FudgeViewHandler extends MultiViewHandler {

    @Override
    public String getActionURL(FacesContext context, String viewId) {
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        String requestViewID = request.getRequestURI().substring(
                request.getContextPath().length());

        if (requestViewID.equals(viewId)) {
            String action = (String) request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI);
            if (action==null) {
                action = request.getRequestURI();
            }
            return action;
        } else {
            return super.getActionURL(context, viewId);
        }
    }
}