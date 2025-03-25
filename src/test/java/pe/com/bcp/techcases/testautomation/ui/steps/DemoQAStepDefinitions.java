package pe.com.bcp.techcases.testautomation.ui.steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;
import pe.com.bcp.techcases.testautomation.ui.actors.DefaultUser;

import static org.assertj.core.api.Assertions.assertThat;


public class DemoQAStepDefinitions {

    @Steps
    private DefaultUser jhon;


    /**
     * Debería agregar algo parecido donde se puedan leer los datos completos en un solo paso
     * consumiendo un DataTable para mayor flexibilidad
     */

/*    @Cuando("llena los campos minimos y envia el formulario")
    public void fillsTheMinimumFields(DataTable fieldsAndValues) throws IllegalAccessException {
        List<Map<String, String>> rows = fieldsAndValues.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String field = columns.get("field");
            String value = columns.get("value");
            jhon.fillsField(field, value);
        }
    }*/


    @Dado("el usuario {user}")
    public void a(DefaultUser user) {
    }

    @Cuando("visita la pagina {page}")
    public void visits(Class<PageObject> page) {
        jhon.goToPage(page);
    }

    @Cuando("completa {string} con {string}")
    public void fillsWith(String field, String value) throws IllegalAccessException {
        jhon.fillsField(field, value);
    }

    @Cuando("hace clic en {string}")
    public void clicksOn(String elementName) throws IllegalAccessException {
        jhon.clicksOn(elementName);
    }

    @Entonces("en la pagina debería mostrarse {string}")
    public void thePageSays(String text) throws IllegalAccessException {
        assertThat(jhon.isAbleToSee(text)).isTrue();
    }
}