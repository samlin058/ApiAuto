package Service.Config;

import Service.Enum.ProjectEnum;
import Service.Pojo.LoginObject;
import Service.Util.Universal.FileHandler;
import Service.Util.Universal.GetStackInfoHandler;
import Service.Util.Universal.StringHandler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public class Login {
    // 默认登录数据信息
    private static final List<LoginObject> DEFAULT_LOGIN_OBJECTS = loadDefaultLoginData();

    // 动态加载登录信息
    public static List<LoginObject> dynamicLoginObjects;

    static {
        dynamicLoginObjects = loadDynamicLoginData();
    }

    // 加载默认登录数据
    private static List<LoginObject> loadDefaultLoginData() {
        String defaultLoginJsonStr = FileHandler.loadJsonFile("src/main/resources/defaultLogin.json");
        return JSONArray.parseArray(defaultLoginJsonStr, LoginObject.class);
    }

    // 加载动态登录数据
    private static List<LoginObject> loadDynamicLoginData() {
        String loginJsonStr = FileHandler.loadJsonFile("src/main/resources/login.json");
        return JSONArray.parseArray(loginJsonStr, LoginObject.class);
    }

    public static Map<String, String> getDefaultLoginByProjectAndUser(ProjectEnum projectEnum, String userName) {
        return getLoginByUser(DEFAULT_LOGIN_OBJECTS, projectEnum, userName);
    }

    public static Map<String, String> getLoginByUser(String userName) {
        String ClassName = GetStackInfoHandler.getCallerClassPath();
        String projectName = StringHandler.getNthValue(ClassName, "\\.", 3);
        if (dynamicLoginObjects == null || dynamicLoginObjects.isEmpty()) {
            dynamicLoginObjects = loadDynamicLoginData();
        }
        return getLoginByUser(dynamicLoginObjects, ProjectEnum.valueOf(projectName), userName);
    }

    private static Map<String, String> getLoginByUser(List<LoginObject> loginObjects, ProjectEnum projectEnum, String userName) {
        if (loginObjects == null || loginObjects.isEmpty()) {
            return null;
        }

        // 查找匹配的 LoginObject
        return loginObjects.stream()
                .filter(loginObject -> projectEnum.name().equals(loginObject.getProject()))
                .findFirst()
                .map(loginObject -> {
                    String templateStr = JSON.toJSONString(loginObject.getTemplate());
                    String userInfoStr = loginObject.getLoginInfo().stream()
                            .flatMap(userInfoMap -> userInfoMap.entrySet().stream())
                            .filter(entry -> entry.getKey().equals(userName))
                            .map(Map.Entry::getValue)
                            .findFirst()
                            .orElse("");
                    return StringHandler.getLoginInfo(templateStr, userInfoStr);
                })
                .orElse(null);
    }

    public static boolean hasData(String projectName) {
        if (StringHandler.isEmpty(dynamicLoginObjects)) {
            return false;
        }
        return dynamicLoginObjects.stream()
                .anyMatch(loginObject -> projectName.equals(loginObject.getProject()) &&
                        !StringHandler.isEmpty(loginObject.getLoginInfo()));
    }
}
