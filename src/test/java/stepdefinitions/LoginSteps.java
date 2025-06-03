package stepdefinitions;

import drivers.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.en.*;
import pages.LoginPage;
import pages.android.LoginPageAndroid;
import pages.ios.LoginPageIOS;

public class LoginSteps {
    private LoginPage login;

    private void initLoginPage() {
        if (login == null) {
            String platform = DriverFactory.getPlatform();
            if ("ios".equalsIgnoreCase(platform)) {
                login = new LoginPageIOS((IOSDriver) DriverFactory.getDriver());
            } else {
                login = new LoginPageAndroid((AndroidDriver) DriverFactory.getDriver());
            }
        }
    }

    @Given("o usuário está na tela de login")
    public void naTelaLogin() {
        initLoginPage();
    }

    @When("ele insere usuário {string} e senha {string}")
    public void inserirCredenciais(String usuario, String senha) {
        initLoginPage();
        login.enterUsername(usuario);
        login.enterPassword(senha);
    }

    @And("toca no botão de login")
    public void tocarBotaoLogin() {
        initLoginPage();
        login.tapLogin();
    }

    @Then("ele deve ver a tela inicial")
    public void validarTelaInicial() {
        initLoginPage();
        // Validação futura
    }
}