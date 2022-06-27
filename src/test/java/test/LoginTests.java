package test;

import Page.LoginPage;
import browser.SelenideConfiguration;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class LoginTests {

    LoginPage loginPage;

    @BeforeMethod
    public void setUp() {

        String browser = Optional.ofNullable(System.getProperty("browser")).orElse("chrome");
        SelenideConfiguration.configureBrowser(browser);

        open("login");
        loginPage = new LoginPage();
    }

    @Test
    public void loginWithIncorrectCredentials() {

        String errorMessage = "These credentials do not match our records.";
        String email = "vladimirwolkov@mail.ru";
        String password = "123";

        loginPage.login(email, password);

        loginPage.errorMessage
                .shouldBe(visible)
                .shouldHave(exactText(errorMessage));
    }
}
