package selenium.testcase;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import selenium.page.App;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.*;

class ManagetoolPageTest {

    public static App app;

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
    void addPic() {
        app.toMaterialLibrary().addPic("/Users/liyinglong/Pictures/01489_treeandthemountain_2560x1600.jpg");
    }
}