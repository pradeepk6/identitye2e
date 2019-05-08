package steps;

import cucumber.api.java.After;
import support.WebdriverHooks;
import pageobjects.ConfirmVehiclePage;
import pageobjects.StartPage;
import pageobjects.VehicelEnquiryPage;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static junit.framework.TestCase.assertEquals;

public class VerifyVehicleSteps {

    WebDriver driver;
    StartPage startPage;
    VehicelEnquiryPage vehicelEnquiryPage;
    ConfirmVehiclePage confirmVehiclePage;
    String featureFilePath = "src/acceptancetest/resources/features/VerifyVehicle.feature";


    @Before
    public void setUp() {
        //System.out.println("VerifyVehicleSteps.setUp");
        driver = WebdriverHooks.driver;
    }

    @Given("I go to DVLA get-vehicle-details page")
    public void i_go_to_DVLA_get_vehicle_details_start_page() {
        startPage = new StartPage(driver);
    }

    @And("I start the get-vehicle-details journey")
    public void i_start_the_get_vehicle_details_journey() {
        startPage.clickStartnow();
    }

    @When("^I submit vehicle reg number (.*)$")
    public void i_submit_vehicle_reg_number(String regNum) {
        vehicelEnquiryPage = new VehicelEnquiryPage(driver);
        vehicelEnquiryPage.fillVehicleRegNum(regNum);
        vehicelEnquiryPage.submitVehicleEnquiryForm();
    }

    @Then("^I should be able to confirm vehicle details: regNum (.*),make (.*) and colour (.*)$")
    public void should_be_able_to_confirm_vehicle_details(String regNum, String make, String colour) {
        confirmVehiclePage = new ConfirmVehiclePage(driver);
        assertEquals(regNum.toLowerCase(), confirmVehiclePage.getRegNum().toLowerCase());
        assertEquals(make.toLowerCase(), confirmVehiclePage.getMake().toLowerCase());
        assertEquals(colour.toLowerCase(), confirmVehiclePage.getColour().toLowerCase());
    }

    @After
    public void tearDown() {

        //TestDataUtils.removeTestDataFromFeatureFile(featureFilePath);
    }

}
