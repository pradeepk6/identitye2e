package pageobjects;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import support.World;

public class VehicelEnquiryPage extends Page {

    @FindBy(how = How.ID, using = "Vrm")
    public WebElement regNumInput;

    @FindBy(how = How.NAME, using = "Continue")
    public WebElement continueBt;

    @Inject
    public VehicelEnquiryPage(World world) {
        super(world);
    }

    public void fillVehicleRegNum(String regNum){
        this.regNumInput.sendKeys(regNum);
    }

    public void submitVehicleEnquiryForm() {
        this.continueBt.submit();
    }

}
