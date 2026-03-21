package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.SearchPage;
import PageObjects.DetailsPageObject;
import org.openqa.selenium.WebElement;
import java.util.List;

public class TC_05_VerifyProductDetailsPage extends BaseClass {

    @Test
    public void verifyProductDetailsPage() {

        SearchPage search = new SearchPage(driver);
        DetailsPageObject productDetails = new DetailsPageObject(driver);

        search.waitForPageLoad();

        List<WebElement> prices = search.getAllProductPrices();

        int indexToClick = -1;

        for (int i = 0; i < prices.size(); i++) {
            String priceText = prices.get(i).getText();
            if (priceText != null && !priceText.isEmpty()) {
                indexToClick = i;
                break;
            }
        }

        if (indexToClick == -1) {
            Assert.fail("No product with visible price found.");
        }

        search.clickProductByIndex(indexToClick);

        String productName = productDetails.getBrandNameHeader();
        String productPrice = productDetails.getPrice();

        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);

        Assert.assertFalse(productName.isEmpty(), "Product name is not visible!");
        Assert.assertFalse(productPrice.isEmpty(), "Product price is not visible!");
    }
}