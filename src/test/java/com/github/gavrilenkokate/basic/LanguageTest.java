package com.github.gavrilenkokate.basic;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.github.gavrilenkokate.basic.Constants.URL;
import static com.github.gavrilenkokate.basic.XPaths.CHANGE_LANGUAGE_LINK;
import static org.junit.Assert.assertEquals;

/**
 * Created by katsiarynahaurylenka on 1/26/17.
 */
@RunWith(JUnitParamsRunner.class)
public class LanguageTest extends BasicChromeTest {

    private Object[][] navBarItems() {
        return new Object[][]{
                // #1 - test case
                {"it",       new String[]{"voli", "tours", "automobili", "offerte", "blog", "hotel"}},
                // #2 - test case
                {"fr",       new String[]{"des vols", "tours", "des voitures", "des offres", "blog", "hôtels"}}
        };
    }

    @Test
    @Parameters(method = "navBarItems")
    @TestCaseName("[{index}] {method} lang: '{0}'")
    public void testSelectLanguage(String langID, String[] expectedNavBarItems) {
        //given
        driver().get(URL);
        WebElement changeLanguage = driver().findElement(By.xpath(CHANGE_LANGUAGE_LINK));
        // when
        clickJsVoid(changeLanguage);
        // select given lang from dropdown
        clickJsVoid(driver().findElement(By.id(langID)));

        // then
        WebElement navBarElem = driver().findElement(By.xpath("//div[contains(@class, 'navbar-collapse collapse')]/ul"));
        List<WebElement> elements = navBarElem.findElements(By.tagName("li"));
        int expectedNumOfElements = expectedNavBarItems.length;
        int actualNumOfElements = elements.size() - 1;

        assertEquals(expectedNumOfElements, actualNumOfElements);

        // skip first element with 0 index because it is not well formed
        elements = elements.subList(1, elements.size());

        for (int i = 0; i < expectedNavBarItems.length; i++) {
            String expectedElem = expectedNavBarItems[i];
            String actualElem = elements.get(i).getText().trim().toLowerCase();
            assertEquals(expectedElem, actualElem);
        }
    }

}
