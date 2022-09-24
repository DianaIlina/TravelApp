package ru.netology.page;

import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class FormToCredit {
    public void fill(String card, int month, int year, String holder, String code) {
        $x("//form/fieldset//span[contains(text(), 'Номер карты')]/../span/input")
                .setValue(card);
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
                .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
                .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();
    }
}
