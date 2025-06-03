package drivers;

import config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {
    private static AppiumDriver driver;

    public static void initialize() {
        if (driver != null) {
            return; // Evita reinicializações desnecessárias
        }

        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("deviceName", ConfigManager.get("deviceName"));
            caps.setCapability("platformVersion", ConfigManager.get("platformVersion"));
            caps.setCapability("app", ConfigManager.get("app"));

            final String platform = ConfigManager.get("platform");
            if ("android".equalsIgnoreCase(platform)) {
                caps.setCapability("platformName", "Android");
                caps.setCapability("appPackage", ConfigManager.get("appPackage"));
                caps.setCapability("appActivity", ConfigManager.get("appActivity"));
                driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
            } else if ("ios".equalsIgnoreCase(platform)) {
                caps.setCapability("platformName", "iOS");
                driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
            } else {
                throw new IllegalArgumentException("Plataforma desconhecida: " + platform);
            }
        } catch (MalformedURLException e) {
            System.err.println("Erro na URL do Appium: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro ao inicializar o driver: " + e.getMessage());
        }
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver não inicializado. Chame initialize() primeiro.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getPlatform() {
        return ConfigManager.get("platform");
    }
}
