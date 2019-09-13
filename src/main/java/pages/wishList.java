package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class wishList {

    @Test
    public void test() throws Exception {

        URL serverUrl = new URL("http://127.0.0.1:4723/wd/hub");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10");

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy_Nexus_Test");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("autoDismissAlerts", true);

        AppiumDriver driver = new AndroidDriver(serverUrl, capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        JavascriptExecutor js = driver;



        System.out.println("Step 1. Open a website");
        driver.get("https://m.rozetka.com.ua/");

        System.out.println("Step 2. Open menu");
        WebElement menuButton = driver.findElement(By.cssSelector(".header-tools-menu-btn"));
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menuButton.click();

        System.out.println("Step 3. Open catalog");
        WebElement catalog = driver.findElement(By.cssSelector("a[href*='/menu/']"));
        catalog.click();
        WebElement popUp = driver.findElement(By.className("popup-close"));
        popUp.click();

        System.out.println("Step 4. Go to electronic section");
        WebElement electronicSection = driver.findElement(By.cssSelector("a[href*='/menu/4627949/']"));
        electronicSection.click();

        System.out.println("Step 5. Go to mobile phones section");
        WebElement mobileSection = driver.findElement(By.cssSelector("a[href*='/catalog/80003/']"));
        mobileSection.click();

        System.out.println("Step 6. Narrow down search results by providing additional filter");
        WebElement filters = driver.findElement(By.cssSelector(".svg-icon.filter-icon.svg-blue"));
        filters.click();

        System.out.println("Step 7. Filters");
        WebElement newPhoneCheckBox = driver.findElement(By.cssSelector("div.filters-content > div > div:nth-child(1) > ul > li:nth-child(2) > a"));
        newPhoneCheckBox.click();
        WebElement iphoneCheckBox = driver.findElement(By.cssSelector("div:nth-child(3) > ul > li:nth-child(1) > a"));
        iphoneCheckBox.click();

        System.out.println("Step 8. Price");
        WebElement minPrice = driver.findElement(By.cssSelector(".decimal-inputs-left"));
        js.executeScript("arguments[0].scrollIntoView();", minPrice);
        minPrice.sendKeys("1");
        minPrice.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        minPrice.sendKeys("10000");

        WebElement maxPrice = driver.findElement(By.cssSelector(".decimal-inputs-right"));
        js.executeScript("arguments[0].scrollIntoView();", maxPrice);
        maxPrice.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        maxPrice.sendKeys("30000");

        System.out.println("Step 9. Color Black");
        WebElement blackColor = driver.findElement(By.cssSelector("div:nth-child(17) > ul > li:nth-child(1) > a > span.checkbox-label"));
        js.executeScript("arguments[0].scrollIntoView();", blackColor);
        blackColor.click();

        System.out.println("Step 10. Click show button");
        WebElement showResultButton = driver.findElement(By.cssSelector(".btn-link.btn-link-blue.filters-btn-show"));
        showResultButton.click();

        System.out.println("Step 11. Add phones to wish list");

        WebElement phone1 = driver.findElement(By.cssSelector("ul > li:nth-child(1) > div.catalog-list-i-b-left > div > a > svg"));
        WebElement phone2 = driver.findElement(By.cssSelector("ul > li:nth-child(2) > div.catalog-list-i-b-left > div > a > svg"));

        wait.until(ExpectedConditions.elementToBeClickable( phone1));
        phone1.click();
        phone2.click();
        WebElement phoneElement = driver.findElement(By.cssSelector("li:nth-child(2) > div.catalog-list-i-b-right > a:nth-child(4) > div > ul > li:nth-child(1) > span > span"));
        js.executeScript("arguments[0].scrollIntoView();",phoneElement);
        WebElement phone3 = driver.findElement(By.cssSelector("ul > li:nth-child(3) > div.catalog-list-i-b-left > div > a > svg"));
        WebElement phone4 = driver.findElement(By.cssSelector("ul > li:nth-child(4) > div.catalog-list-i-b-left > div > a > svg"));
        WebElement phone5 = driver.findElement(By.cssSelector("ul > li:nth-child(5) > div.catalog-list-i-b-left > div > a > svg"));
        phone3.click();
        phone4.click();
        phone5.click();


        System.out.println("Step 12. Go to wish list");
        WebElement menu = driver.findElement(By.cssSelector(".header-tools-menu-btn"));
        menu.click();
        WebElement wishList = driver.findElement(By.cssSelector("a[href*='/wishlists/']"));
        wishList.click();

        System.out.println("ASSERT If elements are 5");
        WebElement count = driver.findElement(By.cssSelector(".items-count"));
        Assert.assertEquals(Integer.parseInt(count.getText()), 5);

        System.out.println("ASSERT if actual products are the sase like expected");

        driver.findElement(By.cssSelector(".nav-l-i-link-inner")).click();

        List<String> actualProducts_name = new ArrayList<>();
        List<WebElement> element = driver.findElements(By.className("g-title"));
        for (WebElement phoneName : element) {
            actualProducts_name.add(phoneName.getText());
        }

        List<String> expectedProducts_name = new ArrayList<>();
        expectedProducts_name.add("Apple iPhone XR 64GB (Black) Dual SIM");
        expectedProducts_name.add("IPhone 7 128Gb Black");
        expectedProducts_name.add("Apple iPhone 7 32GB Jet Black (2423585)");
        expectedProducts_name.add("Apple iPhone 7 128GB Neverlock Black CPO");
        expectedProducts_name.add("Смартфон Apple iPhone XR 128GB Black (MRY92)");
        Assert.assertEquals(actualProducts_name, expectedProducts_name);

        System.out.println("Step 14. Close driver");
        driver.quit();
    }
}
