package drivers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;

import configs.Constants;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.yaml.snakeyaml.scanner.Constant;


public class DriverBase {

    protected static AndroidDriver<AndroidElement>  driver;
    private static DesiredCapabilities cap;


    public  AndroidDriver<AndroidElement> setCapabilities(String deviceName) throws MalformedURLException
        {
            File appDir = new File(Constants.appPath);
            File app = new File(appDir, Constants.appName);

            cap = new DesiredCapabilities();
            cap.setCapability("noReset", "false");
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            cap.setCapability("appPackage",Constants.appPackage );
            cap.setCapability("appActivity", Constants.appActivity);

            System.out.println("Starting Android Driver");

            try{
                driver = new AndroidDriver<AndroidElement>(new URL(Constants.appiumServcrURL), cap);
            }
            catch (Exception e){
                cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                driver = new AndroidDriver<AndroidElement>(new URL(Constants.appiumServcrURL), cap);

            }

            return driver;
        }

    public AndroidDriver<AndroidElement> getDriver() {
        return driver;
    }
    }

