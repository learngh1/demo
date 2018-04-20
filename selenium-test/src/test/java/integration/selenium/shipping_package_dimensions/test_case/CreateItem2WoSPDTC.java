package integration.selenium.shipping_package_dimensions.test_case;

import integration.selenium.shipping_package_dimensions.SPDEditTestSuite;
import integration.selenium.shipping_package_dimensions.SPDTestUtil;
import integration.selenium.shipping_package_dimensions.SPDVariableNames;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */
public class CreateItem2WoSPDTC implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception {
        String item_2_name = variables.get(SPDVariableNames.ITEM_2_NAME);
        driver.findElement(By.linkText("Add")).click();
        assertFalse(driver.findElement(By.id("cb_shippable")).isSelected());
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(item_2_name);
        SPDTestUtil.clickSaveButtonAndWait(driver, SPDEditTestSuite.WAIT_AFTER_CLICK_BUTTON_MILLIS);
        assertTrue(SPDTestUtil.isElementPresent(driver, By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'" + item_2_name + "')]")));
    }
}
