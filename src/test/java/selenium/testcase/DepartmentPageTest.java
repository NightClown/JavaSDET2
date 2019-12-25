package selenium.testcase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import selenium.page.App;

import java.net.MalformedURLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentPageTest {
    public static App app;
    private WebDriver driver;

    @BeforeAll
    static void beforeAll() throws MalformedURLException {
        app = new App();
        app.loginWithCookie();
    }

    @AfterAll
    static void afterAll() throws InterruptedException {
        app.quit();
    }

    @Test
    void addDepartment() {
        app.toDepartment().addDepartment("洛杉矶湖人");
    }

    @Test
    void editDepartment(){
        app.toDepartment().editDepartment("洛杉矶湖人48.013514372724806","洛杉矶湖人A2");
    }

    @Test
    void moveDepartment(){
        app.toDepartment().moveDepartment("金州勇士","上移");
    }

    @Test
    void delDepartment(){
        app.toDepartment().delDepartment();
    }
}