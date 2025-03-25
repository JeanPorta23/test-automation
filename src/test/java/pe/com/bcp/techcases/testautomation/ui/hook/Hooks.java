package pe.com.bcp.techcases.testautomation.ui.hook;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import net.thucydides.core.pages.PageObject;
import pe.com.bcp.techcases.testautomation.ui.actors.DefaultUser;

import java.lang.reflect.InvocationTargetException;

public class Hooks {

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String blankToEmptyString(String cell) {
        return cell;
    }

    @ParameterType(".*")
    public DefaultUser user(String user) {
        try {
            return (DefaultUser) Class.forName("pe.com.bcp.techcases.testautomation.ui.actors." + user)
                    .getConstructor().newInstance();
        } catch (ClassNotFoundException
                 | NoSuchMethodException
                 | IllegalAccessException
                 | InstantiationException
                 | IllegalArgumentException
                 | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterType(".*")
    public Class<PageObject> page(String pageName) {
        try {
            if (pageName.contains("Orange")) pageName = "orange." + pageName;
            else pageName = "sauce." + pageName;
            return (Class<PageObject>) Class.forName("pe.com.bcp.techcases.testautomation.ui.pages." + pageName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
