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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {

    WebDriver driver;

    // Dưới đây là Wait tường minh: có trạng thái cụ thể cho element
    // VD như các trạng thái: Visible/ Invisible/ Presence/ Number/ Clickable/...
    WebDriverWait explicitWait;

    @BeforeClass
    public void beforeClass() {

        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Dưới đây là Wait ngầm định: ko rõ ràng cho 1 trạng thái cụ thể nào của element hết
        // wait cho việc tìm element thôi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        // 1 - Click vào 1 thẻ để cho nó xổ hết các items trong dropdown ra
        // driver.findElement(By.cssSelector("span#number-button")).click();
        // 2.1 - Nó sẽ xổ ra chứa hết tất cả các items
        // 2.2 - Nó sẽ xổ ra nhưng chỉ chứa 1 phần và đang load thêm (ngàn/ triệu record)
        // Chờ cho nó xổ ra hết tất cả các item trong dropdown
        // Hàm explicitWait này để cho các giá trị xuất hiện đầy đủ trong Html
        /*explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
        // allItems này đang lưu trữ 19 items bên trong

        for (WebElement item : allItems) {
            String textItem = item.getText();
            System.out.println("Text item = " + textItem);

            if (textItem.equals("8")) {
                item.click();
                break; // Break là từ khoá thoát vòng lặp. Khi thoát vòng lặp có nghĩa là từ 9 -> 19 ko đc kiểm tra

            }
        }*/
        // 3.1 - Nếu như item cần chọn nó hiển thị thì click vào
        // 3.2 - Nếu như item cần chọn nằm bên dưới thì 1 số trường hợp cần scroll xuống để hiển thị lên rồi mới chọn (Angular/ React/ VueJS/..)
        // 4 - Trước khi click cần kiểm tra nếu như text của item bằng với item cần chọn thì click vào

        /*driver.findElement(By.cssSelector("span#number-button")).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul#number-menu div")));
        allItems = driver.findElements(By.cssSelector("ul#number-menu div"));
        for (WebElement item : allItems) {
            String textItem = item.getText();
            System.out.println("Text item = " + textItem);

            if (textItem.equals("5")) {
                item.click();
                break;*/
// Nếu khai báo giống như ở phần AfterClass thì không cần viết nhiều dòng code giống như ở trên, mà chỉ cần 1 dòng như dưới đây
        selectItemInDropdown("span#speed-button", "ul#speed-menu li", "Faster");
        sleepInSeconds(3);
        selectItemInDropdown("span#files-button", "ul#files-menu li", "ui.jQuery.js");
        sleepInSeconds(3);
        selectItemInDropdown("span#number-button", "ul#number-menu div", "15");
        sleepInSeconds(3);
        selectItemInDropdown("span#salutation-button", "ul#salutation-menu div", "Dr");
        sleepInSeconds(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(), "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(), "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(), "15");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(), "Dr");

    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        selectItemInDropdown("div#root", "div.item>span", "Matt");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
        sleepInSeconds(3);


        selectItemInDropdown("div#root", "div.item>span", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
        sleepInSeconds(3);

        selectItemInDropdown("div#root", "div.item>span", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
        sleepInSeconds(3);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("div.btn-group", "ul.dropdown-menu a", "Second Option");
        sleepInSeconds(3);

        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
        sleepInSeconds(3);


    }

    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search", "div.item span", "Anguilla");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Anguilla");
        sleepInSeconds(3);

        selectItemInEditableDropdown("input.search", "div.item span", "Belgium");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Belgium");
        sleepInSeconds(3);

        selectItemInEditableDropdown("input.search", "div.item span", "Afghanistan");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");
        sleepInSeconds(3);


    }

    @Test
    public void TC_05_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
        // Dropdown là default nhưng không sử dụng thư viện Select để verify
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value ='18']")).isSelected());
        sleepInSeconds(3);

        selectItemInDropdown("select[name='DateOfBirthMonth']", "select[name='DateOfBirthMonth']>option", "September");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']>option[value ='9']")).isSelected());
        sleepInSeconds(3);

        selectItemInDropdown("select[name='DateOfBirthYear']", "select[name='DateOfBirthYear']>option", "1994");
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']>option[value ='1994']")).isSelected());
        sleepInSeconds(3);

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

    // Những dữ liệu dùng để truyền vào sẽ xem là tham số
    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click();

        //vừa Wait vừa tìm element
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
    // Khi làm cho 1 dự án khác với hành vi khác thì cần sửa hàm theo đúng action của dự án đó
    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear();
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected);

        //vừa Wait vừa tìm element
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));
        for (WebElement item : allItems) {
            if (item.getText().equals(itemTextExpected)) {
                item.click();
                break;
            }
        }
    }
}
