package test;

import com.codeborne.selenide.Condition;
import data.DataGenerator;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class CardOrderTest {

    @Test
    public void notFilledCity() {
        open("http://localhost:9999");
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    public void notFilledName() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Смоленск");
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    public void notFilledDate() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Москва");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=name] input").setValue("Антон Антонович Петров");
        $("[data-test-id=phone] input").setValue("+78000055544");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(byText("Неверно введена дата")).shouldBe(visible);
    }

    @Test
    public void notFilledPhone() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Смоленск");
        $("[data-test-id=name] input").setValue("Сергей Антонович Семижен");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    public void notFilledCheckPoint() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Смоленск");
        $("[data-test-id=name] input").setValue("Андрей Юрьевич Марков");
        $("[data-test-id=phone] input").setValue("+78000055544");
        $("button.button").click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));
    }

    @Test
    public void shouldBe() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        String planningDate = DataGenerator.generateDate(4);
        $("[data-test-id=date] input").setValue(planningDate);
        $("[data-test-id=name] input").setValue("Андрей Юрьевич Марков");
        $("[data-test-id=phone] input").setValue("+78000055544");
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + planningDate));
    }
}
