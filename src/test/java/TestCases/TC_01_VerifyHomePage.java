package TestCases;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_01_VerifyHomePage extends BaseClass {
    
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        // Implicit wait (optional)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Initialize explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open initial URL
        driver.get("https://www.pepperfry.com/");
    }

    @Test
    public void verifyHomePage() {
        // Hide login/signup popup if it exists
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("let popup = document.querySelector('div.login-popup-selector');"
                           + "if(popup){ popup.style.display='none'; }");
            System.out.println("Login popup handled.");
        } catch(Exception e) {
            System.out.println("Login popup not found.");
        }

        // Open a new tab
        ((JavascriptExecutor) driver).executeScript("window.open();");

        // Switch to new tab
        String newTab = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(newTab);

        // Navigate to Pepperfry
        driver.get("https://www.pepper'"
        		+ "fry.com");

        // Wait for page to load completely
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

        // Print page title
        System.out.println("Page title: " + driver.getTitle());
    }
 
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}