package stepdefinitions;

import core.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import pages.LoginPage;

public class LoginSteps {
    private AppiumDriver driver;
    private LoginPage login;

    @Given("the app is launched")
    public void launchApp() throws Exception {
        driver = DriverFactory.getDriver();
        //login = new LoginPage(driver);
    }

    @When("I enter valid credentials")
    public void enterValidCredentials() {
        login.enterUsername("user");
        login.enterPassword("1234");
        login.tapLogin();
    }

    @Then("I should be logged in")
    public void verifyLogin() {
        // Implementar validações conforme aplicação
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
