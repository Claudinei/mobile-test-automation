package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class DriverFactory {
    private static AppiumDriver driver;

    public static AppiumDriver getDriver() throws Exception {
        if (driver == null) {
            Properties props = ConfigManager.load();
            String appPath = props.getProperty("appPath");
            String absoluteAppPath = Paths.get(System.getProperty("user.dir"), appPath).toString();

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", props.getProperty("deviceName"));
            caps.setCapability("platformVersion", props.getProperty("platformVersion"));

            caps.setCapability("app", absoluteAppPath);
            caps.setCapability("platformName", props.getProperty("platform"));
            caps.setCapability("automationName", "UiAutomator2");
            caps.setCapability("appPackage",props.getProperty("appPackage") );
            caps.setCapability("appActivity", props.getProperty("appActivity"));
            caps.setCapability("uiautomator2ServerInstallTimeout", 60000);

            URL appiumServerUrl = new URL(props.getProperty("appiumServer"));

            if (props.getProperty("platform").equalsIgnoreCase("Android")) {
                driver = new AndroidDriver(appiumServerUrl, caps);
            } else {
                driver = new IOSDriver(appiumServerUrl, caps);
            }
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
