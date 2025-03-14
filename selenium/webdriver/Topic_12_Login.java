package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_12_Login {
    WebDriver driver;

    public Topic_12_Login() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Empty() {
        this.driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText();

        Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");

    }

    @Test
    public void TC_02_Invalid_email() throws InterruptedException {
        this.driver.get("https://live.techpanda.org/");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("123@123.123");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12312322");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(),
                "Please enter a valid email address. For example johndoe@domain.com.");
        Thread.sleep(2000);
    }

    @Test
    public void TC_03_Invalid_Password() {
        this.driver.get("https://live.techpanda.org/");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("thinh@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();

        Assert.assertEquals( driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(),
                "Please enter 6 or more characters without leading or trailing spaces.");

    }

    @Test
    public void TC_04_Incorrect_Email() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        driver.findElement(By.cssSelector("input#email")).sendKeys("thinh@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("12334256");
        driver.findElement(By.cssSelector("button#send2")).click();
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("button#proceed-button")).click();
        Assert.assertEquals( driver.findElement(By.cssSelector("li.error-msg span")).getText(),"Invalid login or password.");

    }
    @AfterClass
    public void afterClass(){
        //driver.quit();
    }

}

