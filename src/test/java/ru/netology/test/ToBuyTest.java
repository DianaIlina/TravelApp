package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DatabaseHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.data.DataHelper;
import ru.netology.data.DatabaseHelper;
import ru.netology.page.Errors;
import ru.netology.page.MainPage;
import ru.netology.page.Notifications;

import java.util.Calendar;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        DatabaseHelper dh = new DatabaseHelper();
        int dbRecordsBefore = dh.findRecords("payment_entity", "APPROVED");

        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Notifications.checkNotificationSuccess();

        int dbRecordsAfter = dh.findRecords("payment_entity", "APPROVED");
        assertEquals(1, dbRecordsAfter-dbRecordsBefore);
    }

    @Test
    void shouldUseDeclinedCardToBuy() {
        DatabaseHelper dh = new DatabaseHelper();
        int dbRecordsBefore = dh.findRecords("payment_entity", "DECLINED");

        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getDeclinedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();

        int dbRecordsAfter = dh.findRecords("payment_entity", "DECLINED");
        assertEquals(1, dbRecordsAfter-dbRecordsBefore);
    }

    @Test
    void shouldUseWrongCardNumberToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getWrongNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();
    }

    @Test
    void shouldCheckValidationMonthToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = "3";
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationMonth();
    }

    @Test
    void shouldCheckValidationMonthBelowToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateMonthBelow();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationMonthOverToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateMonthOver();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationYearToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = "1";
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationYear();
    }

    @Test
    void shouldCheckValidationYearBelowToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateYearBelow();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorYearBelow();
    }

    @Test
    void shouldCheckValidationYearOverToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateYearOver();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorYearOver();
    }

    @Test
    void shouldCheckValidationHolderToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolderRus();
        String code = DataHelper.GenerateData.generateCode();

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationHolder();;
    }

    @Test
    void shouldCheckValidationCodeToBuy() {
        var mainPage = new MainPage();
        var formFillToBuy = mainPage.ButtonBuy();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = "63";

        formFillToBuy.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationCode();
    }
}
