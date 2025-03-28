package webdriver;

import graphql.schema.usage.SchemaUsage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_26_Iframe {
    WebDriver driver;
    Actions action;
    public Topic_26_Iframe() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new EdgeDriver();
        //this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01() {
        this.driver.get("https://www.embedgooglemap.net/");
       // driver.switchTo().frame(0);//ko dùng index vì dễ thêm sửa xóa
       // driver.switchTo().frame("");// ko nên dùng name vì iframe có thể ko có id/name

        //switch vào iframe
        driver.switchTo().frame(driver.findElement(By.cssSelector("div.gmap_canvas>iframe")));// nên dùng

        //đang ở B
        String addressName = driver.findElement(By.cssSelector("div.place-name")).getText();
        System.out.println(addressName);

        //B chứa iframe C


        //switch vào iframe C
        driver.switchTo().frame(driver.findElement(By.cssSelector("div#mapDiv>div>div>iframe")));

        //đang ở C
        System.out.println(driver.getPageSource());

        //từ C quay lại B
       driver.switchTo().parentFrame();

       addressName = driver.findElement(By.cssSelector("div.place-name")).getText();
       System.out.println(addressName);

       //B quay lại A
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("input#s")).clear();
        driver.findElement(By.cssSelector("input#s")).sendKeys("21b đường tên lửa");



    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");

        driver.findElement(By.cssSelector("button.osano-cm-accept-all")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        Thread.sleep(3000);

        driver.switchTo().frame(driver.findElement(By.cssSelector("div#formTemplateContainer>iframe")));

        new Select(driver.findElement(By.xpath("//label[contains(text(),'Year')]/following-sibling::select"))).selectByVisibleText("Sophomore");
        new Select(driver.findElement(By.xpath("//label[contains(text(),'Residence')]/following-sibling::select"))).selectByVisibleText("South Dorm");
        driver.findElement(By.xpath("//label[contains(text(),'Male')]")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("input#FSsubmit")).click();
        Thread.sleep(3000);


        //từ trong iframe switch ra trang bên ngoài chứa nó
        driver.switchTo().defaultContent();

        driver.findElement(By.cssSelector("nav[class='header header--desktop'] a.menu-item-login")).click();
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("button#login")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div#message-error")).getText(),"Username and password are both required.");

    }

    @Test
    public void TC_03() {
    }


    @Test
    public void TC_04() {
    }


    @Test
    public void TC_05() {
    }


    @AfterClass
    public void afterClass(){ driver.quit();}
}
