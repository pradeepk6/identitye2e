package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import support.World;


/**
 * Abstract class representation of a Page in the UI.
 */
public abstract class Page {

    protected WebDriver driver;
    protected World world;

    public Page(World world) {
        this.driver = world.driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

}


