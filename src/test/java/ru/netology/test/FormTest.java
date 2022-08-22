package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.FormPage;
import ru.netology.page.MainPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

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
    void shouldUseActiveCardToBuy() {
        MainPage.ButtonBuy();
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());

        FormPage.formPage();

        $x("//*/div[contains(text(), 'Успешно')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldUseActiveCardToCredit() {
        MainPage.ButtonCredit();
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());

        FormPage.formPage();

        $x("//*/div[contains(text(), 'Успешно')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldUseDeclinedCardToBuy() {
        MainPage.ButtonBuy();
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getDeclinedNumber());

        FormPage.formPage();

        $x("//*/div[contains(text(), 'Ошибка')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldUseDeclinedCardToCredit() {
        MainPage.ButtonCredit();
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getDeclinedNumber());

        FormPage.formPage();

        $x("//*/div[contains(text(), 'Ошибка')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldUseWrongCardNumberToBuy() {
        MainPage.ButtonBuy();
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue("4444 4444 4444 4444");

        FormPage.formPage();

        $x("//*/div[contains(text(), 'Ошибка')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldUseWrongCardNumberToCredit() {
        MainPage.ButtonCredit();
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue("4444 4444 4444 4445");

        FormPage.formPage();

        $x("//*/div[contains(text(), 'Ошибка')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationMonthToBuy() {
        MainPage.ButtonBuy();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue("1");
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationYearToBuyBelow() {
        MainPage.ButtonBuy();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue("19");
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Истёк срок действия карты')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationYearToBuyOver() {
        MainPage.ButtonBuy();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue("29");
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Неверно указан срок действия карты')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationHolderToBuy() {
        MainPage.ButtonBuy();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue("Диана Ильина");
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationCodeToBuy() {
        MainPage.ButtonBuy();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue("53");

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationMonthToCredit() {
        MainPage.ButtonCredit();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue("1");
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationYearToCreditBelow() {
        MainPage.ButtonCredit();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue("19");
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Истёк срок действия карты')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationYearToCreditOver() {
        MainPage.ButtonCredit();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue("29");
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Неверно указан срок действия карты')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationHolderToCredit() {
        MainPage.ButtonCredit();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue("Диана Ильина");
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(20));
    }

    @Test
    void shouldCheckValidationCodeToCredit() {
        MainPage.ButtonCredit();

        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(DataHelper.GenerateData.getApprovedNumber());
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue("53");

        $x("//form/fieldset//button").click();

        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(20));
    }
}