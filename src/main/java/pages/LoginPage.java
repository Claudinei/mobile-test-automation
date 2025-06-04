package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class LoginPage {
    private AppiumDriver driver;

    // TO-DO implementar login
    private By usernameField = By.id("com.exemplo:id/username");
    private By passwordField = By.id("com.exemplo:id/password");
    private By loginButton = By.id("com.exemplo:id/login");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void inserirUsuario(String user) {
        driver.findElement(usernameField).sendKeys(user);
    }

    public void inserirSenha(String pass) {
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void tapLogin() {
        driver.findElement(loginButton).click();
    }
}
