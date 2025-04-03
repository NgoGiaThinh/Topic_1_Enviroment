package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_35_Wait_V_Explicit_III {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    String uploadFilePath = System.getProperty("user.dir") + "\\uploadFile\\";
    String hinh1 = "hinh1.jpg";
    String hinh2 = "hinh2.jpg";
    String hinh3 = "hinh3.jpg";

    String hinh1Path = uploadFilePath + hinh1;
    String hinh2Path = uploadFilePath + hinh2;
    String hinh3Path = uploadFilePath + hinh3;
    public Topic_35_Wait_V_Explicit_III() {
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
    public void TC_01() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        //Wait cho Calender hiển thị
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1")));

        //wait cho text hiển thị
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display."));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"No Selected Dates to display.");

        // wait để click vào ngày tháng năm
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='3']")));

        //click vào 1 ngày tháng năm hiện tại
        driver.findElement(By.xpath("//td/a[text()='3']")).click();

        //wait loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id*='RadCalendar']>div.raDiv")));

        //Wait cho text được cập nhật lại ngày
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Thursday, April 3, 2025"));
        Assert.assertEquals(driver.findElement(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1")).getText(),"Thursday, April 3, 2025");

        //wait cái ngày dc chọn đã được selected rồi
        explicitWait.until(ExpectedConditions.attributeContains(By.xpath("//a[text()='3']/parent::td"),"class", "rcSelected"));
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='3']/parent::td")).getDomAttribute("class"),"rcSelected rcHover");



    }



    @Test
    public void TC_02() {
        driver.get("https://gofile.io/home");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(60));

        //wait cho all loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#index_loader")));

        //load file lên
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(hinh1Path +"\n" + hinh2Path + "\n" + hinh3Path);

        //wait cho tất cả các progress bar biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#fileList div.progress-container"))));

        //wait text hiển thị
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div.text-center>h2"),"Upload Complete"));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.text-center>h2")).getText(), "Upload Complete");

        String link = driver.findElement(By.cssSelector("a.linkSuccessCard")).getDomAttribute("href");
        driver.get(link);

        //wait cho all loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div#index_loader"))));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='truncate']/a[text()='" + hinh1 + "']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='" + hinh1 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='" + hinh2 + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='truncate']/a[text()='" + hinh3 + "']")).isDisplayed());


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
