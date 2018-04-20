package integration.selenium.shipping_package_dimensions.test_case;

import integration.selenium.shipping_package_dimensions.SPDEditTestSuite;
import integration.selenium.shipping_package_dimensions.SPDTestUtil;
import integration.selenium.shipping_package_dimensions.SPDVariableNames;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */
public class CreateItem1WithSPDTestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception {
        String item_1_name = variables.get(SPDVariableNames.ITEM_1_NAME);
        driver.findElement(By.linkText("Add")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys(item_1_name);
        driver.findElement(By.xpath("//div/div[2]/form/fieldset/span/div/label/span[contains(text(),'Shippable')]")).click();
        driver.findElement(By.id("sc_height")).clear();
        driver.findElement(By.id("sc_height")).sendKeys("1");
        driver.findElement(By.id("sc_length")).clear();
        driver.findElement(By.id("sc_length")).sendKeys("2");
        driver.findElement(By.id("sc_width")).clear();
        driver.findElement(By.id("sc_width")).sendKeys("3");
        driver.findElement(By.id("sc_weight")).clear();
        driver.findElement(By.id("sc_weight")).sendKeys("4");

        SPDTestUtil.clickSaveButtonAndWait(driver, SPDEditTestSuite.WAIT_AFTER_CLICK_BUTTON_MILLIS);

        assertTrue(SPDTestUtil.isElementPresent(driver, By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'" + item_1_name + "')]")));
    }
}
