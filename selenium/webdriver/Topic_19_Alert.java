package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_19_Alert {
    WebDriver driver;

    public Topic_19_Alert() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        this.driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Thread.sleep(3000);

        // chờ cho 1 alert được xuất hiện trong HTML + sau đó switch vào
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Alert");

        //Accept Alert
        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");



        //Switch vào 1 alert (ko dùng Wait) (ko nên dùng)
        //Alert alert = driver.switchTo().alert();



        //Cancel Alert
        //driver.switchTo().alert();

        //Get text ra để verify
        //driver.switchTo().alert();

        //nhập text rồi Accept
        //driver.switchTo().alert();


    }

    @Test
    public void TC_02_Confirm() throws InterruptedException {
        this.driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        Thread.sleep(3000);

        // chờ cho 1 alert được xuất hiện trong HTML + sau đó switch vào
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS Confirm");

        //Accept dismiss
        alert.dismiss();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");

    }
    @Test
    public void TC_03_Prompt() throws InterruptedException {

        this.driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Thread.sleep(3000);

        // chờ cho 1 alert được xuất hiện trong HTML + sau đó switch vào
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(alert.getText(), "I am a JS prompt");

        String name = "Maxxel";
        alert.sendKeys(name);
        Thread.sleep(2000);
        //Accept Alert
        alert.accept();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: " + name);
    }

    @Test
    public void TC_04() throws InterruptedException {
        String username = "admin";
        String password = "admin";

        // cách 1  truyền thẳng U/P vào trong url
       driver.get("https://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth");
       Thread.sleep(2000);

       Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    @Test
    public void TC_05() throws InterruptedException {
        String username = "admin";
        String password = "admin";

        // cách 1  truyền thẳng U/P vào trong url
        driver.get("https://the-internet.herokuapp.com/");
        Thread.sleep(2000);


        String authenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getDomProperty("href");

        driver.get(passUserToAuthenLink(authenLink,username,password));

        Assert.assertEquals(driver.findElement(By.cssSelector("div.example>p")).getText(), "Congratulations! You must have the proper credentials.");
    }

    public String passUserToAuthenLink(String authenLink, String username, String password){

        String[] text = authenLink.split("//");
        return text[0] + "//" + username + ":" + password + "@" + text[1];
    }
    @AfterClass
    public void afterClass(){ driver.quit();}
}
