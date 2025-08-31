package api;

import helpers.LoginExtension;
import io.restassured.response.Response;
import models.AddBookRequest;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;

public class AddBookApi {

    public static void addBookToProfileTest(String isbn) {
        AddBookRequest request = new AddBookRequest(
                LoginExtension.loginResponse.getUserID(),
                Collections.singletonList(new AddBookRequest.Isbn(isbn))
        );

        Response response = given(requestSpec)
                .header("Authorization", "Bearer " + LoginExtension.loginResponse.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec(201))
                .extract().response();

        String actualIsbn = response.jsonPath().getString("books[0].isbn");
        assertEquals(isbn, actualIsbn, "ISBN книги не совпадает");
    }
}
