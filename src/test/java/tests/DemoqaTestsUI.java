package tests;

import api.AuthorizationApi;
import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;

public class DemoqaTestsUI extends TestBase {
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
            bookPage.addBook(isbnTwo);
        });

        step("Удаляем книгу из корзины", () -> {
            profilePage
                .checkAuthorization()
                .clickDeleteAllBooks(isbnTwo)
                .checkDeleteBook(isbnTwo);
        });
    }
}
