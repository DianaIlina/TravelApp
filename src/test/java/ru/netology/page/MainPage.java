package ru.netology.page;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {
    public FormToBuy ButtonBuy() {
        $x("//button//span[normalize-space(text())='Купить']")
                .click();
        return new FormToBuy();
    }

    public FormToCredit ButtonCredit() {
        $x("//button//span[normalize-space(text())='Купить в кредит']")
                .click();
        return new FormToCredit();
    }

}
