package helpers;
import io.restassured.response.Response;
import models.LoginRequest;
import models.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.BaseSpecs.requestSpec;
import static specs.BaseSpecs.responseSpec;
import static tests.TestData.*;


public class LoginExtension implements BeforeEachCallback {

    public static LoginResponse loginResponse;

    @Override
    public void beforeEach(ExtensionContext context) {

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserName(USERNAME);
        loginRequest.setPassword(PASSWORD);

        Response response = given()
                .spec(requestSpec)
                .body(loginRequest)
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpec(200))
                .extract().response();

        loginResponse = new LoginResponse();
        loginResponse.setToken(response.jsonPath().getString("token"));
        loginResponse.setUserID(response.jsonPath().getString("userId"));
        loginResponse.setExpires(response.jsonPath().getString("expires"));
        loginResponse.setStatusCode(response.getStatusCode());

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
    }
}

