package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_30_Wait_I_II {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    public Topic_30_Wait_I_II() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //tìm thấy 1 element
        //vào sẽ tìm thấy element ngay chứ ko cần chờ hết timeout của Implicit
        //driver.findElement(By.cssSelector("input#email"));


        //tìm thấy 0 element
        //vào sẽ ko thấy element và sẽ tìm đi tìm lại mỗi 0.5s 1 lần cho đến khi hê timeout là 10s = 20 lần
        // đánh fail testcase tại vị trí này và show ra lỗi NoSuchElementException
        //driver.findElement(By.cssSelector("input#emailAddress"));


        //tìm thấy nhiều hơn 1 element
        //nó sẽ luôn thao tác với element đầu tiên
        driver.findElement(By.cssSelector("input:not([type='hidden'])")).sendKeys("selenium");
        //driver.findElement(By.cssSelector("a[title='My Account']")).click();


    }

    @Test
    public void TC_02() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //tìm thấy 1 element
        //Trả về 1 element + ko cần chờ hết timeout
//        List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
//        System.out.println(elements.size());

        //tìm thấy 0 element
        //vào sẽ ko thấy element và sẽ tìm đi tìm lại mỗi 0.5s 1 lần cho đến khi hê timeout là 10s = 20 lần
        // Không đánh fail testcase mà trả về list rỗng (empty) = 0

        //List<WebElement> elements = driver.findElements(By.cssSelector("input#emailAddress"));
        //System.out.println(elements.size());

        //không hiển thị và ko có trong HTML
        //Assert.assertEquals(elements.size(), 0);

        //nhiều hơn 1
       List<WebElement> elementList = driver.findElements(By.cssSelector("input:not([type='hidden'])"));

       //thao tác vs List
        elementList.get(1).sendKeys("selenium");
        elementList.get(3).sendKeys("selenium");

        //thao tác với tất cả
        for (WebElement element : elementList){
            element.sendKeys("selenium");
        }

    }

    @Test
    public void TC_03() {
        driver.get("");
    }


    @Test
    public void TC_04() {
        driver.get("");
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
