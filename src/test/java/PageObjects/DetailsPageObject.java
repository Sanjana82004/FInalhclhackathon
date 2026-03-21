package PageObjects;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailsPageObject  extends basePage{

    WebDriver driver;

    // Constructor
    public DetailsPageObject(WebDriver driver) {
        super(driver);
        
    }
    
   //Brand Name 
    @FindBy(xpath = "//span[@class='cursor-pointer color-orange ng-star-inserted']")
    WebElement brandNameTop;

   //Name of Product
    @FindBy(xpath = "//h1[@class='color-tertiary text-lg font-medium']")
    WebElement brandNameHeader;

    // Price
    @FindBy(xpath = "//span[@class='text-xxl font-bold ng-tns-c182-1']")
    WebElement price;

    // Discount Tag
    @FindBy(xpath = "//span[@class='text-md color-green font-bold ng-tns-c182-1']")
    WebElement discount;

    //original mrp
    @FindBy(xpath = "(//span[@class='text-lg ng-tns-c182-1']")
    WebElement originalMRP;

<<<<<<< HEAD
<<<<<<< HEAD
    // Pincode Input
    @FindBy(xpath = "//input[@placeholder='Enter Pincode']")
    WebElement pincodeInput;
=======

>>>>>>> 507b00aa7c67991a2c5b6219bdcfd102eba66049
=======
    // Pincode Input
    @FindBy(xpath = "//input[@placeholder='Enter Pincode']")
    WebElement pincodeInput;
>>>>>>> 0a19e5ea39870568caf302bad3bf186625cfc1dc

    // Locate Button
    @FindBy(xpath = "//span[@class='pincode-locate-txt text-md color-orange font-bold']")
    WebElement locateBtn;

    // Add to Cart Button
    @FindBy(xpath = "//button[@id='Button']")
    WebElement addToCartBtn;

    // Go to Cart Button 
    @FindBy(xpath = "//button[@id='Button']")
    WebElement goToCartBtn;

    // Product Images
    @FindBy(xpath = "//img[@class='ng-tns-c187-0 ng-star-inserted']")
    List<WebElement> productImages;

    // Next Image Button
    @FindBy(xpath = "//img[@title='vip-gallery-next-icon']")
    WebElement nextBtn;

    // Previous Image Button
    @FindBy(xpath = "//img[@title='vip-gallery-prev-icon']")
    WebElement prevBtn;
    
    @FindBy(xpath = "//div[@class='pf-col xs-12 vip-delivery-error-msg text-md font-medium ng-star-inserted']")
    WebElement invalidPincodeMessage;
    
    @FindBy(xpath = "//span[@class='vip-service-delivery-text text-sm color-primary font-medium']")
    WebElement validPincodeMessage;

    
    // Get Brand Name
    public String getBrandNameTop() {
        return brandNameTop.getText();
    }



    // Get Price
    public String getPrice() {
        return price.getText();
    }

    // Get Discount
    public String getDiscount() {
        return discount.getText();
    }

    // Get Original MRP
    public String getOriginalMRP() {
        return originalMRP.getText();
    }

    // Enter Pincode
    public void enterPincode(String pin) {
        pincodeInput.clear();
        pincodeInput.sendKeys(pin);
    }

    // Click Locate
    public void clickLocate() {
        locateBtn.click();
    }

    // Add to Cart
    public void clickAddToCart() {
        addToCartBtn.click();
    }

    // Go to Cart
    public void clickGoToCart() {
        goToCartBtn.click();
    }

    // Get number of images
    public int getImageCount() {
        return productImages.size();
    }

    // Click Next Image
    public void clickNextImage() {
        nextBtn.click();
    }

    // Click Previous Image
    public void clickPrevImage() {
        prevBtn.click();
    }
    
    public int convertPriceToInt(String priceText) {
        return Integer.parseInt(priceText.replaceAll("[^0-9]", ""));
    }
    
    public int getPriceValue() {
        return convertPriceToInt(price.getText());
    }

    public int getMRPValue() {
        return convertPriceToInt(originalMRP.getText());
    }
    
    public int getDiscountValue() {
     return convertPriceToInt(discount.getText());
    }
    
    
    //validation methods
    public boolean discountCalculation() {
     
     
         int priceVal=getPriceValue();
         
        
         int mrpVal = getMRPValue();

         // Extract and clean discount %
         int actualDiscount = getDiscountValue();

         
         int expectedDiscount = ((mrpVal - priceVal) * 100) / mrpVal;
		return false;
    }

