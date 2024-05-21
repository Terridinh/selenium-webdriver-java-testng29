package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.List;

public class Topic_14_Actions {

    WebDriver driver;
    Actions actions;

    JavascriptExecutor javascriptExecutor;
    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        //driver = new ChromeDriver();

        // actions giả lập lại hành vi của Mouse/ Keyboard/ Pen nên khi nó đang chạy mình k sử dụng các thiết bị này

        actions = new Actions(driver);

        // Khoi tao JavascriptExecutor
        javascriptExecutor = (JavascriptExecutor) driver;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover_Tooltip() {
        // Các hàm trong Actions đều phải dùng perform() mới thực hiện được
        //actions.click(driver.findElement(By.xpath(""))).perform();
        // Hàm release chỉ dùng duy nhất với clickAndHold()
        //actions.clickAndHold(driver.findElement(By.xpath(""))).release();
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        // Phải khởi tạo actions thì mới dùng đc ( có thể khởi tạo ở beforeClass hoặc khởi tạo ngay tại hàm dưới đây của tcs)
        actions.moveToElement(ageTextbox).perform();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Menu_Login() {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"))).perform();
        sleepInSeconds(2);
        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        sleepInSeconds(2);
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(),"Kids Home Bath");

    }
    @Test
    public void TC_03_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        // Tổng số chưa chọn
        List<WebElement> allNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
        // 20 ô
        Assert.assertEquals(allNumber.size(),20);
        // Chọn theo Block - Ngang/ Dọc
        // Chọn theo khối 1 -> 15
        actions.clickAndHold(allNumber.get(0)) // Click lên số 1 và giữ chuột
                .pause(2000) // Trong trường hợp muốn dừng lại ở action nào đó thì dùng hàm pause
                .moveToElement(allNumber.get(14)) // Di chuột trái đến số 15
                .release()  // Nhả chuột trái ra
                .perform(); // Execute tất cả các action trên
        sleepInSeconds(3);

        // Tổng các số đã chọn
        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(),12);
    }
    @Test
    public void TC_04_Click_And_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        String osName = System.getProperty("os.name");
        Keys keys;
        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }
        List<WebElement> allNumber = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumber.size(), 20);

        // Chọn 1 -> 15 theo đủ hàng đủ cột
        // Click and and + move to element: Chọn 1-> 12
        actions.clickAndHold(allNumber.get(0)).moveToElement(allNumber.get(11)).release().perform();
        // Nhấn Ctrl + chọn: 13 -> 15
        actions.keyDown(keys).perform();
        actions.click(allNumber.get(12))
                .click(allNumber.get(13))
                .click(allNumber.get(14))
                .keyUp(keys)
                .perform();

        List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("li.ui-selected"));
        Assert.assertEquals(allNumberSelected.size(), 15);

    }
    @Test
    public void TC_05_DoubleClick() {

        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
        // Cần scroll tới element rồi mới double click ( Chỉ dành riêng cho Firefox, còn Chrome thì k cần scroll cũng tìm đc element)
        if(driver.toString().contains("firefox")) {
            // scrollIntoView(true): kéo mép trên của element lên phía trên cùng của viewport
            // scrollIntoView(false): kéo mép dưới của element xuống phía dưới cùng của viewport

            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
            sleepInSeconds(3);

        } else {
            actions.scrollToElement(doubleClickButton).perform();
            sleepInSeconds(3);
        }

        actions.doubleClick(doubleClickButton).perform();
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(),"Hello Automation Guys!");
    }
    @Test
    public void TC_06_RightClick() {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        // Chưa click chuột phải thì nó đang ko hiển thị (invisible)
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        // Click chuột phải
        actions.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
        sleepInSeconds(2);

        // Mới click chuột phải lên - Các element sẽ được visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

        // move chuột đến và hover
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);
        // Sau khi hover vào thì class của element này được cập nhật lại - Kiểm tra sự kiện hover thành công
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-hover")).isDisplayed());

        // CLick lên Paste
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
        sleepInSeconds(2);

        driver.switchTo().alert().accept();
        sleepInSeconds(2);

        // Kiểm tra Paste ko còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-paste")).isDisplayed());

    }

    @Test
    public void TC_07_DragDropHTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));
        // Cách 1
        //actions.dragAndDrop(smallCircle, bigCircle).perform();
        // Cách 2
        actions.clickAndHold(smallCircle).moveToElement(bigCircle).release().perform();
        sleepInSeconds(2);
        Assert.assertEquals(bigCircle.getText(),"You did great!");
        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(),"#03a9f4");
    }
    @Test
    public void TC_08_DragDropHTML5_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

        String projectPath = System.getProperty("user.dir");

        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js";

        String jsContentFile = getContentFile(dragAndDropFilePath);
        //jsContentFile = jsContentFile + "$('div#column-a').simulateDragDrop({ dropTarget: 'div#column-b'});";

        // Thực thi đoạn lệnh JS
        javascriptExecutor.executeScript(jsContentFile);
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");
    }
    @Test
    public void TC_09_DragDropHTML5_Xpath() throws AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"A");

        dragAndDropHTML5ByXpath("//div[@id='column-b']", "//div[@id='column-a']");
        sleepInSeconds(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(),"A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(),"B");

    }
    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        // Setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        // Get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        // Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }
    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }
    public void sleepInSeconds(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
