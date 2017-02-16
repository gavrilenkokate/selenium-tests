package com.github.gavrilenkokate.functional;

import com.github.gavrilenkokate.common.BasicSeleniumTest;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.github.gavrilenkokate.common.Constants.BLOG_URL;
import static com.github.gavrilenkokate.common.Constants.CARS_URL;
import static com.github.gavrilenkokate.common.Constants.FLIGHTS_URL;
import static com.github.gavrilenkokate.common.Constants.HOTELS_URL;
import static com.github.gavrilenkokate.common.Constants.OFFERS_URL;
import static com.github.gavrilenkokate.common.Constants.TOURS_URL;
import static com.github.gavrilenkokate.common.Constants.URL;

/**
 * Created by katsiaryna_haurylenka.
 */
public class NavBarClassLinksTest extends BasicSeleniumTest {

    @Test
    public void checkOfLinkTest() {
        //given
        String[] navBarLinks = new String[]{FLIGHTS_URL, TOURS_URL, CARS_URL, OFFERS_URL, BLOG_URL, HOTELS_URL};
        driver().get(URL);

        //when
        for (int i = 0; i < navBarLinks.length; i++) {
            List<WebElement> listOfLinks = driver().findElements(By.xpath("//div[contains(@class, 'navbar-collapse collapse')]/ul[1]/li"));
            // skip first element
            listOfLinks = listOfLinks.subList(1, listOfLinks.size());

            listOfLinks.get(i).click();
            waitForUrl(navBarLinks[i]);
            driver().navigate().back();
            waitForUrl(URL);
        }
    }

    // waits util the given url is available
    private void waitForUrl(String url) {
        new WebDriverWait(driver(), 5).until(ExpectedConditions.urlToBe(url));
    }

}
