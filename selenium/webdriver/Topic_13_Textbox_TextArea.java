package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Topic_13_Textbox_TextArea {
    WebDriver driver;

    public Topic_13_Textbox_TextArea() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        //
        this.driver.get("https://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();

        driver.findElement(By.cssSelector("a[title='Create an Account']")).click();

        String firstname = "Donald";
        String lastname = "Trump";
        String fullName = firstname + " " + lastname;
        String emailAddress = "donaldtrump" + new Random().nextInt(9999) + "@gmail.net";
        String password = "1234567";
        String thoughtReview = "Best Phone\nBest Provider\nest Product";
        String summaryReview = "dinh noc kich tran";
        String nickName = "Maxxel";



        driver.findElement(By.id("firstname")).sendKeys(firstname);
        driver.findElement(By.id("lastname")).sendKeys(lastname);
        driver.findElement(By.id("email_address")).sendKeys(emailAddress);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmation")).sendKeys(password);

        driver.findElement(By.cssSelector("button[title='Register']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msp span")).getText(),
                "Thank you for registering with Main Website Store.");

        String contactInformation = driver.findElement(
                By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        Assert.assertTrue(contactInformation.contains(fullName));
        Assert.assertTrue(contactInformation.contains(emailAddress));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']")).click();
        driver.findElement(By.xpath("//a[text()='Add Your Review']")).click();

        driver.findElement(By.cssSelector("input[id='Quality 1_5']")).click();
        driver.findElement(By.cssSelector("textarea#review_field")).sendKeys(thoughtReview);
        driver.findElement(By.cssSelector("input#summary_field")).sendKeys(summaryReview);
        driver.findElement(By.cssSelector("input#nickname_field")).clear();
        driver.findElement(By.cssSelector("input#nickname_field")).sendKeys(nickName);

        driver.findElement(By.cssSelector("button[title='Submit Review']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msp span")).getText(),
                "Your review has been accepted for moderation.");
    }

    @Test
    public void TC_02() {
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
