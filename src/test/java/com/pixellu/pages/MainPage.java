package com.pixellu.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.pixellu.helpers.PageObjectUtils;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://www.pixellu.com/
public class MainPage {
    public final SelenideElement acceptCookiesButton = $("[data-tid='banner-accept']");

    public final SelenideElement headerLoginButton = $("header a[target='_blank']");

    public final SelenideElement divPixelluProductsSmartAlbums = $("header div[class='container']");
    public MainPage() {
        PageObjectUtils.waitPageIsPresentByURL("https://www.pixellu.com");
    }

    @Step("Accept Cookies")
    public MainPage acceptCookies() {
        acceptCookiesButton.shouldBe(Condition.visible).click();
        return this;
    }

    @Step("Click the 'Login' button in Header")
    public AuthPixelluLoginPage clickLoginButton() {
        headerLoginButton.shouldBe(Condition.visible).hover().click();
        Selenide.switchTo().window(1);
        return new AuthPixelluLoginPage();
    }
}