package api;

import helpers.LoginExtension;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RequestSpec.requestSpec;
import static specs.ResponseSpec.responseSpec;
import static tests.TestData.isbn;

public class AddBookApi {

    public static void addBookToProfileTest() {
        String requestBody = String.format(
                "{ \"userId\": \"%s\", \"collectionOfIsbns\": [ { \"isbn\": \"%s\" } ] }",
                LoginExtension.loginResponse.getUserID(),
                isbn
        );

        Response response = given(requestSpec)
                .header("Authorization", "Bearer " + LoginExtension.loginResponse.getToken())
                .body(requestBody)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec(201))
                .extract().response();

        String actualIsbn = response.jsonPath().getString("books[0].isbn");
        assertEquals(isbn, actualIsbn, "ISBN книги не совпадает");
    }
}
