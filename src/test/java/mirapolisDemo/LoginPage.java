package mirapolisDemo;

import core.BaseSeleniumPage;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage extends BaseSeleniumPage {


    @FindBy(xpath = "//input[@name='user']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;
    @FindBy(id = "button_submit_login_form")
    private WebElement enterButton;
    @FindBy(xpath = "/html/body/script")
    private WebElement scriptMessage;
    @FindBy(xpath = "//button[@id='show_password']")
    private WebElement showPassButton;

    @FindBy(xpath = "//a[@class='mira-default-login-page-link']")
    private WebElement remindPasswordButton;

    @FindBy(xpath = "//div[contains(text(),'Вход в систему')]")
    private WebElement loginPageText;

    public LoginPage() {
        driver.get("https://lmslite47vr.demo.mirapolis.ru/mira");
        PageFactory.initElements(driver, this);
    }

    public static String getTextFromAlertAndClose() {

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String altmsg = alert.getText();
        alert.accept();
        return altmsg;
    }

    public void stepsToLogin(String login, String password) {
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        enterButton.click();
    }

    public MainPage trueLog(String login, String password) {
        stepsToLogin(login, password);
        return new MainPage();
    }

    public String negativeLog(String login, String password) {
        stepsToLogin(login, password);
        return (getTextFromAlertAndClose());
    }

    public String clickOnShowPassButton() {
        showPassButton.click();
        return (passwordField.getAttribute("type"));

    }

    public String doubleClickOnShowPassButton() {
        showPassButton.click();
        showPassButton.click();
        return (passwordField.getAttribute("type"));
    }

    public RemindPasswordPage clickRemindPasswordButton() {
        remindPasswordButton.click();
        return new RemindPasswordPage();
    }

    public String checkLoginPageText() {
        return loginPageText.getText();
    }

}
