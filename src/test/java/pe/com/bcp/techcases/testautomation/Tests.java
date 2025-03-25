package pe.com.bcp.techcases.testautomation;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.logging.Logger;

/*
    1) Generar los reportes en HTML y JSON por defecto de Cucumber (plug-in)
    2) Realizar la ejecución a partir anotaciones categorizadas en los archivos .features
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"json:target/cucumber/cucumber.json", "html:target/cucumber/fast-report/cucumber-pretty.html"},
        features = "src/test/resources/features/",
        stepNotifications = true,
        glue = "pe.com.bcp.techcases.testautomation"
)
public class Tests {

    @Test
    public void unnecessaryTest() {
        Logger.getLogger("Tests").info("Is everything ok or not?");
    }

}
