package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.InputEvent;
import java.time.Duration;

public class Topic_22_Action_Part_III {
    WebDriver driver;
    Actions action;
    public Topic_22_Action_Part_III() {
    }

    @BeforeClass
    public void beforeClass(){
        //this.driver = new FirefoxDriver();
        this.driver = new EdgeDriver();
        //this.driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();

        action = new Actions(driver);
    }

    @Test
    public void TC_01_Drag_HTML4() {
        this.driver.get("https://automationfc.github.io/kendo-drag-drop/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));

        //trước khi drag and drop
        Assert.assertEquals(targetCircle.getText(), "Drag the small circle here.");

        //Drag and drop
        action.dragAndDrop(sourceCircle, targetCircle).perform();

        //sau khi drag and drop
        Assert.assertEquals(targetCircle.getText(), "You did great!");
    }

    @Test
    public void TC_02_Drag_HTML5() {
        this.driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement sourceCircle = driver.findElement(By.cssSelector("div#column-a"));
        WebElement targetCircle = driver.findElement(By.cssSelector("div#column-b"));

        action.dragAndDrop(sourceCircle, targetCircle).perform();

        //sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
    }

    @Test
    public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator ) throws AWTException {

        WebElement source = driver.findElement(By.xpath(sourceLocator));
        WebElement target = driver.findElement(By.xpath(targetLocator));

        //setup robot
        Robot robot = new Robot();
        robot.setAutoDelay(500);

        //Get size of elements
        Dimension sourceSize = source.getSize();
        Dimension targetSize = target.getSize();

        //get center distance
        int xCentreSource = sourceSize.width / 2;
        int yCentreSource = sourceSize.height / 2;
        int xCentreTarget = targetSize.width / 2;
        int yCentreTarget = targetSize.height / 2;

        Point sourceLocation = source.getLocation();
        Point targetLocation = target.getLocation();

        //Make Mouse coordinate center of element
        sourceLocation.x += 20 + xCentreSource;
        sourceLocation.y += 110 + yCentreSource;
        targetLocation.x += 20 + xCentreTarget;
        targetLocation.y += 110 + yCentreTarget;

        // Move mouse to drag from location
        robot.mouseMove(sourceLocation.x, sourceLocation.y);

        // Click and drag
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

        // Move to final position
        robot.mouseMove(targetLocation.x, targetLocation.y);

        // Drop
        robot.mouseRelease(InputEvent.BUTTON1_MASK);


    }


    @Test
    public void TC_04_Drag_Drop_HTML5_Robot() throws InterruptedException, AWTException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        //Drag and drop
        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        Thread.sleep(3000);

        //sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");

        //Drag and drop
        dragAndDropHTML5ByXpath("//div[@id='column-a']","//div[@id='column-b']");
        Thread.sleep(3000);

        //sau khi drag and drop
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
    }

    @Test
    public void TC_05() {
    }


    @AfterClass
    public void afterClass(){ driver.quit();}
}
