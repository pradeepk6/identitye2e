package support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Support {

    public String baseUrl;

    public void getProperties() throws IOException {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("acceptanceTest.properties");
        prop.load(inputStream);
        this.baseUrl = prop.getProperty("baseUrl");
    }

}
