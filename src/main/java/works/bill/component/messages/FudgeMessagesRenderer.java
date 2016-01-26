package works.bill.component.messages;

import org.primefaces.component.messages.Messages;
import org.primefaces.component.messages.MessagesRenderer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by Bill on 25/01/2016.
 */
public class FudgeMessagesRenderer extends MessagesRenderer {

    @Override
    protected void encodeSeverityMessages(FacesContext context, Messages uiMessages, String severity, List<FacesMessage> messages) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        String styleClass = "alert-info";
        String iconClass = "fa-info-circle";


        switch (severity) {
            case "info":
                styleClass = "alert-info";
                iconClass = "fa-info-circle";
                break;
            case "warn":
                styleClass = "alert-warning";
                iconClass = "fa-exclamation-triangle";
                break;
            case "error":
            case "fatal":
                styleClass = "alert-danger";
                iconClass = "fa-exclamation-circle";
        }

        boolean escape = uiMessages.isEscape();


        for (FacesMessage msg : messages) {

            writer.startElement("div", null);
            writer.writeAttribute("class", "alert " + styleClass + (uiMessages.isGlobalOnly() ? " alert-top":"") + (uiMessages.isClosable() ? " alert-dismissable fade in":""), null);
            writer.writeAttribute("role", "alert", null);


            if(uiMessages.isClosable()) {
                encodeCloseIcon(context, uiMessages);
            }


            String summary = msg.getSummary() != null ? msg.getSummary() : "Success";
            String detail = msg.getDetail() != null ? msg.getDetail() : summary;

            writer.startElement("div", null);
            writer.writeAttribute("class", "row", null);

            writer.startElement("div", null);
            writer.writeAttribute("class", "col-md-4", null);
            writer.startElement("span", null);
            writer.writeAttribute("class", "fa " + iconClass, null);
            writer.writeAttribute("style", "padding-right:5px;", null);
            writer.endElement("span");
            writer.startElement("span", null);

            if(escape) {
                writer.writeText(summary, null);
            } else {
                writer.write(summary);
            }
            writer.endElement("span");

            writer.endElement("div");

            writer.startElement("div", null);
            writer.writeAttribute("class", "col-md-8", null);

            if(escape) {
                writer.writeText(detail, null);
            } else {
                writer.write(detail);
            }
            writer.endElement("div");
            writer.endElement("div");

            msg.rendered();


            writer.endElement("div");
        }

    }

    @Override
    protected void encodeCloseIcon(FacesContext context, Messages uiMessages) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.startElement("div", null);
        writer.writeAttribute("class", "close pull-right", null);
        writer.writeAttribute("data-dismiss", "alert", null);
        writer.write("&nbsp;");
        writer.endElement("div");
    }
}
