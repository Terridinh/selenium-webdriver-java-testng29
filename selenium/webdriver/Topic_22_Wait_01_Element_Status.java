package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Element_Status {

    WebDriver driver;
    WebDriverWait explicitWait;
    By reconfirmEmailTextbox = By.cssSelector("input[name='reg_email_confirmation__']");
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Visible() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("tien@gmail.com");
        sleepInSeconds(3);
        // Điều kiện 1 - Element có xuất hiện ở trên UI và có trong cây Html
        // Tại thời điểm này, confirm email textbox đang hiển thị ( Visible) -> Can apply Wait Visible
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmailTextbox));

        // Kiểm tra nó hiển thị
        Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        // Điều kiện 2: Element ko có trên UI nhưng có trong Html
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);
        // Tại thời điểm này, confirm email textbox đang ko hiển thị ( Invisible) -> Can apply Wait Invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        // Kiểm tra nó không hiển thị
        // Step này sẽ chạy nhanh và trả về kết quả Pass
        Assert.assertFalse(driver.findElement(reconfirmEmailTextbox).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_Not_In_DOM() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);

        // Click vào icon Close để đóng popup laị
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);

        // Điều kiện 3: Không xuất hiện trên UI và cũng k có trong Html
        // Tại thời điểm này, confirm email textbox đang ko hiển thị ( Invisible) -> Can apply Wait Invisible
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmailTextbox));
        //Kiểm tra nó không hiển thị ( ko dùng cách isDisplayed để verify vì element ko có trong DOM nên sẽ bị fail. Sẽ học cách verify sau
        //Assert.assertTrue(driver.findElement(reconfirmEmailTextbox).isDisplayed());
    }
    @Test
    public void TC_03_Presence() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(2);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("tien@gmail.com");
        sleepInSeconds(3);

        // Điều kiện 1 - Element có xuất hiện ở trên UI và có trong cây Html
        // Tại thời điểm này, confirm email textbox đang hiển thị ( Visible) -> Can apply Wait Visible
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSeconds(3);

        // Điều kiện 2: Element ko có trên UI nhưng có trong Html
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmailTextbox));

    }
    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.cssSelector("a[data-testid='open-registration-form-button']")).click();
        sleepInSeconds(3);
        // Tại thời điểm này element có xuất hiện và mình sẽ findElement
        WebElement reconfirmEmail = driver.findElement(reconfirmEmailTextbox);

        // Click vào icon Close để đóng popup laị
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
        sleepInSeconds(3);

        // Điều kiện 3: Không xuất hiện trên UI và cũng k có trong Html
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmEmail));

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
