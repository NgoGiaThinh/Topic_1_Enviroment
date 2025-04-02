package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_29_UploadFile {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;

    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFile\\";
    String hinh1 = "hinh1.jpg";
    String hinh2 = "hinh2.jpg";
    String hinh3 = "hinh3.jpg";

    String hinh1Path = uploadFilePath + hinh1;
    String hinh2Path = uploadFilePath + hinh2;
    String hinh3Path = uploadFilePath + hinh3;


    public Topic_29_UploadFile() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
    }

    @Test
    public void TC_01_Single() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");

        //load file lên
        driver.findElement(uploadFile).sendKeys(hinh1Path);
        driver.findElement(uploadFile).sendKeys(hinh2Path);
        driver.findElement(uploadFile).sendKeys(hinh3Path);

        //verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinh1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinh2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinh3 + "']")).isDisplayed());

        //upload từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement start : startButtons){
            start.click();
            Thread.sleep(2000);
        }

        //verify file được upload lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinh1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinh2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinh3 + "']")).isDisplayed());


    }

    @Test
    public void TC_02_Multiple() throws InterruptedException {
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        By uploadFile = By.xpath("//input[@type='file']");

        //load file lên
        driver.findElement(uploadFile).sendKeys(hinh1Path +"\n" + hinh2Path + "\n" + hinh3Path);

        //verify file được load lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinh1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinh2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + hinh3 + "']")).isDisplayed());

        //upload từng file
        List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

        for (WebElement start : startButtons){
            start.click();
            Thread.sleep(2000);
        }

        //verify file được upload lên
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinh1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinh2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + hinh3 + "']")).isDisplayed());

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
