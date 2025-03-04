package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_03_Relative_locator {
    WebDriver driver;

    public Topic_03_Relative_locator() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        this.driver.get("https://live.techpanda.org/index.php/catalogsearch/advanced");
        //this.driver.get("https://live.techpanda.org/index.php/customer/account/create/");
        //1 khai báo biến
        //khi dữ liệu này được sử dụng nhiều lần = tái sử dụng (Re useable)

        //String emailAddress = "ngogiathinh2511@gmail.com";
        //By emailTextboxBy = By.cssSelector("");
        //WebElement emailTextboxElement = driver.findElement(emailTextboxBy);


        //2 - không khai báo biến
        //khi chỉ dùng 1 lần
        // driver.findElement(emailTextboxBy).sendKeys("");


        WebElement priceFromeElement = driver.findElement(RelativeLocator.with(By.tagName("input"))
                .toLeftOf(By.name("price[to]"))
                .below(By.id("sku"))
                .above(By.id("tax_class_id")));
        priceFromeElement.sendKeys("100");


    }


    @Test
    public void TC_02() {
        this.driver.get("https://demo.nopcommerce.com/register");

        driver.findElement(By.xpath("//div[@class='gender']"));

        driver.findElement(By.xpath("//input[@id='FirstName']"));
        driver.findElement(By.xpath("//input[@name='FirstName']"));

        driver.findElement(By.xpath("//input[@id='LastName']"));
        driver.findElement(By.xpath("//input[@name='LastName']"));

        driver.findElement(By.xpath("//input[@id='Email']"));
        driver.findElement(By.xpath("//input[@name='Email']"));

        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//input[@name='Company']"));

        driver.findElement(By.xpath("//input[@id='Newsletter']"));
        driver.findElement(By.xpath("//input[@name='Newsletter']"));

        driver.findElement(By.xpath("//input[@id='Password']"));
        driver.findElement(By.xpath("//input[@name='Password']"));

    }

    @AfterClass
    public void afterClass(){
        driver.quit();}
}
