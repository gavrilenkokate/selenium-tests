package com.github.gavrilenkokate.common;


import com.github.gavrilenkokate.framework.DriverServiceFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;

import java.io.IOException;

import static com.github.gavrilenkokate.common.Conf.WEB_DRIVER;

/**
 * Basic test class that prepares selenium service and manages test's life-cycle:
 * creates web driver before each test case starts and closes it after completion.
 * <p>
 * Created by katsiaryna_haurylenka
 */
public abstract class BasicSeleniumTest {
    private static DriverService service;
    private WebDriver driver;

    // executed by junit before test class
    @BeforeClass
    public static void createAndStartService() throws IOException {
        service = DriverServiceFactory.create();
        service.start();
    }

    // stops web driver after all tests completed
    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    /**
     * Creates web driver.
     */
    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(), getDesiredCapabilities());
    }

    /**
     * Closes web driver.
     */
    @After
    public void quitDriver() {
        driver.quit();
    }

    public WebDriver driver() {
        return driver;
    }

    protected void clickJsVoid(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver();
        executor.executeScript("arguments[0].click();", element);
    }

    private static DesiredCapabilities getDesiredCapabilities() {
        switch (WEB_DRIVER) {
            case CHROME:
                return DesiredCapabilities.chrome();
            case GECKO:
                return DesiredCapabilities.firefox();
            default:
                throw new UnsupportedOperationException("unsupported driver: " + WEB_DRIVER);
        }
    }

}
