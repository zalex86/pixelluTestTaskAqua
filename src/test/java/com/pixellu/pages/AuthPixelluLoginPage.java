package com.pixellu.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.pixellu.helpers.PageObjectUtils;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://auth.pixellu.com/login?next=https:%2F%2Fauth-api.pixellu.com%2Fo%2Fauthorize%2F%3Fresponse_type%3Dcode%26client_id%3Dpx-dashboard%26state%3Dhttps%253A%252F%252Faccount.pixellu.com%252Fdashboard
// https://auth.pixellu.com/login?next=https:%2F%2Fauth-api.pixellu.com%2Fo%2Fauthorize%2F%3Fresponse_type%3Dcode%26client_id%3Dpx-dashboard%26state%3Dhttps%253A%252F%252Faccount.pixellu.com%252Fdashboard
public class AuthPixelluLoginPage {
    public final SelenideElement inputPristineTouched = $("input");
    public final SelenideElement continueButton = $("mds-button[data-qa='continue-button'] button");
    public final SelenideElement validationMessage = $("div[class='supporting-text'] p");
    private final String PAGE_URL = "https://auth.pixellu.com/login?next=https:%2F%2Fauth-api.pixellu.com%2Fo%2Fauthorize%2F%3Fresponse_type%3Dcode%26client_id%3Dpx-dashboard%26state%3Dhttps%253A%252F%252Faccount.pixellu.com%252Fdashboard";

    public final SelenideElement emailInput = $("mds-text-field[data-qa='email-input'] input");

    public final SelenideElement inputPristineTouched2 = $("mds-text-field[data-qa='password-input'] input");

    public final SelenideElement buttonLog = $("mds-button[data-qa='action-btn'] button");

    public AuthPixelluLoginPage() {
        PageObjectUtils.waitPageIsPresentByURL(PAGE_URL);
    }

    @Step("Click the 'Continue' button under the email input field")
    public AuthPixelluLoginPage clickContinueButton() {
        continueButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Enter login {0}")
    public AuthPixelluLoginPage sendEmail(String login) {
        emailInput.shouldHave(Condition.visible).click();
                emailInput.sendKeys(login);
        return this;
    }

    public AuthPixelluLoginPage sendPassword(String password) {
        inputPristineTouched2.shouldHave(Condition.visible).click();
                inputPristineTouched2.sendKeys(password);
        return this;
    }

    public AccountPixelluDashboardPage clickOnSubmitButton() {
        buttonLog.click();
        return new AccountPixelluDashboardPage();
    }
}