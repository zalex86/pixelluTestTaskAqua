package com.pixellu.tests;

import com.pixellu.helpers.DriverFactory;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.logging.Logger;

import static com.codeborne.selenide.Selenide.open;

public class BaseSetup {
    public static String SERVER;
    public static Logger LOGGER = Logger.getLogger("");
    public static final String downloadFilepath = "D:\\Users\\AleksanDR\\Downloads\\";//"/tmp/";
//    public static final String downloadFilepath = "/tmp/";

    @BeforeSuite
    public void setupServer() {
        serverSetup();
    }

    @BeforeMethod
    public final void setUpDriver() {
        DriverFactory.setUpDriver();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .savePageSource(false)
                .includeSelenideSteps(false));
        open(SERVER);
    }

    @AfterMethod
    public final void closeWebDriver() {
        Selenide.closeWebDriver();
    }

    private void serverSetup() {
        SERVER = System.getProperty("server");

    }
}
