package com.pixellu.tests;

import com.codeborne.selenide.Condition;
import com.pixellu.pages.MainPage;
import org.testng.annotations.Test;

public class LoginTests extends BaseSetup {
    @Test(description = "Try to login without an email")
    public void loginWOemail() {
        MainPage mainPage = new MainPage();
        mainPage.acceptCookies()
                .clickLoginButton()
                .clickContinueButton()
                .validationMessage.shouldBe(Condition.visible)
                .shouldHave(Condition.text("Enter a valid email address"));
    }
}
