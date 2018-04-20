package integration.selenium.shipping_package_dimensions.test_case;

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
public class SelectItem2CheckSpdTestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception {
        String item_2_name = variables.get(SPDVariableNames.ITEM_2_NAME);
        driver.findElement(By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'" + item_2_name + "')]")).click();
        assertEquals(item_2_name, driver.findElement(By.id("name")).getAttribute("value"));
        assertTrue(driver.findElement(By.id("cb_shippable")).isSelected());
        assertEquals("102", driver.findElement(By.id("sc_height")).getAttribute("value"));
        assertEquals("202", driver.findElement(By.id("sc_length")).getAttribute("value"));
        assertEquals("302", driver.findElement(By.id("sc_width")).getAttribute("value"));
        assertEquals("402", driver.findElement(By.id("sc_weight")).getAttribute("value"));
    }
}
