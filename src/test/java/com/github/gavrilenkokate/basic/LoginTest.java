package com.github.gavrilenkokate.basic;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.github.gavrilenkokate.basic.Constants.*;
import static com.github.gavrilenkokate.basic.XPaths.MY_ACCOUNT_LINK;

/**
 * Created by katsiarynahaurylenka on 1/26/17.
 */
public class LoginTest extends BasicChromeTest {

    @Test
    public void testLogin() throws InterruptedException {
        // given
        driver().get(URL);
        WebElement myAccountRef = driver().findElement(By.xpath(MY_ACCOUNT_LINK));
        clickJsVoid(myAccountRef);
        // when
        driver().findElement(By.partialLinkText("Login")).click();
        driver().findElement(By.name("username")).sendKeys(USER_EMAIL);
        driver().findElement(By.name("password")).sendKeys(PASSWORD);
        driver().findElement(By.xpath("//button[contains(@type, 'submit')]")).click();
        // then
        new WebDriverWait(driver(), 5).until(ExpectedConditions.urlToBe(ACCOUNT_URL));
    }
}
