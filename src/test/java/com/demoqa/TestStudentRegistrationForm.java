package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestStudentRegistrationForm {

    @BeforeAll
    static void configure(){
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }
    @Test
    void fillFormTest(){

        open("/automation-practice-form");

        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("Ivan@gmail.com");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("1478521478");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1994");
        $(".react-datepicker__day--021").click();

        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();

        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").setValue("Kaliningrad");

        $("#state").click();
        $(byText("Haryana")).click();

        $("#city").click();
        $(byText("Panipat")).click();

        $("#submit").click();

        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").shouldHave(
          text("Ivan"),
          text("Ivanov"),
          text("Ivan@gmail.com"),
          text("Male"),
          text("1478521478"),
          text("21 July,1994"),
          text("Maths"),
          text("Sports, Reading"),
          text("image.jpg"),
          text("Kaliningrad"),
          text("Haryana Panipat"));
    }
}
