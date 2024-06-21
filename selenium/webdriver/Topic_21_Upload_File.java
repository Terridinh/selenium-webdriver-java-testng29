package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_21_Upload_File {

    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    String batmanName = "batman.png";
    String workingName = "workingarea.jpg";
    String weddingName = "wedding.jpg";

    String batmanFilePath = projectPath + File.separator + "uploadFiles" + File.separator + batmanName;
    String workingFilePath = projectPath + File.separator + "uploadFiles" + File.separator + workingName;
    String weddingFilePath = projectPath + File.separator + "uploadFiles" + File.separator + weddingName;


    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Single_File() {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        // Fix cứng Path
        // String filePath = "/Users/nghiatran/Downloads/P2-56.jpg";

        // File này nằm ở đâu ( nếu file nằm ở ổ C thì chỉ chạy đc trong máy này thôi) -> vì có thể trên máy khác nó sẽ nằm ở ổ khác.
        // Tốt nhất để sử dụng máy nào cũng có thể làm dc -> Đưa vào chính trong cái Project này

        // Nếu máy khác dùng hệ điều hành khác chạy đc ko?
        // Để máy khác cũng chạy đc thì cần phải lấy đc đường dẫn tương đối của File đó
        // Đã setup trước phần Before Class
        By uploadBy = By.cssSelector("input[name= 'files[]']");
        driver.findElement(uploadBy).sendKeys(batmanFilePath);
        sleepInSeconds(2);

        driver.findElement(uploadBy).sendKeys(workingFilePath);
        sleepInSeconds(2);

        driver.findElement(uploadBy).sendKeys(weddingFilePath);
        sleepInSeconds(2);

        // Verify file đc load success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()= '" + batmanName +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()= '" + workingName +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()= '" + weddingName +"']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td button.start"));
        // Classic for ( Vòng lặp for cổ điển) -> dùng nếu phải check điều kiện trong vòng lặp
        //for (int i = 0; i < startButtons.size(); i++) {
           // startButtons.get(i).click();
            // sleepInSeconds(2);

        // For-each -> dùng nếu ko phải check điều kiện thì nó sẽ gọn hơn for cổ điển
            for (WebElement button: startButtons) {
                button.click();
                sleepInSeconds(2);
            }

            // Verify file được upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + batmanName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + workingName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + weddingName + "']")).isDisplayed());

    }


    @Test
    public void TC_02_Multiple_File() {

        driver.get("https://blueimp.github.io/jQuery-File-Upload/");
        By uploadBy = By.cssSelector("input[name= 'files[]']");
        driver.findElement(uploadBy).sendKeys(batmanFilePath + "\n" + workingFilePath + "\n" + weddingFilePath);
        sleepInSeconds(2);

        // Verify file đc load success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()= '" + batmanName +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()= '" + workingName +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class ='name' and text()= '" + weddingName +"']")).isDisplayed());

        List<WebElement> startButtons = driver.findElements(By.cssSelector("td button.start"));

        // For-each -> dùng nếu ko phải check điều kiện thì nó sẽ gọn hơn for cổ điển
        for (WebElement button: startButtons) {
            button.click();
            sleepInSeconds(2);
        }

        // Verify file được upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + batmanName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + workingName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[@title='" + weddingName + "']")).isDisplayed());
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
