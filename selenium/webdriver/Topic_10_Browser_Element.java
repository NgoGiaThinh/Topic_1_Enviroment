package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_10_Browser_Element {
    WebDriver driver;

    public Topic_10_Browser_Element() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Page_Url() {
        driver.get("https://live.techpanda.org/");

        //click vào MyAccount tại Footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        //cách 1 - dùng nhiều hơn 2 lần
        String loginPageUrl = driver.getCurrentUrl();
        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        // cách 2 - dùng 1 lần duy nhất
        //Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        //chuyển quan trang Register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Page_Title() {
        driver.get("https://live.techpanda.org/");

        //click vào MyAccount tại Footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        Assert.assertEquals(driver.getTitle(), "Customer Login");

        //chuyển quan trang Register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_03_Page_Navigation() {
        driver.get("https://live.techpanda.org/");

        //click vào MyAccount tại Footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        //chuyển quan trang Register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertEquals(driver.getCurrentUrl(),"https://live.techpanda.org/index.php/customer/account/create/");

        //quay lại trang trước đó
        driver.navigate().back();

        Assert.assertEquals(driver.getCurrentUrl(), "https://live.techpanda.org/index.php/customer/account/login/");

        //chuyển tiếp về trang trước đó
        driver.navigate().forward();

        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_04_Page_Source() {

        driver.get("https://live.techpanda.org/");

        //click vào MyAccount tại Footer
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        //tuương đối = assertTrue/false
        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));


        //chuyển quan trang Register
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
