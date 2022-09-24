package ru.netology.page;

import lombok.experimental.UtilityClass;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

@UtilityClass
public class Notifications {
    public void checkNotificationSuccess() {
        $x("//*/div[contains(text(), 'Успешно')]")
                .should(visible, Duration.ofSeconds(20));
    }

    public void checkNotificationFail() { //plus card bad validation
        $x("//*/div[contains(text(), 'Ошибка')]")
                .should(visible, Duration.ofSeconds(20));
    }
}
