package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends basePage {

    Actions act;
    JavascriptExecutor js;

    public HomePage(WebDriver driver) {
        super(driver);
        act = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    // ---------------- Locators ----------------
    @FindBy(id = "search")
    WebElement searchBar;

    @FindBy(xpath = "//img[@alt='cart icon']")
    WebElement cart;

    @FindBy(xpath = "//img[@alt='wishlist icon']")
    WebElement wishlist;

    @FindBy(xpath = "//img[@alt='studio icon']")
    WebElement store;

    @FindBy(xpath = "//img[@alt='profile icon']")
    WebElement signup;

    @FindBy(xpath = "//img[@alt='PepperfryLogo']")
    WebElement logo;

    @FindBy(xpath = "//a[contains(text(),'Luxury')]")
    WebElement luxury;

    @FindBy(xpath = "//a[normalize-space()='Bedroom']")
    WebElement bedroom;

    @FindBy(xpath = "//a[normalize-space()='Wardrobes']")
    WebElement wardrobes;

    // Cookie popup button
    By cookieCloseButton = By.xpath("//button[contains(text(),'Accept') or contains(text(),'Close')]");

    // First product in search results
    By firstProductLocator = By.xpath("(//a[contains(@href,'product')])[1]");

    // Add to Cart button on product page
    By addToCartLocator = By.xpath("//button[contains(text(),'ADD TO CART') or contains(text(),'Add to Cart')]");

    // ---------------- Actions ----------------
    public void handleCookiePopup() {
        try {
            WebElement cookieBtn = wait.until(driver -> driver.findElement(cookieCloseButton));
            if (cookieBtn.isDisplayed() && cookieBtn.isEnabled()) {
                js.executeScript("arguments[0].scrollIntoView(true);", cookieBtn);
                cookieBtn.click();
                System.out.println("Cookie popup closed successfully.");
            }
        } catch (Exception e) {
            System.out.println("No cookie popup displayed.");
        }
    }

    public void searchProduct(String product) {
        wait.until(driver -> searchBar.isDisplayed());
        searchBar.clear();
        searchBar.sendKeys(product);
        searchBar.sendKeys(Keys.ENTER);
    }

    public void clickCart() {
        wait.until(driver -> cart.isDisplayed() && cart.isEnabled());
        js.executeScript("arguments[0].scrollIntoView(true);", cart);
        cart.click();
    }

    public void clickWishlist() {
        wait.until(driver -> wishlist.isDisplayed() && wishlist.isEnabled());
        wishlist.click();
    }

    public void clickStore() {
        wait.until(driver -> store.isDisplayed() && store.isEnabled());
        store.click();
    }

    public void clickSignup() {
        wait.until(driver -> signup.isDisplayed() && signup.isEnabled());
        signup.click();
    }

    public void clickFirstProduct() {
        WebElement firstProduct = wait.until(driver -> driver.findElement(firstProductLocator));
        js.executeScript("arguments[0].scrollIntoView(true);", firstProduct);
        firstProduct.click();
    }

    public void clickAddToCart() {
        WebElement addToCartButton = wait.until(driver -> driver.findElement(addToCartLocator));
        js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
        addToCartButton.click();
    }

    // ---------------- Hover Actions ----------------
    public void hoverOnLuxury() {
        wait.until(driver -> luxury.isDisplayed());
        act.moveToElement(luxury).perform();
    }

    public void clickBedroom() {
        wait.until(driver -> bedroom.isDisplayed() && bedroom.isEnabled());
        bedroom.click();
    }

    public void clickWardrobes() {
        wait.until(driver -> wardrobes.isDisplayed() && wardrobes.isEnabled());
        wardrobes.click();
    }

    // ---------------- Validations ----------------
    public boolean isCartDisplayed() {
        return cart.isDisplayed();
    }

    public boolean isWishlistDisplayed() {
        return wishlist.isDisplayed();
    }

    public boolean isStoreDisplayed() {
        return store.isDisplayed();
    }

    public boolean isSignupDisplayed() {
        return signup.isDisplayed();
    }

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    // ---------------- Getters ----------------
    public WebElement getBedroomElement() {
        return bedroom;
    }

    public WebElement getWardrobesElement() {
        return wardrobes;
    }

    public WebElement getCartElement() {
        return cart;
    }

    public WebElement getFirstProductElement() {
        return wait.until(driver -> driver.findElement(firstProductLocator));
    }

    public WebElement getAddToCartElement() {
        return wait.until(driver -> driver.findElement(addToCartLocator));
    }

	public void navigateToSofasCategory() {
		// TODO Auto-generated method stub
		
	}
}