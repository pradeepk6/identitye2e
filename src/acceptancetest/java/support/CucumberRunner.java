package support;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import utils.TestDataUtils;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = {"@verifyVehicle"},
        features = "src/acceptancetest/resources/features",
        glue = {"steps","support"},
        monochrome = true,
        plugin = {"html:build/cucumber-reports"})
public class CucumberRunner {

    /**
     * gets test data and appends to feature file DataTable
     */
    /*
    @BeforeClass
    public static void testDataSetUp(){
       // TestDataUtils.testDataSetUp();
    }
     */

    /**
     * remvoves test data from feature file DataTable
     */
    /*
    @AfterClass
    public static void testDataTearDown(){
       // TestDataUtils.testDataTearDown();
    }
     */
}


