package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_11_Element_Excecise {
    WebDriver driver;

    public Topic_11_Element_Excecise() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    //kiểm tra 1 element có hiển thị hay ko
    //áp dùng cho tất cả các loại element
    //element.isDisplayed();//**

    // kiểm tra 1 element đã được chọn hay chưa
    //áp dùng:checkbox/radio/dropdown
    // element.isSelected();//*

    //kiê tra 1 element có cho phép thao tác lên hay ko
    //cho phép sữa dữ liệu
    //true=được phép chỉnh sửa/ thao tác
    //false=bị disabled
    //test tính năng phân quyền
    // element.isEnabled();

    @Test
    public void TC_01_Displayed() {
        //Displayed # Undisplayed
        //người dùng có thể nhìn thấy (kích thước cụ thể)

        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()){
            driver.findElement(By.cssSelector("input#mail")).sendKeys("Automation Maxxel");
            System.out.println("Email textbox is displayed");
        } else {
            System.out.println("Email textbox is not displayed");
        }




        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()){
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("Automation Maxxel");
            System.out.println("Education TextArea is displayed");
        } else {
            System.out.println("Education TextArea is not displayed");
        }


        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()){
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Age under 18 Radio is displayed");
        } else {
            System.out.println("Age under 18 Radio is not displayed");
        }


        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()){
            driver.findElement(By.xpath("//h5[text()='Name: User5']/following-sibling::a")).click();
            System.out.println("Name user 5 Text is displayed");
        } else {
            System.out.println("Name user 5 Text is not displayed");
        }
    }

    @Test
    public void TC_02_Enabled() {
        //Selected # Disabled

        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()){

            System.out.println("Email textbox is Enabled");
        } else {
            System.out.println("Email textbox is not disabled");
        }


        if (driver.findElement(By.cssSelector("input#disable_password")).isEnabled()){

            System.out.println("Password textbox is Enabled");
        } else {
            System.out.println("Password textbox is disabled");
        }


        if (driver.findElement(By.cssSelector("input#slider-2")).isEnabled()){

            System.out.println("Slider is Enabled");
        } else {
            System.out.println("Slider is disabled");
        }




    }

    @Test
    public void TC_03_Selected() {
        //
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //click chọn
        driver.findElement(By.cssSelector("input#java")).click();
        driver.findElement(By.cssSelector("input#under_18")).click();

        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()){

            System.out.println("Age under 18 is selected");
        } else {
            System.out.println("Age under 18 is Deselected");
        }


        if (driver.findElement(By.cssSelector("input#java")).isSelected()){

            System.out.println("Java checkbox is selected");
        } else {
            System.out.println("Java checkbox  is Deselected");
        }

        //click chọn
        driver.findElement(By.cssSelector("input#java")).click();
        driver.findElement(By.cssSelector("input#under_18")).click();

        if (driver.findElement(By.cssSelector("input#under_18")).isSelected()){

            System.out.println("Age under 18 is selected");
        } else {
            System.out.println("Age under 18 is Deselected");
        }


        if (driver.findElement(By.cssSelector("input#java")).isSelected()){

            System.out.println("Java checkbox is selected");
        } else {
            System.out.println("Java checkbox  is Deselected");
        }


    }

    @Test
    public void TC_04_MailChimp() throws InterruptedException {
        driver.get("https://login.mailchimp.com/signup/");

        By signupButton = By.cssSelector("button#create-account-enabled");

        driver.findElement(By.cssSelector("input#email")).sendKeys("automationtesting@gmail.net");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(signupButton).click();

        Thread.sleep(3000);

        //empty
        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());


        //lowercase

        driver.findElement(By.cssSelector("input#new_password")).sendKeys("sele");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());


        //uppercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("SELE");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //numbercase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("123456");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //specialcase
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("!!@@##");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        //username
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("auto");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        //8 chars
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("AUTOMATION");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();
        Thread.sleep(3000);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());


        //full
        driver.findElement(By.cssSelector("input#new_password")).clear();
        driver.findElement(By.cssSelector("input#new_password")).sendKeys("Selenium123!!");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(signupButton));
        driver.findElement(By.cssSelector("button#create-account-enabled")).click();


        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        Assert.assertFalse(driver.findElement(By.cssSelector("input#marketing_newsletter")).isSelected());


    }

    @AfterClass
    public void afterClass(){
       //driver.quit();
    }
}
