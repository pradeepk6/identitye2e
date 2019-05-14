package support;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DriverManager {
    private static final Logger log = LoggerFactory.getLogger(DriverManager.class);

    protected RemoteWebDriver driver;
    protected abstract void startService();
    protected abstract void stopService();
    protected abstract void createDriver();

    public void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }

    public RemoteWebDriver getDriver() {
        if (null == driver || driver.getSessionId() == null) {
            startService();
            createDriver();
        }
        return driver;
    }
}
