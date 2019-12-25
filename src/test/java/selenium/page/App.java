package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class App extends BasePage {
    public App loginWithCookie() throws MalformedURLException {
        String url="https://work.weixin.qq.com/";
        System.setProperty("webdriver.chrome.driver","/Users/liyinglong/WebDriver/chromedriver");
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setCapability("pageLoadStrategy","none");
//        driver = new ChromeDriver(chromeOptions);

        driver=new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), chromeOptions);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
        driver.manage().window().maximize();
        driver.findElement(By.linkText("企业登录")).click();

        System.out.println(driver.manage().getCookies());

        driver.manage().addCookie(new Cookie("wwrtx.refid", "30481189592949222"));
        driver.manage().addCookie(new Cookie("wwrtx.sid", "Tcb6SA36TuYf_VH4RkNXl_0ZOWv05BOjOgam6lJqP01E0sqdbJy_RnE4StGtPncu"));
        driver.navigate().refresh();
        return this;
    }
    public ContactPage toContact(){
        findElement(By.linkText("通讯录")).click();
        return new ContactPage();
    }

    public ContactPage toMemberAdd(){
        //find click
        findElement(By.linkText("添加成员")).click();
        return new ContactPage();
    }

    public BroadcastPage toGroupMessage(){
        findElement(By.linkText("管理工具")).click();
        findElement(By.cssSelector(".ww_icon_AppGroupMessageBig")).click();
        return new BroadcastPage();
    }

    public DepartmentPage toDepartment(){
        findElement(By.linkText("通讯录")).click();
        return new DepartmentPage();
    }

    public ManagetoolPage toMaterialLibrary(){
        findElement(By.linkText("管理工具")).click();
        findElement(By.xpath("//a[contains(@href, '#material/text')]")).click();
        return new ManagetoolPage();
    }

}
