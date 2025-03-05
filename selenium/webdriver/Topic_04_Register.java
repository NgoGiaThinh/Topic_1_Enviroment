package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_Register {
    WebDriver driver;

    public Topic_04_Register() {
    }

    @BeforeClass
    public void beforeClass(){
        //Arrange
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Register_01_Empty_Data() {
        //Action 1
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        //Action2
        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        //get text thì pha verify(dùng thư viện Assert)
        //Actual Result - Expected Result
        //Assert 1
        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }

    @Test
    public void Register_02_Invalid_Email() {
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.name("txtFirstname")).sendKeys("Maxxel");
        driver.findElement(By.name("txtEmail")).sendKeys("Maxxel@123..123");
        driver.findElement(By.name("txtCEmail")).sendKeys("Maxxel@123..123");
        driver.findElement(By.name("txtPassword")).sendKeys("123456");
        driver.findElement(By.name("txtCPassword")).sendKeys("123456");
        driver.findElement(By.name("txtPhone")).sendKeys("0987654321");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email hợp lệ");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại email hợp lệ");
    }

    @Test
    public void Register_03_Incorrect_Confirm_Email() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.name("txtFirstname")).sendKeys("Maxxel");
        driver.findElement(By.name("txtEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("Maxxel@maxel.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123456");
        driver.findElement(By.name("txtCPassword")).sendKeys("123456");
        driver.findElement(By.name("txtPhone")).sendKeys("0987654321");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Email nhập lại không đúng");
    }

    @Test
    public void Register_04_Incorrect_Password() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.name("txtFirstname")).sendKeys("Maxxel");
        driver.findElement(By.name("txtEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123");
        driver.findElement(By.name("txtCPassword")).sendKeys("123");
        driver.findElement(By.name("txtPhone")).sendKeys("0987654321");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void Register_05_Incorrect_Confirm_Password() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.findElement(By.name("txtFirstname")).sendKeys("Maxxel");
        driver.findElement(By.name("txtEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123456");
        driver.findElement(By.name("txtCPassword")).sendKeys("123466");
        driver.findElement(By.name("txtPhone")).sendKeys("0987654321");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();


        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void Register_6_Invalid_Phone_Number() {

        driver.get("https://alada.vn/tai-khoan/dang-ky.html");

        //case 1 - less than 10 chars
        driver.findElement(By.name("txtFirstname")).sendKeys("Maxxel");
        driver.findElement(By.name("txtEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtCEmail")).sendKeys("Maxxel@maxxel.com");
        driver.findElement(By.name("txtPassword")).sendKeys("123456");
        driver.findElement(By.name("txtCPassword")).sendKeys("123456");
        driver.findElement(By.name("txtPhone")).sendKeys("0987654");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();


        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //case 2 - more than 11 chars
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("0987654321311");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();


        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại phải từ 10-11 số.");

        //case 3 - contains text
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("0987654s21");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();


        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập con số");

        //case 4 - Not start with 0xxx
        driver.findElement(By.name("txtPhone")).clear();
        driver.findElement(By.name("txtPhone")).sendKeys("2987654321");


        driver.findElement(By.xpath("//form[@id='frmLogin']//button[text()='ĐĂNG KÝ']")).click();


        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019 - 088 - 03 - 05 - 07 - 08");
    }
    @AfterClass
    public void afterClass(){ driver.quit();}
}
