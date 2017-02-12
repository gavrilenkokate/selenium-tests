package com.github.gavrilenkokate.functional;

import com.github.gavrilenkokate.common.BasicSeleniumTest;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.github.gavrilenkokate.common.Constants.URL;

/**
 * Created by katsiaryna_haurylenka.
 */
public class HomePageTest extends BasicSeleniumTest {

    @Test
    public void testOpenHomePage(){
        driver().get(URL);
        new WebDriverWait(driver(), 5).until(ExpectedConditions.urlToBe(URL));
    }

}
