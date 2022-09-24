package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.Errors;
import ru.netology.page.MainPage;
import ru.netology.page.Notifications;

import static com.codeborne.selenide.Selenide.open;

public class ToCreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void openTravelApp() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldUseApprovedCardToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationSuccess();
    }

    @Test
    void shouldUseDeclinedCardToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getDeclinedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();
    }

    @Test
    void shouldUseWrongCardNumberToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getWrongNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();
    }

    @Test
    void shouldCheckValidationMonthToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = 1;
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationMonth();
    }

    @Test
    void shouldCheckValidationMonthBelowToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateCurrentYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationMonthOverToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYearMax();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationYearToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = 2;
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationYear();
    }

    @Test
    void shouldCheckValidationYearBelowToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYearBelow();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorYearBelow();
    }

    @Test
    void shouldCheckValidationYearOverToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYearOver();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorYearOver();
    }

    @Test
    void shouldCheckValidationHolderToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateCurrentYear();
        String holder = DataHelper.GenerateData.generateHolderRus();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationHolder();;
    }

    @Test
    void shouldCheckValidationCodeToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateCurrentYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = "63";

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationCode();
    }
}
