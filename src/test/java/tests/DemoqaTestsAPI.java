package tests;

import api.AccountApiSteps;
import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;


public class DemoqaTestsAPI extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    BooksPage bookPage = new BooksPage();

    @WithLogin
    @Test
    void deleteBookTest() {
        step("Проверка успешной авторизации", () -> {
            AccountApiSteps.loginWithApi();
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
