package pageobjects;

import com.google.inject.Inject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import support.World;

public class StartPage extends Page {

    public static String url = "https://www.gov.uk/get-vehicle-information-from-dvla";

    @FindBy(how = How.PARTIAL_LINK_TEXT, using = "Start now")
    public WebElement startNow;

    @Inject
    public StartPage(World world) {
        super(world);
        visitPage();
    }

    public void visitPage() {
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
