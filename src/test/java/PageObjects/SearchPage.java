package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends basePage {
	public SearchPage(WebDriver driver) {
		super(driver);
	}

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

	// Methods to expand filters
	public void clickBrand() {
		wait.until(ExpectedConditions.elementToBeClickable(brand)).click();
	}

	public void clickPrice() {
		wait.until(ExpectedConditions.elementToBeClickable(price)).click();
	}

	public void clickPromotions() {
		wait.until(ExpectedConditions.elementToBeClickable(promotions)).click();
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

	// methods to select dropdown
	public void selectColor(String colorName) {
		Select colorSelect = new Select(colorsDropdown);
		colorSelect.selectByVisibleText(colorName);
	}

	public void selectBrand(String b) {
		Select brandSelect = new Select(brand);
		brandSelect.selectByVisibleText(b);
	}

	public void selectCollections(String collection) {
		Select collectionset = new Select(collections);
		collectionset.selectByVisibleText(collection);

	}

	public void selectMaterials(String materials) {
		Select materialset = new Select(material);
		materialset.selectByVisibleText(materials);

	}

	public void selectDiscount(String discounts) {
		Select collectionset = new Select(discount);
		collectionset.selectByVisibleText(discounts);

	}

	public void clickApplyButton() {
		applybtn.click();

	}

	public void clickClearButton() {
		clearallbtn.click();
	}

	@FindBy(xpath="//div[@class='image-cls-container ng-star-inserted']")
	WebElement firstProduct;

	@FindBy(xpath="//div[@class='product-name color-tertiary text-md font-medium ng-star-inserted']")
	WebElement firstProductName;

	@FindBy(xpath="//div[@class='product-brand text-xs color-secondary font-medium ng-star-inserted']")
	WebElement firstProductBrandname;

	@FindBy(xpath="//div[@class='product-offer-price font-bold text-xl ng-star-inserted']")
	WebElement firstProductprice;

	public void clickFirstProduct() {
	    wait.until(ExpectedConditions.elementToBeClickable(firstProduct)).click();
	}

	public String getFirstProductDetail() {
	    return wait.until(ExpectedConditions.visibilityOf(firstProductName)).getText();
	}

	public String getFirstProductBrandName() {
	    return wait.until(ExpectedConditions.visibilityOf(firstProductBrandname)).getText();
	}

	public String getFirstProductPrice() {
	    return wait.until(ExpectedConditions.visibilityOf(firstProductprice)).getText();
	}
	
	
}
