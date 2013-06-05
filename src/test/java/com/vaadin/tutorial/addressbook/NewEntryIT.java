package com.vaadin.tutorial.addressbook;

import com.vaadin.testbench.Parameters;
import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBench;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.screenshot.ImageFileUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * This class simply tries to enter a new contact, search
 * for it and verify that the new contact is in the contacts table.
 * <p/>
 * It is recommended to use the PageObject pattern (http://code.google.com/p/selenium/wiki/PageObjects)
 * in your tests. This example does not use the pattern in order to
 * keep everything in one place as the example itself is very simple.
 * <p/>
 * The *IT.java naming convention is from the maven-failsafe-plugin
 * telling it that this is an integration test and thus letting maven
 * run it at the appropriate time in the build process.
 */
public class NewEntryIT extends TestBenchTestCase {

    public static final String FIRST_NAME = "Sven";
    public static final String LAST_NAME = "Svensson";
    public static final String COMPANY_NAME = "Vaadin Ltd";
    /**
     * Grabs a screenshot whenever a test fails and quits the driver after the test.
     */
    @Rule
    public ScreenshotOnFailureRule screenshotOnFailure = new ScreenshotOnFailureRule(this, true);

    @Before
    public void setUp() {
        // Uncomment the FirefoxDriver line below to run with Firefox
        setDriver(TestBench.createDriver(new PhantomJSDriver(DesiredCapabilities.phantomjs())));
//        setDriver(TestBench.createDriver(new FirefoxDriver()));
        Parameters.setScreenshotReferenceDirectory("src/test/resources/reference-images");
        Parameters.setScreenshotErrorDirectory("target/testbench/errors");
        testBench().resizeViewPortTo(1024, 768);
    }

    @Test
    public void testEnterNewContact() throws Exception {
        driver.get("http://localhost:8080/?restartApplication");

        // Click the "new" button
        driver.findElement(By.id("new-contact")).click();

        // Enter the name
        WebElement firstName = driver.findElement(By.id("First-Name"));
        WebElement lastName = driver.findElement(By.id("Last-Name"));
        WebElement company = driver.findElement(By.id("Company"));
        // Clear first as the fields contain text
        firstName.clear();
        firstName.sendKeys(FIRST_NAME);
        lastName.clear();
        lastName.sendKeys(LAST_NAME);
        company.sendKeys(COMPANY_NAME);

        // Search for sven
        driver.findElement(By.id("search-field")).sendKeys(FIRST_NAME);

        // Wait for the text change event
        Thread.sleep(500);

        // Assert the entered data
        WebElement firstNameCell = driver.findElement(By.xpath("id('contact-list')//td[@class='v-table-cell-content'][1]"));
        WebElement lastNameCell = driver.findElement(By.xpath("id('contact-list')//td[@class='v-table-cell-content'][2]"));
        WebElement companyCell = driver.findElement(By.xpath("id('contact-list')//td[@class='v-table-cell-content'][3]"));
        Assert.assertEquals(FIRST_NAME, firstNameCell.getText());
        Assert.assertEquals(LAST_NAME, lastNameCell.getText());
        Assert.assertEquals(COMPANY_NAME, companyCell.getText());

        Assert.assertTrue(testBench().compareScreen(ImageFileUtil.getReferenceScreenshotFile("search-result.png")));
    }

}
