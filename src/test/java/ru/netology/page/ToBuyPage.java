package ru.netology.page;

import static com.codeborne.selenide.Selenide.$x;

public class ToBuyPage {
    public ToBuyPage() {
        $x("//button[1]").click();
    }
}
