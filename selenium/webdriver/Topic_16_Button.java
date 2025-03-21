package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Button {
    WebDriver driver;

    public Topic_16_Button() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() {
        this.driver.get("https://www.fahasa.com/customer/account/login/referer");
        driver.findElement(By.cssSelector("li.popup-login-tab-item.popup-login-tab-register")).click();


        By registerButton = By.cssSelector(("button.fhs-btn-register"));
        By loginButton = By.cssSelector(("button.fhs-btn-login"));

        Assert.assertFalse(driver.findElement(loginButton).isEnabled());


        Assert.assertEquals(Color.fromString(driver.findElement(loginButton)
                .getCssValue("background-color"))
                .asHex().toUpperCase(), "#00000000");

        // 1 - Clickable
        // chờ cho 1 element không được phép click trong vòng 10s
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(registerButton)));

        // 2 - Text hiển thị đúng
        Assert.assertEquals(driver.findElement(registerButton).getText().trim(), "Đăng ký");

        // 3 - Background màu gì
        String backgroundRegister = driver.findElement(registerButton).getCssValue("background-color");
        Assert.assertEquals(backgroundRegister, "rgba(0, 0, 0, 0)");
        Color.fromString(backgroundRegister).asHex();

        // 4 - Disable/ Enable
        // mong đợi element là enable thì assertTrue
        // mong đợi element là disable thì assertFalse
        Assert.assertFalse(driver.findElement(registerButton).isEnabled());

    }

    @Test
    public void TC_02() throws InterruptedException {
        this.driver.get("https://www.fahasa.com/customer/account/login/referer");

        By registerButton = By.cssSelector(("button.fhs-btn-register"));
        By loginButton = By.cssSelector(("button.fhs-btn-login"));

        //verify button disable
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());


        Assert.assertEquals(Color.fromString(driver.findElement(loginButton)
                        .getCssValue("background-color"))
                .asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("thinh@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");
        Thread.sleep(2000);

        //verify button enable
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        Assert.assertEquals(Color.fromString(driver.findElement(loginButton)
                        .getCssValue("background-color"))
                .asHex().toUpperCase(), "#C92127");
    }

    @AfterClass
    public void afterClass(){ driver.quit();}
}
