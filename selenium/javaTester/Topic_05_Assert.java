package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_05_Assert {

    @Test
    public void verifyTestNG() {
        WebDriver driver;
        driver = new FirefoxDriver();
        driver.get("https://www.facebook.com/");

        // Trong Java có nhiều thư viện để verify dữ liệu
        // Testing Framework (Unit/ Integration/ UI Automation Test)
        //JUnit 4/ Test NG/ JUnit 5/ Hamcrest/ AssertJ/..

        // kiểu dữ liệu nhận vào là boolean (true/false)
        // khi mong muốn điều kiện trả về là đúng thì dùng assertTrue để verify
        Assert.assertTrue(driver.getPageSource().contains("You must log in to continue."));

        // Khi mong muốn điều kiện trả về là sai thì dùng assertFalse để verify
        Assert.assertFalse(driver.getPageSource().contains("Please enter your email address or mobile number to search for your account."));

        // Các hàm trả về kiểu dữ liệu là boolean thì dùng Assert True/False để verify
        // Quy tắc: Các hàm bắt đầu tiền tố là isXXX -> dữ liệu trả về dạng Boolean và sẽ dùng AssertTrue/False để verify
        // WebElement
        driver.findElement(By.id("")).isDisplayed();
        driver.findElement(By.id("")).isEnabled();
        driver.findElement(By.id("")).isSelected();
        new Select(driver.findElement(By.id(""))).isMultiple();

        // Thường dùng cho Unit Test/ không dùng cho UI Test ( chỉ đưa vào lấy ví dụ để tham khảo)
        Object name = null;
        Assert.assertNull(name);
        // Khi khai báo name = testing -> nó k null nữa ( NotNull là đúng)
        name = "Testing";
        Assert.assertNotNull(name);

        // Mong đợi 1 điều kiện nó giống như thực tế Actual = Expected (tuyệt đối)
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

    }
    }
