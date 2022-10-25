package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.dataClasses.DataHelper;
import ru.netology.dataClasses.DatabaseHelper;
import ru.netology.page.Errors;
import ru.netology.page.MainPage;
import ru.netology.page.Notifications;

import java.util.Calendar;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void shouldReceiveReplyFromDB() {
        DatabaseHelper dh = new DatabaseHelper();
        int dbRecordsBefore = dh.findRecords("credit_request_entity", "APPROVED");


        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationSuccess();

        int dbRecordsAfter = dh.findRecords("credit_request_entity", "APPROVED");
        assertEquals(1, dbRecordsAfter-dbRecordsBefore);
    }

    @Test
    void shouldUseApprovedCardToCredit() {
        DatabaseHelper dh = new DatabaseHelper();
        int dbRecordsBefore = dh.findRecords("credit_request_entity", "APPROVED");


        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationSuccess();
    }

    @Test
    void shouldUseDeclinedCardToCredit() {
        DatabaseHelper dh = new DatabaseHelper();
        int dbRecordsBefore = dh.findRecords("credit_request_entity", "DECLINED");

        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getDeclinedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();

        int dbRecordsAfter = dh.findRecords("credit_request_entity", "DECLINED");
        assertEquals(1, dbRecordsAfter-dbRecordsBefore);
    }

    @Test
    void shouldUseWrongCardNumberToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getWrongNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Notifications.checkNotificationFail();
    }

    @Test
    void shouldCheckValidationMonthToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = "3";
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationMonth();
    }

    @Test
    void shouldCheckValidationMonthBelowToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateMonthBelow();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationMonthOverToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateMonthOver();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorWrongMonth();
    }

    @Test
    void shouldCheckValidationYearToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = "1";
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationYear();
    }

    @Test
    void shouldCheckValidationYearBelowToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateYearBelow();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorYearBelow();
    }

    @Test
    void shouldCheckValidationYearOverToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateYearOver();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorYearOver();
    }

    @Test
    void shouldCheckValidationHolderToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolderRus();
        String code = DataHelper.GenerateData.generateCode();

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationHolder();;
    }

    @Test
    void shouldCheckValidationCodeToCredit() {
        var mainPage = new MainPage();
        var formFillToCredit = mainPage.ButtonCredit();

        Calendar date = DataHelper.GenerateData.generateValidDate();

        String card = DataHelper.GenerateData.getApprovedNumber();
        String month = DataHelper.GenerateData.getMonthFromDate(date);
        String year = DataHelper.GenerateData.getYearFromDate(date);
        String holder = DataHelper.GenerateData.generateHolder();
        String code = "63";

        formFillToCredit.fill(card, month, year, holder, code);
        Errors.checkErrorBadValidationCode();
    }
}
