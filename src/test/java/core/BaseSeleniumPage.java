package core;

import org.openqa.selenium.WebDriver;

public class BaseSeleniumPage extends SeleniumInit {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
