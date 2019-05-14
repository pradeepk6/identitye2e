package support;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebdriverHooks {

    private static final Logger logger = LoggerFactory.getLogger(WebdriverHooks.class);

    public static Injector injector = Guice.createInjector(new DriverModule());

    @Inject
    public World world;


    @Before(order = 0) // meant to run before every other @Before tag
    public void setUp(Scenario scenario) {

        /*
        System.out.println("WebdriverHooks.setUp");
        String browser = System.getProperty("browser");
        if(browser == null || browser.isEmpty()) browser = "CHROME";
        if(driverManager != null) {
            //driverManager = DriverManagerFactory.getManager(browser);
            driverManager.startService();
        }
        if(driver != null) {
            //driver = driverManager.getDriver();
            driver.manage().window().maximize();
        }
         */
    }

    @After
    public void finish(Scenario scenario) {
        // if (scenario.isFailed()) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) world.driver);
            byte[] screenshot = scrShot.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            logger.error(somePlatformsDontSupportScreenshots.getMessage());
        }
        //}
    }

    @After(order = 999) // meant to run after every other @After tag
    public void tearDown(Scenario scenario) {
        if (world.driver != null) {
            if (scenario.getSourceTagNames().contains("@reuseDriver")) {
                world.driver.manage().deleteAllCookies();
                //driver.quit();
            } else {
                //driverManager.stopService();
                world.driver.quit();
            }
        }
    }

}
