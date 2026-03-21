package PageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.windowHandleUtility;

public class basePage {
	public WebDriver driver;
	 public WebDriverWait wait;
	  public windowHandleUtility windowUtil; 
	    public basePage(WebDriver driver) {
	    	this.driver= driver;
	    	
	    	PageFactory.initElements(driver, this);
	    	
	    	 wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	 windowUtil = new windowHandleUtility(driver);
	    
	    	}
}
