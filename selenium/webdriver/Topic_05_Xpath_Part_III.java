package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_05_Xpath_Part_III {
    WebDriver driver;

    public Topic_05_Xpath_Part_III() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        this.driver.get("https://automationfc.github.io/basic-form/");

        // chỉ dùng text()= khi nằm trên cùng 1 hàng với chính node(tagname)- ko nằm trong child node
        driver.findElement(By.xpath("//h1[text()='Selenium WebDriver API']"));
        driver.findElement(By.xpath("//span[text()=' (Ignore Me) ']"));// tính cả khoảng trống



    }

    @Test
    public void TC_02() {
        this.driver.get("https://automationfc.github.io/basic-form/");

        driver.findElement(By.xpath("//h5[contains(text(),'Hello World!')]"));
        //contains(.) và contains string giống nhau
        driver.findElement(By.xpath("//h5[contains(.,'Michael Jackson')]"));
        driver.findElement(By.xpath("//h5[contains(string(),'Michael Jackson')]"));

    }
    @Test
    public void TC_03() {
        this.driver.get("https://automationfc.github.io/basic-form/");

        //text vừa chứa dấu kí tự nháy đơn - vừa chứa nháy đôi
        driver.findElement(By.xpath("//span[text()=concat('Hello \"John\", What',\"'s happened?\")]"));


    }

    @Test
    public void TC_04() {
        this.driver.get("https://automationfc.github.io/basic-form/");

        //cả 2 điều kiện đều phải đúng
        driver.findElement(By.xpath("//input[@type='email' and @id='email']"));

        //1 trong 2 điều kiện phải đúng
        driver.findElement(By.xpath("//input[@type='email1' or @id='email']"));

    }

    @Test
    public void TC_05() {
        this.driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //not(điều kiện đúng) = sai
        //not(điều kiện sai) = đúng
        //chỉ đúng khi có 2 cái

        // sự dụng thêm click() để trigger hành động
        driver.findElement(By.xpath("//a[text()='18']")).click();
        driver.findElement(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']"));

    }
    @Test
    public void TC_06() {
        this.driver.get("https://automationfc.github.io/jquery-selectable/");

        driver.findElement(By.xpath("//ol[@id='selectable']/li[position()=1]"));

        //lấy cuối
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()]"));

        //gần kề last
        driver.findElement(By.xpath("//ol[@id='selectable']/li[last()-1]"));

        //giống với hàm last
        driver.findElement(By.xpath("//ol[@id='selectable']/li[count(//li)]"));

    }




    @AfterClass
    public void afterClass(){ driver.quit();}
}
