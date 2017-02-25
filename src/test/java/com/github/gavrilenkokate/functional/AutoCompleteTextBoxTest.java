package com.github.gavrilenkokate.functional;


import com.github.gavrilenkokate.common.BasicSeleniumTest;
import com.github.gavrilenkokate.common.Conf;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.github.gavrilenkokate.common.Constants.URL;
import static org.junit.Assert.assertEquals;

/**
 * Created by katsiarynahaurylenka on 2/23/17.
 */
public class AutoCompleteTextBoxTest extends BasicSeleniumTest {
    @Test
    public void testAutocompleteTextBox() {
        //given
        String expectedText = "New York City, New York, United States";
        driver().get(URL);
        //When
        WebElement autoCompleteBox = driver().findElement(By.name("city"));
        autoCompleteBox.click();
        autoCompleteBox.sendKeys("New");
        //wait for suggestedElement visibility
        WebDriverWait wait = new WebDriverWait(driver(), 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#eac-container-citiesInput > ul > li:nth-child(1)")));
        // Create object of Actions class to emulate user gestures
        Actions builder = new Actions(driver());
        WebElement suggestedElement = driver().findElement(By.cssSelector("#eac-container-citiesInput > ul > li:nth-child(1)"));
        //Use Mouse hover action for suggestedElement
        builder.moveToElement(suggestedElement).build().perform();
        builder.click(suggestedElement).build().perform();
        assertEquals(expectedText, suggestedElement.getText());


    }
}
