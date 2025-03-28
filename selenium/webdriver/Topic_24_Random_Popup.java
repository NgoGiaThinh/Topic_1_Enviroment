package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_24_Random_Popup {
    WebDriver driver;
    Actions action;
    public Topic_24_Random_Popup() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_InDom() throws InterruptedException {
        this.driver.get("https://dehieu.vn/");

        Thread.sleep(5000);
        By popup = By.cssSelector("div.modal-content");

        if(driver.findElement(popup).isDisplayed()){
            //case 1 : nếu popup hiển thị close đi rồi click vào đăng nhập
            System.out.println(" nếu popup hiển thị thì CLose đi rồi click vào Đăng nhập");
            driver.findElement(By.cssSelector("div.modal-content button.close")).click();
        }

        //case 2: nếu popup ko hiển thị thì click vào đăng nhập luôn
        System.out.println("nếu popup ko hiển thị thì click vào đăng nhập luôn");

        driver.findElement(By.cssSelector("a.signin-site-menu")).click();

        //verify form login hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div.b-login")).isDisplayed());

    }

    @Test
    public void TC_02_KMPlayer_InDom() throws InterruptedException {

        this.driver.get("https://www.kmplayer.com/home");

        Thread.sleep(5000);
        By popup = By.cssSelector("div.pop-container");

        if(driver.findElement(popup).isDisplayed()){
            //case 1 : nếu popup hiển thị close đi rồi click vào đăng nhập
            System.out.println(" nếu popup hiển thị thì CLose đi rồi qua step tiếp theo");
            driver.findElement(By.cssSelector("div.pop-container div.close")).click();
            Thread.sleep(2000);
        }

        //case 2: nếu popup ko hiển thị thì click vào đăng nhập luôn
        System.out.println("nếu popup ko hiển thị thì hover va PC và click KMPlayer");
        new Actions(driver).moveToElement(driver.findElement(By.cssSelector("li.pc.pc64x"))).perform();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//li[@class='pc']/a[text()='KMPlayer']")).click();

        //verify form login hiển thị
        Assert.assertEquals(driver.findElement(By.cssSelector("div.sub>h1")).getText(),"KMPlayer - Video Player for PC");

    }

    @Test
    public void TC_03_Not_In_Dom() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");
        Thread.sleep(1000);

        By popup = By.xpath("/div[starts-with(@data-title,'Newsletter') and not(contains(@style,'display:none'))]");

        //driver.findElements(popup).size()>0: lấy ra số lượng element và kiểm tra lớn hơn 0
        //driver.findElements(popup).get(0).isDisplayed(): lấy ra elemnt đầu tie kiểm tra hiển thị
        if(driver.findElements(popup).size()>0 && driver.findElements(popup).get(0).isDisplayed()){
            //case 1 : nếu popup hiển thị thì close đi
            System.out.println(" nếu popup hiển thị thì CLose đi rồi qua step tiếp theo");
            driver.findElement(By.xpath("//div[starts-with(@data-title,'Newsletter') and " +
                    "not(contains(@style,'display:none'))]//a[text()='x']")).click();
            Thread.sleep(2000);
        }

        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Selenium");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button#search-submit")).click();
        Thread.sleep(2000);

        List<WebElement> articles = driver.findElements(By.cssSelector("ul#posts-container h2.post-title>a"));
        for (WebElement article: articles){
            System.out.println(article.getText());
        }
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
