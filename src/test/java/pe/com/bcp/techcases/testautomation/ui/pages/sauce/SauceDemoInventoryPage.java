package pe.com.bcp.techcases.testautomation.ui.pages.sauce;

import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoInventoryPage extends PageObject {

    private String expectedURL = "/inventory.html";

    public boolean urlShouldContains(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 15);
        return wait.until(ExpectedConditions.urlContains(expectedURL));
    }

}
