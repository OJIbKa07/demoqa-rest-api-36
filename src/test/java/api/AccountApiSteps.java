package api;

import io.restassured.response.Response;
import models.LoginRequest;
import models.LoginResponse;

import static io.restassured.RestAssured.given;
import static specs.BaseSpecs.requestSpec;
import static specs.BaseSpecs.responseSpec;
import static tests.TestData.PASSWORD;
import static tests.TestData.USERNAME;

public class AccountApiSteps {

    public static LoginResponse loginWithApi() {
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

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setStatusCode(response.getStatusCode());

        if (response.statusCode() == 200) {
            loginResponse.setToken(response.jsonPath().getString("token"));
            loginResponse.setUserID(response.jsonPath().getString("userId"));
            loginResponse.setExpires(response.jsonPath().getString("expires"));
        } else {
            throw new RuntimeException("Авторизация не удалась: " + response.asString());
        }

        return loginResponse;
    }
}
