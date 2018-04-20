package integration.selenium.shipping_package_dimensions.test_case;

import integration.selenium.shipping_package_dimensions.SPDEditTestSuite;
import integration.selenium.shipping_package_dimensions.SPDTestUtil;
import integration.selenium.shipping_package_dimensions.SPDVariableNames;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */
public class SelectItem2CheckNoSpdSetSpdTestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception {
        String item_2_name = variables.get(SPDVariableNames.ITEM_2_NAME);
        driver.findElement(By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'" + item_2_name + "')]")).click();
        assertEquals(item_2_name, driver.findElement(By.id("name")).getAttribute("value"));
        assertFalse(driver.findElement(By.id("cb_shippable")).isSelected());
        driver.findElement(By.id("cb_shippable")).click();
        driver.findElement(By.id("sc_height")).clear();
        driver.findElement(By.id("sc_height")).sendKeys("102");
        driver.findElement(By.id("sc_length")).clear();
        driver.findElement(By.id("sc_length")).sendKeys("202");
        driver.findElement(By.id("sc_width")).clear();
        driver.findElement(By.id("sc_width")).sendKeys("302");
        driver.findElement(By.id("sc_weight")).clear();
        driver.findElement(By.id("sc_weight")).sendKeys("402");
        SPDTestUtil.clickSaveButtonAndWait(driver, SPDEditTestSuite.WAIT_AFTER_CLICK_BUTTON_MILLIS);
    }
}
