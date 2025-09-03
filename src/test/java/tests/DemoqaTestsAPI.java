package tests;

import api.AccountApiSteps;
import helpers.WithLogin;
import models.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;


public class DemoqaTestsAPI extends TestBase {
    ProfilePage profilePage = new ProfilePage();
    BooksPage bookPage = new BooksPage();
    LoginResponse loginResponse;

    @WithLogin
    @BeforeEach
    void setUpLogin() {
        loginResponse = AccountApiSteps.loginWithApi();
    }

    @Test
    void deleteBookTest() {
        step("Проверка успешной авторизации", () -> {
            profilePage
                    .openPage()
                    .checkAuthorization();
        });

        step("Добавляем книгу в корзину", () -> {
            bookPage
                    .addBook(loginResponse, isbnOne);
        });

        step("Удаляем книгу № 1 из корзины", () -> {
            profilePage
                    .deleteBook(loginResponse, isbnOne);
        });
    }
}
