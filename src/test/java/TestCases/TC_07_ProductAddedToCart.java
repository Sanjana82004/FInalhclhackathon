package TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageObjects.CartPage;
import PageObjects.DetailsPageObject;
import PageObjects.HomePage;

import PageObjects.SearchPage2;


public class TC_07_ProductAddedToCart extends BaseClass {

	
    HomePage home;
    SearchPage2 search;
    DetailsPageObject details;
    CartPage cart;
    
    
    @BeforeMethod
    public void setupTest() {
       
        home = new HomePage(driver);
        search = new SearchPage2(driver);
        details = new DetailsPageObject(driver);
        cart = new CartPage(driver);
        
   
    }
    @Test(priority = 1)
	public void verifyProductAddedToCart() {
		
		
        home.searchProduct("sofa");

     
        search.clickProductByIndex(0);

     
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        
      String  productNameOnDetails = details.getBrandNameHeader();
      String  productPriceOnDetails = details.getPrice();
        
        System.out.println("Adding to cart: " + productNameOnDetails);
        
        details.enterPincode("400001");
        details.clickLocate();
        details.clickAddToCart();

     
        details.clickGoToCart();

        
      //  Assert.assertTrue(cart.getCartItemCount() > 0, "FAIL: Cart is empty!");

       
        String actualName = cart.getProductName(0);
        String actualPrice = cart.getProductPrice(0);

       
        Assert.assertTrue(actualName.contains(productNameOnDetails),
                "Product name mismatch");
        
        
    }
		
		
    
    @Test(priority = 2)
    public void verifyQuantityUpdate() {
        CartPage cart = new CartPage(driver);

        
        String initialTotal = cart.getTotalPriceText();
        System.out.println("Initial Total: " + initialTotal);

       
        cart.selectQuantity(0, "2");

    
        String updatedTotal = cart.getTotalPriceText();
        System.out.println("Updated Total after Qty 2: " + updatedTotal);

        Assert.assertNotEquals(updatedTotal, initialTotal, 
            "FAIL: Total price should change after updating quantity to 2!");

       
        System.out.println("TC2 Passed: Quantity and Total Price updated correctly.");
		
	}
    
    
    @Test(priority = 3)
    public void verifyProductRemovalFromCart() {

        
        int initialCount = cart.getCartItemCount();
        System.out.println("Initial Cart Count: " + initialCount);

    
        cart.clickRemoveProduct();

     
        cart.confirmRemoval();

      
        int updatedCount = cart.getCartItemCount();
        System.out.println("Updated Cart Count: " + updatedCount);

        // Step 5: Verify product removed
        Assert.assertTrue(updatedCount < initialCount,
                "FAIL: Product was not removed from cart!");

        System.out.println("TC9 Passed: Product removed successfully and cart updated.");
    }
    
    
}
	

