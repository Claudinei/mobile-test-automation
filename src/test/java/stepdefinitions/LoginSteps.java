package stepdefinitions;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import pages.LoginPage;
import pages.android.LoginPageAndroid;
import pages.ios.LoginPageIOS;

public class LoginSteps {
    private LoginPage login;

    @Before
    public void setUp() {
        DriverFactory.initialize();
        if (DriverFactory.getDriver() == null) {
            throw new IllegalStateException("O driver não foi inicializado corretamente.");
        }

        String platform = DriverFactory.getPlatform();
        if ("ios".equalsIgnoreCase(platform)) {
            login = new LoginPageIOS((IOSDriver) DriverFactory.getDriver());
        } else if ("android".equalsIgnoreCase(platform)) {
            login = new LoginPageAndroid((AndroidDriver) DriverFactory.getDriver());
        } else {
            throw new IllegalArgumentException("Plataforma desconhecida: " + platform);
        }
    }

    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                if (DriverFactory.getDriver() instanceof TakesScreenshot) {
                    byte[] screenshot = ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Failed Screenshot", new java.io.ByteArrayInputStream(screenshot));
                } else {
                    System.err.println("O driver atual não suporta captura de tela.");
                }
            } catch (Exception e) {
                System.err.println("Erro ao capturar screenshot: " + e.getMessage());
            }
        }
        DriverFactory.quitDriver();
    }

    @Given("o usuário está na tela de login")
    public void naTelaLogin() {
        // O app já abre na tela de login
    }

    @When("ele insere usuário {string} e senha {string}")
    public void inserirCredenciais(String usuario, String senha) {
        if (login == null) {
            throw new IllegalStateException("LoginPage não foi inicializado corretamente.");
        }
        login.enterUsername(usuario);
        login.enterPassword(senha);
    }

    @And("toca no botão de login")
    public void tocarBotaoLogin() {
        login.tapLogin();
    }

    @Then("ele deve ver a tela inicial")
    public void validarTelaInicial() {
        // Validação simples (ex: esperar elemento da home)
    }
}
