package pages.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;

import java.time.Duration;

public class LoginPageAndroid implements LoginPage {
    private final AppiumDriver driver;
    private final WebDriverWait wait;

    public LoginPageAndroid(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterUsername(String username) {
        getElement(By.xpath("//*[@content-desc='usernameField']")).sendKeys(username);
    }

    public void enterPassword(String password) {
        getElement(By.xpath("//*[@content-desc='passwordField']")).sendKeys(password);
    }

    public void tapLogin() {
        getElement(By.xpath("//*[@content-desc='loginBtn']")).click();
    }
}