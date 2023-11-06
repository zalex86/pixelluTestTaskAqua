package com.pixellu.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.pixellu.helpers.PageObjectUtils;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

// page_url = https://auth.pixellu.com/login?next=https:%2F%2Fauth-api.pixellu.com%2Fo%2Fauthorize%2F%3Fresponse_type%3Dcode%26client_id%3Dpx-dashboard%26state%3Dhttps%253A%252F%252Faccount.pixellu.com%252Fdashboard
public class AuthPixelluLoginPage {
    private final String PAGE_URL = "https://auth.pixellu.com/login?next=https:%2F%2Fauth-api.pixellu.com%2Fo%2Fauthorize%2F%3Fresponse_type%3Dcode%26client_id%3Dpx-dashboard%26state%3Dhttps%253A%252F%252Faccount.pixellu.com%252Fdashboard";
    public final SelenideElement inputPristineTouched = $("input");

    public final SelenideElement continueButton = $("mds-button[data-qa='continue-button'] button");

    public final SelenideElement validationMessage = $("div[class='supporting-text'] p");

    public AuthPixelluLoginPage() {
        PageObjectUtils.waitPageIsPresentByURL(PAGE_URL);
    }

    @Step("Click the 'Continue' button under the email input field")
    public AuthPixelluLoginPage clickContinueButton() {
        continueButton.shouldBe(Condition.visible).click();
        return this;
    }
}