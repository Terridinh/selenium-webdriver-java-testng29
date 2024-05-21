package webdriver;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.openqa.selenium.devtools.v85.network.model.Headers;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Topic_13_Alert {

    WebDriver driver;
    WebDriverWait explicitWait;
    By resultText = By.cssSelector("p#result");
    String username = "admin";
    String password = "admin";
    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Accept_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        // Chờ cho alert present
        // Nếu trong thời gian chờ mà xuất hiện thì tự switch vào
        // Nếu hết thời gian chờ mà chưa xuất hiện thì fail

        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        sleepInSeconds(3);
        //Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        //Lưu ý: Khi mình accept hoặc cancel rồi thì Alert sẽ mất luôn
        alert.accept();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked an alert successfully");

        // Cancel alert: void dismiss(); ( chỉ dùng cho Confirm Alert để cancel)
        // Accept alert: void accept(); (Dùng đc cho cả 3 loại Accept, Confirm và promp Alert)_alert.accept
        // Get text trong Alert ra: String getText(); ( Dùng cho Accept Alert)_alert.getText()
        // Nhập text vào Alert: void sendKeys(String keysToSend); ( dùng cho Promp Alert)

    }

    @Test
    public void TC_02_Confirm_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS Confirm");
        alert.dismiss();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You clicked: Cancel");

    }
    @Test
    public void TC_03_Promp_Alert() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(),"I am a JS prompt");

        String text = "Selenium Webdriver";
        alert.sendKeys(text);
        sleepInSeconds(3);

        alert.accept();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(resultText).getText(),"You entered: " + text);
    }

    @Test
    public void TC_04_Authentication_byPass_To_Url() {
        // Lưu ý, thư viện Alert không dùng cho Authentication Alert được
        // Authentication Alert phải dùng Chrome DevTool  Protocol (CDP) - nhưng chỉ dùng được với Chrome/Edge (Chromium)

        // Cách 1: Truyền trực tiếp Username & Password vào Ulr theo cú pháp dưới đây
        String username = "admin";
        String password = "admin";

        driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        // Cách 2:
        driver.get("http://the-internet.herokuapp.com/");
        String authenLinkUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
        String[] authenArray =  authenLinkUrl.split("//");
        System.out.println(authenArray[0]);
        System.out.println(authenArray[1]);
        driver.get(authenArray[0] + "//" + username + ":" + password + "@" + authenArray[1]);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());

        //driver.get(authenLinkUrl);

        // Cách khác: Chạy trên Windows (AutoIT)_Không chạy trên Mac và Linus (chỉ chạy trên Window)
        
    }

    @Test
    public void TC_05_Authentication_Selenium_4x() {

        //Chỉ chạy với Chrome
        // driver = new ChromeDriver();
        DevTools devTools = ((HasDevTools)driver).getDevTools();

        // Start new session
        devTools.createSession();

        // Enable the Network domain of devtools
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        // Encode username/password
        Map<String, Object> headers = new HashMap<String, Object>();
        String basicAuthen = "Basic" + new String(new Base64().encode(String.format("%s:%s",username,password).getBytes()));
        headers.put("Authorization",basicAuthen);

        // Set to Header
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        driver.get("http://the-internet.herokuapp.com/basic_auth");


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
