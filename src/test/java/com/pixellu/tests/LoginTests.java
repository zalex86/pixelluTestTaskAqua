package com.pixellu.tests;

import com.codeborne.selenide.Condition;
import com.pixellu.helpers.PageObjectUtils;
import com.pixellu.pages.MainPage;
import com.pixellu.pages.PixelluSmartslidesPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends BaseSetup {

    private String login;
    private String password;

    @Test(description = "Try to login without an email")
    public void loginWOemail() {
        MainPage mainPage = new MainPage();
        mainPage.acceptCookies()
                .clickLoginButton()
                .clickContinueButton()
                .validationMessage.shouldBe(Condition.visible)
                .shouldHave(Condition.text("Enter a valid email address"));
    }

    @Test(description = "Try to login with an email and password")
    public void loginWithEmailAndPassword() {
        login = "ta_interview@pixellu.com";
        password = "ta_interview";
        new MainPage();
        PageObjectUtils.waitPageIsPresentByURL(SERVER);
        open("https://www.pixellu.com/smartslides/");
        MainPage mainPage = new PixelluSmartslidesPage();
        mainPage
                .clickLoginButton()
                .sendEmail(login)
                .clickContinueButton()
                .sendPassword(password)
                .clickOnLoginButton()
                .clickOnGetStarted()
                .createNewSlideShow.shouldBe(Condition.visible);
    }
}
