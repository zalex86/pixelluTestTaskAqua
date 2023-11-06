package com.pixellu.helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import static com.pixellu.tests.BaseSetup.LOGGER;
import static com.pixellu.tests.BaseSetup.downloadFilepath;

public final class DriverFactory {

    public static void setUpDriver() {
//        Configuration.remote = ParametersProvider.getProperty("seleniumUrl");
        Configuration.browser = System.getProperty("gridBrowserName");
        System.setProperty("webdriver.chrome.driver",
                "D:\\Users\\AleksanDR\\AquaProjects\\sobes\\pixellu\\chrome\\chromedriver-win64\\chromedriver.exe");
//        Configuration.startMaximized = true;

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        // Устанавливаем размер окна браузера равным максимальному разрешению экрана
        Configuration.browserSize = screenWidth + "x" + screenHeight;
        DesiredCapabilities capabilities = new DesiredCapabilities();

        switch (Configuration.browser) {
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

    private static DesiredCapabilities getChromeCapabilities() {
        Map<String, Object> prefsMap = new HashMap<>();
        prefsMap.put("profile.default_content_settings.popups", 0);
        prefsMap.put("download.default_directory", downloadFilepath);
        prefsMap.put("intl.accept_languages", "en, en_US");

        ChromeOptions options = new ChromeOptions();
//        options.setBinary("D:\\Users\\AleksanDR\\IdeaProjects\\md\\frontend\\chrome\\win64-116.0.5845.96\\chrome-win64\\chrome.exe");
        options.setExperimentalOption("prefs", prefsMap);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        //options.addArguments("--lang=ru-RU");   Не работало только с этим параметром
        //Timed out receiving message from renderer
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-password-manager");
        options.addArguments("--remote-allow-origins=*");
//        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        //show browser window during tests
//        capabilities.setCapability("enableVNC", true);
        //enable video recording during tests
//        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        return capabilities;
    }

    private static DesiredCapabilities getFireFoxCapabilities() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
                "application/pdf,application/vnd.ms-excel,application/zip");
        profile.setPreference("pdfjs.disabled", true);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        options.addArguments("--test-type");
        options.addArguments("--disable-extensions");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        options.addArguments("--disable-gpu");
        options.addArguments("--disable-password-manager");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        //show browser window during tests
        capabilities.setCapability("enableVNC", true);
        //enable video recording during tests
//        capabilities.setCapability("enableVideo", false);
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        return capabilities;
    }

}
