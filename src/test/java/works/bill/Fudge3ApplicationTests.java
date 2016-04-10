package works.bill;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Fudge3ApplicationTests {

	private WebDriver driver;

	private WebDriverWait wait;

	private String baseUrl = "http://web:8080/fudge3-0.0.1-SNAPSHOT/";

	@Before
	public void spinUp() {
		driver = new HtmlUnitDriver();
		wait = new WebDriverWait(driver, 60);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testTitle() throws Exception {
		driver.get(baseUrl);
		System.out.println(driver.findElement(By.cssSelector("h1")));
		assertEquals("ICE", driver.findElement(By.cssSelector(".subtitle h1")).getText());
	}

}
