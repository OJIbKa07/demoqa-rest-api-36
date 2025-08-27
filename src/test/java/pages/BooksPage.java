package pages;

import api.AddBookApi;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.TestData.isbn;


public class BooksPage {

    @Step("Добавляем книгу в корзину")
    public BooksPage addBook() {
        open("/books");

        AddBookApi.addBookToProfileTest();
        return this;
    }
}
