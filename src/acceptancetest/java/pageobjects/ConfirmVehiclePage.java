package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ConfirmVehiclePage extends Page {

    @FindBy(how = How.CLASS_NAME, using = "reg-mark")
    public WebElement regNum;

    @FindBy(how = How.XPATH, using = "//span[.='Make']/following-sibling::span[1]")
    public WebElement make;

    @FindBy(how = How.XPATH, using = "//span[.='Colour']/following-sibling::span[1]")
    public WebElement colour;

    public ConfirmVehiclePage(WebDriver driver) {
        super(driver);
    }

    public String getRegNum(){
        return this.regNum.getText();
    }
    public String getMake(){
        return this.make.getText();
    }
    public String getColour(){
        return this.colour.getText();
    }

}
