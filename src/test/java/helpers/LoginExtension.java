package helpers;
import io.restassured.response.Response;
import models.LoginRequest;
import models.LoginResponse;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

import static io.restassured.RestAssured.given;
import static java.sql.DriverManager.getDriver;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;
import static tests.TestData.*;


public class LoginExtension implements BeforeEachCallback {

    public static LoginResponse loginResponse;

    @Override
    public void beforeEach(ExtensionContext context) {

        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);

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

        WebDriver driver = getDriver();
        driver.manage().addCookie(new Cookie("token", loginResponse.getToken()));
    }
}

