package api;

import helpers.LoginExtension;
import io.restassured.response.Response;
import models.lobmok.DeleteBookRequestLombokModel;

import static io.restassured.RestAssured.given;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;
import static tests.TestData.isbn;

public class DeleteBookApi {

    public static void deleteBookFromProfileTest() {
        DeleteBookRequestLombokModel request = new DeleteBookRequestLombokModel(
                LoginExtension.loginResponse.getUserID(),
                isbn
        );

        Response response = given(requestSpec)
                .header("Authorization", "Bearer " + LoginExtension.loginResponse.getToken())
                .body(request)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec(204))
                .extract().response();
    }
}
