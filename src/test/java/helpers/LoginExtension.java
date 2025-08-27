package helpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.LoginRequest;
import models.lobmok.LoginResponseLombokModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static specs.ResponseSpec.responseSpec;
import static specs.RequestSpec.requestSpec;


public class LoginExtension implements BeforeEachCallback {

    public static LoginResponseLombokModel loginResponse;

    @Override
    public void beforeEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            WithLogin annotation = method.getAnnotation(WithLogin.class);
            if (annotation != null) {
                String username = annotation.username();
                String password = annotation.password();

                // DTO для запроса (лучше чем строка)
                LoginRequest loginRequest = new LoginRequest(username, password);

                Response response = io.restassured.RestAssured
                        .given(requestSpec)
                        .body(loginRequest)
                        .post("/Account/v1/Login")
                        .then()
                        .spec(responseSpec(200))
                        .extract().response();

                loginResponse = new LoginResponseLombokModel();
                loginResponse.setToken(response.jsonPath().getString("token"));
                loginResponse.setUserID(response.jsonPath().getString("userId"));
                loginResponse.setExpires(response.jsonPath().getString("expires"));
                loginResponse.setStatusCode(response.getStatusCode());
            }
        });
    }
}

