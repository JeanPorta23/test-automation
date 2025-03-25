package pe.com.bcp.techcases.testautomation.ui.actors;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.steps.ScenarioSteps;
import pe.com.bcp.techcases.testautomation.ui.pages.sauce.SauceDemoInventoryPage;
import pe.com.bcp.techcases.testautomation.ui.pages.sauce.SauceDemoLoginPage;

public class SauceDemoUser extends ScenarioSteps {

    @Steps
    private SauceDemoLoginPage loginPage;
    @Steps
    private SauceDemoInventoryPage inventoryPage;

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
        SauceDemoUser.PageContainer.setCurrentPage(page);
        return page;
    }

    @Step
    public void fillsFieldsAndLogin(String user, String pass) {
        loginPage.typeUser(user);
        loginPage.typePassword(pass);
        loginPage.logIn();
    }

    @Step
    public String errorMessage() {
        return loginPage.getErrorMessage();
    }
}
