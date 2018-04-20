package integration.selenium.shipping_package_dimensions;

/**
 * @author atrotsenko
 * created on 28.03.2018
 */

import integration.selenium.shipping_package_dimensions.test_case.CreateItem1WithSPDTestCase;
import integration.selenium.shipping_package_dimensions.test_case.CreateItem2WoSPDTC;
import integration.selenium.shipping_package_dimensions.test_case.DeleteItem1TestCase;
import integration.selenium.shipping_package_dimensions.test_case.DeleteItem2TestCase;
import integration.selenium.shipping_package_dimensions.test_case.LoginTestCase;
import integration.selenium.shipping_package_dimensions.test_case.NavigateToMerchandiseTestCase;
import integration.selenium.shipping_package_dimensions.test_case.SelectItem1CheckNoSpdTestCase;
import integration.selenium.shipping_package_dimensions.test_case.SelectItem1CheckSpdSetNewValuesTestCase;
import integration.selenium.shipping_package_dimensions.test_case.SelectItem1CheckSpdTestCase;
import integration.selenium.shipping_package_dimensions.test_case.SelectItem2CheckNoSpdSetSpdTestCase;
import integration.selenium.shipping_package_dimensions.test_case.SelectItem2CheckSpdTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SPDEditTestSuite {
    private WebDriver driver;
    public static int ELEMENT_PRESENT_TIMEOUT_SECS = 2;
    public static int IMPLICIT_TIMEOUT_SECS = 1;
    public static int WAIT_AFTER_CLICK_BUTTON_MILLIS = 1000;

    private Map<String, String> variables;

    private String webUrl;

    private void fillVariables(Map<String, String> variables) {
        variables.put(SPDVariableNames.LOGIN_EMAIL, "some@email.com");
        variables.put(SPDVariableNames.LOGIN_PASSWORD, "somepassword");
        variables.put(SPDVariableNames.ITEM_1_NAME, "Item_1_for_test_spd");
        variables.put(SPDVariableNames.ITEM_2_NAME, "Item_2_for_test_spd");
    }

    @Before
    public void setUp() throws Exception {
        variables = new HashMap<>();
        fillVariables(variables);
        driver = createDriver();
    }

    private WebDriver createDriver() throws MalformedURLException {
        WebDriver driver;
        String seleniumUrl = System.getProperty("selenium.url");

        //remote driver
        if (seleniumUrl != null) {
            webUrl = "http://uventex_web:8080";

            ELEMENT_PRESENT_TIMEOUT_SECS = 3;
            IMPLICIT_TIMEOUT_SECS = 5;
            WAIT_AFTER_CLICK_BUTTON_MILLIS = 3000;

            driver = new RemoteWebDriver(new URL(seleniumUrl), DesiredCapabilities.chrome());
            driver.manage().window().maximize();
            return driver;
        }

        //local driver
        webUrl = System.getProperty("webUrl");
        if (webUrl == null) {
            webUrl = "http://127.0.0.1:8080";
        }

        String chromeDriverPath = System.getProperty("webdriver.chrome.driver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.addArguments("excludeSwitches", "ignore-certificate-errors");
        //options.addArguments("--headless");
        options.addArguments(String.format("--window-size=%s", "1800,900"));

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.binary", chromeDriverPath);
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new ChromeDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT_SECS, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void testSPDEditSuite() throws Exception {
        driver.get(webUrl + "/#login");

        new LoginTestCase().test(driver, variables);

        new NavigateToMerchandiseTestCase().test(driver, variables);

        new CreateItem1WithSPDTestCase().test(driver, variables);

        new CreateItem2WoSPDTC().test(driver, variables);

        new SelectItem1CheckSpdSetNewValuesTestCase().test(driver, variables);

        new SelectItem2CheckNoSpdSetSpdTestCase().test(driver, variables);

        new SelectItem1CheckSpdTestCase().test(driver, variables);

        new SelectItem2CheckSpdTestCase().test(driver, variables);

        new SelectItem1CheckNoSpdTestCase().test(driver, variables);

        new DeleteItem1TestCase().test(driver, variables);

        new DeleteItem2TestCase().test(driver, variables);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

