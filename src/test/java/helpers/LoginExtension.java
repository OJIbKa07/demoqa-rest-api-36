package helpers;
import api.AccountApiSteps;
import models.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class LoginExtension implements BeforeEachCallback {

    public static LoginResponse loginResponse;

    @Override
    public void beforeEach(ExtensionContext context) {
        loginResponse = AccountApiSteps.loginWithApi();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserID()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        refresh();
    }
}

