package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;

public class Topic_01_Datatype {
    //1 kiểu dữ liệu sẽ được sử dụng cho 1 thông tin/thuộc tính nào đó
    //8 kiểu đại diện chia ra 4 nhóm
    //kiểu kí tự (đại diện cho 1 kí tự)
    char c = 'B';

    //kiểu số nguyên ( Không có thập phân)
    byte bNumber = 10; //kiểu số ko có nháy đơn hoặc nháy đôi
    short sNumber = 100;
    int iNumber = 70000;
    long lNumber = 1000000;

    //kiểu số thực(có thập phân)
    float fNumber = 10.5f;
    double dNumber = 15.9d;

    // Kiểu logic
    boolean sex = true;

    // 2 - kiểu dữ liệu tham chiếu(reference type)
    // kiểu Mảng (Array)
    // Mảng kiểu String
    String[] studentName = {"Nguyễn Văn Nam", "Lê Văn Cẩu", "Hoàng Thị Lan"};
    int[] studentAge = {25, 22, 28};

    // Kiểu Object(đại diện cho các kiểu dữ liệu khác)
    // Đối tượng => chuyển đổi qua các kiểu dữ liệu khác
    Object studentAddress = 35;
    Object employeeAge = 35;
    Object employeeSex = false;

    // Kiểu chuỗi (String)
    String name = "Bình";
    String employeeNumber = "123456789";

    // Class/ Interface/ Collection
    FirefoxDriver ffDriver = new FirefoxDriver();
    //public class FirefoxDriver

    // Interface
    WebDriver driver = new ChromeDriver();
    //public interface WebDriver

    //Collection
    List<WebElement> textboxes = driver.findElements(By.cssSelector(""));
    ArrayList<String> studentCity = new ArrayList<>();
}
