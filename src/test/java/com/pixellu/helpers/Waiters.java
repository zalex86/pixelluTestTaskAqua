package com.pixellu.helpers;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import com.codeborne.selenide.ex.ElementShould;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;
import java.util.logging.Level;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.pixellu.tests.BaseSetup.LOGGER;

public final class Waiters {
    public static int EXPLICIT_TIMEOUT = 60;

    @Step("Wait angular ready")
    public static void waitUntilAngularReady() {
        String angularReady = "return angular.element(document).injector()"
                        + ".get('$http').pendingRequests.length === 0";
        ExpectedCondition<Boolean> angularLoad = driver -> Boolean.valueOf(((JavascriptExecutor) driver)
                        .executeScript(angularReady).toString());
        new WebDriverWait(getWebDriver(), EXPLICIT_TIMEOUT).until(angularLoad);
    }

    public static void waitElementVisibility(By element){
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, TimeOuts.ELEMENT_LOAD_TIMEOUT_S.getValue());
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitElementVisibility(By element, int timeoutInSeconds){
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static void waitElementVisibility(SelenideElement element){
        WebDriver driver = getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, TimeOuts.ELEMENT_LOAD_TIMEOUT_S.getValue());
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitElementClickable(SelenideElement element){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), TimeOuts.ELEMENT_LOAD_TIMEOUT_S.getValue());
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static void waitElementPresence(By element){
        WebDriverWait wait = new WebDriverWait(getWebDriver(), TimeOuts.ELEMENT_LOAD_TIMEOUT_S.getValue());
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static void waitListIsLoad(By elementBy) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (getWebDriver().findElements(elementBy).isEmpty()) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("List not display");
            }
            sleep(500);
        }
    }

    public static void waitListIsLoad(By elementBy, int listElementsCount) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (getWebDriver().findElements(elementBy).size() != listElementsCount) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("List not have " + listElementsCount + " elements");
            }
            sleep(500);
        }
    }

    public static void waitListIsLoad(By elementBy, int listElementsCount, int timeoutInSeconds) {
        long timeoutExpired = System.currentTimeMillis() + timeoutInSeconds * 1000;
        while (getWebDriver().findElements(elementBy).size() != listElementsCount) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("List not load " + listElementsCount + " elements");
            }
            sleep(500);
        }
    }

    public static void waitListIsEmptyDuringTime(ElementsCollection elements, int timeoutInSeconds){
        long timeoutExpired = System.currentTimeMillis() + timeoutInSeconds * 1000;
        while (System.currentTimeMillis() <= timeoutExpired) {
            elements.shouldHave(CollectionCondition.size(0));
        }
    }

    public static void waitElementTextEquals(By elementBy, String text) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (!getWebDriver().findElement(elementBy).getText().equals(text)) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + elementBy + " didn't have text " + text);
            }
            sleep(500);
        }
    }

    public static void waitElementTextEquals(SelenideElement element, String text) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (!element.getText().equals(text)) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " didn't have text " + text);
            }
            sleep(500);
        }
    }

    public static void waitElementTextNotEmpty(SelenideElement element) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (element.getText().equals("")) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " have empty text");
            }
            sleep(500);
        }
    }

    public static void waitAttributeContainText(SelenideElement element, String attribute,
                                                String textInAttribute) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (!Objects.requireNonNull(element.getAttribute(attribute)).contains(textInAttribute)) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " didn't have text '"
                        + textInAttribute + "' in attribute '" + attribute + "'");
            }
            sleep(500);
        }
    }

    public static void waitAttributeNotContainText(SelenideElement element, String attribute, String textInAttribute) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (Objects.requireNonNull(element.getAttribute(attribute)).contains(textInAttribute)) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " have text '"
                        + textInAttribute + "' in attribute '" + attribute + "'");
            }
            sleep(500);
        }
    }

    public static void waitAttributeDisappear(SelenideElement element, String attribute) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (element.getAttribute(attribute) != null) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " have attribute '" + attribute + "'");
            }
            sleep(500);
        }
    }

    public static void waitValueNotContainText(SelenideElement element, String value) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_VALUE_TIMEOUT_MS.getValue();
        while (Objects.equals(element.getValue(), value)) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " have value '"
                        + value + "'");
            }
            sleep(500);
        }
    }

    public static void waitUntilElementNotMove(SelenideElement element) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        int x = element.getLocation().x;
        int y = element.getLocation().y;
        sleep(400);
        while (element.getLocation().x != x || element.getLocation().y != y){
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Element " + element + " still moving");
            }
            x = element.getLocation().x;
            y = element.getLocation().y;
            sleep(400);
        }
    }

    public static void waitButtonIsActive(SelenideElement button) {
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.ELEMENT_LOAD_TIMEOUT_MS.getValue();
        while (Objects.requireNonNull(button.getAttribute("class")).contains("md-btn--disabled")) {
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                LOGGER.log(Level.INFO, "Button " + button + " not active");
                return;
            }
            sleep(500);
        }
    }

    public static void waitWindowsLoad(int windowsCount){
        long timeoutExpired = System.currentTimeMillis() + TimeOuts.PAGE_LOAD_TIMEOUT_MS.getValue();
        while (getWebDriver().getWindowHandles().size() != windowsCount){
            long wait = timeoutExpired - System.currentTimeMillis();
            if (wait <= 0) {
                throw new RuntimeException("Открыто " + getWebDriver().getWindowHandles().size() + " вкладок, " +
                        "ожидалось " + windowsCount);
            }
            sleep(500);
        }
    }

    public static void waitElementVisibleAndDisappear(SelenideElement element){
        try {
            element.shouldBe(Condition.visible, Duration.ofMillis(TimeOuts.SVG_LOADING_MS.getValue()));
        } catch (ElementNotFound | ElementShould e){
            e.printStackTrace();
            return;
        }
        element.shouldBe(Condition.disappear, Duration.ofMillis(TimeOuts.ELEMENT_INVISIBILITY_TIMEOUT_MS.getValue()));
    }

    public static void sleep(Integer milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
