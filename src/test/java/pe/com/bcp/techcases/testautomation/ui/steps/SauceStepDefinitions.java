package pe.com.bcp.techcases.testautomation.ui.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;
import org.junit.jupiter.api.Assertions;
import pe.com.bcp.techcases.testautomation.ui.actors.SauceDemoUser;

public class SauceStepDefinitions {

    @Steps
    private SauceDemoUser sauceUser;

    @Dado("que el usuario visita la pagina {page}")
    public void queElUsuarioVisitaLaPaginaSauceDemoPage(Class<PageObject> page) {
        sauceUser.goToPage(page);
    }

    @Cuando("ingresa las credenciales {string} y {string} para iniciar sesión")
    public void ingresaLasCredencialesYParaIniciarSesión(String user, String pass) {
        sauceUser.fillsFieldsAndLogin(user, pass);
    }

    @Entonces("valida que el mensaje de error contiene {string}")
    public void validaQueElMensajeDeErrorContiene(String error) {
        Assertions.assertTrue(sauceUser.errorMessage().contains(error),
                "El mensaje de error no contiene >>> " + error);
    }

}