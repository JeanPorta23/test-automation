package pe.com.bcp.techcases.testautomation.ui.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.*;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;
import pe.com.bcp.techcases.testautomation.ui.actors.OrangeUser;

import java.util.List;
import java.util.Map;

public class OrangeHRMStepDefinitions {

    @Steps
    private OrangeUser orangeUser;

    @E("inicia sesión con las credenciales")
    public void iniciaSesiónConLasCredenciales(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String user = rows.get(0).get("user");
        String password = rows.get(0).get("password");
        orangeUser.fillsFieldsAndLogin(user, password);
    }

    @Dado("me dirijo al menú {string}")
    public void meDirijoAlMenu(String menu) {
        orangeUser.selectMenu(menu);
    }

    @Cuando("presiono el botón para agregar un nuevo empleado")
    public void presionoElBotónParaAgregarUnNuevoEmpleado() {
        orangeUser.clickAddEmployee();
    }

    @Dado("que el usuario abre la pagina {page}")
    public void queElUsuarioAbreLaPaginaOrangeLoginPage(Class<PageObject> page) {
        orangeUser.goToPage(page);
    }

    @Y("completo la información del nuevo empleado")
    public void completoLaInformaciónDelNuevoEmpleado(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        String firstName = rows.get(0).get("firstName");
        String middleName = rows.get(0).get("middleName");
        String lastName = rows.get(0).get("lastName");
        orangeUser.createEmployeer(firstName, middleName, lastName);
    }

    @Entonces("valido que el nuevo empleado se haya creado correctamente")
    public void validoQueElNuevoEmpleadoSeHayaCreadoCorrectamente() {
        orangeUser.validateNewEmployee();
    }

    @Y("cierro sesión")
    public void cierroSesión() {
        orangeUser.closeSession();
    }
}
