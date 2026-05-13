package Service.Pojo;

import java.util.List;
@lombok.Data
public class ToFileModel {
    // 项目名
    public String project;
    // 接口
    public String  controller;
    // 接口第一字符小写
    public String  controllerLower;
    // contrller+Test
    public String testName;
    // 方法名
    public List<toMethod> methods;

}
