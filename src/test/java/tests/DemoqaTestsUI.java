package tests;

import api.AccountApiSteps;
import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.BooksPage;
import pages.ProfilePage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.TestData.*;


public class DemoqaTestsUI extends TestBase {
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
                    .addBook(isbnTwo);
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
