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
        this.driver.get("http://live.techpanda.org/");
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

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
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

        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(),
                "Your review has been accepted for moderation.");
    }

    @Test
    public void TC_02_OrangeHRM() throws InterruptedException {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        String firstname = "Donald";
        String lastname = "Trump";
        String username = "donald" + new Random().nextInt(9999);
        String password = "Donald1@";
        String passportnumber = "555-666-777-8888";
        String passportcomment = "Nice try\nGG\nGZ";


        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//span[text()='PIM']")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[text()='Add Employee']")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input[name='firstName']")).sendKeys(firstname);
        driver.findElement(By.cssSelector("input[name='lastName']")).sendKeys(lastname);

        String employeeID = driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value");

        driver.findElement(By.xpath("//p[text()='Create Login Details']/following-sibling::div//label")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//label[text()='Username']/parent::div/following-sibling::div/input")).sendKeys(username);
        driver.findElement(By.xpath("//label[text()='Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//label[text()='Confirm Password']/parent::div/following-sibling::div/input")).sendKeys(password);
        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(8000);


        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastname);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);

        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(2000);

         driver.findElement(By.xpath("" +
                 "//h6[text()='Assigned Immigration Records']/following-sibling::button[contains(string(),'Add')]")).click();

        driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).sendKeys(passportnumber);
        driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).sendKeys(passportcomment);

        driver.findElement(By.xpath("//button[contains(string(),'Save')]")).click();
        Thread.sleep(4000);

        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportnumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), passportcomment);


        driver.findElement(By.cssSelector("p.oxd-userdropdown-name")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[text()='Logout']")).click();
        Thread.sleep(2000);

        //Employee
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys(username);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
        Thread.sleep(4000);

        driver.findElement(By.xpath("//span[text()='My Info']")).click();
        Thread.sleep(3000);

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='firstName']")).getAttribute("value"), firstname);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='lastName']")).getAttribute("value"), lastname);
        Assert.assertEquals(driver.findElement(By.xpath(
                "//label[text()='Employee Id']/parent::div/following-sibling::div/input")).getAttribute("value"), employeeID);


        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input")).isEnabled());

        driver.findElement(By.xpath("//a[text()='Immigration']")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("i.bi-pencil-fill")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input")).getAttribute("value"), passportnumber);
        Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Comments']/parent::div/following-sibling::div/textarea")).getAttribute("value"), passportcomment);


    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
