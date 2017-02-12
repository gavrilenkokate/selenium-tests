package com.github.gavrilenkokate.framework;

import com.github.gavrilenkokate.common.Conf;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.service.DriverService;

import java.io.File;

import static com.github.gavrilenkokate.common.Conf.WEB_DRIVER;

/**
 * Created by katsiaryna_haurylenka.
 */
public final class DriverServiceFactory {

    private DriverServiceFactory() {
    }

    /**
     * Configures and creates service to manage web driver.
     *
     * @return service to manage web driver
     */
    public static DriverService create() {
        switch (WEB_DRIVER) {
            case CHROME:
                return new ChromeDriverService.Builder()
                        .usingDriverExecutable(new File(Conf.PATH_TO_CHROME_EXECUTABLE))
                        .usingAnyFreePort()
                        .build();

            case GECKO:
                return new GeckoDriverService.Builder()
                        .usingDriverExecutable(new File(Conf.PATH_TO_GECKO_EXECUTABLE))
                        .usingAnyFreePort()
                        .build();

            default:
                throw new UnsupportedOperationException("unsupported driver type: " + WEB_DRIVER);
        }
    }
}
