package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Scope {
    // Các biến được khai báo ở bên ngoài hàm -> có phạm là Class => Gọi là biến Global ( biến toàn cục)
    // Có thể dùng cho tất cả các hàm trong 1 Class
    WebDriver driver;
    String homePageUrl = "https://www.facebook.com/"; //Khai báo: Declare
    String fullName = "Automation FC"; //Khai báo + khởi tạo (Initial)

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_() {
        //Các biến được khai báo ở trong 1 hàm/ trong 1 block code -> Phạm vi cục bộ (local)/ Biến local
        //Chỉ được dùng trong hàm nó được khai báo/ block code được sinh ra

        String homePageUrl = "https://www.facebook.com/";

        // Trong 1 hàm nếu có 2 biến cùng tên ( 1 biến Global, 1 biến Local) thì nó sẽ ưu tiên lấy biến local dùng
        // 1 biến local nếu đc gọi tới dùng mà chưa được khởi tạo thì sẽ bị lỗi
        // Biến local chưa khởi tạo mà gọi ra dùng nó sẽ báo lỗi ngay (compile code)
        driver.get(homePageUrl);

        //Nếu trong 1 hàm có 2 biến cùng tên (Global/Local) -> ưu tiên dùng biến local. Tuy nhiên mình muốn lấy biến Global để dùng -> Dùng từ khoá this
        //Biến Global chưa khởi tạo mà gjoi ra dùng thì sẽ ko báo lỗi ở level compile code. Nhưng level runtime thì sẽ báo lỗi
        driver.get(this.homePageUrl);

    }

    @Test
    public void TC_02_() {

    }
    @Test
    public void TC_03_() {

    }
    @Test
    public void TC_04_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
