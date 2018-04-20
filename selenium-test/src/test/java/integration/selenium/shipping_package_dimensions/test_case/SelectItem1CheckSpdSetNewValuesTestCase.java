package integration.selenium.shipping_package_dimensions.test_case;

import integration.selenium.shipping_package_dimensions.SPDEditTestSuite;
import integration.selenium.shipping_package_dimensions.SPDTestUtil;
import integration.selenium.shipping_package_dimensions.SPDVariableNames;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */
public class SelectItem1CheckSpdSetNewValuesTestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception {
        String item_1_name = variables.get(SPDVariableNames.ITEM_1_NAME);
        driver.findElement(By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'" + item_1_name + "')]")).click();
        assertEquals(item_1_name, driver.findElement(By.id("name")).getAttribute("value"));
        assertTrue(driver.findElement(By.id("cb_shippable")).isSelected());
        assertEquals("1", driver.findElement(By.id("sc_height")).getAttribute("value"));
        assertEquals("2", driver.findElement(By.id("sc_length")).getAttribute("value"));
        assertEquals("3", driver.findElement(By.id("sc_width")).getAttribute("value"));
        assertEquals("4", driver.findElement(By.id("sc_weight")).getAttribute("value"));
        driver.findElement(By.id("sc_height")).clear();
        driver.findElement(By.id("sc_height")).sendKeys("10");
        driver.findElement(By.id("sc_length")).clear();
        driver.findElement(By.id("sc_length")).sendKeys("20");
        driver.findElement(By.id("sc_width")).clear();
        driver.findElement(By.id("sc_width")).sendKeys("30");
        driver.findElement(By.id("sc_weight")).clear();
        driver.findElement(By.id("sc_weight")).sendKeys("40");
        SPDTestUtil.clickSaveButtonAndWait(driver, SPDEditTestSuite.WAIT_AFTER_CLICK_BUTTON_MILLIS);
    }
}
