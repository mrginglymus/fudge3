package works.bill;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class Fudge3ApplicationTests {

	private static final String USERNAME = "bilbocollins";
	private static final String ACCESS_KEY = "6bcb2f57-3859-4b87-91a8-22582dfb2d41";
	private static final String SAUCELABS_URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";

	private WebDriver driver;

	private WebDriverWait wait;

	private String baseUrl = "http://localhost:8080/fudge3-0.0.1-SNAPSHOT/";

	@Before
	public void spinUp() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "48.0");

		driver = new RemoteWebDriver(new URL(SAUCELABS_URL), caps);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testTitle() throws Exception {
		driver.get("http://localhost:8080/");
		System.out.println("********************************");
		System.out.println(driver.getTitle());
		System.out.println("********************************");
		driver.get(baseUrl);
		System.out.println("********************************");
		System.out.println(driver.getTitle());
		System.out.println("********************************");
		assertEquals("ICE", driver.findElement(By.cssSelector(".subtitle h1")).getText());
	}

}
