package com.pixellu.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://account.pixellu.com/dashboard
public class AccountPixelluDashboardPage {
    public final SelenideElement linkGetStarted = $(".-pss .c-subscription__footer [target]");

    public void clickOnGetStart() {
        linkGetStarted.shouldBe(Condition.visible).click();
    }
}