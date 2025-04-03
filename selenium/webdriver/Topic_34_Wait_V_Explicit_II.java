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

public class Topic_34_Wait_V_Explicit_II {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;

    By startButton = By.cssSelector("div#start>button");
    By loadingIcon = By.cssSelector("div#loading");
    By helloText = By.cssSelector("div#finish>h4");

    public Topic_34_Wait_V_Explicit_II() {
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
        driver.get("");



    }

    @Test(description = "thời gian Explicit = 0")
    public void TC_02() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(0));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        //1 - chờ cho step trước hoàn thành (loading icon biến mất/invisible) - ko quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        //2 - chờ cho step sau xuất hiện (hello text hiển thị/visible) - ko quan tâm step sau(loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");

    }

    @Test(description = "thời gian Explicit ngắn hơn điều kiện xảy ra")
    public void TC_03() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        //1 - chờ cho step trước hoàn thành (loading icon biến mất/invisible) - ko quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        //2 - chờ cho step sau xuất hiện (hello text hiển thị/visible) - ko quan tâm step sau(loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }


    @Test(description = "thời gian Explicit bằng thời gian điều kiện xảy ra")
    public void TC_04() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        //1 - chờ cho step trước hoàn thành (loading icon biến mất/invisible) - ko quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        //2 - chờ cho step sau xuất hiện (hello text hiển thị/visible) - ko quan tâm step sau(loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }


    @Test(description = "thời gian Explicit dài hơn điều kiện xảy ra")
    public void TC_05() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        driver.findElement(startButton).click();

        //1 - chờ cho step trước hoàn thành (loading icon biến mất/invisible) - ko quan tâm step sau (hello text)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        //2 - chờ cho step sau xuất hiện (hello text hiển thị/visible) - ko quan tâm step sau(loading icon)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(helloText));

        Assert.assertEquals(driver.findElement(helloText).getText(), "Hello World!");
    }


    @AfterClass
    public void afterClass(){
        driver.quit();
    }
}
