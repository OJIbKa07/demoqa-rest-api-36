package pages;

import api.DeleteBookApi;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
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

    @Step("Удаляем книгу из корзины по API")
    public ProfilePage deleteBook() {
        DeleteBookApi.deleteBookFromProfileTest();

        return this;
    }

    @Step("Удаляем книгу из корзины в UI")
    public ProfilePage clickDeleteAllBooks() {
        deleteButton.click();
        modalWindow.shouldBe(Condition.visible)
                .click();
        switchTo().alert().accept();

        return this;
    }

    public ProfilePage checkDeleteBook() {
        listMyBook.shouldBe(Condition.visible);

        return this;
    }
}
