import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T04_Status_Enabled_Selected {

    WebDriver driver;


    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test(priority = 0)
    public void Browser() throws InterruptedException {

        //IsEnabled status in box1
        boolean Box1Enabled = driver.findElements(By.cssSelector("input[type=\"checkbox\"]")).get(0).isEnabled();
        System.out.println(Box1Enabled);

        // check isSelected for checkbox1
        boolean Box1Selected = driver.findElements(By.cssSelector("input[type=\"checkbox\"]")).get(0).isSelected();
        System.out.println(Box1Selected);

            // isEnabled for Checkbox2
        boolean Box2Enabled = driver.findElements(By.cssSelector("input[type=\"checkbox\"]")).get(1).isEnabled();
        System.out.println(Box2Enabled);

        // isSelected in Box2
        boolean Box2Selected = driver.findElements(By.cssSelector("input[type=\"checkbox\"]")).get(1).isSelected();
        System.out.println(Box2Selected);



    }

    @AfterMethod
    public void quitBrowser() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();

    }
}
