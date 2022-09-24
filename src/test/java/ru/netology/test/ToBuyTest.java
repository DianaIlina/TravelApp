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

public class ToBuyTest {

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
    void shouldUseApprovedCardToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Notifications.checkNotificationSuccess();
    }

    @Test
    void shouldUseDeclinedCardToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getDeclinedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();
    }

    @Test
    void shouldUseWrongCardNumberToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getWrongNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();
    }

    @Test
    void shouldCheckValidationMonthToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = 1;
        int year = DataHelper.GenerateData.generateYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationMonth();
    }

    @Test
    void shouldCheckValidationMonthBelowToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateCurrentYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationMonthOverToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYearMax();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationYearToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = 2;
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationYear();
    }

    @Test
    void shouldCheckValidationYearBelowToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYearBelow();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorYearBelow();
    }

    @Test
    void shouldCheckValidationYearOverToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateYearOver();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorYearOver();
    }

    @Test
    void shouldCheckValidationHolderToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateCurrentYear();
        String holder = DataHelper.GenerateData.generateHolderRus();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationHolder();;
    }

    @Test
    void shouldCheckValidationCodeToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        String card = DataHelper.GenerateData.getApprovedNumber();
        int month = DataHelper.GenerateData.generateMonth();
        int year = DataHelper.GenerateData.generateCurrentYear();
        String holder = DataHelper.GenerateData.generateHolder();
        String code = "63";

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationCode();
    }
}
