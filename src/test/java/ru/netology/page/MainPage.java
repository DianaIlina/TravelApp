package ru.netology.page;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public static FormPage ButtonBuy() {
        $x("//button//span[normalize-space(text())='Купить']")
                .click();
        return new FormPage();
    }

    public static FormPage ButtonCredit() {
        $x("//button//span[normalize-space(text())='Купить в кредит']")
                .click();
        return new FormPage();
    }

}
