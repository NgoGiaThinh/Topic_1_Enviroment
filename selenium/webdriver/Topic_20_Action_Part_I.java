package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;

public class Topic_20_Action_Part_I {
    WebDriver driver;
    Actions action;

    public Topic_20_Action_Part_I() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        action = new Actions(driver);
    }

    @Test
    public void TC_01_JQuery() {
        this.driver.get("https://automationfc.github.io/jquery-tooltip/");

        action.moveToElement(driver.findElement(By.cssSelector("input#age")))
                .pause(Duration.ofSeconds(3)).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content"))
                .getText(), "We ask for your age only for statistical purposes.");

    }

    @Test
    public void TC_02_Myntra() {
        driver.get("https://www.myntra.com/");

        action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']")))
                .pause(Duration.ofSeconds(3)).perform();

        action.click(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Home & Bath']")))
                .perform();

        System.out.println(driver.findElement(By.xpath("div.title-container")).getText());
    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        //Thread.sleep(10000);

        action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu")))
                .pause(Duration.ofSeconds(1)).perform();

        action.moveToElement(driver.findElement(By.xpath("//span[text()='VPP - Dụng Cụ Học Sinh']")))
                .pause(Duration.ofSeconds(1)).perform();

        action.click(driver.findElement(By.xpath("//div[contains(@class, 'fhs_menu_content')]//a[text()='Gọt Bút Chì']")))
                .perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(), "GỌT BÚT CHÌ");
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
