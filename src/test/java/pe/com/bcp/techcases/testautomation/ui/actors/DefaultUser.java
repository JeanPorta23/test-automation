package pe.com.bcp.techcases.testautomation.ui.actors;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

public class DefaultUser extends ScenarioSteps {


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
        PageContainer.setCurrentPage(page);
        return page;
    }

    @Step
    public PageObject goToPage(Class<PageObject> pageClass, String method, String[] params) {
        PageObject page = getPages().get(pageClass);
        page.open(method, params);
        PageContainer.setCurrentPage(page);
        return page;
    }

    @Step
    public void clicksOn(String elementName) throws IllegalAccessException {
        ((JavascriptExecutor)getDriver()).executeScript(
                "arguments[0].scrollIntoView();arguments[0].click()",
                getReflexiveElement(elementName));
    }

    @Step
    public void fillsField(String elementName, String value) throws IllegalAccessException {
        ((JavascriptExecutor)getDriver()).executeScript(
                "arguments[0].scrollIntoView();arguments[0].setAttribute('value', arguments[1])",
                getReflexiveElement(elementName), value);
    }

    @Step
    public boolean isAbleToSee(String elementName) throws IllegalAccessException {
        try{
            return getReflexiveElement(elementName).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private WebElement getReflexiveElement(String elementName) throws IllegalAccessException {
        try {
            Field field = PageContainer.getCurrentPage()
                    .getClass().getDeclaredField(elementName);
            field.setAccessible(true);
            return (WebElement)field.get(
                    PageContainer.getCurrentPage());
        } catch(NoSuchFieldException e) {
            try {
                return getDriver().findElement(
                        By.xpath("//*[ contains (text(), '"+elementName+"')] | //*[@*='"+elementName+"']"));
            } catch (NoSuchElementException ee) {
                throw new NoSuchElementException(
                        "Cannot find element with text: "+elementName+".\nPlease use specific locator to find it", ee);
            }

        }
    }

}
