package Utilities;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class windowHandleUtility {

    public WebDriver driver;
    public String parentWindow;

    // Constructor
    public windowHandleUtility(WebDriver driver) {
        this.driver = driver;
        this.parentWindow = driver.getWindowHandle(); // store parent window
    }

    // Switch to new window
    public void switchToNewWindow() {
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
    }

    // Switch back to parent window
    public void switchToParentWindow() {
        driver.switchTo().window(parentWindow);
    }
}
