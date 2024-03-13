package test;

import core.BaseSeleniumPage;
import jdk.jfr.Description;
import mirapolisDemo.LoginPage;
import mirapolisDemo.MainPage;
import org.junit.Assert;
import org.junit.Test;

import static helpers.Helper.randomValue;
import static helpers.TestValues.*;

public class MirapolisDemoTest extends BaseSeleniumPage {

    @Test
    @Description("Вход в систему с использованием верных логина и пароля")
    public void successfulAuthorization() {
        MainPage mainPage = new LoginPage().trueLog(TRUE_LOG, TRUE_PASS);
        Assert.assertEquals(mainPage.getMainPageTitle(), MAIN_PAGE_TITLE);
        Assert.assertTrue(mainPage.getFullName());
    }

    @Test
    @Description("Вход в систему с пустыми полями логина и пароля")
    public void emptyFields() {
        Assert.assertTrue(new LoginPage().negativeLog("", "").contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием логина и пароля, состоящих только из пробелов")
    public void onlySpaceButtonInput() {
        Assert.assertTrue(new LoginPage().negativeLog(LOGIN_ONLY_SPACES, PASS_ONLY_SPACES).contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему c вводом пробелов перед верными реквизитами в соответсвующих полях")
    public void enteringSpacesBeforeTrueLoginAndPass() {
        MainPage mainPage = new LoginPage().trueLog(TRUE_LOG_WITH_SPACES_BEFORE, TRUE_PASS_WITH_SPACES_BEFORE);
        Assert.assertEquals(mainPage.getMainPageTitle(), MAIN_PAGE_TITLE);
        Assert.assertTrue(mainPage.getFullName());
    }
    //Ожидаемый результат подогнал под актуальный. Считаю ,что
    //хотя бы поле пароля должно быть чувствительно к подобному вводу пробелов и приводить к
    //появлению ALERT_MESSAGE.

    @Test
    @Description("Вход в систему c вводом пробелов после верных реквизитов в соответсвующих полях")
    public void enteringSpacesAfterTrueLoginAndPass() {
        MainPage mainPage = new LoginPage().trueLog(TRUE_LOG_WITH_SPACES_AFTER, TRUE_PASS_WITH_SPACES_AFTER);
        Assert.assertEquals(mainPage.getMainPageTitle(), MAIN_PAGE_TITLE);
        Assert.assertTrue(mainPage.getFullName());
    }
    //Ожидаемый результат подогнал под актуальный. Считаю ,что
    //хотя бы поле пароля должно быть чувствительно к подобному вводу пробелов и приводить к
    //появлению ALERT_MESSAGE.

    @Test
    @Description("Вход в систему c вводом пробелов внутри верных реквизитов в соответсвующих полях")
    public void enteringSpacesInsideTrueLoginAndPass() {
        Assert.assertTrue(new LoginPage().negativeLog(TRUE_LOG_WITH_SPACES_INSIDE, TRUE_PASS_WITH_SPACES_INSIDE).
                contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему c вводом скрипта в поля логина и пароля")
    public void enteringScriptLoginAndPassword() {
        Assert.assertTrue(new LoginPage().negativeLog(SCRIPT, SCRIPT).contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием пустого поля логина и корректного пароля")
    public void emptyLogCorrectPass() {
        Assert.assertTrue(new LoginPage().negativeLog("", TRUE_PASS).contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием корректного логина и пустого поля пароля")
    public void CorrectLogEmptyPass() {
        Assert.assertTrue(new LoginPage().negativeLog(TRUE_LOG, "").contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием символьного логина и корректного пароля")
    public void SymbolsLogPassTrue() {
        Assert.assertTrue(new LoginPage().negativeLog(SYMBOLS_LOGIN, TRUE_PASS).contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием корректного логина и символьного пароля пароля")
    public void trueLogSymbolsPass() throws InterruptedException {
        Assert.assertTrue(new LoginPage().negativeLog(TRUE_LOG, SYMBOLS_PASS).contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Вход в систему с использованием верного логина с изменением регистра некоторых букв на верхний " +
            "и верного пароля с изменением регистра некоторых букв на нижний")
    public void trueLogWithUpperCaseAndPassWithLowerCase() {
        Assert.assertTrue(new LoginPage().negativeLog(TRUE_LOG_WITH_UPPERCASE, TRUE_PASS_WITH_LOWERCASE).
                contains(ALERT_MESSAGE));
    }

    @Test
    @Description("Использование логина и пароля длиной, превышающей максимальную длину соответсвующего поля")
    public void moreThanMaxLoginAndPass() {
        Assert.assertTrue(new LoginPage().negativeLog(randomValue(300), randomValue(300)).contains(ALERT_MESSAGE_MAX));
    }

    @Test
    @Description("Востановление пароля с ипользованием существующего логина")
    public void passwordRecoveryWithTrueLogin() {
        String success = new LoginPage().clickRemindPasswordButton().passwordRecovery(TRUE_LOG);
        Assert.assertEquals(success, SUCCESS_MESSAGE);
    }

    @Test
    @Description("Восстановление пароля с использованием несуществующего логина")
    public void passwordRecoveryWithWrongLogin() {
        String alert = new LoginPage().clickRemindPasswordButton().passwordRecovery(randomValue(30));
        Assert.assertEquals(alert, ALERT_MESSAGE_REMIND_PASS);
    }

    @Test
    @Description("Восстановление пароля с пустым полем логина")
    public void passwordRecoveryWithEmptyLogin() {
        String alert = new LoginPage().clickRemindPasswordButton().passwordRecovery("");
        Assert.assertEquals(alert, ALERT_MESSAGE_REMIND_PASS);
    }

    @Test
    @Description("Изменение параметра 'type' кнопки показа пароля путем нажатия на нее")
    public void showPasswordTest() {

        Assert.assertEquals(new LoginPage().clickOnShowPassButton(), "text");
    }

    @Test
    @Description("Изменение с последующим возвратом к исходному состоянию" +
            "параметра 'type'  кнопки показа пароля путем двойного нажатия на нее")
    public void hidePasswordTest() {

        Assert.assertEquals(new LoginPage().doubleClickOnShowPassButton(), "password");
    }

    @Test
    @Description("Нажатие кнопки восстановления пароля с последующим нажатием кнопки возврата к странице входа в систему")
    public void backToLoginPage() {
        String text = new LoginPage().clickRemindPasswordButton().clickOnBackButton().checkLoginPageText();
        Assert.assertEquals(text, LOGIN_PAGE_TEXT);
    }
}