package PageObjects;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.DetailsPageObject;
import TestCases.BaseClass;

public class DetailsPageObject extends BaseClass {

    @Test(description = "Verify that clicking a product opens the product details page correctly")
    public void verifyProductDetailsPage() {

        SearchPage search = new SearchPage(driver);
        DetailsPageObject productDetails = new DetailsPageObject(driver);

        search.waitForPageLoad();

        int indexToClick = -1;

        for (int i = 0; i < search.firstProductprice.size(); i++) {
            String price = search.getProductPriceByIndex(i);
            if (price != null && !price.isEmpty()) {
                indexToClick = i;
                break;
            }
        }

        if (indexToClick == -1) {
            Assert.fail("No product with visible price found.");
        }

        search.clickProductByIndex(indexToClick);



        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);

        Assert.assertFalse(productName.isEmpty(), "Product name is not visible!");
        Assert.assertTrue(priceVisible, "Product price is not visible!");

        // Optional: Verify discount calculation is correct
        if (productDetails.getDiscount() != null && !productDetails.getDiscount().isEmpty()) {
            Assert.assertTrue(productDetails.discountCalculation(), "Discount calculation mismatch!");
        }
    }

