package drivers;

import config.ConfigManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DriverFactory {
    private static AppiumDriver driver;

    public static void initialize() {
        if (driver != null) {
            return;
        }

        try {
            DesiredCapabilities caps = new DesiredCapabilities();

            // Pega caminho relativo do app do config.properties
            String appRelativePath = ConfigManager.get("app");
            // Monta caminho absoluto baseado no diretório do projeto
            Path appPath = Paths.get(System.getProperty("user.dir"), appRelativePath);

            caps.setCapability("deviceName", ConfigManager.get("deviceName"));
            caps.setCapability("app", appPath.toString());

            final String platform = ConfigManager.get("platform");

            if ("android".equalsIgnoreCase(platform)) {
                caps.setCapability("platformName", "Android");
                caps.setCapability("automationName", "UiAutomator2");
                caps.setCapability("noReset", true);
                driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

            } else if ("ios".equalsIgnoreCase(platform)) {
                caps.setCapability("platformName", "iOS");
                caps.setCapability("automationName", "XCUITest");
                caps.setCapability("bundleId", ConfigManager.get("bundleId"));
                driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);

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
