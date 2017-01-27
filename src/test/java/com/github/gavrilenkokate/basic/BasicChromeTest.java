package com.github.gavrilenkokate.basic;


import com.github.gavrilenkokate.common.Conf;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;

public abstract class BasicChromeTest {
    private static ChromeDriverService service;
    private WebDriver driver;

    // executed by junit before test class
    @BeforeClass
    public static void createAndStartService() throws IOException {
        // configures and creates service to manage chromedriver
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(Conf.PATH_TO_CHROME_EXECUTABLE))
                .usingAnyFreePort()
                .build();
        // starts chromedriver
        service.start();
    }

    // stops chromedriver after all tests completed
    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    // executed before each test in the current test class
    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
    }

    @After
    public void quitDriver() {
        driver.quit();
    }

    public WebDriver driver() {
        return driver;
    }

    void clickJsVoid(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver();
        executor.executeScript("arguments[0].click();", element);
    }

}
