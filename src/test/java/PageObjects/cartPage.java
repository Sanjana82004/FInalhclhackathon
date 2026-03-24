package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends basePage {

	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		
	}
	
	@FindBy(xpath = "//h3[@data-test='productName']")
    private List<WebElement> allProductNames;

    
    @FindBy(xpath = "//span[@data-test='cardPriceItmPrice']")
    private List<WebElement> allProductPrices;
    
    @FindBy(xpath = "//section[@class='ck-product-card ng-star-inserted']") 
    private List<WebElement> productCards;
    
    @FindBy(xpath = "//div[contains(@class,'quantity-dropdown')]//button")
	private List<WebElement> qtyDropdownButtons;
    
    
    @FindBy(xpath = "//span[contains(text(),'Delete')]/ancestor::button")
    private List<WebElement> allDeleteButtons;

    @FindBy(xpath = "//button[contains(@class, 'remove-confirm-btn') or contains(., 'Remove')]")
    private List<WebElement> allPopupRemoveButtons;
    
 
    @FindBy(xpath = "//span[normalize-space()='Cancel']/ancestor::button")
    private List<WebElement> allPopupCancelButtons;
    
   
    @FindBy(xpath = "//div[contains(@class, 'modal') or contains(@class, 'pop-up')]")
    private List<WebElement> allPopupContainers;
    
    @FindBy(xpath = "//div[contains(@class, 'cart-you-pay')]//span[@class='heading-lg']")
    private WebElement totalPrice;

    
    public int getCartItemCount() {
        return productCards.size();
    }

    
    
    
   /* public String getProductPriceByIndex(int index) {
        return allProductPrices.get(index).getText().trim();
    } */
    public String getProductName(int index) {
        return allProductNames.get(index).getText().trim();
    }
    
    public String getProductPrice(int index) {
       
        return allProductPrices.get(index).getText().trim();
    }
	
	
    
    public String getTotalPriceText() {
        return totalPrice.getText().trim();
    }
	
	

	
    public void selectQuantity(int productIndex, String qtyValue) {

        
        qtyDropdownButtons.get(productIndex).click();

       
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

       
        String optionXpath = "//div[contains(@class, 'quantity-dropdown')]//li[normalize-space()='" + qtyValue + "']";


        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        option.click();
    }
	public void clickRemoveProduct() {
	    allPopupRemoveButtons.get(0).click();
	}
	
	public void confirmRemoval() {
	    allPopupRemoveButtons.get(0).click();
	}

}
