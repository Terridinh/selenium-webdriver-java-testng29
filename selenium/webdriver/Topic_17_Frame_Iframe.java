package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Frame_Iframe {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Form_Site() {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSeconds(3);

        // Check Iframe hiển thị
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        Assert.assertTrue(formIframe.isDisplayed());

        // Switch vào frame/iframe trước khi thao tác với các element bên trong
        // Có 3 cách
        // Cách 1: Switch bằng index
        // driver.switchTo().frame(0);
        // Cách 2: Switch bằng Name/ ID
        // driver.switchTo().frame("frame-one85593366");
        // Cách 3: Switch bằng cách dùng element -> nên dùng cách này vì nó ít thay đổi nhất
        driver.switchTo().frame(formIframe);

        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");
        sleepInSeconds(3);
        // Switch ra lại trang A = defaultContent
        driver.switchTo().defaultContent();

        // Click button Login
        driver.findElement(By.cssSelector("nav.header--desktop-floater a[title='Log in']")).click();

        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

    }

    @Test
    public void TC_02_KynaEnglish() {
        driver.get("https://skills.kynaenglish.vn/");
        // Web ngừng hoạt động :(
    }

    @Test
    public void TC_03_Frame_HDFCBank() {
        driver.get("https://netbanking.hdfcbank.com/netbanking/");

        driver.switchTo().frame("login_page");
        driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("luis_suarez");
        sleepInSeconds(3);
        driver.findElement(By.cssSelector("a.login-btn")).click();

        //driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("123456789");

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
