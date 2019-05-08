package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class StartPage extends Page {

    public static String url = "https://www.gov.uk/get-vehicle-information-from-dvla";

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Start now")
    public WebElement startNow;

    public StartPage(WebDriver driver) {
        super(driver);
        visitPage();
    }

    void visitPage() {
        driver.get(url);
    }

    public boolean isOnPage() {
        //return checkVisibilityofElemLocated(By.partialLinkText("Start now"));
        //return getTitle().startsWith("");
        //todo
        return true;
    }

    public void clickStartnow() {
        startNow.click();
    }
}
