package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class cartPage extends basePage
{

	public cartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath = "//section[@data-test='productCardContainer']") private WebElement productCards;
	
	@FindBy(xpath = " //h3[@data-test='productName']")
	private List<WebElement> allProductNames;
	
	@FindBy(xpath = "//span[@data-test='cardPriceItmPrice']")
    private List<WebElement> allProductPrices;
	@FindBy(xpath = "//div[contains(@class,'quantity-dropdown')]//button")
    private List<WebElement> qtyDropdownButtons;
	@FindBy(xpath = "//div[contains(@class, 'cart-you-pay')]//span[contains(text(), '₹')]")
    private WebElement totalPayablePrice;
	
	public String getProductName(int index) {
        return allProductNames.get(index).getText().trim();
    }

  
	
	public String getProductPrice(int index) {
       
        return allProductPrices.get(index).getText().trim();
    }
	
	
	

    public void updateQuantity(int productIndex, String qtyValue) {
       
        qtyDropdownButtons.get(productIndex).click();

      
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        
      
        String optionXpath = "//div[contains(@class, 'quantity-dropdown')]//li[normalize-space()='" + qtyValue + "']";
        WebElement targetOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(optionXpath)));
        
        targetOption.click();
    }
	
   
    public String getTotalPayableValue() {
        return totalPayablePrice.getText().trim();
    }

    
    public int getNumericTotalPrice() {
      
        String price = getTotalPayableValue().replaceAll("[^0-9]", "");
        return Integer.parseInt(price);
    }
	
	
	

}
