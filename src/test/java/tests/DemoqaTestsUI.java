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


public class DemoqaTestsUI extends TestBase {
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
        LoginResponse loginResponse = AccountApiSteps.loginWithApi();
        step("Проверка успешной авторизации", () -> {
            profilePage
                    .openPage()
                    .checkAuthorization();
        });

        step("Добавляем книгу в корзину", () -> {
            bookPage
                    .addBook(loginResponse, isbnTwo);
        });

        step("Удаляем книгу из корзины", () -> {
            profilePage
                    .clickDeleteBook(bookTwoTitle)
                    .closeConfirmationWindow();
        });

        step("Проверяем, что книга удалена", () -> {
            profilePage
                    .checkDeleteBook(bookTwoTitle);
        });
    }
}
