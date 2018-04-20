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
public class DeleteItem2TestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception {
        String item_2_name = variables.get(SPDVariableNames.ITEM_2_NAME);
        driver.findElement(By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'" + item_2_name + "')]")).click();
        assertEquals(item_2_name, driver.findElement(By.id("name")).getAttribute("value"));
        SPDTestUtil.clickDeleteButtonAndWait(driver, SPDEditTestSuite.WAIT_AFTER_CLICK_BUTTON_MILLIS);
        SPDTestUtil.waitForElementPresent(driver, By.xpath("//html/body/div[5]/div/div[2]/div/div/div/div[3]/div[3]/a[1]"));

        driver.findElement(By.xpath("//html/body/div[5]/div/div[2]/div/div/div/div[3]/div[3]/a[1]")).click();
        Thread.sleep(1000);
        assertFalse(SPDTestUtil.isElementPresent(driver, By.xpath("//div/div/table/tbody/tr/td/div[contains(text(),'Item_2_for_test_spd')]")));
    }
}
