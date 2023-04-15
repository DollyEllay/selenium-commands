import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T03_Get_Info_WebElements {

    WebDriver driver;


    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test(priority = 0)
    public void Browser() throws InterruptedException {

        String getText = driver.findElement(By.cssSelector("a[target=\"_blank\"][href=\"http://elementalselenium.com/\"]")).getText();            //Expected output Elemental Selenium
        System.out.println(getText);

        String getAtt = driver.findElement(By.cssSelector("a[target=\"_blank\"][href=\"http://elementalselenium.com/\"]")).getAttribute("href");
        System.out.println(getAtt);

        driver.findElement(By.className("radius")).click();
        String colorValue = driver.findElement(By.id("flash")).getCssValue("background-color");
        System.out.println(colorValue);


    }

    @AfterMethod
    public void quitBrowser() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();

    }
}
