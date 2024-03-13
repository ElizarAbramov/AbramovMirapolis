package mirapolisDemo;

import core.BaseSeleniumPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemindPasswordPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[@class='mira-page-forgot-password-button']")
    private WebElement sendButton;
    @FindBy(xpath = "//a[@class='mira-page-forgot-password-link'] ")
    private WebElement backToLoginPageButton;
    @FindBy(xpath = "//input[@class='mira-widget-login-input mira-default-login-page-text-input']")
    private WebElement loginOrMailField;
    @FindBy(xpath = "//div[@class='alert']")
    private WebElement alertMessage;

    @FindBy(xpath = "//div[@class='success']")
    private WebElement successMessage;

    public RemindPasswordPage() {
        PageFactory.initElements(driver, this);
    }


    public String passwordRecovery(String login) {
        String e;
        loginOrMailField.sendKeys(login);
        sendButton.click();
        try {
            e = successMessage.getText();
        } catch (NoSuchElementException exception) {
            e = alertMessage.getText();
        }
        return e;

    }


    public LoginPage clickOnBackButton() {
        backToLoginPageButton.click();
        return new LoginPage();
    }
}
