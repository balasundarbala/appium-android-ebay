package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage{

    private static AndroidDriver<AndroidElement>  driver;
    private static WebDriverWait wait;

    public LoginPage( AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 120);
    }

    @FindBy(id="home")
    private MobileElement menuButton;

    @FindBy(id="profile_container")
    private MobileElement signIn;

    @FindBy(id="button_classic")
    private MobileElement useEmailOrUsername;

    @FindBy(id="edit_text_username")
    private MobileElement userId;

    @FindBy(id="et_temp")
    private MobileElement passWord;

    @FindBy(id="button_sign_in")
    private MobileElement signInButton;

    // Selecting the Home Icon to view navigation menu
    public void selectHomeMenu(){
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButton.click();

    }

    //Select sign in menu option
    public void selectSignIn(){
        wait.until(ExpectedConditions.visibilityOf(signIn));
        signIn.click();
    }

    /**
     * Method to login to application with credentials
     *
     * @param username,password
     **/
    public void loginWithUserNameAndPassword(String username,String password) throws InterruptedException {
        selectSignIn();
        wait.until(ExpectedConditions.visibilityOf(useEmailOrUsername));
        useEmailOrUsername.click();
        wait.until(ExpectedConditions.visibilityOf(userId));
        userId.sendKeys(username);
        passWord.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(signInButton));
        signInButton.click();

    }

}
