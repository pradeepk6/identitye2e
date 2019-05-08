package support;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@verifyVehicle"},
        features = "src/acceptancetest/resources/features",
        glue = {"steps","support"},
        monochrome = true,
        plugin = {"html:build/cucumber-reports"})
public class CucumberRunner {
}


