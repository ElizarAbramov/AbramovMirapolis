package mirapolisDemo;

import core.BaseSeleniumPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static helpers.Helper.randomValue;
import static helpers.TestValues.TRUE_LOG;

public class RemindPasswordPage extends BaseSeleniumPage {
    @FindBy(xpath = "//button[contains(text(),'Отправить')]")
    private WebElement sendButton;
    @FindBy(xpath = "//div[contains(text(),'Назад к входу в систему')]")
    private WebElement backToLoginPageButton;
    @FindBy(xpath = "//tbody/tr[1]/td[2]/input[1]")
    private WebElement loginOrMailField;
    @FindBy(xpath = "//div[contains(text(),'Пользователь с таким именем не найден.')]")
    private WebElement alertMessage;

    @FindBy(xpath = "//div[@class='success']")
    private WebElement successMessage;

    public RemindPasswordPage() {
        PageFactory.initElements(driver, this);
    }


    public String passwordRecoveryWithTrueLogin() {
        loginOrMailField.sendKeys(TRUE_LOG);
        sendButton.click();
        return successMessage.getText();
    }

    public String passwordRecoveryWithWrongLogin() {
        String login = randomValue(50);
        loginOrMailField.sendKeys(login);
        sendButton.click();
        return alertMessage.getText();
    }

    public String passwordRecoveryWithEmptyLogin() {
        loginOrMailField.sendKeys("");
        sendButton.click();
        return alertMessage.getText();
    }

    public LoginPage clickOnBackButton() {
        backToLoginPageButton.click();
        return new LoginPage();
    }
}
