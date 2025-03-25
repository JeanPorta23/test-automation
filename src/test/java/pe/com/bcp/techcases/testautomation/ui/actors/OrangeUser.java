package pe.com.bcp.techcases.testautomation.ui.actors;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.steps.ScenarioSteps;
import org.junit.Assert;
import pe.com.bcp.techcases.testautomation.ui.pages.orange.OrangeHomePage;
import pe.com.bcp.techcases.testautomation.ui.pages.orange.OrangeLoginPage;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class OrangeUser extends ScenarioSteps {

    @Steps
    private OrangeLoginPage loginPage;

    private OrangeHomePage homePage;

    static class PageContainer {
        private static PageObject CURRENT_PAGE;

        public static PageObject getCurrentPage() {
            return CURRENT_PAGE;
        }

        public static PageObject setCurrentPage(PageObject page) {
            CURRENT_PAGE = page;
            return CURRENT_PAGE;
        }
    }

    @Step
    public PageObject goToPage(Class<PageObject> pageClass) {
        PageObject page = getPages().get(pageClass);
        page.open();
        OrangeUser.PageContainer.setCurrentPage(page);
        return page;
    }

    @Step
    public void fillsFieldsAndLogin(String user, String pass) {
        loginPage.typeUser(user);
        loginPage.typePassword(pass);
        loginPage.logIn();
    }

    @Step
    public void selectMenu(String menu) {
        String xpathMenu = "//li/a/*[text()='" + menu + "']";
        homePage.waitMenu(xpathMenu);
        homePage.clickMenu(xpathMenu);
        homePage.waitMenu("//h6[text()='" + menu + "']");
    }

    @Step
    public void clickAddEmployee() {
        homePage.clickAddEmployee();
        homePage.waitTitleEmployee();
    }

    @Step
    public void createEmployeer(String firstName, String middleName, String lastName) {
        homePage.writeFirstName(firstName);
        homePage.writeMiddleName(middleName);
        homePage.writeLastName(lastName);
        theActorInTheSpotlight().remember("id", homePage.getId());
        homePage.saveEmployeer();
        homePage.waitTitleNewEmployee();
    }

    @Step
    public void validateNewEmployee() {
        homePage.clickEmployeeList();
        homePage.searchNewEmployee(
                theActorInTheSpotlight().recall("id")
        );
        homePage.clickSearch();
        homePage.waitOnlyOneResult();

        Assert.assertEquals("El id esperado no coincide con el real",
                theActorInTheSpotlight().recall("id"),
                homePage.getIdNewEmployee()
        );
    }

    @Step
    public void closeSession() {
        homePage.clickOptions();
        homePage.clickCloseSession();
    }
}