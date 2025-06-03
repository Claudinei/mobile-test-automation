package pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPage {
    AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElementByAccessibilityId("usernameField").sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElementByAccessibilityId("passwordField").sendKeys(password);
    }

    public void tapLogin() {
        driver.findElementByAccessibilityId("loginBtn").click();
    }
}
