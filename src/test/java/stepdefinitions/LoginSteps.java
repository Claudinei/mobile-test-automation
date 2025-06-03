package stepdefinitions;
import drivers.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;

public class LoginSteps {
    LoginPage login;

    @Before
    public void setUp() {
        DriverFactory.initialize();
        login = new LoginPage(DriverFactory.getDriver());
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failed Screenshot", new java.io.ByteArrayInputStream(screenshot));
        }
        DriverFactory.quitDriver();
    }

    @Given("o usuário está na tela de login")
    public void naTelaLogin() {
        // app já abre na tela de login
    }

    @When("ele insere usuário {string} e senha {string}")
    public void inserirCredenciais(String usuario, String senha) {
        login.enterUsername(usuario);
        login.enterPassword(senha);
    }

    @And("toca no botão de login")
    public void tocarBotaoLogin() {
        login.tapLogin();
    }

    @Then("ele deve ver a tela inicial")
    public void validarTelaInicial() {
        // validação simples (ex: esperar elemento da home)
    }
}
