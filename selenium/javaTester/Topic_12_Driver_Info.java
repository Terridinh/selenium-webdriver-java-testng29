package javaTester;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12_Driver_Info {
    WebDriver driver;
    @Test
    public void testDriverInformation() {
        driver = new FirefoxDriver();
        // Ở trên OS nào
        // Browser nào
        // Browser class nào
        // ID của driver là gì
        System.out.println(driver.toString());
        //FirefoxDriver: firefox on mac (9ec361ba-e06a-4d2d-8ec0-9481792efa11)

        driver = new ChromeDriver();
        System.out.println(driver.toString());
        //ChromeDriver: chrome on mac (bc70e32e1eb482aa4008516691c6baa3)

        if(driver.toString().contains("firefox")) {
            // Scroll
        }
        driver.quit();


    }
}
