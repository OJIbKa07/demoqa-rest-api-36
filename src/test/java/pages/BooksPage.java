package pages;

import api.BookStoreApiSteps;
import io.qameta.allure.Step;
import models.LoginResponse;

import static com.codeborne.selenide.Selenide.*;

public class BooksPage {

    @Step("Добавляем книгу в корзину")
    public BooksPage addBook(LoginResponse loginResponse, String isbn) {
        BookStoreApiSteps.addBookToProfile(loginResponse, isbn);
        refresh();
        return this;
    }
}
