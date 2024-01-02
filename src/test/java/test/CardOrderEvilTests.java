package test;

import com.codeborne.selenide.Condition;
import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderEvilTests {
    @BeforeEach
    void settup() {
        open("http://localhost:9999");
    }
    @Test
    public void notFilledCity() { //
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    public void notFilledName() {
        DataGenerator.UserInfo userValid = DataGenerator.Registration.generateUser("ru");
        $("[data-test-id=city] input").setValue(userValid.getCity());
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    public void notFilledDate() {
        DataGenerator.UserInfo userValid = DataGenerator.Registration.generateUser("ru");
        $("[data-test-id=city] input").setValue(userValid.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=name] input").setValue(userValid.getName());
        $("[data-test-id=phone] input").setValue(userValid.getPhone());
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(byText("Неверно введена дата")).shouldBe(visible);
    }

    @Test
    public void notFilledPhone() {
        DataGenerator.UserInfo userValid = DataGenerator.Registration.generateUser("ru");
        $("[data-test-id=city] input").setValue(userValid.getCity());
        $("[data-test-id=name] input").setValue(userValid.getName());
        $("[data-test-id=agreement]").click();
        $("button.button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(visible);
    }

    @Test
    public void notFilledCheckPoint() {
        DataGenerator.UserInfo userValid = DataGenerator.Registration.generateUser("ru");
        $("[data-test-id=city] input").setValue(userValid.getCity());
        $("[data-test-id=name] input").setValue(userValid.getName());
        $("[data-test-id=phone] input").setValue(userValid.getPhone());
        $("button.button").click();
        $("[data-test-id=agreement]").shouldHave(Condition.cssClass("input_invalid"));
    }
}
