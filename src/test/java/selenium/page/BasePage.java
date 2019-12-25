package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {
    public static RemoteWebDriver driver;

    public WebElement findElement(By by){
        return findElement(by, 5);
    }
    public WebElement findElement(By by, int timeout){
        System.out.println(by);
        if(timeout>0) {
            waitClickable(by, timeout);
            System.out.println("clickable");
        }
        return driver.findElement(by);
    }

    public List<WebElement> findElements(By by){
        return  driver.findElements(by);
    }

    public void waitClickable(By by, int timeout){
        new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(by));
    }
    public void waitClickable(By by){
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(by));
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(by));

    }

    public void waitElementsVisibility(List<WebElement> elements){
        new WebDriverWait(driver,5).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void quit() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }
}
