package integration.selenium.shipping_package_dimensions.test_case;

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
public class NavigateToMerchandiseTestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws Exception{
        driver.findElement(By.linkText(variables.get(SPDVariableNames.LOGIN_EMAIL))).click();
        driver.findElement(By.linkText("Merchandise")).click();
        SPDTestUtil.waitForElementPresent(driver, By.xpath("//div[@id='content']/div/div/div/div/h3[contains(text(),'Merchandise Editor')]"));
        assertTrue(SPDTestUtil.isElementPresent(driver, By.xpath("//div[@id='content']/div/div/div/div/h3[contains(text(),'Merchandise Editor')]")));
    }
}
