package works.bill;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import java.util.EnumSet;

@SpringBootApplication
public class Fudge3Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Fudge3Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Fudge3Application.class, Fudge3Initializer.class);
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet facesServlet = new FacesServlet();
        return new ServletRegistrationBean(facesServlet, "*.xhtml");
    }

    @Bean
    public FilterRegistrationBean prettyFilter() {
        FilterRegistrationBean prettyFilter = new FilterRegistrationBean(new RewriteFilter());
        prettyFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
        prettyFilter.addUrlPatterns("/*");
        return prettyFilter;
    }



}
