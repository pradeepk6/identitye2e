package support;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import org.openqa.selenium.WebDriver;

public class DriverModule extends AbstractModule {

    @Override
    protected void configure() {

        String browser = System.getProperty("browser").toUpperCase();
        switch (browser) {
            case "CHROME":
                bind(DriverManager.class).to(ChromeDriverManager.class).in(Scopes.SINGLETON);
                break;
            case "FIREFOX":
                //todo
                break;
            default:
                throw new IllegalArgumentException("Unknown browser name : " + browser);
        }
    }

    @Provides
    public WebDriver getDriver(DriverManager driverManager) {
        return driverManager.getDriver();
    }

}
