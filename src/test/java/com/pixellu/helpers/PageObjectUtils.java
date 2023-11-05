package com.pixellu.helpers;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class PageObjectUtils {

    private PageObjectUtils() {
    }

    @Step("Ожидание прогрузки страницы {0}")
    public static void waitPageIsPresentByURL(String pageUrl) {
        new WebDriverWait(WebDriverRunner.getWebDriver(), TimeOuts.PAGE_LOAD_TIMEOUT_S.getValue())
                .until(ExpectedConditions.urlContains(pageUrl));
    }

    public static boolean checkPageIsPresentByURL(String pageUrl) {
        try {
            new WebDriverWait(WebDriverRunner.getWebDriver(), TimeOuts.PAGE_LOAD_TIMEOUT_S.getValue())
                    .until(ExpectedConditions.urlContains(pageUrl));
        } catch (TimeoutException e){
            return false;
        }
        return true;
    }
}
