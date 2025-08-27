package pages;

import api.DeleteBookApi;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.USERNAME;

public class ProfilePage {

    private SelenideElement
            usernameLabel = $("#userName-value");

    @Step("Проверяем успешность авторизации")
    public ProfilePage checkAuthorization() {
        open("/profile");
        $(usernameLabel).shouldHave(text(USERNAME));

        return this;
    }

    @Step("Удаляем книгу из корзины")
    public ProfilePage deleteBook() {
        DeleteBookApi.deleteBookFromProfileTest();

        return this;
    }
}
