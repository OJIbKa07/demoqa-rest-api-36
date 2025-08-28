package tests;

import api.AuthorizationApi;
import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;

public class DemoqaTestsAPI extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    BooksPage bookPage = new BooksPage();

    @WithLogin(username = USERNAME, password = PASSWORD)
    @Test
    void deleteBookTest() {
        step("Проверка успешной авторизации", () -> {
            AuthorizationApi.loginWithApi();
            profilePage
                    .openPage()
                    .removeAds()
                    .checkAuthorization();
        });

        step("Добавляем книгу в корзину", () -> {
            bookPage.addBook(isbnOne);
        });

        step("Удаляем книгу № 1 из корзины", () -> {
            profilePage.deleteBook(isbnOne);

        });
    }
}
