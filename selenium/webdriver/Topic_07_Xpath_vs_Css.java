package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_07_Xpath_vs_Css {
    WebDriver driver;

    public Topic_07_Xpath_vs_Css() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        this.driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //dấu # là đại diện ID bên Css
        driver.findElement(By.xpath("//button[@id='send2']"));
        driver.findElement(By.cssSelector("button[id='send2']"));
        driver.findElement(By.cssSelector("button#send2"));
        driver.findElement(By.cssSelector("#send2"));

        //Direct Child (đi 1 node)
        driver.findElement(By.xpath("//div/button[@type='submit']"));
        driver.findElement(By.cssSelector("div>button[type='submit']"));

        //dấu . đại diện cho class bên Css
        //class không cần phải lấy toàn bộ giá trị - có thể lấy 1 phần
        driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']"));
        driver.findElement(By.cssSelector("input[class='input-text required-entry validate-email']"));
        driver.findElement(By.cssSelector("input.validate-email"));
        driver.findElement(By.cssSelector(".validate-email"));

        // And - thêm [] ở attribute thứ 2
        driver.findElement(By.xpath("//input[@id='email' and @name='login[username]']"));
        driver.findElement(By.cssSelector("input#email[name='login[username]']"));

        // Or - thêm dấu , giữa 2 attribute
        driver.findElement(By.xpath("//input[@id='email' or @id='pass']"));
        driver.findElement(By.cssSelector("input#email,[id='pass']"));

        // Contains(chứa giá trị gì)
        driver.findElement(By.xpath("//input[contains(@title,'Email Address')]"));
        driver.findElement(By.cssSelector("input[title*='Email Address']"));
        driver.findElement(By.cssSelector("input[title*='Email']"));
        driver.findElement(By.cssSelector("input[title*='Address']"));

        //starts-with (bắt đầu bằng giá trị gì)
        driver.findElement(By.xpath("//input[starts-with(@title,'Email')]"));
        driver.findElement(By.cssSelector("input[title^='Email']"));

        //end-with thì Xpath ko hỗ trợ, chỉ có Css
        driver.findElement(By.cssSelector("input[title$='Address']"));

    }

    @Test
    public void TC_02() {
        this.driver.get("https://live.techpanda.org/index.php/mobile.html");

        //following sibling
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div/h2/following-sibling::div[@class='actions']"));
        //truyền index
        driver.findElement(By.xpath("//a[@title='Samsung Galaxy']/following-sibling::div/h2/following-sibling::div[1]"));
        //dùng dấu + sẽ lấy gần kề, dấu ~ sẽ lấy toàn bộ
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>h2+div"));
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>h2~div"));
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>h2~div.actions"));
        //:nth-of-type() - dùng để truyền index và có thẻ tên giống nhau
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>h2~div:nth-of-type(1)"));
        //lấy đầu tiên
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>div:first-of-type"));
        //lấy cuối cùng
        driver.findElement(By.cssSelector("a[title='Samsung Galaxy']+div>div:last-of-type"));

    }

    //Css ko support đi ngược node lên:Preceding-sibling,Parent, Ancestor (từ node em lên node anh, từ node con lên node cha)

    @AfterClass
    public void afterClass(){ driver.quit();}
}
