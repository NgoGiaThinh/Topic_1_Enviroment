package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_25_Shadow_DOM {
    WebDriver driver;
    Actions action;
    public Topic_25_Shadow_DOM() {
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
        this.driver.get("https://automationfc.github.io/shadow-dom/");

        //element ko nằm trong shadow
        String scrollText = driver.findElement(By.xpath("//a[@href='scroll.html']")).getText();
        System.out.println(scrollText);

        // tìm element chứa shadow
        WebElement firstShadowHostElement = driver.findElement(By.xpath("//div[@id='shadow_host']"));

        //lấy ra cái shadow
        SearchContext firstShadowRoot = firstShadowHostElement.getShadowRoot();

        //element nằm trong shadow
        String shadowText = firstShadowRoot.findElement(By.cssSelector("a")).getText();
        System.out.println(shadowText);

        firstShadowRoot.findElement(By.cssSelector("input[type='text']")).sendKeys("maxxel");
        System.out.println(firstShadowRoot.findElement(By.cssSelector("span#shadow_content>span")).getText());

        //Tìm ra cái element chứa shadow thứ 2 (nằm trong shadow đầu tiên)
        WebElement secondShadowHostElement = firstShadowRoot.findElement(By.cssSelector("div#nested_shadow_host"));

        //lấy ra shadow thứ 2
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();

        //lấy text ra
        System.out.println( secondShadowRoot.findElement(By.cssSelector("div#nested_shadow_content>div")).getText());

    }

    @Test
    public void TC_02() throws InterruptedException {
        this.driver.get("https://books-pwakit.appspot.com/");

        Thread.sleep(3000);
        WebElement firstShadowHostElement = driver.findElement(By.cssSelector("book-app[apptitle='BOOKS']"));
        SearchContext fisrtShadowRoot = firstShadowHostElement.getShadowRoot();

        fisrtShadowRoot.findElement(By.cssSelector("input#input")).sendKeys("Harry Potter");
        Thread.sleep(3000);

        WebElement secondShadowHostElement = fisrtShadowRoot.findElement(By.cssSelector("app-toolbar>book-input-decorator"));
        SearchContext secondShadowRoot = secondShadowHostElement.getShadowRoot();

        secondShadowRoot.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        WebElement thirdShadowHostElement = fisrtShadowRoot.findElement(By.cssSelector("main.main-content>book-explore"));
        SearchContext thirdShadowHostRoot = thirdShadowHostElement.getShadowRoot();

        List<WebElement> forthShadowHostElement = thirdShadowHostRoot.findElements(By.cssSelector("ul.books>li>book-item"));

        for (WebElement element : forthShadowHostElement){
            SearchContext shadowRoot = element.getShadowRoot();
            System.out.println(shadowRoot.findElement(By.cssSelector("div.title-container>h2")).getText());
        }



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
