package pages.ios;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

public class LoginPageIOS implements LoginPage {
    private final AppiumDriver driver;

    public LoginPageIOS(AppiumDriver driver) {
        this.driver = driver;
    }

    private WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    public void enterUsername(String username) {
        getElement(By.xpath("//*[@content-desc='username_ios']")).sendKeys(username);
    }

    public void enterPassword(String password) {
        getElement(By.xpath("//*[@content-desc='password_ios']")).sendKeys(password);
    }

    public void tapLogin() {
        getElement(By.xpath("//*[@content-desc='loginBtn_ios']")).click();
    }
}
