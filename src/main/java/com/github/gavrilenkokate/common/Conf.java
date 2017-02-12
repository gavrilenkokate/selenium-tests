package com.github.gavrilenkokate.common;

import static com.github.gavrilenkokate.common.Conf.WebDriver.CHROME;
import static com.github.gavrilenkokate.common.Conf.WebDriver.fromDriverName;

/**
 * Created by katsiaryna_haurylenka.
 */
public final class Conf {

    private Conf() {
    }

    private static String WEB_DRIVER_DIR = System.getProperty("user.dir") + "/web_driver";
    private static String OS = System.getProperty("os.name").toLowerCase();

    public static final WebDriver WEB_DRIVER = getWebDriver();
    public static final String PATH_TO_CHROME_EXECUTABLE = getPathToExecutable(WebDriver.CHROME);
    public static final String PATH_TO_GECKO_EXECUTABLE = getPathToExecutable(WebDriver.GECKO);

    public static String getPathToExecutable(WebDriver webDriver) {
        return WEB_DRIVER_DIR + "/" + getDriverFolder() + "/" + getDriverNameWithExtension(webDriver);
    }

    private static WebDriver getWebDriver(){
        String webDriverName = System.getProperty("WEB_DRIVER", CHROME.getDriverName()).toLowerCase();
        WebDriver webDriver = fromDriverName(webDriverName);
        if (webDriver == null) {
            throw new UnsupportedOperationException("unsupported driver type: " + webDriverName);
        }
        return webDriver;
    }

    private static String getDriverFolder() {
        if (isWindows()) {
            return "win";
        } else if (isMac()) {
            return "mac";
        } else if (isUnix()) {
            return "unix";
        } else throw new UnsupportedOperationException(String.format("%s OS isn't supported", OS));
    }

    private static String getDriverNameWithExtension(WebDriver webDriver) {
        if (isWindows()) {
            return webDriver.driverName + ".exe";
        } else {
            return webDriver.driverName;
        }
    }

    private static boolean isWindows() {
        return OS.contains("win");
    }

    private static boolean isMac() {
        return OS.contains("mac");
    }

    private static boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    public enum WebDriver {

        CHROME("chromedriver"),
        GECKO("geckodriver");

        private String driverName;

        WebDriver(String driverName) {
            this.driverName = driverName;
        }

        public static WebDriver fromDriverName(String driverName) {
            for (WebDriver webDriver : WebDriver.values()) {
                if (webDriver.driverName.equals(driverName)) {
                    return webDriver;
                }
            }
            return null;
        }

        public String getDriverName() {
            return driverName;
        }
    }
}
