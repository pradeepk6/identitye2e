package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VehicelEnquiryPage extends Page {

    @FindBy(how = How.ID, using = "Vrm")
    public WebElement regNumInput;

    @FindBy(how = How.NAME, using = "Continue")
    public WebElement continueBt;

    public VehicelEnquiryPage(WebDriver driver) {
        super(driver);
    }

    public void fillVehicleRegNum(String regNum){
        this.regNumInput.sendKeys(regNum);
    }

    public void submitVehicleEnquiryForm() {
        this.continueBt.submit();
    }

}
