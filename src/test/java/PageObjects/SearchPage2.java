package PageObjects;

<<<<<<< HEAD
package PageObjects;

=======
>>>>>>> sanjana
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage2 extends basePage {

<<<<<<< HEAD
    // Constructor
    public SearchPage2(WebDriver driver) {
        super(driver);
    }

    // ================== Product Locators ==================

    @FindBy(xpath = "//div[@class='image-cls-container ng-star-inserted']")
=======
	public SearchPage2(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath = "//div[@class='image-cls-container ng-star-inserted']")
>>>>>>> sanjana
    List<WebElement> productCards;

    @FindBy(xpath = "//h2[@class='product-name color-tertiary text-md font-medium ng-star-inserted']")
    List<WebElement> productNames;

    @FindBy(xpath = "//span[@class='product-offer-price font-bold text-xl ng-star-inserted']")
    List<WebElement> productPrices;
<<<<<<< HEAD

    // ================== Actions ==================

    public void clickProductByIndex(int index) {
        if (index < 0 || index >= productCards.size()) {
            throw new IllegalArgumentException("Invalid product index: " + index);
        }
=======
    
    public void clickProductByIndex(int index) {
        
>>>>>>> sanjana
        wait.until(ExpectedConditions.elementToBeClickable(productCards.get(index))).click();
    }

    public String getProductNameByIndex(int index) {
<<<<<<< HEAD
        if (index < 0 || index >= productNames.size()) {
            throw new IllegalArgumentException("Invalid product index: " + index);
        }
=======
        
>>>>>>> sanjana
        return wait.until(ExpectedConditions.visibilityOf(productNames.get(index))).getText();
    }

    public String getProductPriceByIndex(int index) {
        if (index < 0 || index >= productPrices.size()) {
            throw new IllegalArgumentException("Invalid product index: " + index);
        }
        return wait.until(ExpectedConditions.visibilityOf(productPrices.get(index))).getText();
    }

    // ================== Utility ==================

    public int getTotalProducts() {
        return productCards.size();
    }
<<<<<<< HEAD
}
=======
	

}
>>>>>>> sanjana
