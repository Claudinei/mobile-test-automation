package drivers;

import config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public class DriverFactory {
    public static AppiumDriver driver;

    public static void initialize() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", ConfigManager.get("deviceName"));
            caps.setCapability("platformVersion", ConfigManager.get("platformVersion"));
            caps.setCapability("app", ConfigManager.get("app"));

            String platform = ConfigManager.get("platform");
            if (platform.equalsIgnoreCase("android")) {
                caps.setCapability("platformName", "Android");
                caps.setCapability("appPackage", ConfigManager.get("appPackage"));
                caps.setCapability("appActivity", ConfigManager.get("appActivity"));
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
            } else {
                caps.setCapability("platformName", "iOS");
                driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) driver.quit();
    }
}

