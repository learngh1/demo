package integration.selenium.shipping_package_dimensions.test_case;

import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */
public interface SeleniumTestCase {
    void test(WebDriver driver, Map<String, String> variables) throws Exception;
}
