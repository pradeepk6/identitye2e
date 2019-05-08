package support;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeDriverManager extends DriverManager {

    private ChromeDriverService chService;

    @Override
    public void startService() {
        if (null == chService) {
            // make sure there is .exe for windows OS
            if(System.getProperty("os.name").toLowerCase().startsWith("win")) {
                String driverSysProperty = System.getProperty("webdriver.chrome.driver");
                if(!driverSysProperty.endsWith(".exe")) {
                    System.setProperty("webdriver.chrome.driver", driverSysProperty + ".exe");
                }
            }
            try {
                chService = new ChromeDriverService.Builder()
                       // .usingDriverExecutable(new File("drivers/chromedriver.exe"))
                        .usingAnyFreePort()
                        .build();
                chService.start();
            } catch (Exception e) {
                e.printStackTrace();
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
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chService, capabilities);
    }

}
