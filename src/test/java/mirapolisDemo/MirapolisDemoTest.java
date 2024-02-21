package mirapolisDemo;

import core.BaseSeleniumPage;
import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;

import static helpers.TestValues.*;

public class MirapolisDemoTest extends BaseSeleniumPage {

    @Test
    @Description("Вход в систему с использованием верных логина и пароля")
    public void successfulAuthorization() {
        MainPage mainPage = new LoginPage().loginWithTrueLoginAndPass();
        Assert.assertEquals(mainPage.getMainPageTitle(), MAIN_PAGE_TITLE);
        Assert.assertTrue(mainPage.getFullName());
    }

    @Test
    @Description("Вход в систему с пустыми полями логина и пароля")
    public void emptyFields() throws InterruptedException {
        Assert.assertTrue(new LoginPage().loginWithEmptyFields().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием логина и пароля, состоящих только из пробелов")
    public void onlySpaceButtonInput() throws InterruptedException {
        Assert.assertTrue(new LoginPage().loginOnlySpaceButtonInput().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему c вводом пробелов перед верными реквизитами в соответсвующих полях")
    public void enteringSpacesBeforeTrueLoginAndPass() throws InterruptedException {
        MainPage mainPage = new LoginPage().enteringSpacesBeforeTrueLoginAndPass();
        Assert.assertEquals(mainPage.getMainPageTitle(), MAIN_PAGE_TITLE);
        Assert.assertTrue(mainPage.getFullName());
    }
    //Ожидаемый результат подогнал под актуальный. Считаю ,что
    //хотя бы поле пароля должно быть чувствительно к подобному вводу пробелов и приводить к
    //появлению ALERT_MESSAGE.

    @Test
    @Description("Вход в систему c вводом пробелов после верных реквизитов в соответсвующих полях")
    public void enteringSpacesAfterTrueLoginAndPass() throws InterruptedException {
        MainPage mainPage = new LoginPage().enteringSpacesAfterTrueLoginAndPass();
        Assert.assertEquals(mainPage.getMainPageTitle(), MAIN_PAGE_TITLE);
        Assert.assertTrue(mainPage.getFullName());
    }
    //Ожидаемый результат подогнал под актуальный. Считаю ,что
    //хотя бы поле пароля должно быть чувствительно к подобному вводу пробелов и приводить к
    //появлению ALERT_MESSAGE.

    @Test
    @Description("Вход в систему c вводом пробелов внутри верных реквизитов в соответсвующих полях")
    public void enteringSpacesInsideTrueLoginAndPass() throws InterruptedException {
        Assert.assertTrue(new LoginPage().enteringSpacesInsideTrueLoginAndPass().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему c вводом скрипта в поля логина и пароля")
    public void enteringScriptLoginAndPassword() throws InterruptedException {
        Assert.assertTrue(new LoginPage().useScriptInLogAndPassFields().contains(ALERT_MESSAGE));
    }



    @Test
    @Description("Вход в систему с использованием пустого поля логина и корректного пароля")
    public void emptyLogCorrectPass() throws InterruptedException {
        Assert.assertTrue(new LoginPage().loginEmptyPasswordCorrect().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием корректного логина и пустого поля пароля")
    public void CorrectLogEmptyPass() throws InterruptedException {
        Assert.assertTrue(new LoginPage().loginCorrectPasswordEmpty().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием символьного логина и корректного пароля")
    public void SymbolsLogPassTrue() throws InterruptedException {
        Assert.assertTrue(new LoginPage().symbolsLoginPasswordTrue().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием корректного логина и символьного пароля пароля")
    public void trueLogSymbolsPass() throws InterruptedException {
        Assert.assertTrue(new LoginPage().trueLoginSymbolsPassword().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием верного логина с изменением регистра некоторых букв на верхний " +
            "и верного пароля с изменением регистра некоторых букв на нижний")
    public void trueLogWithUpperCaseAndPassWithLowerCase() throws InterruptedException {
        Assert.assertTrue(new LoginPage().trueLoginWithUpperCaseAndPassWithLowerCase().contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Использование логина и пароля длиной, превышающей максимальную длину соответсвующего поля")
    public void moreThanMaxLoginAndPass() throws InterruptedException {
        Assert.assertEquals(new LoginPage().moreThanMaxLengthLogAndPass(), ALERT_MESSAGE_MAX);
    }

    @Test
    @Description("Востановление пароля с ипользованием существующего логина")
    public void passwordRecoveryWithTrueLogin() {
        String success = new LoginPage().clickRemindPasswordButton().passwordRecoveryWithTrueLogin();
        Assert.assertEquals(success, SUCCESS_MESSAGE);
    }

    @Test
    @Description("Восстановление пароля с использованием несуществующего логина")
    public void passwordRecoveryWithWrongLogin() {
        String alert = new LoginPage().clickRemindPasswordButton().passwordRecoveryWithWrongLogin();
        Assert.assertEquals(alert, ALERT_MESSAGE_REMIND_PASS);
    }

    @Test
    @Description("Восстановление пароля с пустым полем логина")
    public void passwordRecoveryWithEmptyLogin() {
        String alert = new LoginPage().clickRemindPasswordButton().passwordRecoveryWithEmptyLogin();
        Assert.assertEquals(alert, ALERT_MESSAGE_REMIND_PASS);

    }

    @Test
    @Description("Нажатие кнопки демонстрации пароля")
    public void showPasswordTest() {
        Assert.assertEquals(new LoginPage().showPassword(), "text");
    }

    @Test
    @Description("Нажатие кнопки демонстрации пароля с последующим его сокрытием")
    public void hidePasswordTest() {
        Assert.assertEquals(new LoginPage().hidePassword(), "password");
    }

    @Test
    @Description("Нажатие кнопки восстановления пароля с последующим нажатием кнопки возврата к странице входа в систему")
    public void backToLoginPage() {
        String text = new LoginPage().clickRemindPasswordButton().clickOnBackButton().checkLoginPageText();
        Assert.assertEquals(text, LOGIN_PAGE_TEXT);
    }
}
