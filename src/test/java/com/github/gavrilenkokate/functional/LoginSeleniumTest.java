package com.github.gavrilenkokate.functional;

import com.github.gavrilenkokate.common.BasicSeleniumTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.github.gavrilenkokate.common.Constants.ACCOUNT_URL;
import static com.github.gavrilenkokate.common.Constants.PASSWORD;
import static com.github.gavrilenkokate.common.Constants.URL;
import static com.github.gavrilenkokate.common.Constants.USER_EMAIL;
import static com.github.gavrilenkokate.common.XPaths.MY_ACCOUNT_LINK;

/**
 * Created by katsiaryna_haurylenka.
 */
public class LoginSeleniumTest extends BasicSeleniumTest {

    @Test
    public void testLogin() {
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
