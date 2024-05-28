package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_18_Window_Tab {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Basic_Form() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String parentID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        sleepInSeconds(3);

        // Switch qua trang Google
        switchToWindowByTitle("Google");

        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");
        sleepInSeconds(3);

        // Switch quay lại trang Basic Form
        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
        driver.findElement(By.cssSelector("input#email")).sendKeys("tien@gmail.com");
        switchToWindowByTitle("Selenium WebDriver");

        closeAllWindowWithoutParent(parentID);

    }

    @Test
    public void TC_02_TechPanda() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        sleepInSeconds(3);

        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.xpath("//a[@title='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[@class='link-compare']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//span[text()='Compare']")).click();
        sleepInSeconds(3);

        switchToWindowByTitle("Products Comparison List - Magento Commerce");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");

        switchToWindowByTitle("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        sleepInSeconds(3);

    }

    @Test
    public void TC_03_Selenium_Version_4() {
        driver.get("https://automationfc.github.io/basic-form/index.html ");
        System.out.println("Driver ID Basic Form = " + driver.toString());
        // New 1 tab mới/ window mới
        WebDriver googleDriver = driver.switchTo().newWindow(WindowType.WINDOW);
        googleDriver.get("https://www.google.com.vn/");
        System.out.println("Driver ID Google = " + driver.toString());

        googleDriver.findElement(By.xpath("//textarea[@title='Tìm kiếm']")).sendKeys("Automation Test");
        sleepInSeconds(3);

        driver.switchTo().newWindow(WindowType.TAB).get("https://tiki.vn/");
        sleepInSeconds(3);

        switchToWindowByTitle("Selenium WebDriver");

        driver.findElement(By.xpath("//a[text()='LAZADA']")).click();



    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void switchToWindowByTitle (String expectedTitle) {
        // Lấy hết tất cả các ID của các Window/Tab
        Set<String> allIDs = driver.getWindowHandles();

        // Dùng vòng lặp duyệt qua Set ID ở trên
        for(String id: allIDs) {
            // Cho switch vào tưng ID trước
            driver.switchTo().window(id);
            sleepInSeconds(3);
            // Lấy ra title của Tab/window hiện tại
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    public void closeAllWindowWithoutParent(String parentID) {
        Set<String> allIDs = driver.getWindowHandles();
        for (String id : allIDs) {
        if (!id.equals(parentID)) {
            driver.switchTo().window(id);
            driver.close();
        }
        }
        driver.switchTo().window(parentID);
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
