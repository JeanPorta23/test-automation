package pe.com.bcp.techcases.testautomation.ui.pages.sauce;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("page:saucedemo.page")
public class SauceDemoLoginPage extends PageObject {

    private final By userNameInput = By.xpath("//input[@id='user-name']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginButton = By.xpath("//input[@id='login-button']");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");

    public void typeUser(String user) {
        getDriver().findElement(userNameInput).sendKeys(user);
    }

    public void typePassword(String pass) {
        getDriver().findElement(passwordInput).sendKeys(pass);
    }

    public void logIn() {
        getDriver().findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return getDriver().findElement(errorMessage).getText();
    }

}