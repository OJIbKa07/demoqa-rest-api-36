package pages;

import api.AddBookApi;
import io.qameta.allure.Step;


import static com.codeborne.selenide.Selenide.open;


public class BooksPage {

    @Step("Добавляем книгу в корзину")
    public BooksPage addBook(String isbn) {
        open("/books");

        AddBookApi.addBookToProfileTest(isbn);
        return this;
    }
}
