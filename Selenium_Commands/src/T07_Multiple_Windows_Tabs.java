import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T07_Multiple_Windows_Tabs {

    WebDriver driver;


    @BeforeMethod
    public void OpenBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Browsers\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(priority = 1)
   public void nopCommerce() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");

        //1- Click on Social MEdia Links
        driver.findElement(By.cssSelector("a[href=\"http://www.facebook.com/nopCommerce\"]")).click();

        //2-  Arraylist tab0 , tab1
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        //3- Switch driver from tab0 to tab1
        driver.switchTo().window(tabs.get(1));

        //4- GetCurrentURL and assert it contains "Social media links" AKA twitter.com
        //Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/nopCommerce"));
        //System.out.println(driver.getCurrentUrl());
    }


    @Test (priority = 2)
    public void TwitterLink() throws InterruptedException {
        driver.get("https://demo.nopcommerce.com/");

        //1- Click on Social MEdia Links
        driver.findElement(By.cssSelector("a[href=\"https://twitter.com/nopCommerce\"]")).click();

        //2-  Arraylist tab0 , tab1
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        //3- Switch driver from tab0 to tab1
        driver.switchTo().window(tabs.get(1));

        //4- GetCurrentURL and assert it contains "Social media links" AKA twitter.com
        Thread.sleep(4000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://twitter.com/nopCommerce"));
        //System.out.println(driver.getCurrentUrl());

    }

    @Test (priority = 3)
    public void MultipleTabsFB_Tw () throws InterruptedException {

        driver.get("https://demo.nopcommerce.com/");

        //1- Click on Social MEdia Links
        driver.findElement(By.cssSelector("a[href=\"http://www.facebook.com/nopCommerce\"]")).click();

        //2-  Arraylist tab0 , tab1
        Thread.sleep(3000);
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        //3- Switch driver from tab0 to tab1
        driver.switchTo().window(tabs.get(1));

        //4- GetCurrentURL and assert it contains "Social media links" AKA twitter.com
        Thread.sleep(4000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://www.facebook.com/nopCommerce"));
        //System.out.println(driver.getCurrentUrl());

        // 5- Close Current Tab
        driver.close();

    // 6- Switch driver to tab 0
        driver.switchTo().window(tabs.get(0));
    // 7- Click on Twitter to open new tab
        driver.findElement(By.cssSelector("a[href=\"https://twitter.com/nopCommerce\"]")).click();
        Thread.sleep(3000);

        tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        // "Social media links" AKA twitter.com
        Thread.sleep(4000);
        Assert.assertTrue(driver.getCurrentUrl().contains("https://twitter.com/nopCommerce"));



    }


    @AfterMethod
    public void quitBrowser() throws InterruptedException {

        Thread.sleep(3000);
        driver.quit();

    }
}
