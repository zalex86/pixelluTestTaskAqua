package com.pixellu.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://smartslides.com/
public class SmartslidesPage {
    public final SelenideElement createNewSlideShow = $("button[data-qa='collections-add']");
}