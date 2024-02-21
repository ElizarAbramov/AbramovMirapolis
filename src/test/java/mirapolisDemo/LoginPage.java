package mirapolisDemo;

import core.BaseSeleniumPage;
import helpers.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Helper.randomValue;
import static helpers.TestValues.*;

public class LoginPage extends BaseSeleniumPage {



    @FindBy(xpath = "//tbody/tr[1]/td[2]/input[1]")
    private WebElement loginField;

    @FindBy(xpath = "//tbody/tr[2]/td[2]/div[1]/input[1]")
    private WebElement passwordField;
    @FindBy(id = "button_submit_login_form")
    private WebElement enterButton;
    @FindBy(xpath = "/html/body/script")
    private WebElement scriptMessage;
    @FindBy(xpath = "//button[@id='show_password']")
    private WebElement showPassButton;

    @FindBy(xpath = "//tbody/tr[1]/td[1]/a[1]/div[1]")
    private WebElement remindPasswordButton;

    @FindBy(xpath = "//div[contains(text(),'Вход в систему')]")
    private  WebElement loginPageText;

    public LoginPage() {
        driver.get("https://lmslite47vr.demo.mirapolis.ru/mira");
        PageFactory.initElements(driver,this);
    }


    public MainPage loginWithTrueLoginAndPass(){
        loginField.sendKeys(TRUE_LOG);
        passwordField.sendKeys(TRUE_PASS);
        enterButton.click();
        return new MainPage();
    }

    public String loginWithEmptyFields() throws InterruptedException {
        loginField.sendKeys("");
        passwordField.sendKeys("");
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String loginOnlySpaceButtonInput() throws InterruptedException {
        loginField.sendKeys(LOGIN_ONLY_SPACES);
        passwordField.sendKeys(PASS_ONLY_SPACES);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String loginEmptyPasswordCorrect() throws InterruptedException {
        loginField.sendKeys("");
        passwordField.sendKeys(TRUE_PASS);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String loginCorrectPasswordEmpty() throws InterruptedException {
        loginField.sendKeys(TRUE_LOG);
        passwordField.sendKeys("");
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String symbolsLoginPasswordTrue() throws InterruptedException {
        loginField.sendKeys(SYMBOLS_LOGIN);
        passwordField.sendKeys(TRUE_PASS);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String trueLoginSymbolsPassword() throws InterruptedException {
        loginField.sendKeys(TRUE_LOG);
        passwordField.sendKeys(SYMBOLS_PASS);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String trueLoginWithUpperCaseAndPassWithLowerCase() throws InterruptedException{
        loginField.sendKeys(TRUE_LOG_WITH_UPPERCASE);
        passwordField.sendKeys(TRUE_PASS_WITH_LOWERCASE);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String moreThanMaxLengthLogAndPass() throws InterruptedException {
        Helper helper = new Helper();
        String login = randomValue(300);
        String pass = randomValue(300);
        loginField.sendKeys(login);
        passwordField.sendKeys(pass);
        enterButton.click();
        Thread.sleep(1500);
        return (helper.getTextFromAlertAndClose());
    }

    public String useScriptInLogAndPassFields() throws InterruptedException {
        loginField.sendKeys(SCRIPT);
        passwordField.sendKeys(SCRIPT);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public  MainPage enteringSpacesBeforeTrueLoginAndPass() throws InterruptedException {
        loginField.sendKeys(TRUE_LOG_WITH_SPACES_BEFORE);
        passwordField.sendKeys(TRUE_PASS_WITH_SPACES_BEFORE);
        enterButton.click();
        Thread.sleep(2000);
        return new MainPage();
    }


    public  MainPage enteringSpacesAfterTrueLoginAndPass() throws InterruptedException {
        loginField.sendKeys(TRUE_LOG_WITH_SPACES_AFTER);
        passwordField.sendKeys(TRUE_PASS_WITH_SPACES_AFTER);
        enterButton.click();
        Thread.sleep(2000);
        return new MainPage();
    }

    public  String enteringSpacesInsideTrueLoginAndPass() throws InterruptedException {
        loginField.sendKeys(TRUE_LOG_WITH_SPACES_INSIDE);
        passwordField.sendKeys(TRUE_PASS_WITH_SPACES_INSIDE);
        enterButton.click();
        Thread.sleep(2000);
        return (new Helper().getTextFromAlertAndClose());
    }

    public String showPassword(){
        passwordField.sendKeys(TRUE_PASS);
        showPassButton.click();
        return (passwordField.getAttribute("type"));

    }
    public String hidePassword() {
        passwordField.sendKeys(TRUE_PASS);
        showPassButton.click();
        showPassButton.click();
        return (passwordField.getAttribute("type"));
    }

    public RemindPasswordPage clickRemindPasswordButton(){
        remindPasswordButton.click();
        return new RemindPasswordPage();
    }

    public String checkLoginPageText(){
        return loginPageText.getText();
    }

}
