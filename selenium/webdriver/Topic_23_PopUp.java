package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class Topic_23_PopUp {
    WebDriver driver;
    Actions action;
    public Topic_23_PopUp() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01() throws InterruptedException {
        this.driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        By loginDialog = By.cssSelector("div#custom-dialog div[role='dialog']");

        //kiểm tra popup hiển thị
        Assert.assertTrue(driver.findElement(loginDialog).isDisplayed());

        driver.findElement(By.cssSelector("input[placeholder='Tài khoản đăng nhập']")).sendKeys("automation");
        driver.findElement(By.name("password")).sendKeys("automationfc");

        driver.findElement(By.xpath("//div[@id='custom-dialog']//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(),"Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        //close popup
        driver.findElement(By.cssSelector("div#custom-dialog h2>button")).click();

        Thread.sleep(2000);
        //kiểm tra popup ko còn hiển thị

        Assert.assertEquals(driver.findElements(loginDialog).size(), 0);

    }

    @Test
    public void TC_02_NotInDom() throws InterruptedException {

        driver.get("https://tiki.vn/");

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();
        Thread.sleep(2000);


        By loginPopup = By.cssSelector("div.ReactModalPortal div[role='dialog']");

        //kiểm tra popup hiển thị
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("div.ReactModalPortal button.btn-close")).click();
        Thread.sleep(2000);

        //kiểm tra popup ko còn hiển thị
        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);

    }

    @Test
    public void TC_03() {
    }


    @Test
    public void TC_04() {
    }


    @Test
    public void TC_05() {
    }


    @AfterClass
    public void afterClass(){ driver.quit();}
}
