package selenium.page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.*;

/**
 * @ClassName DepartmentPage
 * @Description TODO
 * @Author liyinglong
 * @Date 2019/11/21 10:56 下午
 * @Version 1.0
 **/
public class DepartmentPage extends BasePage {
    /**
     * @return selenium.page.DepartmentPage
     * @Author liyinglong
     * @Description 增加子部门
     * @Date 1:51 下午 2019/11/22
     * @Param [name]
     **/
    public DepartmentPage addDepartment(String name) {
        Set<String> idList = getDepartmentList().keySet();
        try {
            for (String id : idList
            ) {
                StringBuffer locate = new StringBuffer();
                locate.append("//a[@id=");
                locate.append("'");
                locate.append(id);
                locate.append("'");
                locate.append("]/span");
                System.out.println(locate);

                waitClickable(By.id(id));
                findElement(By.id(id)).click();
                waitClickable(By.xpath(String.valueOf(locate)));
                findElement(By.xpath(String.valueOf(locate))).click();
                findElement(By.linkText("添加子部门")).click();
                String r = String.valueOf((Math.random() * 100));
                findElement(By.name("name")).sendKeys(name + r);
                findElement(By.linkText("确定")).click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("element not found");
        }
        return this;
    }

    /**
     * @Author liyinglong
     * @Description //传入要修改部门名称和新名称
     * @Date 10:15 下午 2019/12/1
     * @Param [subname, newname]
     * @return selenium.page.DepartmentPage
     **/
    public DepartmentPage editDepartment(String subname,String newname) {
        openList();
        HashMap<String,String > map = getDepartmentList();
        Set<String> keyList = map.keySet();
        if (!map.containsValue(subname)){
            System.out.println("不存在这个部门");
        } else {
            for (String id:keyList){
                if (map.get(id).equals(subname)){
                    StringBuffer locate = new StringBuffer();
                    locate.append("//a[@id=");
                    locate.append("'");
                    locate.append(id);
                    locate.append("'");
                    locate.append("]/span");
                    System.out.println(locate);

                    waitClickable(By.id(id));
                    findElement(By.id(id)).click();
                    waitClickable(By.xpath(String.valueOf(locate)));
                    findElement(By.xpath(String.valueOf(locate))).click();
                    findElement(By.linkText("修改名称")).click();
                    findElement(By.name("name")).clear();
                    findElement(By.name("name")).sendKeys(newname);
                    findElement(By.linkText("保存")).click();
                }
        }
        }
        return this;
    }

    /**
     * @Author liyinglong
     * @Description //传入要移动的部门以及上移或者下移
     * @Date 10:16 下午 2019/12/1
     * @Param [name, action]
     * @return selenium.page.DepartmentPage
     **/
    public DepartmentPage moveDepartment(String name,String action) {
        openList();
        HashMap<String,String > map = getDepartmentList();
        Set<String> keyList = map.keySet();
        if (!map.containsValue(name)){
            System.out.println("不存在这个部门");
        }else{
            for (String id:keyList){
                if (map.get(id).equals(name)){
                    StringBuffer locate = new StringBuffer();
                    locate.append("//a[@id=");
                    locate.append("'");
                    locate.append(id);
                    locate.append("'");
                    locate.append("]/span");
                    System.out.println(locate);

                    waitClickable(By.id(id));
                    findElement(By.id(id)).click();
                    waitClickable(By.xpath(String.valueOf(locate)));
                    findElement(By.xpath(String.valueOf(locate))).click();
                    try {
                        findElement(By.linkText(action)).click();
                    }catch (Exception e){
                        System.out.println("该部门不能" + action);
                    }
                    }

                }
        }
        return this;
    }

    public DepartmentPage delDepartment() {
        openList();
        Set<String> keySet = getDepartmentList().keySet();
        for (String id:keySet){
            StringBuffer locate = new StringBuffer();
            locate.append("//a[@id=");
            locate.append("'");
            locate.append(id);
            locate.append("'");
            locate.append("]/span");
            System.out.println(locate);

            waitClickable(By.id(id));
            findElement(By.id(id)).click();
            waitClickable(By.xpath(String.valueOf(locate)));
            findElement(By.xpath(String.valueOf(locate))).click();
            findElement(By.linkText("删除")).click();
            try{
                findElement(By.linkText("确定")).click();
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println("无法删除部门");
            }
        }
        return this;
    }

    /**
     * @Author liyinglong
     * @Description //获取部门列表的id和name
     * @Date 10:17 下午 2019/12/1
     * @Param []
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     **/
    public HashMap<String, String> getDepartmentList() {
        HashMap<String, String> departmentMap = new HashMap<>();
        int i = 0;
        while (i < 2) {
            try {
                findElements(By.cssSelector(".jstree-anchor")).forEach(webElement -> departmentMap.put(webElement.getAttribute("id"), webElement.getText()));
                if (!departmentMap.isEmpty()) {
                    departmentMap.forEach((id, name) -> {
                        System.out.println(name + ":" + id);
                    });
                }
                System.out.println("总共有：" + (departmentMap.size() + 1) + "个部门");
                break;
            } catch (StaleElementReferenceException ex) {
                System.out.println("无法捕获到元素");
            }
            i++;
        }
        return departmentMap;
    }

    /**
     * @Author liyinglong
     * @Description //展开部门列表
     * @Date 10:17 下午 2019/12/1
     * @Param []
     * @return void
     **/
    public void openList(){
        int i =0;
        while (i<2){
            try {
                waitElementsVisibility(findElements(By.cssSelector("i.jstree-icon.jstree-ocl")));
                List<WebElement> list =findElements(By.cssSelector("i.jstree-icon.jstree-ocl"));
                if (list.size() > 1){
                    list.remove(0);
                    for(WebElement icon :list){
                        icon.click();
                        Thread.sleep(500);
                    }
                    break;
                }
            }catch (StaleElementReferenceException | InterruptedException ex){
                System.out.println("无法打开下拉箭头");
            }
            i++;
        }
    }
}
