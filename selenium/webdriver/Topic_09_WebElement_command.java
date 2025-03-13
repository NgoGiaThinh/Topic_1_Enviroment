package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_09_WebElement_command {
    WebDriver driver;

    public Topic_09_WebElement_command() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01() {
        //tương tác trực tiếp lên
        driver.findElement(By.cssSelector(""));

        //thao tác nhiều lần lên 1 element -> khai báo biến
        WebElement element = driver.findElement(By.cssSelector("input#firstname"));

        //xóa dữ liệu ở trong 1 editable element
        //textbox/ textArea/ Dropdown
        element.clear();

        //nhập dữ liệu vào 1 editable element(nhập)
        element.sendKeys("Maxxel");

        //1 - Element cha dùng 1 loại locator - element con 1 loại locator
        driver.findElement(By.cssSelector("div.form-fields"))
                .findElement(By.xpath("//input[@id='FirstName']"));

        //2 - cả cha vs con đều chung 1 loại locator
        driver.findElement(By.cssSelector("div.form-fields input#FirstName"));

        //tìm 1 element vs locator là tham số truyền vào
        driver.findElement(By.cssSelector(""));

        //tim nhiều loại element vs locator là tham số truyền vào
        driver.findElements(By.cssSelector(""));

        
        //click lên clickable element
        element.click();

        //tương đương vs submit thông tin để gửi lên server
        //giả lập hành vi Enter của End user
        //Register/login// search/...
        element.submit();

        //kiểm tra 1 element có hiển thị hay ko
        //áp dùng cho tất cả các loại element
        element.isDisplayed();

        //kiểm tra 1 element có được chọn hay chưa
        //áp dụng checkbox, radio,Dropdown
        element.isSelected();

        //kiểm tra 1 element có cho phép thao tác lên hay ko
        //cho phép sửa dữ liệu
        //true = được phép chỉnh sửa/thao tác
        //false = bị disabled
        //Test tính năng phân chuyển
        element.isEnabled();

        // III - lấy dữ liệu
        element.getSize();

        //lấy text của 1 element
        element.getSize();

        element.getAttribute("placeholder");
        //Search store

        //Shadow DOM
        element.getShadowRoot();
        //dev Frontend
        element.getAriaRole();
        element.getDomProperty("");
        element.getDomAttribute("");
        element.getAccessibleName();

        //Font/ Background/Font Size/... =>css
        element.getCssValue("background-color");//*
        // #df280a
        element.getCssValue("font-size");
        //13px
        element.getCssValue("font-family");
        //13px

        //lấy ra vị trí của element (góc trên bên trái) so với lại browser
        element.getLocation();

        //lấy ra tên thẻ html của element
        //By.id/ class/ name/ css
        element.getTagName();

        driver.findElement(By.id("email")).getTagName();
        //input

        //take screenshot(chụp hình lỗi)
        element.getScreenshotAs(OutputType.FILE);
        element.getScreenshotAs(OutputType.BYTES);
        element.getScreenshotAs(OutputType.BASE64);
    }

    @Test
    public void TC_02() {
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
