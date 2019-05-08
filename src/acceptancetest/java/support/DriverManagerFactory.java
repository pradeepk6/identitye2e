package support;



public class DriverManagerFactory {

    public static DriverManager getManager(String type) {

        DriverManager driverManager = null;

        switch (type) {
            case "CHROME":
                driverManager = new ChromeDriverManager();
                break;
            case "FIREFOX":
                //todo
                //driverManager = new FirefoxDriverManager();
                break;
            default:

        }
        return driverManager;

    }
}