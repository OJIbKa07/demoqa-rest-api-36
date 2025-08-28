package pages;

import api.AddBookApi;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class BooksPage {


    @Step("Добавляем книгу в корзину")
    public BooksPage openPage() {
        open("/books");

        return this;
    }

    @Step("Добавляем книгу в корзину")
    public BooksPage addBook(String isbn) {
        AddBookApi.addBookToProfileTest(isbn);
        refresh();

        return this;
    }

    @Step("Убираем рекламу")
    public BooksPage removeAds() {
        executeJavaScript("$('footer').remove();");
        executeJavaScript("$('#fixedban').remove();");

        return this;
    }
}
