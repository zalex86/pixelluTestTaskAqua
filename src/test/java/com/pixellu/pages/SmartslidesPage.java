package com.pixellu.pages;

import com.codeborne.selenide.SelenideElement;
import com.pixellu.helpers.PageObjectUtils;

import static com.codeborne.selenide.Selenide.$;

// page_url = https://smartslides.com/
public class SmartslidesPage {
    public final SelenideElement createNewSlideShow = $("button[data-qa='collections-add']");

    public SmartslidesPage() {
        PageObjectUtils.waitPageIsPresentByURL("https://smartslides.com");
    }
}