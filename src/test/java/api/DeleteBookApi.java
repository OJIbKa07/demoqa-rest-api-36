package api;

import helpers.LoginExtension;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;
import static tests.TestData.isbn;

public class DeleteBookApi {

    public static void deleteBookFromProfileTest() {
        String requestBody = String.format(
                "{ \"isbn\": \"%s\", \"userId\": \"%s\" }",
                isbn,
                LoginExtension.loginResponse.getUserID() // ✅ берём из Lombok модели
        );

        Response response = given(requestSpec)
                .header("Authorization", "Bearer " + LoginExtension.loginResponse.getToken())
                .body(requestBody)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec(204))
                .extract().response();
    }
}
