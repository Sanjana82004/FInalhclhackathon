 package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends basePage {

	    WebDriverWait wait;

	    public SearchPage(WebDriver driver) {
	        super(driver);
	        PageFactory.initElements(driver, this);
	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    }

	    // ================== Filter locators ==================
	    @FindBy(xpath = "//span[contains(text(),'Color Swatch')]")
	    WebElement colorsDropdown;

	    @FindBy(xpath = "//accordion-group[2]//div[1]//div[1]//h4[1]")
	    WebElement brand;

	    @FindBy(xpath = "//accordion-group[3]//div[1]//div[1]//h4[1]")
	    WebElement price;

	    @FindBy(xpath = "//accordion-group[4]//div[1]//div[1]//h4[1]")
	    WebElement promotions;

	    @FindBy(xpath = "//accordion-group[5]//div[1]//div[1]//h4[1]")
	    WebElement material;

	    @FindBy(xpath = "//accordion-group[6]//div[1]//div[1]//h4[1]")
	    WebElement collections;

	    @FindBy(xpath = "//accordion-group[7]//div[1]//div[1]//h4[1]")
	    WebElement dimensions;

	    @FindBy(xpath = "//div[@class='panel panel-default dropup']//h4[@class='panel-title']")
	    WebElement discount;

	    @FindBy(xpath = "//span[normalize-space()='APPLY']")
	    WebElement applybtn;

	    @FindBy(xpath = "//span[normalize-space()='CLEAR ALL']")
	    WebElement clearallbtn;

	    // ================== Product locators ==================
	    @FindBy(xpath = "//div[@class='image-cls-container ng-star-inserted']")
	   List <WebElement> firstProduct;

	    @FindBy(xpath = "//h2[@class='product-name color-tertiary text-md font-medium ng-star-inserted']")
	    List <WebElement> firstProductName;

	    @FindBy(xpath = "//div[@class='product-brand text-xs color-secondary font-medium ng-star-inserted']")
	    WebElement firstProductBrandname;

	    

	    // ================== Filter Methods ==================
	    public void clickBrand() {
	        wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
	    }

	    public void clickPrice() {
	        wait.until(ExpectedConditions.elementToBeClickable(price)).click();
	    }

	    public void clickMaterial() {
	        wait.until(ExpectedConditions.elementToBeClickable(material)).click();
	    }

	    public void clickCollections() {
	        wait.until(ExpectedConditions.elementToBeClickable(collections)).click();
	    }

	    public void clickDimensions() {
	        wait.until(ExpectedConditions.elementToBeClickable(dimensions)).click();
	    }

	    public void clickDiscount() {
	        wait.until(ExpectedConditions.elementToBeClickable(discount)).click();
	    }

	    public void clickApplyButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(applybtn)).click();
	    }

	    public void clickClearButton() {
	        wait.until(ExpectedConditions.elementToBeClickable(clearallbtn)).click();
	    }

	    // ================== Filter Selection ==================
	    public void selectBrand(String brandName) {
	        clickBrand();
	        WebElement option = driver.findElement(By.xpath("//label[contains(text(),'" + brandName + "')]"));
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }

	    public void selectPrice(String priceRange) {
	        clickPrice();
	        WebElement option = driver.findElement(By.xpath("//label[contains(text(),'" + priceRange + "')]"));
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }

	    public void selectMaterial(String materialName) {
	        clickMaterial();
	        WebElement option = driver.findElement(By.xpath("//label[contains(text(),'" + materialName + "')]"));
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }

	    public void selectCollection(String collectionName) {
	        clickCollections();
	        WebElement option = driver.findElement(By.xpath("//label[contains(text(),'" + collectionName + "')]"));
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }

	    public void selectColor(String colorName) {
	        clickBrand(); // Expand first if needed
	        WebElement option = driver.findElement(By.xpath("//span[contains(text(),'" + colorName + "')]"));
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }

	    public void selectDiscount(String discountValue) {
	        clickDiscount();
	        WebElement option = driver.findElement(By.xpath("//label[contains(text(),'" + discountValue + "')]"));
	        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
	    }
	 
	    public void selectPromotion(String promotionName) {
	        // Pehle Promotions section ko click karke expand karein
	        wait.until(ExpectedConditions.elementToBeClickable(promotions)).click();
	        
	        // Dinamic locator for specific promotion checkbox label
	        String promoXpath = "//label[contains(text(),'" + promotionName + "')]";
	        WebElement promoOption = driver.findElement(By.xpath(promoXpath));
	        
	        wait.until(ExpectedConditions.elementToBeClickable(promoOption)).click();
	    }

	    
	    public void setPriceRange(String min, String max) {
	        // Price section expand karein
	        clickPrice();
	        
	        // Min aur Max input fields ke locators (Standard Pepperfry structure ke hisab se)
	        WebElement minPriceInput = driver.findElement(By.xpath("//input[@aria-label='Minimum Price'] | //input[@placeholder='Min']"));
	        WebElement maxPriceInput = driver.findElement(By.xpath("//input[@aria-label='Maximum Price'] | //input[@placeholder='Max']"));
	        
	        // Purani value clear karke nayi value daalein
	        minPriceInput.clear();
	        minPriceInput.sendKeys(min);
	        
	        maxPriceInput.clear();
	        maxPriceInput.sendKeys(max);
	    }

	    public void setDimensions(String width, String height, String depth) {
	        // Dimensions section expand karein
	        clickDimensions();
	        
	        // Locators based on your screenshot's structure
	        WebElement widthInput = driver.findElement(By.xpath("//div[text()='Width']/following-sibling::div//input"));
	        WebElement heightInput = driver.findElement(By.xpath("//div[text()='Height']/following-sibling::div//input"));
	        WebElement depthInput = driver.findElement(By.xpath("//div[text()='Depth']/following-sibling::div//input"));

	        // Width set karein
	        widthInput.clear();
	        widthInput.sendKeys(width);
	        
	        // Height set karein
	        heightInput.clear();
	        heightInput.sendKeys(height);
	        
	        // Depth set karein
	        depthInput.clear();
	        depthInput.sendKeys(depth);
	    }

	    // ================== Product Methods ==================
	    public void clickProductByIndex(int index) {
	        if (index < 0 || index >= firstProduct.size()) {
	            System.out.println("Invalid index: " + index + ". List size: " + firstProduct.size());
	            return;
	        }
	        firstProduct.get(index).click();
	        System.out.println("Clicked product at index: " + index);
	    }
	    
	    public String getProductNameByIndex(int index) {
	        if (index < 0 || index >= firstProduct.size()) {
	            System.out.println("Invalid index: " + index + ". List size: " + firstProduct.size());
	            return "";
	        }
	        String name = firstProduct.get(index).getText();
	        System.out.println("Product name at index " + index + ": " + name);
	        return name;
	    }

	    public String getFirstProductDetail() {
	        return wait.until(ExpectedConditions.visibilityOf(firstProductName)).getText();
	    }

	    public String getFirstProductBrandName() {
	        return wait.until(ExpectedConditions.visibilityOf(firstProductBrandname)).getText();
	    }

//	    public String getFirstProductPrice() {
//	        return wait.until(ExpectedConditions.visibilityOf(firstProductprice)).getText();
//	    }

	    // ================== Wait for Page Load ==================
	    public void waitForPageLoad() {
	        // Wait until document readyState is complete
	        wait.until(driver -> ((JavascriptExecutor) driver)
	                .executeScript("return document.readyState").equals("complete"));

	        // Wait until first product is visible
	        By firstProductLocator = By.xpath("//div[@class='image-cls-container ng-star-inserted']");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductLocator));
	    }
	    public String getProductPriceByIndex(int index) {
	        if (index < 0 || index >= firstProduct.size()) {
	            System.out.println("Invalid index: " + index + ". List size: " + firstProduct.size());
	            return "";
	        }
	        // Assuming each product card has a child element for price
	        String price = firstProduct.get(index).findElement(By.cssSelector(".product-price")).getText();
	        System.out.println("Product price at index " + index + ": " + price);
	        return price;
	    }
	    
	    @FindBy(xpath = "//span[@class='product-offer-price font-bold text-xl ng-star-inserted']")
	    List<WebElement> firstProductprice;
	    public List<WebElement> getAllProductPrices() {
	        return firstProductprice;
	    }


