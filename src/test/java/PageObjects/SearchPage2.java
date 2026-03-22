package PageObjects;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SearchPage2 extends basePage {

    public SearchPage2(WebDriver driver) {
        super(driver);
    }

    // ✅ Single locator for product card (parent)
    private By productCards = By.xpath("//img[@loading='lazy']");

    // ================== Core Helper ==================

    private WebElement getProductCard(int index) {
        List<WebElement> cards = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(productCards)
        );

        if (index < 0 || index >= cards.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }

        WebElement card = cards.get(index);

        // ✅ Scroll happens ONCE here
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", card);

        wait.until(ExpectedConditions.visibilityOf(card));

        return card;
    }

    // ================== Actions ==================

    public String getProductNameByIndex(int index) {
        try {
            WebElement card = getProductCard(index);

            WebElement name = card.findElement(
                    By.xpath("//h2[@class='product-name color-tertiary text-md font-medium ng-star-inserted']")
            );

            return name.getText().trim();

        } catch (StaleElementReferenceException e) {
            return getProductNameByIndex(index); // retry
        }
    }

    public String getProductPriceByIndex(int index) {
        try {
            WebElement card = getProductCard(index);

            WebElement price = card.findElement(
                    By.xpath("//span[@class='product-offer-price font-bold text-xl ng-star-inserted']")
            );

            return price.getText().trim();

        } catch (StaleElementReferenceException e) {
            return getProductPriceByIndex(index); // retry
        }
    }

    public void clickProductByIndex(int index) {
        try {
            WebElement card = getProductCard(index);

            WebElement clickable = card.findElement(By.xpath(".//a"));

            wait.until(ExpectedConditions.elementToBeClickable(clickable)).click();

        } catch (Exception e) {
            WebElement card = getProductCard(index);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", card);
        }
    }

    // ================== Utility ==================

    public int getTotalProducts() {
        return driver.findElements(productCards).size();
    }
}