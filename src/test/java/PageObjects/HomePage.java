package PageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends basePage {

    Actions act; // used for advanced interaction

    // constructor
    public HomePage(WebDriver driver) {
        super(driver);
        act = new Actions(driver);
    }

    //locators

    @FindBy(id = "search")
    WebElement searchBar;

    @FindBy(xpath = "//img[@alt='profile icon']")
    WebElement signUp;

    @FindBy(xpath = "//img[@alt='studio icon']")
    WebElement store;

    @FindBy(xpath = "//img[@alt='wishlist icon']")
    WebElement wishlist;

    @FindBy(xpath = "//img[@alt='cart icon']")
    WebElement cart;

    @FindBy(xpath = "//img[@alt='PepperfryLogo']")
    WebElement logo;

    @FindBy(xpath = "//img[@alt='hamburger icon']")
    WebElement menu;

    @FindBy(xpath = "//a[contains(text(),'Luxury')]")
    WebElement luxury;

    @FindBy(xpath = "//a[normalize-space()='Bedroom']")
    WebElement bedroom;

    @FindBy(xpath = "//a[normalize-space()='Wardrobes']")
    WebElement wardrobes;

    //actions method
    public void searchProduct(String product) {
        searchBar.clear();
        searchBar.sendKeys(product);
        searchBar.sendKeys(Keys.ENTER);
    }

    public void clickSignUp() {
        signUp.click();
    }

    public void clickStore() {
        store.click();
    }

    public void clickWishlist() {
        wishlist.click();
    }

    public void clickCart() {
        cart.click();
    }

    //until menu is click
    public void clickMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(menu)).click();
    }

    // wait before hover
    public void hoverOnLuxury() {
        wait.until(ExpectedConditions.visibilityOf(luxury));
        act.moveToElement(luxury).perform();
    }

    //wait before clicking submenu
    public void clickBedroom() {
        wait.until(ExpectedConditions.elementToBeClickable(bedroom)).click();
    }

    public void clickWardrobes() {
        wait.until(ExpectedConditions.elementToBeClickable(wardrobes)).click();
    }

    //validation method
    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }

    public boolean isSignUpDisplayed() {
        return signUp.isDisplayed();
    }

    public boolean isStoreDisplayed() {
        return store.isDisplayed();
    }

    public boolean isWishlistDisplayed() {
        return wishlist.isDisplayed();
    }

    public boolean isCartDisplayed() {
        return cart.isDisplayed();
    }

    public boolean isMenuDisplayed() {
        return menu.isDisplayed();
    }
}