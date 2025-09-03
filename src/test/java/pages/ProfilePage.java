package pages;

import api.BookStoreApiSteps;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import models.LoginResponse;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.USERNAME;

public class ProfilePage {

    private SelenideElement
            usernameLabel = $("#userName-value"),
            okButton = $("#closeSmallModal-ok"),
            bookTable = $(".rt-tbody");

    private ElementsCollection
            bookNames = $$(".mr-2");

    public String
            deletedRow = ".rt-tr",
            binIcon = "#delete-record-undefined";

    @Step("Открываем страницу профиля")
    public ProfilePage openPage() {
        open("/profile");

        return this;
}

    @Step("Проверяем успешность авторизации")
    public ProfilePage checkAuthorization() {
        $(usernameLabel).shouldHave(text(USERNAME));

        return this;
    }

    @Step("Удаляем книги из корзины по API")
    public ProfilePage deleteBook(LoginResponse loginResponse, String isbn) {
        BookStoreApiSteps.deleteBookFromProfile(loginResponse, isbn);
        return this;
    }

    @Step("Удаляем книгу из корзины в UI")
    public ProfilePage clickDeleteBook(String deletedBookTitle) {
        bookNames.findBy(text(deletedBookTitle)).closest(deletedRow).$(binIcon).click();

        return this;
    }

    @Step("Подтверждаем удаление книги")
    public ProfilePage closeConfirmationWindow() {
        okButton.click();

        return this;
    }

    @Step("Проверяем факт удаления книги")
    public ProfilePage checkDeleteBook(String title) {
        bookTable.shouldNotHave(text(title));

        return this;
    }
}
