package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_12_Checkbox_Radio {

    WebDriver driver;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Default_Telerik() {
        // Case này web đổi từ Default sang Checkbox Custom nên thầy hướng dẫn xử lý Default ko chạy đc
        // Tuy nhiên mình vẫn giữ để xem cách viết code của checkbox default
        // Xử lý đúng theo checkbox custom -> làm ở tc4
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        sleepInSeconds(2);

        By rearSideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::span/input");
        By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");

        if (!driver.findElement(rearSideCheckbox).isSelected()) {
            driver.findElement(rearSideCheckbox).click();
        }
        sleepInSeconds(2);

        if (!driver.findElement(dualZoneCheckbox).isSelected()) { // true -> mới vào thân hàm if, còn nếu điều kiện mà false -> nó sẽ qua step tiếp theo
            driver.findElement(dualZoneCheckbox).click();
        }
        sleepInSeconds(2);

        // Verify checkbox đã được chọn
        Assert.assertTrue(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        // Bỏ chọn 2 checkbox
        uncheckToElement(rearSideCheckbox);
        uncheckToElement(dualZoneCheckbox);

        // Verify checkbox đã được bỏ chọn thành công
        Assert.assertFalse(driver.findElement(rearSideCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());

    }

    @Test
    public void TC_02_Default_Telerik_Radio() {
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        // Chọn chứ ko có bỏ chọn được
        By twoPetrolRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");
        By twoDieselRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::span/input");

        // Chọn 1 trong 2 ( ko chọn đc cả hai vì nó là single choose)
        checkToElement(twoPetrolRadio);
        //Verify
        Assert.assertTrue(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertFalse(driver.findElement(twoDieselRadio).isSelected());

        checkToElement(twoDieselRadio);
        Assert.assertFalse(driver.findElement(twoPetrolRadio).isSelected());
        Assert.assertTrue(driver.findElement(twoDieselRadio).isSelected());
    }

        @Test
        public void TC_03_Select_All_Checkbox() {
            driver.get("https://automationfc.github.io/multiple-fields/");
            List<WebElement> allCheckboxes = driver.findElements(By.cssSelector("span.form-checkbox-item input"));
            // Chọn hết tất cả checkbox
            for (WebElement checkbox: allCheckboxes)
            {
                if(!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
            // Verify hết tất cả các checkbox
            for(WebElement checkbox: allCheckboxes) {
                Assert.assertTrue(checkbox.isSelected());

            }
            // Chon 1 checkbox/ radio nào đó trong tất cả các checkbox/radio
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
            allCheckboxes = driver.findElements(By.cssSelector("span.form-checkbox-item input"));

            for(WebElement checkbox: allCheckboxes) {
                if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                    checkbox.click();
                    sleepInSeconds(1);

                }
             }

            for (WebElement checkbox : allCheckboxes) {
                if (checkbox.getAttribute("value").equals("Heart Attack")) {
                    Assert.assertTrue(checkbox.isSelected());
                } else {
                    Assert.assertFalse(checkbox.isSelected());
                }
            }


        }

        @Test
        public void TC_04_Custom_Checkbox() {
            driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
            sleepInSeconds(2);

            //Cách kiểm tra 1 element bị ẩn. Chạy lệnh sau ở tab console kiểm tra xem click đc ko, rồi dùng JS để handle
            //var radio = $x("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input")[0];
            //radio.click();
            By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(dualZoneCheckbox));
            sleepInSeconds(3);
            Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());

        }

        @Test
        public void TC_05_Custom_Google_Docs() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canThoRadio = By.xpath("//div[@aria-label = 'Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label = 'Quảng Nam']");
        By quangBinhCheckbox = By.xpath("//div[@aria-label = 'Quảng Bình']");
        // Verify radio is not selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(canThoRadio));
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");


        driver.findElement(canThoRadio).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");

        driver.findElement(quangNamCheckbox).click();
        driver.findElement(quangBinhCheckbox).click();
        sleepInSeconds(2);

        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"true");
        Assert.assertEquals(driver.findElement(quangBinhCheckbox).getAttribute("aria-checked"),"true");
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
    public void checkToElement(By byXpath) {
        // Nếu như element chưa đc chọn thì mới click
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
        }
        sleepInSeconds(2);
    }

    public void uncheckToElement(By byXpath) {
        // Nếu như element đc chọn thì mới click để bỏ chọn
        if (driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
        }
        sleepInSeconds(2);
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
