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
    public final SelenideElement emailInput = $("mds-text-field[data-qa='email-input'] input");
    public final SelenideElement passwordInput = $("mds-text-field[data-qa='password-input'] input");
    public final SelenideElement loginButton = $("mds-button[data-qa='action-btn'] button");
    private final String PAGE_URL = "https://auth.pixellu.com/login?next=https:%2F%2Fauth-api.pixellu.com%2Fo%2Fauthorize%2F%3Fresponse_type%3Dcode%26client_id%3Dpx-dashboard%26state%3Dhttps%253A%252F%252Faccount.pixellu.com%252Fdashboard";
    private final SelenideElement scriptLoader = $("#Mixpanel_RS");

    public AuthPixelluLoginPage() {
        PageObjectUtils.waitPageIsPresentByURL("https://auth.pixellu.com");
        scriptLoader.shouldBe(Condition.hidden);
        emailInput.shouldBe(Condition.visible);
    }

    @Step("Click on the 'Continue' button")
    public AuthPixelluLoginPage clickContinueButton() {
        continueButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Enter login {0}")
    public AuthPixelluLoginPage sendEmail(String login) {
        emailInput.shouldHave(Condition.visible).hover().click();
        emailInput.sendKeys(login);
        return this;
    }

    @Step("Enter password {0}")
    public AuthPixelluLoginPage sendPassword(String password) {
        passwordInput.shouldHave(Condition.visible).click();
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Click on the 'Log in' button")
    public AccountPixelluDashboardPage clickOnLoginButton() {
        loginButton.click();
        return new AccountPixelluDashboardPage();
    }
}