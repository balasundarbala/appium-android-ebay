package pages;

import configs.Constants;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class CheckoutPage {

    private static AndroidDriver<AndroidElement> driver;
    private static WebDriverWait wait;

    public CheckoutPage( AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Constants.LONG);
    }

    @FindBy(id="title")
    private MobileElement titlePage;

    @FindBy(id="item_title")
    private MobileElement itemTitle;


    @FindBy(id="item_price")
    private MobileElement itemPrice;

    @FindBy(id="item_action_remove_from_cart")
    private MobileElement removeItem;



    //get the product details from checkout page
    public Map getProductInfoFromCart(){
        HashMap<String,String> actualProudctInfo = new HashMap<>();
        wait.until(ExpectedConditions.visibilityOf(itemTitle));
        String productName= itemTitle.getText().trim();
        String productPrice= itemPrice.getText().trim();
        actualProudctInfo.put("productName",productName);
        actualProudctInfo.put("productPrice",productPrice);
        return  actualProudctInfo;
    }

    //remove the added item from cart
    public void removeITemfromCart() throws InterruptedException {
        removeItem.click();
    }
}
