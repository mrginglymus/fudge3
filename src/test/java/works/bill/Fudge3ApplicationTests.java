package works.bill;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import works.bill.web.beans.SessionBean;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Fudge3Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class Fudge3ApplicationTests {

	private WebDriver driver;

	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void spinUp() {
		driver = new HtmlUnitDriver();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void myTest() {
		driver.get("http://localhost:" + serverPort);
		System.out.println(driver.getTitle());
		SessionBean sessionBean = webApplicationContext.getBean(SessionBean.class);
		System.out.println(sessionBean.isLoggedIn());
	}

}
