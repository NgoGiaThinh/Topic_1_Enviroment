package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Topic_14_Default_Dropdown {
    WebDriver driver;
    Select select;

    public Topic_14_Default_Dropdown() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        this.driver.get("https://egov.danang.gov.vn/reg");
        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));

        //chạy fail thì nhìn text biết ngay để manual test lại
        //nếu Dev/client họ thay đổi giá trĩ của text item thì detect ra ngay
        //dễ nhớ
        select.selectByVisibleText("tỉnh Bình Thuận");
        Thread.sleep(4000);

        //lâ ra dc item vừa chọn để verify
        Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Bình Thuận");

        //kiểm tra 1 dropdown là single hay multi
        Assert.assertFalse(select.isMultiple());






        //lấy ra cái item bên trong dropdown
        select = new Select(driver.findElement(By.cssSelector("select#thuongtru_tinhthanh")));
        List<WebElement> districtElements = select.getOptions();
        List<String> districtText = new ArrayList<>();

        for (WebElement district: districtElements){
            districtText.add(district.getText());
        }

        Assert.assertTrue(districtText.contains("thành phố Hải Phòng"));
        Assert.assertTrue(districtText.contains("thành phố Đà Nẵng"));
        Assert.assertTrue(districtText.contains("thành phố Cần Thơ"));
    }

    @Test
    public void TC_02() {

        driver.get("https://demo.nopcommerce.com/register");

        String firstName = "John";
        String lastName = "Wick";
        String emaliAddress = "mrjohn" + new Random().nextInt(9999) + "@hotmail.com";
        String companyName = "Noodle";
        String password = "1234567";
        String day = "8";
        String month = "May";
        String year = "1980";

        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.id("LastName")).sendKeys(lastName);
        //new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
        //new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
        //new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);
        driver.findElement(By.id("Email")).sendKeys(emaliAddress);
        driver.findElement(By.id("Company")).sendKeys(companyName);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");

        driver.findElement(By.cssSelector("a.ico-account")).click();

        Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emaliAddress);
        Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
        //Assert.assertEquals( new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
        //Assert.assertEquals( new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month );
        //Assert.assertEquals( new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);

    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
