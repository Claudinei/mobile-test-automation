package stepdefinitions;

import core.DriverFactory;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import io.cucumber.java.After;
import pages.LoginPage;

public class LoginSteps {
    private AppiumDriver driver;
    private LoginPage login;

    @Given("que o aplicativo é iniciado")
    public void launchApp() throws Exception {
        driver = DriverFactory.getDriver();
        //login = new LoginPage(driver);
    }

    @When("Eu insiro credenciais válidas")
    public void insirirCredenciaisValidas() {
        login.inserirUsuario("user");
        login.inserirSenha("1234");
        login.tapLogin();
    }

    @Then("Eu deveria estar logado")
    public void verificarLogin() {
        // Implementar validações conforme aplicação
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
