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
public class LoginTestCase implements SeleniumTestCase {
    @Override
    public void test(WebDriver driver, Map<String, String> variables) throws InterruptedException {
        String loginEmail = variables.get(SPDVariableNames.LOGIN_EMAIL);
        driver.findElement(By.id("inputEmail")).click();
        driver.findElement(By.id("inputEmail")).clear();
        driver.findElement(By.id("inputEmail")).sendKeys(loginEmail);
        driver.findElement(By.id("inputPassword")).click();
        driver.findElement(By.id("inputPassword")).clear();
        driver.findElement(By.id("inputPassword")).sendKeys(variables.get(SPDVariableNames.LOGIN_PASSWORD));
        driver.findElement(By.linkText("Continue")).click();
        SPDTestUtil.waitForElementPresent(driver, By.linkText(loginEmail));
        assertTrue(SPDTestUtil.isElementPresent(driver, By.linkText(loginEmail)));
    }
}
