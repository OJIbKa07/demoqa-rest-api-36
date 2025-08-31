package tests;

import api.AuthorizationApi;
import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;

@WithLogin
public class DemoqaTestsAPI extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    BooksPage bookPage = new BooksPage();


    @Test
    void deleteBookTest() {
        step("Проверка успешной авторизации", () -> {
            AuthorizationApi.loginWithApi();
            profilePage
                    .openPage()
                    .checkAuthorization();
        });

        step("Добавляем книгу в корзину", () -> {
            bookPage
                    .addBook(isbnOne);
        });

        step("Удаляем книгу № 1 из корзины", () -> {
            profilePage
                    .deleteBook(isbnOne);
        });
    }
}
