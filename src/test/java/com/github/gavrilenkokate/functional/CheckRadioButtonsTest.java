package com.github.gavrilenkokate.functional;


import com.github.gavrilenkokate.common.BasicSeleniumTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.github.gavrilenkokate.common.Constants.URL;


/**
 * Created by katsiarynahaurylenka on 2/23/17.
 */
public class CheckRadioButtonsTest extends BasicSeleniumTest {

    @Test
    public void radioButton() {
        //given
        driver().get(URL);
        driver().findElement(By.partialLinkText("Tours")).click();
        WebDriverWait wait = new WebDriverWait(driver(), 3);
        wait.until(ExpectedConditions.visibilityOf(driver().findElement(By.id("collapse1"))));

        List<WebElement> radioButtons = driver().findElements(By.className("iCheck-helper"));
        for (WebElement radioButton : radioButtons) {
            radioButton.click();
        }
    }
}



























