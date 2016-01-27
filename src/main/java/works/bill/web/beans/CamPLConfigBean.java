package works.bill.web.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Bill on 27/01/2016.
 */
@Component
@ConfigurationProperties(prefix = "campl")
@Scope("singleton")
public class CamPLConfigBean {

    private Boolean useFullGlobalHeader;

    private Boolean useFullGlobalFooter;

    public Boolean getUseFullGlobalHeader() {
        return useFullGlobalHeader;
    }

    public void setUseFullGlobalHeader(Boolean useFullGlobalHeader) {
        this.useFullGlobalHeader = useFullGlobalHeader;
    }

    public Boolean getUseFullGlobalFooter() {
        return useFullGlobalFooter;
    }

    public void setUseFullGlobalFooter(Boolean useFullGlobalFooter) {
        this.useFullGlobalFooter = useFullGlobalFooter;
    }

}
