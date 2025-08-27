package api;

import helpers.LoginExtension;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AuthorizationApi {

    public static void loginWithApi() {
        if (LoginExtension.loginResponse == null
                || LoginExtension.loginResponse.getStatusCode() != 200
                || LoginExtension.loginResponse.getToken() == null) {
            throw new RuntimeException("Авторизация не удалась");
        }

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("userID", LoginExtension.loginResponse.getUserID()));
        getWebDriver().manage().addCookie(new Cookie("expires", LoginExtension.loginResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", LoginExtension.loginResponse.getToken()));
    }
}
