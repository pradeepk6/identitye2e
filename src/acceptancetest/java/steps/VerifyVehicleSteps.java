package steps;

import com.google.inject.Inject;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.ConfirmVehiclePage;
import pageobjects.StartPage;
import pageobjects.VehicelEnquiryPage;
import support.World;

import static junit.framework.TestCase.assertEquals;

public class VerifyVehicleSteps {

    @Inject
    World world;
    @Inject
    StartPage startPage;
    @Inject
    VehicelEnquiryPage vehicelEnquiryPage;
    @Inject
    ConfirmVehiclePage confirmVehiclePage;

    @Before
    public void setUp() {
    }

    @Given("I go to DVLA get-vehicle-details page")
    public void i_go_to_DVLA_get_vehicle_details_start_page() {
        startPage.visitPage();
    }

    @And("I start the get-vehicle-details journey")
    public void i_start_the_get_vehicle_details_journey() {
        startPage.clickStartnow();
    }

    @When("^I submit vehicle reg number (.*)$")
    public void i_submit_vehicle_reg_number(String regNum) {
        vehicelEnquiryPage.fillVehicleRegNum(regNum);
        vehicelEnquiryPage.submitVehicleEnquiryForm();
    }

    @Then("^I should be able to confirm vehicle details: regNum (.*),make (.*) and colour (.*)$")
    public void should_be_able_to_confirm_vehicle_details(String regNum, String make, String colour) {
        assertEquals(regNum.toLowerCase(), confirmVehiclePage.getRegNum().toLowerCase());
        assertEquals(make.toLowerCase(), confirmVehiclePage.getMake().toLowerCase());
        assertEquals(colour.toLowerCase(), confirmVehiclePage.getColour().toLowerCase());
    }

    @After
    public void tearDown() {

        //TestDataUtils.removeTestDataFromFeatureFile(featureFilePath);
    }

}
