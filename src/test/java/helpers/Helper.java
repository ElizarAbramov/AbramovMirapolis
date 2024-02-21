package helpers;


import core.BaseSeleniumPage;

import java.util.Random;

public class Helper extends BaseSeleniumPage {

    public String getTextFromAlertAndClose() {
        String altmsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return altmsg;
    }

    public static String randomValue(int length) {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

}
