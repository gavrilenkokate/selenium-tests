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
public class AutoCompleteTextBoxTest extends BasicSeleniumTest {


    @Test
    public void testLocationAutocompleteBestMatch() {
        //given
        String expectedText = "mo";

        driver().get(URL);
        //When
        WebElement autoCompleteBox = driver().findElement(By.name("city"));
        autoCompleteBox.click();
        autoCompleteBox.sendKeys("mo");

        //wait for autoSuggest visibility
        WebDriverWait wait = new WebDriverWait(driver(), 3);

        // Store the auto suggest webElement location
        WebElement autoSuggest = driver().findElement(By.cssSelector("#eac-container-citiesInput > ul"));
        wait.until(ExpectedConditions.visibilityOf(autoSuggest));

        // Store the all auto suggest element in the list
        List<WebElement> suggestedElementsToSelect = autoSuggest.findElements(By.tagName("li"));
        //then
        for (WebElement suggestion : suggestedElementsToSelect) {

            if (suggestion.getText().toLowerCase().contains(expectedText)) {
                suggestion.click();
                break;
            }

        }

    }
}
