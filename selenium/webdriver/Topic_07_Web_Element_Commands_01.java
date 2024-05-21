package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_07_Web_Element_Commands_01 {

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    @Test
    public void TC_01_Element() {
        // Tìm và trả về 1 Element ( chưa tương tác lên)
        driver.findElement(By.id(""));

        // Tìm và tương tác lên Element
        driver.findElement(By.id("")).click();
        driver.findElement(By.id("")).sendKeys();

        // Tìm và lưu nó vào 1 biến WebElement (chưa tương tác)
        // khi có dùng biến này ít nhất 2 lần trở lên
        WebElement fullNameTextbox = driver.findElement(By.id(""));
        fullNameTextbox.clear();
        fullNameTextbox.sendKeys("Test");
        fullNameTextbox.getAttribute("value");

        By testBy = By.id("");

        // Hàm clear dùng để xoá dữ liệu trong 1 field cho phép nhâp (editable)
        //Textbox/ TextArea/ Dropdown(Editable)
        // Thường được sử dụng trước Hàm SendKeys để đảm bảo tính toàn vẹn dữ liệu
        driver.findElement(testBy).clear();

        // Dùng để nhập liệu vào các field bên trên
        driver.findElement(testBy).sendKeys("Test");

        // Dùng để click lên Element
        driver.findElement(testBy).click();

        // Tìm từ node cha vào node con ( không dùng)
        driver.findElement(By.id("form-validate")).findElement(By.id("firstname"));
        driver.findElement(By.id("form-validate")).findElements(By.cssSelector("input"));

        driver.findElement(By.cssSelector("form#form-validate input#firstname"));

        // Trả về 1 element khớp với điều kiện
        WebElement fullNameText = driver.findElement(By.id(""));

        // Trả về nhiều element khớp với điều kiện
        List<WebElement> textboxes = driver.findElements(By.cssSelector(""));

        // Java non Generic -> không ràng buộc kiểu dữ liệu
        ArrayList name = new ArrayList();
        name.add("Automation FC");
        name.add(15);
        name.add('B');
        name.add(true);

        // Java Generic
        ArrayList<String> address = new ArrayList<String>();
        address.add("Automation FC");

        // Dùng để verify 1 checkbox/ radio/dropdown đã được chọn hay chưa
        driver.findElement(By.id("")).isSelected();
        Assert.assertTrue(driver.findElement(By.id("")).isSelected());
        Assert.assertFalse(driver.findElement(By.id("")).isSelected());

        // Dropdown (default/custom)
        Select select = new Select(driver.findElement(By.id("")));

        // Dùng để verify 1 element có hiển thị hay ko
        Assert.assertFalse(driver.findElement(By.id("")).isDisplayed());

        // Dùng để verify 1 elêmnt có được thao tác lên hay ko (ko phải chế độ read-only)
        Assert.assertTrue(driver.findElement(By.id("")).isEnabled());
        Assert.assertFalse(driver.findElement(By.id("")).isEnabled());

        // Html Element
        // <input type="text" class="inputtext _58mg _5dba _2ph-" data-type="text"
        // name="firstname" value="" aria-required="true" placeholder="" aria-label="First name" id="u_0_b_Me">
        driver.findElement(By.id("")).getAttribute("name");
        driver.findElement(By.id("")).getAttribute("class");
        driver.findElement(By.id("")).getAttribute("type");

        // Liên quan đến Tab Accessibility và Tab Property trong Element
        driver.findElement(By.id("")).getAccessibleName();
        driver.findElement(By.id("")).getDomAttribute("checked");
        driver.findElement(By.id("")).getDomProperty("baseURI");

        // Tab Styles trong Elements
        // Dùng để verify font/ Size/ Color/ Background
        driver.findElement(By.id("")).getCssValue("background-color");

        // Vị trí của element so với độ phân giải màn hình
        // Hàm getLocation sẽ trả về dữ liệu kiểu Point
        driver.findElement(By.id("")).getLocation();
        // Khai báo kiểu Point
        Point nameTextboxLocation = driver.findElement(By.id("")).getLocation();
        nameTextboxLocation.getX();
        nameTextboxLocation.getY();

        // :ấy ra chiều rộng + cao
        Dimension addressSize = driver.findElement(By.id("")).getSize();

        // Location + Size
        // Hàm getRect trả về dữ liệu kiểu Rectangle
        driver.findElement(By.id("")).getRect();
        // Khai báo kiểu Rectangle
        Rectangle nameTextboxRect = driver.findElement(By.id("")).getRect();

        // Location
        Point namePoint = nameTextboxRect.getPoint();
        // Size
        Dimension nameSize = nameTextboxRect.getDimension();
        nameSize.getHeight();
        nameSize.getWidth();

        // Shadow Element ( Sẽ học trong bài JavascriptExecutor)
        driver.findElement(By.id("")).getShadowRoot();

        // Từ 1 id/class/name/css/ xpath có thể truy ra ngược lại Tagname của Element đó
        driver.findElement(By.id("firstname")).getTagName(); //input
        driver.findElement(By.className("#firstname")).getTagName(); //input

        driver.findElement(By.id("")).getText();

        // Chụp hình bị lỗi và lưu hình dưới dạng Byte/File/Base64
        driver.findElement(By.id("")).getScreenshotAs(OutputType.FILE);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BYTES);
        driver.findElement(By.id("")).getScreenshotAs(OutputType.BASE64);

        // Form (element nào là thẻ form hoặc nằm trong thẻ form)_VD như trong form Register/ Login/ Search...
        // Hàm này có hành vi giống phím Enter
        driver.findElement(By.id("")).submit();

    }

    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
