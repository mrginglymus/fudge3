package works.bill;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import works.bill.web.scope.ViewScope;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by Bill on 25/01/2016.
 */
@Configuration
public class Fudge3Initializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("primefaces.THEME", "campl");
    }

    @Bean
    public CustomScopeConfigurer customScopeConfigurer() {
        CustomScopeConfigurer customScopeConfigurer = new CustomScopeConfigurer();
        customScopeConfigurer.addScope("view", new ViewScope());
        return customScopeConfigurer;
    }
}
