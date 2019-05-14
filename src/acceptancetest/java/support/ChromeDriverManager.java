package support;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeDriverManager extends DriverManager {

    private static final Logger log = LoggerFactory.getLogger(ChromeDriverManager.class);

    private ChromeDriverService chService;

    @Override
    public void startService() {
        if (null == chService) {
            log.debug("ChromeDriverManager.startService ££££££££££££££££££££££££");
            checkSysPropertyForWindows(); // make sure sys property for windows ends with .exe
            try {
                chService = new ChromeDriverService.Builder()
                        // .usingDriverExecutable(new File("drivers/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                log.error("Error starting driver service." + e);
            }
        }
    }

    // make sure sys property for windows ends with .exe
    private void checkSysPropertyForWindows() {
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            String driverSysProperty = System.getProperty("webdriver.chrome.driver");
            if (!driverSysProperty.endsWith(".exe")) {
                System.setProperty("webdriver.chrome.driver", driverSysProperty + ".exe");
            }
        }
    }


    @Override
    public void stopService() {
        if (null != chService && chService.isRunning())
            chService.stop();

    }

    @Override
    public void createDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        if (System.getProperty("headless").equalsIgnoreCase("true")) {
            chromeOptions.addArguments("--headless");
        }
        driver = new RemoteWebDriver(chService.getUrl(), chromeOptions);
    }
}
