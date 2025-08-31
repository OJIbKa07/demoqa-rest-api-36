package pages;

import api.BookStoreApiSteps;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BooksPage {

    @Step("Добавляем книгу в корзину")
    public BooksPage addBook(String isbn) {
        BookStoreApiSteps.addBookToProfile(isbn);
        refresh();

        return this;
    }
}
