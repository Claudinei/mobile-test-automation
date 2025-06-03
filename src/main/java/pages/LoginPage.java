package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private AppiumDriver driver;

    private By usernameField = By.id("com.exemplo:id/username");
    private By passwordField = By.id("com.exemplo:id/password");
    private By loginButton = By.id("com.exemplo:id/login");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String user) {
        driver.findElement(usernameField).sendKeys(user);
    }

    public void enterPassword(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void tapLogin() {
        driver.findElement(loginButton).click();
    }
}
