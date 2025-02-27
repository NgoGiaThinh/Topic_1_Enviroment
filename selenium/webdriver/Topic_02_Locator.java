package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.namespace.QName;
import java.time.Duration;

public class Topic_02_Locator {
    WebDriver driver;

    public Topic_02_Locator() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_ID() {
        this.driver.get("https://ngoaingu24h.vn/");

        driver.findElement(By.id("textfield")).sendKeys("hi");

    }

    @Test
    public void TC_02_Class() {
        this.driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.className("main-panel"));
    }

    @Test
    public void TC_03_Name() {
        this.driver.get("https://www.youtube.com/");
        driver.findElement(By.name("keywords"));
    }

    @Test
    public void TC_04_Link() {
        this.driver.get("https://tfd.nexon.com/en/main");
        //chỉ dùng được với đường link có text
        //truyền hết cả chuỗi text
        driver.findElement(By.linkText("PRIVACY POLICY"));
    }

    @Test
    public void TC_05_Partial_Link() {
        this.driver.get("https://tfd.nexon.com/en/main");
        //chỉ dùng được với đường link có text
        //truyền hết cả chuỗi text vào cũng chạy được
        //truyền 1 phần chuỗi text vẫn chạy được
        driver.findElement(By.partialLinkText("PRIVACY"));
        driver.findElement(By.partialLinkText("TERMS"));
    }

    @Test
    public void TC_06_Tagname() {
        //tìm nhiều element giống nhau
        this.driver.get("https://ngoaingu24h.vn/");
        int linkNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);
        //tagname giống nhau - element khác nhau
        //tag name là input nhưng element có thể là text box/ button/ radio/ check box....
    }

    @Test
    public void TC_07_Css() {
        this.driver.get("https://live.techpanda.org/index.php/customer/account/create/");


        //css vs ID
        driver.findElement(By.cssSelector("input[id='search']"));
        driver.findElement(By.cssSelector("#search"));
        driver.findElement(By.cssSelector("input#search"));


        //css vs class
        driver.findElement(By.cssSelector("div[class='header-language-background']"));
        driver.findElement(By.cssSelector("div.header-language-background"));
        driver.findElement(By.cssSelector(".header-language-background"));

        //ngoại lệ
        driver.findElement(By.cssSelector("input.validate-email.input-text.required-entry"));
        driver.findElement(By.cssSelector("input.input-text.required-entry"));
        driver.findElement(By.cssSelector("input.required-entry"));

        //css vs name
        driver.findElement(By.cssSelector("input[name='password']"));


        //css vs link
        //css ko dùng dc với text
        driver.findElement(By.cssSelector("a[href='http://live.techpanda.org/index.php/about-magento-demo-store/']"));


        //css vs partial link
        driver.findElement(By.cssSelector("a[href^='http://live.techpanda.org/index.php']"));//lấy ở đầu
        driver.findElement(By.cssSelector("a[href*='org/index.php/about-magento']"));//lấy ở giữa
        driver.findElement(By.cssSelector("a[href$='about-magento-demo-store/']"));//lấy ở cuối

        //css vs tagname
        int linkNumber = driver.findElements(By.tagName("a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);


    }

    @Test
    public void TC_08_XPath() {
        //cú pháp
        //tagname[@attriute='value']
        //tagname[starts-with(@attribute, 'value')]
        this.driver.get("https://live.techpanda.org/index.php/customer/account/create/");


        //xpath vs ID
        driver.findElement(By.xpath("//input[@id='search']"));

        //xpath vs Class
        driver.findElement(By.xpath("//div[@class='header-language-background']"));

        //xpath vs name
        driver.findElement(By.xpath("//input[@name='lastname']"));

        //xpath vs link
        driver.findElement(By.xpath("//a[@href='http://live.techpanda.org/index.php/about-magento-demo-store/']"));
        driver.findElement(By.xpath("//a[text()='About Us']"));

        //xpath vs partial link
        driver.findElement(By.xpath("//a[starts-with(@href,'http://live.techpanda.org/index.php')]"));//lấy ở đầu
        driver.findElement(By.xpath("//a[contains(@href,'org/index.php/about-magento')]"));//lấy ở giữa
        //xpath không có ends-with
        //driver.findElement(By.xpath("//a[ends-withe(@href,'about-magento-demo-store/')]"));//lấy ở cuối

        //xpath vs tagname
        int linkNumber = driver.findElements(By.xpath("//a")).size();
        System.out.println("Tổng số lượng link = " + linkNumber);
    }
   @AfterClass

    public void afterClass(){ driver.quit();}
}
