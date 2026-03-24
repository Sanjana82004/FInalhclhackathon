package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class TC_02_CookiePopupTest extends BaseClass {

	WebDriver driver; // class-level driver

	@Test
	public void verifyCookiePopupHandling() throws InterruptedException {
		// Locators (update with actual cookie popup locators)
		By cookieBannerLocator = By.id("cookie-banner"); // example
		By acceptButtonLocator = By.xpath("//button[text()='Accept']"); // example

		// Wait for cookie banner (simple sleep; replace with WebDriverWait in
		// production)
		Thread.sleep(2000);

		WebElement banner = null;
		try {
			banner = driver.findElement(cookieBannerLocator);
		} catch (Exception e) {
			// Banner not displayed
		}

		if (banner != null && banner.isDisplayed()) {  
			driver.findElement(acceptButtonLocator).click();
			Thread.sleep(1000); // wait for popup to disappear
		}

		// Validate popup is gone
		boolean isBannerGone = driver.findElements(cookieBannerLocator).isEmpty();
		Assert.assertTrue(isBannerGone, "Cookie popup should be closed after accepting");
	}

}
