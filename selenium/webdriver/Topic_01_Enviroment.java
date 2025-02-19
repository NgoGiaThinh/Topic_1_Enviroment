package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_01_Enviroment {
    WebDriver driver;

    public Topic_01_Enviroment() {
    }

    @Test
    public void TC_01_Run_On_Firefox() {
        this.driver = new FirefoxDriver();
        this.driver.get("https://www.facebook.com/");
        this.driver.quit();
    }

    @Test
    public void TC_02_Run_On_Chrome() {
        this.driver = new ChromeDriver();
        this.driver.get("https://www.facebook.com/");
        this.driver.quit();
    }

    @Test
    public void TC_03_Run_On_Edge() {
        this.driver = new EdgeDriver();
        this.driver.get("https://www.facebook.com/");
        this.driver.quit();
    }
}
