package testscripts;

import drivers.DriverBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductSearch;
import utils.JsonUtils;

import java.awt.*;
import java.net.MalformedURLException;
import java.util.Map;

public class SimpleCartFlow extends DriverBase {
    LoginPage login;
    ProductSearch search;
    CheckoutPage cart;
    JsonUtils json;

    @Parameters({"deviceName"})
    @BeforeTest
    public void setUp(String deviceName) throws MalformedURLException {
        driver = setCapabilities(deviceName);
        login =new LoginPage(driver);
        search =new ProductSearch(driver);
        cart =new CheckoutPage(driver);
        json = new JsonUtils();

    }


    @Test(description = "Validate the checkout page product details with selection page product details",groups = "cart_possitive")
    public void validateSimpleCartFlow() throws InterruptedException, AWTException {
		//Step 1: Select Home Menu Button On Screen
        login.selectHomeMenu();
		//Step 2: Login to eBay app with valid credentials
        login.loginWithUserNameAndPassword(json.readFromJson("userName"),json.readFromJson("passWord"));
		//Step 3: Search for the particular product and select a random one from list
        search.searchProduct(json.readFromJson("productName"));
        search.selectRandomProduct();
		//Step 4: Retrive product information before adding to cart
        Map productInfoExpected = search.getProductInfo();
		//Step 5: Add the product to cart and Go to Checkout Page
        search.addToCart();
        System.out.println("Expected Product Info"+ productInfoExpected);
        search.goToCart();
		//Step 6: Retrive the product info on checkout screen
        Map productInfoActual = cart.getProductInfoFromCart();
        System.out.println("Actual Product Info"+ productInfoActual);
		//Step 7: Remove the product from cart.So that next run will not be affected
        cart.removeITemfromCart();
		//Step 8: Validate the Product
        Assert.assertEquals(productInfoExpected.get("productName"),productInfoActual.get("productName"));
    }
}
