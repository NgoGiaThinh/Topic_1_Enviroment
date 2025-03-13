package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;

public class Topic_08_WebBrowser_Commands {
    WebDriver driver;

    public Topic_08_WebBrowser_Commands() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        this.driver.get("https://www.facebook.com/");

        //đóng browser - ko quan tâm có bao nhiu tab/windows
        driver.quit();

        //đóng Browser - chỉ đóng tab-window hiện tại
        driver.close();

        //tìm 1 element vs locator là tham số truyền vào
        driver.findElement(By.xpath(""));

        //tìm nhiều element vs locator là tham số truyền vào
        driver.findElements(By.xpath(""));

        //sử dụng luôn ko cần lưu trự
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/");

        //lấy ra Url ở page hiện tại và sử dụng sau đó
        String homePageUrl = driver.getCurrentUrl();

        //step 10
        driver.get(homePageUrl);

        //lấy ra title ở page hiện tại
        driver.getTitle();

        // lấy ra window ID ở page hiện tại
        driver.getWindowHandle();

        //lấy ra tất cả các window ID của các tab/window
        driver.getWindowHandles();

        //lấy ra source code cuar page hiện tại
        driver.getPageSource();
        System.out.println("page source = " + driver.getPageSource());

        //Alert - Frame/ iFrame - Window/Tab

        //Alert
        driver.switchTo().alert();

        //Frame/ iFrame
        //switch vào frame/ iframe
        driver.switchTo().frame("");

        //switch ra trang cha trở lại
        driver.switchTo().defaultContent();

        //switch từ frame con ra frame cha (nhiều frame lồng nhau)
        driver.switchTo().parentFrame();

        //window
        driver.switchTo().window("");
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.facebook.com/");
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://www.facebook.com/");

        //set timeout để tìm element(áp dụng cho 2 hàm findelement/findelements)
        driver.manage().timeouts().implicitlyWait(Duration.ofNanos(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofHours(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofDays(30));

        //cookie
        driver.manage().getCookies();
        //driver.manage().addCookie();

        //browser :fullscreen/maximize/minimize
        driver.manage().window().fullscreen();
        driver.manage().window().maximize();
        driver.manage().window().minimize();

        //set browser có kích thước bao nhiu (responsive)
        driver.manage().window().setSize(new Dimension(1920,1080));
        driver.manage().window().setSize(new Dimension(1366,768));

        //set browser tại vị trí nào
        driver.manage().window().setPosition(new Point(0,0));
        driver.manage().window().getPosition();

        //selenium log: Browser/driver/network
        driver.manage().logs().get(LogType.BROWSER);
        driver.manage().logs().get(LogType.PERFORMANCE);
        driver.manage().logs().get(LogType.CLIENT);
        driver.manage().logs().get(LogType.SERVER);
        driver.manage().logs().getAvailableLogTypes();

        //quay lại trang trước đó
        driver.navigate().back();

        //chuyển đến trang trước đó
        driver.navigate().forward();

        //refresh lại trang hiện tại
        driver.navigate().refresh();

        driver.navigate().to("https://www.youtube.com/watch?v=I1doAPSxCco");
        //driver.navigate().to(new URL("https://www.youtube.com/watch?v=I1doAPSxCco"));




    }

    @Test
    public void TC_02() {
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
