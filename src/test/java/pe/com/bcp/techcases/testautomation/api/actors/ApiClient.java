package pe.com.bcp.techcases.testautomation.api.actors;

import io.cucumber.java.Before;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;

import java.util.logging.Level;
import java.util.logging.Logger;

import static net.serenitybdd.screenplay.actors.OnStage.*;

public class ApiClient {

    private Response response;
    private static final String contentType = "application/json";

   /* @Step
    public void readStatus(String url) {
        //Agrega lo necesario para ver en el log de ejecución la Petición
        SerenityRest.useRelaxedHTTPSValidation();
        response = SerenityRest
                .given().contentType(contentType).log().all()
                .when().get(url);
        response.
            then().log().all().assertThat().statusCode(200);
    }

    @Step
    public void readContent(String expectedMessage) {
        response.
            then().body("status", equalTo(expectedMessage));
    }*/

    @Step
    public void searchPageBy(String url, int pageNumber) {
        Serenity.setSessionVariable("expectedPage").to(pageNumber);
        //Agrega lo necesario para ver en el log de la Petición
        //Agregar la consulta de QueryParams
        SerenityRest.useRelaxedHTTPSValidation();
        response = SerenityRest
                .given().contentType(contentType)
                //.queryParam("page", pageNumber)
                .when().get(url);
    }

    @Step
    public void validateStatusCode(int statusCode) {
        //Agrega lo necesario para ver en el log de ejecución la Respuestas
        response.then()
                .assertThat()
                .statusCode(statusCode);
    }

    @Step
    public void validatePageValue() {
        //Indicar el jsonPath necesario para obtener el valor de la respuesta
        int currentPage = response.getBody().jsonPath().getInt("<Json Path>");

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Número de página >>> {0}", currentPage);
        int expectedPage = Serenity.sessionVariableCalled("expectedPage");
        Assertions.assertEquals(expectedPage, currentPage,
                "El valor de la pagina esperado no es igual al obtenido.");
    }

    @Before
    public void setUp() {
        setTheStage(new OnlineCast());
        theActorCalled("Mi Actor");
    }
    @Step
    public void createNewUser(String url, String name, String job) {
        SerenityRest.useRelaxedHTTPSValidation();

        String req = "{\n" +
                "    \"name\": \""+name+"\",\n" +
                "    \"job\": \""+job+"\"\n" +
                "}";
        System.out.println("Solicitud:");
        System.out.println("URL: " + url);
        System.out.println("Cuerpo de la solicitud: " + req);

        //Agrega lo necesario para ver en el log de la Petición
        response = SerenityRest
                .given().contentType(contentType)
                .body(req) // Agrega el cuerpo de la solicitud
                .when()
                .post(url);
        System.out.println("Respuesta:");
        System.out.println("Estado: " + response.getStatusCode());
        System.out.println("Cuerpo de la respuesta: " + response.getBody().asString());

        // Obtener el ID de la respuesta
        String id = response.jsonPath().getString("id");
        System.out.println("ID: " + id);

        // Guardar el ID utilizando remember
        theActorInTheSpotlight().remember("id", id);
    }

    @Step
    public void saveIdUser() {
        //String id = response.getBody().jsonPath().getString("<json path>");
        String id = response.getBody().jsonPath().getString("id");
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Id del nuevo usuario >>> {0}", id);
        //Serenity.setSessionVariable("newUserId").to(id);
        theActorInTheSpotlight().remember("newUserId", id);
    }

    @Step
    public void updateUserInfo(String url, String newName, String newJob) {
        SerenityRest.useRelaxedHTTPSValidation();
        String id = theActorInTheSpotlight().recall("newUserId");

        Serenity.setSessionVariable("expectedNewName").to(newName);
        Serenity.setSessionVariable("expectedNewJob").to(newJob);

        String req = "{\n" +
                "    \"name\": \""+newName+"\",\n" +
                "    \"job\": \""+newJob+"\"\n" +
                "}";
        System.out.println("Solicitud de actualización:");
        System.out.println("URL: " + url);
        System.out.println("ID del usuario: " + id);
        System.out.println("Cuerpo de la solicitud: " + req);

        //Agrega lo necesario para ver en el log de la Petición
        response = SerenityRest
                .given().contentType(contentType)
                .pathParam("id", id)
                .body(req)
                .when()
                .put(url +"/{id}");
        System.out.println("Cuerpo de la respuesta: " + response.getBody().asString());
    }

    @Step
    public void validateUpdatedInfo() {
        String responseBody = SerenityRest.lastResponse().body().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String expectedNewName = theActorInTheSpotlight().recall("newName");
        String expectedNewJob = theActorInTheSpotlight().recall("newJob");

        String newName = jsonPath.getString("name");
        String newJob = jsonPath.getString("job");

        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Nuevo nombre del usuario >>> {0}", newName);
        Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Nuevo trabajo del usuario >>> {0}", newJob);

        Assertions.assertEquals(expectedNewName, newName,
                "El nuevo nombre '"+newName+"' no es igual al esperado '"+expectedNewName+"'");
        Assertions.assertEquals(expectedNewJob, newJob,
                "El nuevo trabajo '"+newJob+"' no es igual al esperado '"+expectedNewJob+"'");
    }
}
