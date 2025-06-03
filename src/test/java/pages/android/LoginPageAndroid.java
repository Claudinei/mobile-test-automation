package pages.android;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage; // Certifique-se de importar a interface corretamente

public class LoginPageAndroid implements LoginPage {
    private final AppiumDriver driver;

    public LoginPageAndroid(AppiumDriver driver) {
        this.driver = driver;
    }

    private WebElement getElement(By locator) {
        return driver.findElement(locator);
    }

    @Override
    public void enterUsername(String username) {
        getElement(By.xpath("//*[@content-desc='usernameField']")).sendKeys(username);
    }

    @Override
    public void enterPassword(String password) {
        getElement(By.xpath("//*[@content-desc='passwordField']")).sendKeys(password);
    }

    @Override
    public void tapLogin() {
        getElement(By.xpath("//*[@content-desc='loginBtn']")).click();
    }
}
