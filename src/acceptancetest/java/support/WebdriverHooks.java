package support;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebdriverHooks {

    private static final Logger logger = LoggerFactory.getLogger(WebdriverHooks.class);

    public static WebDriver driver;
    DriverManager driverManager;

    @Before(order = 0) // meant to run before every other @Before tag
    public void setUp(Scenario scenario) {
       // driver = DriverFactory.webDriver();
        String browser = System.getProperty("browser");
        if(browser == null || browser.isEmpty()) browser = "CHROME";
        driverManager = DriverManagerFactory.getManager(browser);
        driver = driverManager.getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void finish(Scenario scenario) {
        // if (scenario.isFailed()) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            byte[] screenshot = scrShot.getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            logger.error(somePlatformsDontSupportScreenshots.getMessage());
        }
        //}
    }

    @After(order = 999) // meant to run after every other @After tag
    public void tearDown(Scenario scenario) {
        if (driver != null) {
            driver.quit();
        }
    }
}
