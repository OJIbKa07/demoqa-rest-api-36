package helpers;
import api.AccountApiSteps;
import models.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        LoginResponse loginResponse = AccountApiSteps.loginWithApi();

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserID()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
    }
}

