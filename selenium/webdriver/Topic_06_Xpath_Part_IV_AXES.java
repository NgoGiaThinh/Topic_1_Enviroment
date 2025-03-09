package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_06_Xpath_Part_IV_AXES {
    WebDriver driver;

    public Topic_06_Xpath_Part_IV_AXES() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        this.driver.get("https://live.techpanda.org/index.php/mobile.html");

        //parent( từ node con đi lên node cha)
        //từ node anh đi xuống node em
        driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']/button"));

        //từ node em đi lên node anh
        driver.findElement(By.xpath("//button[@type='button']/parent::div/preceding-sibling::h2[@class='product-name']/a"));



    }

    @Test
    public void TC_02() {
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
