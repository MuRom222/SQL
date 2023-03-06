package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class LoginTest {

    @BeforeEach
    void login() {
        open("http://localhost:9999");
    }

    @AfterAll
    static void cleanDataBase() {
        SQLHelper.cleanDataBase();
    }

    @Test
    void shouldLoginFirstRegisteredUser() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfoFirstUser();
        loginPage.validLogin(authInfo);
        var verificationPage = new VerificationPage();
        verificationPage.validVerify(SQLHelper.getVerificationCode());
        var dashboardPage = new DashboardPage();

    }
}
