package com.github.gavrilenkokate.functional;

import com.github.gavrilenkokate.common.BasicSeleniumTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.github.gavrilenkokate.common.Constants.URL;
import static org.junit.Assert.assertEquals;


/**
 * Created by katsiaryna_haurylenka.
 */
public class CurrencySeleniumTest extends BasicSeleniumTest {

    @Test
    public void testCurrency() {
        //given
        driver().get(URL);
        String[] currencies = {"USD", "GBP", "SAR", "EUR", "PKR", "KWD", "JPY", "INR", "CNY", "TRY"};
        int expectedCount = currencies.length;
        //when
        WebElement currencyDropDown = driver().findElement(By.name("currency"));
        //select given currency from dropdown
        Select selectCurrency = new Select(currencyDropDown);
        List<WebElement> options = selectCurrency.getOptions();
        //then
        assertEquals(expectedCount, options.size());
        for (int i = 0; i < currencies.length; i++) {
            String expectedCurrency = currencies[i];
            String actualCurrency = options.get(i).getText();
            assertEquals(expectedCurrency, actualCurrency);
        }
    }
}


