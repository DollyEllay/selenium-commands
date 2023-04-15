import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class T05_Status_Displayed_herokuapp {

    WebDriver driver;


    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    }

    @Test(priority = 0)
    public void Browser() throws InterruptedException {

        //Click on Add Element
        driver.findElement(By.cssSelector("button[onclick=\"addElement()\"]")).click();

        // Verify delete button displayed in UI
        boolean status = driver.findElement(By.cssSelector("button[class=\"added-manually\"]")).isDisplayed();
        System.out.println(status);

        // Click on delete button
         driver.findElement(By.cssSelector("button[class=\"added-manually\"]")).click();

        // Verify delete button is removed from DOM page
        // driver.findElements(By.cssSelector("button[class=\"added-manually\"]")).isEmpty();
        int num = driver.findElements(By.cssSelector("button[class=\"added-manually\"]")).size();
        Assert.assertEquals(num, 0);

    }

    @AfterMethod
    public void quitBrowser() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();

    }
}
