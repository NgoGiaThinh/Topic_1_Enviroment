package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_36_Wait_VI_Mixing {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    public Topic_36_Wait_VI_Mixing() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Only_Implicit() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //dùng timeout của Implicit
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("input#email"));

    }

    @Test
    public void TC_02_Implicit_And_Explicit() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //01 - equal: 10s

        //02 - Emplicit > Explicit: 12s (12-10)
        //02 - Emplicit > Explicit: 12s (12-5)
        //02 - Emplicit > Explicit: 10s (12-5)


        //03 - Emplicit > Explicit: 20s (10-12)
        //03 - Emplicit > Explicit: 26s (5-12)
        //03 - Emplicit > Explicit: 20s (5-10)

        //03 - Emplicit > Explicit: 7s (2-5)
        //03 - Emplicit > Explicit: 7s (2-5)
        //03 - Emplicit > Explicit: 7s (2-5)

        //Implicit sẽ luôn được ưu tiên để chạy trước
        //Explicit sẽ chạy sau tầm nửa giây - 3/5s - gần hoàn thành mới kích hoạt Explicit chạy (hên xui)
        //tránh gây hiểu nhầm: set 2 wait bằng nhau


        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        System.out.println("Start = " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        }catch (Exception e){
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }



    }

    @Test
    public void TC_03_Only_Explicit_By() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //lấy 10s của Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }


    @Test
    public void TC_04_Only_Explicit_WebElement() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //dùng timeout của Explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Explicit = 10
        explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#advice-required-entry-email"))));
    }


    @Test
    public void TC_05() {
        driver.get("");
    }

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
