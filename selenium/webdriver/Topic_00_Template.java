package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_00_Template {
    WebDriver driver;
    Actions action;
    public Topic_00_Template() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01() {
        driver.get("");

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
    public void afterClass(){ driver.quit();}
}
