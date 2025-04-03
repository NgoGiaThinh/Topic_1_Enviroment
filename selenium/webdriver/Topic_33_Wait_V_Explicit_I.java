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

public class Topic_33_Wait_V_Explicit_I {
    WebDriver driver;
    Actions action;
    JavascriptExecutor jsExecutor;
    WebDriverWait explicitWait;
    public Topic_33_Wait_V_Explicit_I() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();


        driver.manage().window().maximize();

        action = new Actions(driver);
        jsExecutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01() {
        driver.get("");

        //element clickable (button/checkbox/radio/link/image...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));
        driver.findElement(By.cssSelector("")).click();

        //element visible (All element)
        //get text/ get css/ Attribute/ displayed
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isDisplayed());

        //element selected (checkbox/radio)
        explicitWait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("")));
        Assert.assertTrue(driver.findElement(By.cssSelector("")).isSelected());

        //Invisble(all element in HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));
        Assert.assertFalse(driver.findElement(By.cssSelector("")).isDisplayed());

        //presence (all element in HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("")));

        //element size
        explicitWait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(""), 15));
        Assert.assertEquals(driver.findElements(By.cssSelector("")).size(), 15);

        //Attribute
        explicitWait.until(ExpectedConditions.attributeToBe(By.cssSelector(""),"value", "John"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getDomAttribute("value"),"John");

        //text
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector(""), "Selenium"));
        Assert.assertEquals(driver.findElement(By.cssSelector("")).getText(),"Selenium");

    }

    @Test
    public void TC_02() {
        driver.get("");
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
