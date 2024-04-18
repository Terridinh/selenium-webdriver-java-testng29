package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands_01 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
    //Muốn dùng được phải khởi tạo
        // Nếu không khởi tạo thì sẽ bị lỗi (Lỗi phổ biến nhất: NullPointerException)

        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new SafariDriver();
        driver = new EdgeDriver();
        //Cách viết ImplicitWait của Selenium ver 3/2/1
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Cách viết ImplicitWait của Selenium ver 4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void Tt_01_Browser() throws MalformedURLException {
        //mở ra 1 page url bất kì
        driver.get("https://www.facebook.com/");

        //Nếu như có 1 tab/window thì tính năng tương tự quit
        //Nhiều hơn 1 thì nó sẽ đóng cái nó đang active
        driver.close();

        //Đóng browser (ko care bao nhiêu tab/window)
        driver.quit();

        //2 hàm này sẽ bị ảnh hưởng Timeout của implicitWait (ImplicitWait này là để chờ cho tìm thấy element)

        //Nó sẽ đi tìm với loại By nào và trả về element nếu như được tìm thấy (trả về kiểu dữ liệu WebElement)
        //Ko được tìm thấy: Fail tại step này - throw exception: NoSuchElementException
        driver.findElement(By.id("email"));

        //Khai báo 1 biến để lưu trữ lại Element
        WebElement emailAddressTextbox = driver.findElement(By.id("email"));

        //Nó sẽ đi tìm với loại By nào và trả về 1 danh sách element nếu như được tìm thấy (trả về kiểu dữ liệu WebElement)
        driver.findElements(By.xpath("//input[@type='checkbox']"));

        //Khai báo biến để lưu trữ list Element
        //Ko được tìm thấy: ko bị fail - trả về 1 list rỗng ( 0 element)
        List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        checkboxes.get(1).click();

        // các hàm lấy dữ liệu ra
        // Mục đích lấy dữ liệu ra là để verify
        // Dùng để lấy ra Url của màn hình/page hiện tại đang đứng
        driver.getCurrentUrl();

        // Lấy ra page source HTML/CSS/JS của page hiện tại
        // Dùng getPageSource để verify 1 cách tương đối
        driver.getPageSource();
        driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners.");
        Assert.assertTrue(driver.getCurrentUrl().contains("The Apple and Google Play logos are trademarks of their respective owners."));

        // Lấy ra Title của page hiện tại
        driver.getTitle();

        // Lấy ra ID của cửa sổ/Tab hiện tại
        // Dùng để phục vụ cho bài Handle Window và
        driver.getWindowHandle();
        driver.getWindowHandles();

        //Nếu chỉ dùng 1 lần thì ko khai báo biến
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.facebook.com/");
        //Còn nếu dùng lại nhiều lần thì khai báo biến
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(loginPageUrl,"https://www.facebook.com/");

        // Học trong bài Cookies - Framework
        driver.manage().getCookies();

        // Mục đích dùng để get ra những log ở Dev Tool - Framework
        driver.manage().logs().get(LogType.DRIVER);

        // Apply cho việc tìm element ( findElement/findElements)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        // Chờ cho page được load xong
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Set trước khi dùng với thư viện JavascriptExecutỏ
        // Inject 1 đoạn code JS vào trong Browser/Element
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // Selenium 4 mới có ( Selenium 3 sẽ ko có 3 hàm này). Thường ko dùng các hàm này
        driver.manage().timeouts().getImplicitWaitTimeout();
        driver.manage().timeouts().getPageLoadTimeout();
        driver.manage().timeouts().getScriptTimeout();

        // Muốn chạy tcs full màn hình
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        // Test GUI
        // Dùng để test Responsive (Resolution)
        driver.manage().window().setSize(new Dimension(1366,768));
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().setSize(new Dimension(2568,1440));

        driver.manage().window().getSize();

        // Get cho browser ở vị trí nào so với độ phân giải màn hình ( run trên màn hình có kích thước bao nhiêu)
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        // Dùng để điều hướng trang web
        driver.navigate().back();
        driver.navigate().refresh();
        driver.navigate().forward();

        // Thao tác với history của web page (back/forward)
        driver.navigate().to("https://www.facebook.com/");
        driver.navigate().to(new URL("https://www.facebook.com/"));
        driver.get("https://www.facebook.com/");

        // Hay thao tác với Alert/ Window(Tab)/ Frame (iFrame)
        driver.switchTo().alert().accept();
        driver.switchTo().alert().dismiss();
        driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");

        // Lấy ra ID của cửa sổ/Tab hiện tại
        // Handle Window/ Tab
        String homePageWindowID = driver.getWindowHandle();
        driver.switchTo().window(homePageWindowID);

        // Switch/ handle frame (iframe) có 3 cách dùng
        // Index. ID (name)/ Element
        driver.switchTo().frame(0);
        driver.switchTo().frame("342538739");
        driver.switchTo().frame(driver.findElement(By.id("")));

        // Switch về Html chứa frame trước đó
        driver.switchTo().defaultContent();

        // Từ frame trong đi ra frame ngoài chứa nó
        driver.switchTo().parentFrame();

    }

    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
