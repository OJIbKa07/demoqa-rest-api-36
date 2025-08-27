package tests;

import api.AuthorizationApi;
import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.PASSWORD;
import static tests.TestData.USERNAME;

public class DeleteBookUI extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    BooksPage bookPage = new BooksPage();

    @WithLogin(username = USERNAME, password = PASSWORD)
    @Test
    void deleteBookTest() {
        step("Авторизация и проверка успешной авторизации", () -> {
            AuthorizationApi.loginWithApi();
            profilePage.checkAuthorization();
        });

        step("Добавляем книгу в корзину", () -> {
            bookPage.addBook();
        });

        step("Удаляем книгу из корзины", () -> {
            profilePage
                .checkAuthorization()
                .clickDeleteAllBooks()
                .checkDeleteBook();
        });
    }
}
