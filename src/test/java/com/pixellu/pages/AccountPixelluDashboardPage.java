package com.pixellu.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.pixellu.helpers.PageObjectUtils;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://account.pixellu.com/dashboard
public class AccountPixelluDashboardPage {
    public final SelenideElement getStartedButton = $(".-pss .c-subscription__footer [target]");

    public AccountPixelluDashboardPage() {
        PageObjectUtils.waitPageIsPresentByURL("https://account.pixellu.com/dashboard");
    }

    @Step("Click on the 'Get started' button")
    public SmartslidesPage clickOnGetStarted() {
        getStartedButton.shouldBe(Condition.visible).click();
        Selenide.switchTo().window(2);
        return new SmartslidesPage();
    }
}