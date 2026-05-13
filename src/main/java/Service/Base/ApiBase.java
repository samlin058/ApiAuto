package Service.Base;

import Service.Annotations.AnnotationProcessor.AnnotationInfoExtractor;
import Service.Config.Config;
import Service.Config.Login;
import Service.Enum.ProjectEnum;
import Service.Util.Universal.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@lombok.Data
public class ApiBase {
    // 服务域名orIP
    public static Map<String, String> host;
    // 请求头
    public static Map<String, String> header;
    // 资源地址
    public String url;
    // 请求类型
    public String requestType;
    // 请求头参数
    Map<String, Object> param;
    // url路径参数
    Map<String, Object> pathParam;
    // 请求体
    Map<String, Object> body;
    // 文件对象
    Map<String, String> fileObject;
    // post
    String contentType;
    // 项目默认的请求头
    public static Map<String, String> defaultHeader;
    // 是否输出日志
    private static final String Is_Print_Logger = (String) PropertiesHandle.configuration.get("isPrintLogger");

    static {
        if (host == null) {
            host = new HashMap<>();
        }
        host = PropertiesHandle.PropertiesToHashMapExample(PropertiesHandle.hostProperties);
    }

    /**
     * 不带参数的http请求
     *
     * @return 请求结果
     */
    public String http() {
        String result = "";
        try {
            InitProjectHeader(this);
            // 获取调用http()的方法名
            String methodName = GetStackInfoHandler.getCallerMethodNameByStackIndex(3);
            // 根据方法名获取对应的请求信息,且po转成ro
            AnnotationInfoExtractor.po2Ro(this, methodName);
            result = HttpHandler.http(this);
            // isPass(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Is_Print_Logger.equalsIgnoreCase("true")) {
            if (!GetStackInfoHandler.isCalledFromDataFactory()) {
                StringHandler.jsonFormatOutput(result);
            }
        }
        return result;
    }

    /**
     * 带参数的http请求(单个)
     *
     * @param ro 请求参数对象
     * @return 请求结果
     */
    public String http(ApiBase ro) {
        InitProjectHeader(ro);
        AnnotationInfoExtractor.extractAnnotations(ro);
        String result = HttpHandler.http(ro);
        // isPass(result);
        if (Is_Print_Logger.equalsIgnoreCase("true")) {
            if (!GetStackInfoHandler.isCalledFromDataFactory()) {
                StringHandler.jsonFormatOutput(result);
            }
        }
        return result;
    }

    /**
     * 带参数的http请求(多个ro)
     *
     * @param roList 请求对象数组
     * @return 请求结果
     */
    public String http(List<?> roList) {
        ApiBase ro = AnnotationInfoExtractor.extractAnnotations(roList);
        InitProjectHeader(ro);
        String result = HttpHandler.http(ro);
        // isPass(result);
        if (Is_Print_Logger.equalsIgnoreCase("true")) {
            if (!GetStackInfoHandler.isCalledFromDataFactory()) {
                StringHandler.jsonFormatOutput(result);
            }
        }
        return result;
    }

    /**
     * 设置上传的文件对象
     *
     * @param key  入参字段
     * @param path 路径
     */
    public void setFileObject(String key, String path) {
        Map<String, String> map = new HashMap<>();
        map.put(key, path);
        this.fileObject = map;
    }

    /**
     * 将json字符串转成接口对应的Ro并发起http请求
     *
     * @param requestData 请求数据
     * @param roClazz     对应的ro类
     */
    public String convert2RoAndRequest(String requestData, Class<?> roClazz) {
        if (StringHandler.isEmpty(requestData)) {
            System.out.println("入参为null");
            return null;
        }
        Object ro = DataConverterHandler.jsonToObject(requestData, roClazz);
        if (ro instanceof ApiBase) {
            return http((ApiBase) ro);
        } else if (ro instanceof List) {
            return http((List<?>) ro);
        }
        return null;
    }

    /**
     * 设置默认项目请求头
     */
    private static void InitProjectHeader(ApiBase ro) {
        ApiBase.defaultHeader = Login.getDefaultLoginByProjectAndUser(ProjectEnum.getByCode(getRoBelongProject(ro)), "luandao");
    }

    /**
     * 获取ro所属的项目名
     *
     * @return 项目名
     */
    public static String getRoBelongProject(ApiBase ro) {
        String pkgName = ro.getClass().getPackage().toString();
        return StringHandler.splitByRegexAndGetLast(pkgName, "\\.");
    }

    /**
     * 判断系统处理是否正确，正确处理的标准是code=0
     *
     * @param requestResult 请求结果
     */
    private void isPass(String requestResult) {
        int code = JsonHandler.readIntegerByJsonPath("$.code", requestResult);
        if (code == 1) {
            String className = StringHandler.splitByRegexAndGetLast(this.getClass().getName(), "\\.");
            String method = GetStackInfoHandler.getCallerMethodNameByStackIndex(4);
            System.out.println("报错的类：" + className + "，对应的方法：" + method + "，对应的结果：" + requestResult);
        }
    }

    public static String getConfigValue(String rootPath, String cacheKey) {
        return JsonHandler.readStringByJsonPath(rootPath, Config.getCacheDataAsString(cacheKey));
    }
}
