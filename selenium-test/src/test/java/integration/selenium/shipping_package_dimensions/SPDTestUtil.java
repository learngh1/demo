package integration.selenium.shipping_package_dimensions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static integration.selenium.shipping_package_dimensions.SPDEditTestSuite.ELEMENT_PRESENT_TIMEOUT_SECS;
import static org.junit.Assert.fail;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */
public class SPDTestUtil {
    public static void clickSaveButtonAndWait(WebDriver driver, long millis) throws InterruptedException {
        clickButtonAndWait(driver, "Save", millis);
    }

    public static void clickDeleteButtonAndWait(WebDriver driver, long millis) throws InterruptedException {
        clickButtonAndWait(driver, "Delete", millis);
    }

    private static WebElement findButtonByText(WebDriver driver, String buttonText) {
        return driver.findElement(By.linkText(buttonText));
    }

    private static void clickButtonAndWait(WebDriver driver, String buttonText, long millis) throws InterruptedException {
        WebElement button = findButtonByText(driver, buttonText);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("scroll(250, 0)");
        button.click();
        Thread.sleep(millis);
    }

    public static boolean isElementPresent(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void waitForElementPresent(WebDriver driver, By by) throws InterruptedException {
        for (int second = 0;; second++) {
            if (second >= ELEMENT_PRESENT_TIMEOUT_SECS) fail("timeout");
            try { if (SPDTestUtil.isElementPresent(driver, by)) break; } catch (Exception e) {}
            Thread.sleep(1000);
        }
    }
}
