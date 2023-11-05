package com.pixellu.helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static com.airbnb.tests.BaseSetup.LOGGER;

public final class DriverFactory {

    public static void setUpDriver(){
//        Configuration.remote = ParametersProvider.getProperty("seleniumUrl");
        Configuration.browser = System.getProperty("gridBrowserName");
        Configuration.startMaximized = true;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (Configuration.browser){
            case "chrome":
                capabilities = getChromeCapabilities();
                break;
            case "firefox":
                capabilities = getFireFoxCapabilities();
                break;
            default:
                LOGGER.log(Level.WARNING, "Browser " + Configuration.browser + " is not supporte");
                break;
        }

        Configuration.browserCapabilities = capabilities;
    }

    private static DesiredCapabilities getChromeCapabilities(){
        Map<String, Object> prefsMap = new HashMap<>();
        prefsMap.put("profile.default_content_settings.popups", 0);
//        prefsMap.put("download.default_directory", downloadFilepath);
        prefsMap.put("intl.accept_languages", "en, en_US");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefsMap);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        options.addArguments("--disable-gpu");
        options.addArguments("--disable-password-manager");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //show browser window during tests
//        capabilities.setCapability("enableVNC", true);
        //enable video recording during tests
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    private static DesiredCapabilities getFireFoxCapabilities(){
        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf,application/vnd.ms-excel,application/zip");
        profile.setPreference("pdfjs.disabled", true);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //show browser window during tests
        capabilities.setCapability("enableVNC", true);
        //enable video recording during tests
        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return capabilities;
    }

}
