package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_15_Custom_dropdown {
    WebDriver driver;

    public Topic_15_Custom_dropdown() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01() throws InterruptedException {
        this.driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

       driver.findElement(By.cssSelector("span#salutation-button")).click();
        Thread.sleep(2000);

        //chờ cho tất cả item được load ra hết
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector("ul#salutation-menu div")));

        // tìm và lấy ra hết tất cả các item bên trong và lưu vào 1 biến (kiểu dữ liệu là list)
        List<WebElement> allItems = driver.findElements(By.cssSelector("ul#salutation-menu div"));

        //duyệt qua từng cái element để kiểm tra
        for (WebElement item : allItems){
            //kiểm tra điều kiện: nếu text của item lấy ra bằng vs mong đợi
            if (item.getText().equals("Dr.")) {
                //click vào chính item đó
                item.click();

                //thoát vòng lặp
                break;
            }
        }
        Thread.sleep(5000);
    }

    @Test
    public void TC_02() throws InterruptedException {
        this.driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        driver.findElement(By.cssSelector("div.dropdown")).click();
        Thread.sleep(2000);

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector("div.transition div")));

        List<WebElement> allItems = driver.findElements(By.cssSelector("div.transition div"));

        for (WebElement item : allItems){
            //kiểm tra điều kiện: nếu text của item lấy ra bằng vs mong đợi
            if (item.getText().equals("Christian")) {
                //click vào chính item đó
                item.click();

                //thoát vòng lặp
                break;
            }
        }
        Thread.sleep(5000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.dropdown")).getText(), "Christian");
    }

    @Test
    public void TC_03() throws InterruptedException {

        this.driver.get("https://mikerodham.github.io/vue-dropdowns/");

        driver.findElement(By.cssSelector("div.btn-group")).click();
        Thread.sleep(2000);


        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector("ul.dropdown-menu li")));

        List<WebElement> allItems = driver.findElements(By.cssSelector("ul.dropdown-menu li"));

        for (WebElement item : allItems){
            //kiểm tra điều kiện: nếu text của item lấy ra bằng vs mong đợi
            if (item.getText().equals("Second Option")) {
                //click vào chính item đó
                item.click();

                //thoát vòng lặp
                break;
            }
        }
        Thread.sleep(5000);
       Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");

    }
      @Test
      public void EditableDropdown() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");

        driver.findElement(By.cssSelector("div.hwid-dropInpunt")).click();
          driver.findElement(By.cssSelector("input[ht='input_emailregister_search']")).sendKeys("Benin");
          Thread.sleep(2000);

          new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                  .presenceOfAllElementsLocatedBy(By.cssSelector("ul.hwid-alpla-list div")));

          List<WebElement> allItems = driver.findElements(By.cssSelector("ul.hwid-alpla-list div"));

          for (WebElement item : allItems){

              if (item.getText().equals("Benin")) {
                  item.click();
                  break;
              }
          }
          Thread.sleep(2000);
          Assert.assertEquals(driver.findElement(By.cssSelector("div.hwid-countryDropDown-root span.hwid-select-text")).getText(), "Benin");
      }

    private void selectItemInEditable(String parentLocator, String childLocator, String editalbeLocator, String textItem) throws InterruptedException {


        driver.findElement(By.cssSelector(parentLocator)).click();
        Thread.sleep(1500);

        driver.findElement(By.cssSelector(editalbeLocator)).clear();
        driver.findElement(By.cssSelector(editalbeLocator)).sendKeys(textItem);
        Thread.sleep(2000);

        //chờ cho tất cả item được load ra hết
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

        // tìm và lấy ra hết tất cả các item bên trong và lưu vào 1 biến (kiểu dữ liệu là list)
        List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));

        for (WebElement item : allItems) {
            //kiểm tra điều kiện: nếu text của item lấy ra bằng vs mong đợi
            if (item.getText().equals(textItem)) {
                //click vào chính item đó
                item.click();
                Thread.sleep(2000);

                //thoát vòng lặp
                break;
            }
        }
    }

        @AfterClass
        public void afterClass () {
            driver.quit();
        }
    }