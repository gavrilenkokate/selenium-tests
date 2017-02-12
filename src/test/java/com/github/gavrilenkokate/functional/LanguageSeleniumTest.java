package com.github.gavrilenkokate.functional;

import com.github.gavrilenkokate.common.BasicSeleniumTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.github.gavrilenkokate.common.Constants.URL;
import static com.github.gavrilenkokate.common.XPaths.CHANGE_LANGUAGE_LINK;
import static com.github.gavrilenkokate.common.XPaths.MY_ACCOUNT_LINK;
import static org.junit.Assert.assertEquals;

/**
 * Created by katsiaryna_haurylenka.
 */
@RunWith(JUnitParamsRunner.class)
public class LanguageSeleniumTest extends BasicSeleniumTest {

    private Object[][] navBarItems() {
        return new Object[][]{
                {"it", new String[]{"voli", "tours", "automobili", "offerte", "blog", "hotel"}, "il mio account"},
                {"fr", new String[]{"des vols", "tours", "des voitures", "des offres", "blog", "hôtels"}, "mon compte"},
                {"pt", new String[]{"voos", "tours", "carros", "ofertas", "blog", "hotéis"}, "a minha conta"},
                {"en", new String[]{"flights", "tours", "cars", "offers", "blog", "hotels"}, "my account"},
                {"uk", new String[]{"рейсів", "тури", "автомобілів", "пропозиції", "блог", "готелі"}, "мій рахунок"}
        };
    }

    @Test
    @Parameters(method = "navBarItems")
    @TestCaseName("[{index}] {method} lang: '{0}'")
    public void testTranslateMainPageHeader(String langID, String[] expectedNavBarItems, String expectedAccountTitle) {
        //given
        driver().get(URL);
        WebElement changeLanguage = driver().findElement(By.xpath(CHANGE_LANGUAGE_LINK));
        // when
        clickJsVoid(changeLanguage);
        // select given lang from dropdown
        clickJsVoid(driver().findElement(By.id(langID)));

        // then
        String actualAccountTitle = driver().findElement(By.xpath(MY_ACCOUNT_LINK)).getText().trim().toLowerCase();
        WebElement navBarElem = driver().findElement(By.xpath("//div[contains(@class, 'navbar-collapse collapse')]/ul"));
        List<WebElement> elements = navBarElem.findElements(By.tagName("li"));
        int expectedNumOfElements = expectedNavBarItems.length;
        int actualNumOfElements = elements.size() - 1;

        assertEquals(expectedNumOfElements, actualNumOfElements);
        assertEquals(expectedAccountTitle, actualAccountTitle);

        // skip first element with 0 index because it is not well formed
        elements = elements.subList(1, elements.size());

        for (int i = 0; i < expectedNavBarItems.length; i++) {
            String expectedElem = expectedNavBarItems[i];
            String actualElem = elements.get(i).getText().trim().toLowerCase();
            assertEquals(expectedElem, actualElem);
        }
    }

}
