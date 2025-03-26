package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_21_Action_Part_II {
    WebDriver driver;
    Actions action;
    public Topic_21_Action_Part_II() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        action = new Actions(driver);
    }

    @Test
    public void TC_01_ClickAndHold_Fix() throws InterruptedException {
        this.driver.get("https://automationfc.github.io/jquery-selectable/");

        //click giữ chọn 1 số đầu tiên kéo đến số cuối cùng
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));

        //click():click xong nhả chuột
        //click and hold: click giữ

        action.clickAndHold(numbers.get(4))// click và giữ chuột tại element 5
                .moveToElement(numbers.get(11))// di chuột đến element 12
                .release()// nhả chuột
                .perform();// thực thi câu lệnh

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));

        Assert.assertEquals(numberSelected.size(), 8);


    }

    @Test
    public void TC_02_ClickAndHold_Random() {

        this.driver.get("https://automationfc.github.io/jquery-selectable/");

        //click giữ chọn 1 số đầu tiên kéo đến số cuối cùng
        List<WebElement> numbers = driver.findElements(By.cssSelector("ol#selectable>li"));
        Keys keys = null;

        //Actual number: 3 6 12 14 20
        List<String> actualNumber = new ArrayList<String>();
        actualNumber.add("3");
        actualNumber.add("6");
        actualNumber.add("12");
        actualNumber.add("14");
        actualNumber.add("20");

        keys = Keys.CONTROL;
        action.keyDown(keys).perform();//nhấn phím control xuống

        for (String number :actualNumber){
            action.click(numbers.get(Integer.parseInt(number) - 1));
        }
        action.keyUp(keys).perform();

//        //3 6 12 14 20
//        action.click(numbers.get(2))
//                .click(numbers.get(5))
//                .click(numbers.get(11))
//                .click(numbers.get(13))
//                .click(numbers.get(19))
//                .perform();
//        action.keyUp(keys).perform();

        List<WebElement> numberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
        Assert.assertEquals(numberSelected.size(), 5);

        //Expected number 3 6 12 14 20
        List<String> expectedNumber = new ArrayList<String>();

        for (WebElement number: numberSelected){
            expectedNumber.add(number.getText());
        }

        Assert.assertEquals(actualNumber,expectedNumber);
    }

    @Test
    public void TC_03_DoubleClick() throws InterruptedException {
        this.driver.get("https://automationfc.github.io/basic-form/index.html");


        if (driver.toString().contains("Firefox")){
            //scroll tới element bằng JS
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                    driver.findElement(By.xpath("//button[text()='Double click me']")));
        }

        Thread.sleep(3000);

        action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']")))
                .pause(Duration.ofSeconds(2))
                .perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");

    }

    @Test
    public void TC_04_RightClick() throws InterruptedException {

        this.driver.get(" https://swisnl.github.io/jQuery-contextMenu/demo.html");

        //verify quit menu không còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        //click chuột phải
        action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one")))
                .pause(Duration.ofSeconds(2))
                .perform();

        //verify quit menu hiển thị
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        //hover vào quit menu
        action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        //verify quit menu có sự kiện hover
        Assert.assertTrue(driver.findElement(
                By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover"))
                .isDisplayed());

        //click vào quit menu
        action.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        Thread.sleep(2000);

        //accept alert
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
        //verify quit menu ko còn hiển thị
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }



    @AfterClass
    public void afterClass(){ driver.quit();}
}
