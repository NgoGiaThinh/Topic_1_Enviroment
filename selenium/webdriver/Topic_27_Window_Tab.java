package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;

public class Topic_27_Window_Tab {
    WebDriver driver;
    Actions action;
    public Topic_27_Window_Tab() {
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
    public void TC_01() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //lấy ra window ID của 1 cửa sổ đang active (driver đang đứng đó)
        String githubID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        //lấy ra window ID của 2 cửa sổ/tab
        switchToWindowByTitle("Google");
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium");

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());


        //quay lại trang github
        //lấy ra windowID của google
        String googleID = driver.getWindowHandle();

        switchToWindowByTitle("Selenium WebDriver");

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(3000);

        switchToWindowByTitle("Facebook – log in or sign up");

        System.out.println(driver.getCurrentUrl());
        System.out.println(driver.getTitle());

        driver.findElement(By.cssSelector("input#email")).sendKeys("Selenium@gmail.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("Selenium@gmail.com");

        closeAllExceptMain(githubID);



    }

    // dùng cho duy nhất 2 window
    private void switchToWindowByID(String windowID) {
        //lấy ra window ID của 2 cửa sổ/tab
        Set<String> allWindows = driver.getWindowHandles();

        //duyệt qua từng OD
        for (String window : allWindows){

            //nếu ID nào khác với ID truyền vào
            if (!window.equals(windowID)){

                //thì switch qua

                driver.switchTo().window(window);
                break;
            }
        }
    }

    // dùng cho từ 2 window trở lên
    private void switchToWindowByTitle(String expectedTitle) throws InterruptedException {

        //lấy ra window ID của 2 cửa sổ/tab
        Set<String> allWindows = driver.getWindowHandles();

        //duyệt qua từng ID
        for (String window : allWindows){

            // swtich trước vào từng window
            driver.switchTo().window(window);

            //lấy ra pageTitle đang active
            String pageTitle = driver.getTitle();

            //nếu page title hiện tài = với title mong đợi
            if (pageTitle.equals(expectedTitle)) {
                Thread.sleep(2000);

                //thoát vòng lặp
                break;
            }
        }
        Thread.sleep(2000);

    }

    // dùng cho từ 2 window trở lên
    private void switchToWindowByContainTitle(String expectedTitle) throws InterruptedException {

        //lấy ra window ID của 2 cửa sổ/tab
        Set<String> allWindows = driver.getWindowHandles();

        //duyệt qua từng ID
        for (String window : allWindows){

            // swtich trước vào từng window
            driver.switchTo().window(window);

            //lấy ra pageTitle đang active
            String pageTitle = driver.getTitle();

            //nếu page title hiện tài = với title mong đợi
            if (pageTitle.contains(expectedTitle)) {
                Thread.sleep(2000);

                //thoát vòng lặp
                break;
            }
        }
        Thread.sleep(2000);

    }

    // close  tất cả các window/tab ngoại trừ trang gốc
    private void closeAllExceptMain(String windowID) throws InterruptedException {

        //lấy ra window ID của 2 cửa sổ/tab
        Set<String> allWindows = driver.getWindowHandles();

        //duyệt qua từng OD
        for (String window : allWindows){

            //nếu ID nào khác với ID truyền vào
            if (!window.equals(windowID)){

                //thì switch qua
                driver.switchTo().window(window);

                //close tab đó đi
                driver.close();


            }
        }
        driver.switchTo().window(windowID);
    }

    @Test
    public void TC_02() throws InterruptedException {
        driver.get("https://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driver.findElement(By.xpath(
                "//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
        driver.findElement(By.xpath(
                "//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

        driver.findElement(By.xpath("//button[@title='Compare']")).click();

        switchToWindowByContainTitle("Products Comparison List");

        Assert.assertEquals(driver.findElement(By.cssSelector("div.page-title>h1")).getText(),"COMPARE PRODUCTS");

        driver.findElement(By.cssSelector("button[title='Close Window']")).click();
        Thread.sleep(3000);

        switchToWindowByContainTitle("Mobile");
        driver.findElement(By.cssSelector("input#search")).sendKeys("Samsung Galaxy");
        driver.findElement(By.cssSelector("button.search-button")).click();

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
    public void afterClass(){
       driver.quit();
    }
}
