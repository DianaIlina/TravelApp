package ru.netology.page;

import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@UtilityClass
public class Errors {

    int duration = 20;
    public void checkErrorBadValidationMonth() {
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(duration));
    }

    public void checkErrorWrongMonth() {
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span" +
                "[contains(text(), 'Неверно указан срок действия карты')]")
                .should(visible, Duration.ofSeconds(duration));
    }

    public void checkErrorBadValidationYear() {
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(duration));
    }

    public void checkErrorYearBelow() {
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Истёк срок действия карты')]")
                .should(visible, Duration.ofSeconds(duration));
    }

    public void checkErrorYearOver() {
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span" +
                "[contains(text(), 'Неверно указан срок действия карты')]")
                .should(visible, Duration.ofSeconds(duration));
    }

    public void checkErrorBadValidationHolder() {
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(duration));
    }

    public void checkErrorBadValidationCode() {
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span" +
                "[contains(text(), 'Неверный формат')]")
                .should(visible, Duration.ofSeconds(duration));
    }
}
