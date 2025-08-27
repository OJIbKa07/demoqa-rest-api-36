package pages;

import api.DeleteBookApi;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.USERNAME;

public class ProfilePage {

    private SelenideElement
            usernameLabel = $("#userName-value"),
            deleteButton = $("div.text-right.button.di button"),
            modalWindow =  $("#closeSmallModal-ok"),
            listMyBook = $(".rt-noData");


    @Step("Проверяем успешность авторизации")
    public ProfilePage checkAuthorization() {
        open("/profile");
        $(usernameLabel).shouldHave(text(USERNAME));

        return this;
    }

    @Step("Удаляем книги из корзины по API")
    public ProfilePage deleteBook(String isbn) {
        DeleteBookApi.deleteBookFromProfileTest(isbn);

        return this;
    }

    @Step("Удаляем книгу из корзины в UI")
    public ProfilePage clickDeleteAllBooks() {
        deleteButton.shouldBe(visible, enabled).click();
        modalWindow.shouldBe(visible)
                .click();
        switchTo().alert().accept();

        return this;
    }

    public ProfilePage checkDeleteBook() {
        listMyBook.shouldBe(visible);

        return this;
    }
}
