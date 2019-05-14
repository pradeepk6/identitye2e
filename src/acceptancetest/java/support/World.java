package support;


import cucumber.runtime.java.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;

@ScenarioScoped
public class World {
    public WebDriver driver = WebdriverHooks.injector.getInstance(DriverManager.class).getDriver();
    public Support support = new Support();
}

