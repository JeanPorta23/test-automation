package pe.com.bcp.techcases.testautomation.ui.pages.orange;

import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@DefaultUrl("page:orangehrmlive.page")
public class OrangeHomePage extends PageObject {

    private final By btnAddEmployee = By.xpath("//a[text()='Add Employee']");
    private final By titleEmployee = By.xpath("//h6[text()='Add Employee']");
    private final By inputFirstNameNewEmployee = By.xpath("//*[@name='firstName']");
    private final By inputMiddleNameNewEmployee = By.xpath("//*[@name='middleName']");
    private final By inputLastNameNewEmployee = By.xpath("//*[@name='lastName']");
    private final By bntSaveNewEmployee = By.xpath("//button[@type='submit']");
    private final By txtEmployeeId = By.xpath("//label[text()='Employee Id']/ancestor::div[contains(@class, 'oxd-input-group')]/descendant::input");
    private final By titleNewEmployee = By.xpath("//h6[text()='Personal Details']");
    private final By btnEmployeeList = By.xpath("//a[contains(text(),'Employee List')]");
    private final By inputIdEmployee = By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/input");
    private final By btnSearchEmployee = By.xpath("//button[text()=' Search ']");
    private final By txtResultId = By.xpath("//*[@role='table']/*[2]/*/*/*[2]/*");
    private final By tabOptionsSession = By.className("oxd-userdropdown-tab");
    private final By btnLogout = By.xpath("//ul[@class='oxd-dropdown-menu']/li[4]/a");

    public void waitMenu(String xpath) {
        By locator = By.xpath(xpath);
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void clickMenu(String xpath) {
        getDriver().findElement(By.xpath(xpath)).click();
    }

    public void clickAddEmployee() {
        getDriver().findElement(btnAddEmployee).click();
    }

    public void waitTitleEmployee() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleEmployee));
    }

    public void writeFirstName(String firstName) {
        getDriver().findElement(inputFirstNameNewEmployee).sendKeys(firstName);
    }

    public void writeMiddleName(String middleName) {
        getDriver().findElement(inputMiddleNameNewEmployee).sendKeys(middleName);
    }

    public void writeLastName(String lastName) {
        getDriver().findElement(inputLastNameNewEmployee).sendKeys(lastName);
    }

    public void saveEmployeer() {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", find(bntSaveNewEmployee));
        getDriver().findElement(bntSaveNewEmployee).click();
    }

    public String getId() {
        return getDriver().findElement(txtEmployeeId).getAttribute("value");
    }

    public void waitTitleNewEmployee() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleNewEmployee));
    }

    public void clickEmployeeList() {
        getDriver().findElement(btnEmployeeList).click();
    }

    public void searchNewEmployee(String id) {
        getDriver().findElement(inputIdEmployee).sendKeys(id);
    }

    public void clickSearch() {
        getDriver().findElement(btnSearchEmployee).click();
    }

    public void waitOnlyOneResult() {
        final int attempts = 10;

        for (int i = 0; i <= attempts; i++) {
            List<WebElement> elements = getDriver().findElements(txtResultId);
            if (elements.size() == 1 || elements.isEmpty()) return;
        }

        throw new TimeoutException("No se encontró un solo resultado");
    }

    public String getIdNewEmployee() {
        return getDriver().findElement(txtResultId).getText();

    }

    public void clickOptions() {
        getDriver().findElement(tabOptionsSession).click();
    }

    public void clickCloseSession() {
        getDriver().findElement(btnLogout).click();
    }
}
