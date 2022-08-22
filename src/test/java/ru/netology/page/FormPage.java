package ru.netology.page;

import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$x;

public class FormPage {
    public static FormPage formPage() {
        $x("//form/fieldset//span[contains(text(), 'Месяц')]/../span/input")
         .setValue(String.format("%02d", DataHelper.GenerateData.generateMonth()));
        $x("//form/fieldset//span[contains(text(), 'Год')]/../span/input")
         .setValue(String.valueOf(DataHelper.GenerateData.generateYear()));
        $x("//form/fieldset//span[contains(text(), 'Владелец')]/../span/input")
                .setValue(DataHelper.GenerateData.generateHolder());
        $x("//form/fieldset//span[contains(text(), 'CVC/CVV')]/../span/input")
                .setValue(DataHelper.GenerateData.generateCode());

        $x("//form/fieldset//button").click();

        return new FormPage();
    }
}
