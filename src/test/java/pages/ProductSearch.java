package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class ProductSearch {

    private static AndroidDriver<AndroidElement> driver;
    private static WebDriverWait wait;

    public ProductSearch( AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 120);
    }

    @FindBy(id="search_box")
    private MobileElement searchBox;

    @FindBy(id="text_slot_1")
    private MobileElement messageBox;

    @FindBy(id="textview_item_subtitle")
    private List<MobileElement> itemdesc;

    @FindBy(id="search_src_text")
    private MobileElement searchText;

    @FindBy(className="android.widget.Button")
    private List<MobileElement> addToCart;

    @FindBy(id="call_to_action_button")
    private MobileElement goToCart;

    @FindBy(id="item_count")
    private MobileElement itemCount;

    @FindBy(className="android.widget.TextView")
    private List<MobileElement> itemListName;

    @FindBy(id="textview_item_name")
    private MobileElement itemNameProductPage;

    @FindBy(id="textview_item_price")
    private MobileElement itemPriceProductPage;

    /**
     * Method to Search Product in App
     *
     * @param productName
     **/
    public void searchProduct(String productName) throws InterruptedException, AWTException {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.click();
        wait.until(ExpectedConditions.visibilityOf(searchText));
        searchText.sendKeys((CharSequence) productName);
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOf(messageBox));
        messageBox.click();
        wait.until(ExpectedConditions.visibilityOf(itemCount));
        Assert.assertTrue(itemdesc.size()>=0);

    }

    // Select randam product from list
    public void selectRandomProduct(){
        Random r = new Random();
        int low = 1;
        int high = Integer.parseInt(itemCount.getText().replace(" results","").trim()) -1;
        int randomNum = r.nextInt(high-low) + low;
        itemdesc.get(randomNum).click();
    }

    //Add product to cart
    public void addToCart(){
        wait.until(ExpectedConditions.visibilityOf(addToCart.get(0)));
        addToCart.get(1).click();

    }

    //Select go to cart from screen
    public void goToCart(){
        wait.until(ExpectedConditions.visibilityOf(goToCart));
        goToCart.click();

    }

    //Get product details before adding into cart
    public Map getProductInfo(){
        HashMap<String,String> productInfo = new HashMap<>();
        String productName = itemNameProductPage.getText();
        String productPrice =itemPriceProductPage.getText();
        productInfo.put("productName",productName);
        productInfo.put("productPrice",productPrice);
        return  productInfo;
    }


}
