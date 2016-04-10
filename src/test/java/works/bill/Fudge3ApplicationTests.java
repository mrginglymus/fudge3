package works.bill;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Fudge3ApplicationTests {

	private WebDriver driver;

	private WebDriverWait wait;

	private String baseUrl = "http://localhost:8080/fudge3-0.0.1-SNAPSHOT/";

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
	public void login() throws Exception {
		driver.get(baseUrl);
		assertEquals("ICE", driver.findElement(By.cssSelector(".subtitle h1")).getText());

		driver.findElement(By.cssSelector("a[href*='/things/1/']")).click();

		assertEquals("Log In", driver.findElement(By.cssSelector(".subtitle h1")).getText());

		driver.findElement(By.cssSelector("input[id*='username']")).sendKeys("user1");
		driver.findElement(By.cssSelector("input[id*='password']")).sendKeys("user1");

		driver.findElement(By.cssSelector("button")).click();

		assertTrue(driver.findElement(By.cssSelector("div.alert-info")).isDisplayed());
	}

}
