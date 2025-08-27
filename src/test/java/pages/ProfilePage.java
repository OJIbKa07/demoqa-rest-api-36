package pages;

import api.DeleteBookApi;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static tests.TestData.USERNAME;

public class ProfilePage {

    private SelenideElement
            usernameLabel = $("#userName-value"),
            firstBookRow,
            symbolBasket = $("#delete-record-undefined"),
            modalConfirmDel = $("button#closeSmallModal-ok");
    private ElementsCollection
            bookRows= $$("div[role='row']");


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
    public ProfilePage clickDeleteAllBooks(String isbn) {
        firstBookRow = bookRows.first();
        firstBookRow.shouldBe(Condition.visible);
        symbolBasket.shouldBe(Condition.visible).click();
        modalConfirmDel.click();

        return this;
    }

    public ProfilePage checkDeleteBook(String isbn) {
        bookRows.shouldBe(CollectionCondition.empty);

        return this;
    }
}
