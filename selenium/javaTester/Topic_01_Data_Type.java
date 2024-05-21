package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Topic_01_Data_Type {
    FirefoxDriver firefoxDriver = new FirefoxDriver();

    Select select = new Select(firefoxDriver.findElement(By.className("")));

    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();

    //Object
    Object name = "Automation FC";

    //Array
    int[] studentAge = {15, 20, 8};
    String[] studentName = {"Automation", "Testing"};


public static void main(String[] args) {
    boolean status = true;
    if(status) {
        Assert.assertTrue(true);
    } else {

    }
}
}