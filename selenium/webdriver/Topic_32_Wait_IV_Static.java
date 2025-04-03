package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_32_Wait_IV_Static {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    public Topic_32_Wait_IV_Static() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));



        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test(description = "thời gian implicit ngắn hơn element nó xuất hiện")
    public void TC_01() {


        //step 1: click vào button start
        driver.findElement(By.cssSelector("div#start>button")).click();

        //loading icon sẽ xuất hiện trong vòng 5s
        sleepInSecond(0);

        //step 2: HelloWorld text hiển thị = cần verify
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");


    }

    @Test(description = "thời gian implicit bằng thời gian element nó xuất hiện")
    public void TC_02() {

        //step 1: click vào button start
        driver.findElement(By.cssSelector("div#start>button")).click();

        //loading icon sẽ xuất hiện trong vòng 5s
        sleepInSecond(3);


        //step 2: HelloWorld text hiển thị = cần verify
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test(description = "thời gian implicit dài hơn element nó xuất hiện")
    public void TC_03() {


        //step 1: click vào button start
        driver.findElement(By.cssSelector("div#start>button")).click();

        //loading icon sẽ xuất hiện trong vòng 5s
        sleepInSecond(5);


        //step 2: HelloWorld text hiển thị = cần verify
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }


    @Test(description = "thời gian implicit = 0")
    public void TC_04() {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        //step 1: click vào button start
        driver.findElement(By.cssSelector("div#start>button")).click();

        //loading icon sẽ xuất hiện trong vòng 5s
        sleepInSecond(10);


        //step 2: HelloWorld text hiển thị = cần verify
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    public void sleepInSecond(long timeInSecond){
        try {
            Thread.sleep(timeInSecond * 1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_05() {
        driver.get("");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
