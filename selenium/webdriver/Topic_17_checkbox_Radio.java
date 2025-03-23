package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_17_checkbox_Radio {
    WebDriver driver;

    public Topic_17_checkbox_Radio() {
    }

    @BeforeClass
    public void beforeClass(){
        this.driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_KendoUI() throws InterruptedException {
        this.driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        Thread.sleep(5000);

        String dualZoneCheckbox = "//label[text()='Dual-zone air conditioning']/preceding-sibling::span/input";

        driver.findElement(By.xpath(dualZoneCheckbox)).click();
        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.xpath(dualZoneCheckbox)).isSelected());

        driver.findElement(By.xpath(dualZoneCheckbox)).click();
        Thread.sleep(2000);

        Assert.assertFalse(driver.findElement(By.xpath(dualZoneCheckbox)).isSelected());

    }

    @Test
    public void TC_02() throws InterruptedException {
        this.driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By petrol = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@id='demo-runner']")));
        Thread.sleep(2000);

        if (!driver.findElement(petrol).isSelected()){
            driver.findElement(petrol).click();
            Thread.sleep(2000);
        }
        //Verify chọn thành cong
        Assert.assertTrue(driver.findElement(petrol).isSelected());
    }
    @Test
    public void TC_03_Select_All() throws InterruptedException {


        driver.get("https://automationfc.github.io/multiple-fields/");

        Thread.sleep(2000);

        List<WebElement> allcheckboxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

//        //1 - select hết tất cả checkbox
//        for (WebElement checkbox: allcheckboxes){
//            if(!checkbox.isSelected()){
//                checkbox.click();
//            }
//        }
//
//        //4 - Verify các điều kiện trên
//        for (WebElement checkbox : allcheckboxes){
//            Assert.assertTrue(checkbox.isSelected());
//        }
//
//        //2-  Deselect tất cả check box
//        for (WebElement checkbox: allcheckboxes){
//            if(!checkbox.isSelected()){
//                checkbox.click();
//            }
//        }
//
//        for (WebElement checkbox : allcheckboxes){
//            Assert.assertTrue(checkbox.isSelected());
//        }
//
//    }
        //3 - select/deselect 1 item trong all item

        for (WebElement checkbox: allcheckboxes){
            if(checkbox.getDomAttribute("value").equals("Heart Attack") && !checkbox.isSelected()){
                checkbox.click();
            }
        }
        //4 - verify
        for (WebElement checkbox : allcheckboxes){
            if (checkbox.getDomAttribute("value").equals("Heart Attack")){
                Assert.assertTrue(checkbox.isSelected());
            }
        }
    }

    @Test
    public void TC_04_Radio_Button_Summer() throws InterruptedException {
        driver.get("https://material.angular.io/components/radio/examples");

        Thread.sleep(2000);
        By radioSummer = By.xpath("//label[text()='Summer']/preceding-sibling::div/input");

        if (!driver.findElement(radioSummer).isSelected()){
            driver.findElement(radioSummer).click();
            Thread.sleep(2000);
        }

        Assert.assertTrue(driver.findElement(radioSummer).isSelected());

    }
    @Test
    public void TC_05_checkbox_Checked_Inderterminate() throws InterruptedException {
        driver.get("https://material.angular.io/components/checkbox/examples");
        Thread.sleep(2000);

        By radioChecked = By.xpath("//label[text()='Checked']/preceding-sibling::div/input");
        By radioIndeterminate = By.xpath("//label[text()='Indeterminate']/preceding-sibling::div/input");
        driver.findElement((radioChecked)).click();
        driver.findElement((radioIndeterminate)).click();

        Assert.assertTrue(driver.findElement(radioChecked).isSelected());
        Assert.assertTrue(driver.findElement(radioIndeterminate).isSelected());
        Thread.sleep(2000);


        driver.findElement((radioChecked)).click();
        driver.findElement((radioIndeterminate)).click();

        Assert.assertFalse(driver.findElement(radioChecked).isSelected());
        Assert.assertFalse(driver.findElement(radioIndeterminate).isSelected());

        Thread.sleep(2000);

    }


    @AfterClass
    public void afterClass(){ driver.quit();}
}
