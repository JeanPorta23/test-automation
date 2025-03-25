package pe.com.bcp.techcases.testautomation.ui.pages.orange;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;

@DefaultUrl("page:orangehrmlive.page")
public class OrangeLoginPage extends PageObject {
    private final By userNameInput = By.xpath("//input[@name='username']");
    private final By passwordInput = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//button[@type='submit']");

    public void typeUser(String user) {
        getDriver().findElement(userNameInput).sendKeys(user);
    }

    public void typePassword(String pass) {
        getDriver().findElement(passwordInput).sendKeys(pass);
    }

    public void logIn() {
        getDriver().findElement(loginButton).click();
    }
}
