package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {

    WebDriver driver;

    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() {
        driver.findElement(By.id("FirstName")).sendKeys("Terri");
    }

    @Test
    public void TC_02_Class() {
        driver.findElement(By.className("header-logo"));

    }

    @Test
    public void TC_03_Name() {
        driver.findElement(By.name("DateOfBirthDay"));

    }
    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));

    }

    @Test
    public void TC_05_LinkText() {
        driver.findElement(By.linkText("Shipping & returns"));

    }

    @Test
    public void TC_06_Partial_LinkText() {
        driver.findElement(By.partialLinkText("vendor account"));

    }

    @Test
    public void TC_07_CSS() {
        //Css với ID
        driver.findElement(By.cssSelector("input[id='FirstName']"));
        driver.findElement(By.cssSelector("input#FirstName"));
        driver.findElement(By.cssSelector("#FirstName"));

        //Css với Class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        driver.findElement(By.cssSelector("div.page-title"));
        driver.findElement(By.cssSelector(".page-title"));

        //Css với Name
        driver.findElement(By.cssSelector("input[name='FirstName']"));

        //Css với Tagname
        driver.findElement(By.cssSelector("input"));

        //Css với Link
        driver.findElement(By.cssSelector("a[href='/customer/addresses'"));

        //Css với Partial Link
        driver.findElement(By.cssSelector("a[href*=addresses]"));
        //driver.findElement(By.cssSelector("a[href^=addresses'"));
        //driver.findElement(By.cssSelector("a[href$=addresses'"));


    }

    @Test
    public void TC_08_Xpath() {
        //Xpath với ID
        driver.findElement(By.xpath("//input[@id='FirstName']"));

        //Xpath với Class
        driver.findElement(By.xpath("//div[@class='page-title']"));

        //Xpath với Name
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        //Xpath với Tagname
        driver.findElement(By.xpath("//input"));

        //Xpath với Link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));


        //Xpath với Partial Link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        driver.findElement(By.xpath("//a[contains(text(),'Addresses')]"));




    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
