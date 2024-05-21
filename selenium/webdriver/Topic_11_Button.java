package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.time.Duration;

public class Topic_11_Button {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");
        WebElement registerButton = driver.findElement(By.cssSelector("input.egov-button"));

        // Verify button bị disable khi chưa click vào checkbox
        Assert.assertFalse(registerButton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSeconds(2);
        // Verify button enable khi da click vao checkbox
        Assert.assertTrue(registerButton.isEnabled());

        // Lấy ra mã màu nền của button
        String registerBackgroundRGB = registerButton.getCssValue("background-color");
        System.out.println( "Background color RGB= " + registerBackgroundRGB);

        // Convert từ kiểu String (mã RGB) qua kiểu Color
        Color registerBackgroundColour = Color.fromString(registerBackgroundRGB);

        // Convert qua kiểu Hexa
        String registerBackgroundHexa = registerBackgroundColour.asHex();
        System.out.println( "Background color Hexa= " + registerBackgroundHexa);
        System.out.println( "Background color Hexa= " + registerBackgroundHexa.toUpperCase());
        System.out.println( "Background color Hexa= " + registerBackgroundHexa.toLowerCase());

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(), "#ef5a00");

        // 1 - Viết 1 hàm để tự convert ra Hexa
        // 2 - Dùng thư viện Selenium Color - thư viện khác Java

    }

    @Test
    public void TC_02_Fahasa_Button() {

        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.fahasa.com/customer/account/create");
        WebElement loginButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(loginButton.isEnabled());

        //Assert.assertEquals(Color.fromString(loginButton.getCssValue("background")).asHex().toLowerCase(),"##e0e0e0");

        System.out.println(loginButton.getCssValue("background"));
        String loginButtonColor = loginButton.getCssValue("background");
        // Extract first color form Linear-Gradient
        String firstColor = loginButtonColor.substring(loginButtonColor.indexOf("rgb("), loginButtonColor.indexOf(")") + 1);
        // Convert first Color to String
        Color rgbColor1 = Color.fromString(firstColor);
        // Convert first color from string to hexa
        String hexColor1 = rgbColor1.asHex().toLowerCase();
        // Verify first color rgb and first color hexa
        Assert.assertEquals(hexColor1, "#e0e0e0");

        // Extract second color from Linear-Gradient
        String secondColor = loginButtonColor.substring(loginButtonColor.indexOf("rgb("), loginButtonColor.indexOf(")") + 2);
        Color rgbColor2 = Color.fromString(secondColor);
        String hexColor2 = rgbColor2.asHex().toLowerCase();
        Assert.assertEquals(hexColor2, "#e0e0e0");

        // Nhập email/ Pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("tien@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSeconds(2);
        Assert.assertTrue(loginButton.isEnabled());
        Assert.assertEquals(Color.fromString(loginButton.getCssValue("background-color")).asHex().toUpperCase(),"#C92127");

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
