package api;

import io.restassured.response.Response;
import models.AddBookRequest;
import models.DeleteBookRequest;
import models.LoginResponse;

import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.BaseSpecs.requestSpec;
import static specs.BaseSpecs.responseSpec;

public class BookStoreApiSteps {

    public static void addBookToProfile(LoginResponse loginResponse, String isbn) {
        AddBookRequest request = new AddBookRequest(
                loginResponse.getUserID(),
                Collections.singletonList(new AddBookRequest.Isbn(isbn))
        );

        Response response = given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpec(201))
                .extract().response();

        String actualIsbn = response.jsonPath().getString("books[0].isbn");
        assertEquals(isbn, actualIsbn, "ISBN книги не совпадает");
    }

    public static void deleteBookFromProfile(LoginResponse loginResponse, String isbn) {
        DeleteBookRequest request = new DeleteBookRequest(
                loginResponse.getUserID(),
                isbn
        );

        given(requestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(request)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(responseSpec(204));
    }
}
