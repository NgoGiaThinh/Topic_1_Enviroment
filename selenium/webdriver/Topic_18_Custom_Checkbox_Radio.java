package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_18_Custom_Checkbox_Radio {
    WebDriver driver;

    public Topic_18_Custom_Checkbox_Radio() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Ubuntu() throws InterruptedException {
        this.driver.get("https://login.ubuntu.com/");
        // dùng label để click và dùng input để verify
        //1 element dùng tới 2 locator =>có thay đổi => Maintain code mất nhìu time
        //driver.findElement(By.cssSelector("label.new-user")).click();
        //Thread.sleep(2000);
        //Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

        //dùng thẻ input để click => ko dùng hàm click() của WebElement
        //dùng hàm click của JS
        //verify bình thường
        By registerRadio = By.cssSelector("input#id_new_user");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver
                .findElement(registerRadio));
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("input#id_new_user")).isSelected());

    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        Thread.sleep(2000);
        By canthoRadio = By.cssSelector("div[aria-label='Cần Thơ']");

        //click lên checkbox/radio
        driver.findElement(canthoRadio).click();
        Thread.sleep(2000);

        //cách 1: verify = cách hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("div[aria-label='Cần Thơ'][aria-checked='true']")).isDisplayed());

        //cách 2: verify lấy thuộc tính ra
        Assert.assertEquals(driver.findElement(canthoRadio).getDomAttribute("aria-checked"), "true");
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
