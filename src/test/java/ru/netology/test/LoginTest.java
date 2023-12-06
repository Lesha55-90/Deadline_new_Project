package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.Data;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import ru.netology.page.DashboardPage;
import ru.netology.sql.SQL;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.sql.SQL.cleanAuthCodes;
import static ru.netology.sql.SQL.cleanDatabase;

public class LoginTest {
    LoginPage loginPage;

    @AfterEach
    void tearDown() {
        cleanAuthCodes();
    }

    @AfterAll
    static void  tearDownAll() {
        cleanDatabase();
    }

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }


    @Test
    void shouldSuccessfulLogin() {
        var authInfo = Data.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisibl();
        var verificationCode = SQL.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }


}
