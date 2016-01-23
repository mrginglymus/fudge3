package works.bill;

import javax.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Fudge3Application {

	public static void main(String[] args) {
		SpringApplication.run(Fudge3Application.class, args);
	}
        
        @Bean
        public ServletRegistrationBean servletRegistrationBean() {
            FacesServlet facesServlet = new FacesServlet();
            return new ServletRegistrationBean(facesServlet, "*.xhtml");
            
        }
}
