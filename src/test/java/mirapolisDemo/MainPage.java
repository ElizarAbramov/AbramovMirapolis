package mirapolisDemo;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends BaseSeleniumPage {


    @FindBy(xpath = "//span[contains(text(),'Главная страница')]")
    private WebElement mainPageTitle;
    @FindBy(xpath = "//div[@class = 'avatar-full-name']")
    private WebElement fullName;

    public MainPage() {
        PageFactory.initElements(driver,this);
    }

    public String getMainPageTitle(){
       return mainPageTitle.getText();
    }

    public boolean  getFullName(){
        return fullName.isDisplayed();
    }
}
