package ru.netology.page;

import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class FormToCredit {
    public void fill(String card, String month, String year, String holder, String code) {
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(card);
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(month);
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(year);
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(holder);
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(code);

        $x("//form/fieldset//button").click();
    }
}
