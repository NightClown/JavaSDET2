package selenium.page;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.bcel.internal.generic.DRETURN;
import org.openqa.selenium.By;

/**
 * @ClassName ManagetoolPage
 * @Description TODO
 * @Author liyinglong
 * @Date 2019/12/6 10:41 下午
 * @Version 1.0
 **/
public class ManagetoolPage extends BasePage{
    public ManagetoolPage addPic(String path){
        findElement(By.linkText("图片")).click();
        findElement(By.linkText("添加图片")).click();
        findElement(By.id("js_upload_input")).sendKeys(path);
        findElement(By.linkText("完成")).click();
        return this;
    }
}
