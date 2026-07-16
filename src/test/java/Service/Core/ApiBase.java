package Service.Core;

import Service.Annotations.AnnotationProcessor.AnnotationInfoExtractor;
import Service.Listen.ReportContext;
import Service.Util.Universal.*;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API 接口控制器基类，封装 HTTP 请求的通用逻辑。
 * <p>
 * 各业务域的接口控制器（如 {@code Data.ApisLayer.*}）继承此类，通过注解驱动的方式
 * 自动解析请求路径、请求头、参数及请求体，并发起 HTTP 调用。
 * </p>
 */
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
        long start = System.currentTimeMillis();
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
        recordHttpCall(this, result, System.currentTimeMillis() - start);
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
        long start = System.currentTimeMillis();
        String result = HttpHandler.http(ro);
        recordHttpCall(ro, result, System.currentTimeMillis() - start);
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
        long start = System.currentTimeMillis();
        String result = HttpHandler.http(ro);
        recordHttpCall(ro, result, System.currentTimeMillis() - start);
        // isPass(result);
        if (Is_Print_Logger.equalsIgnoreCase("true")) {
            if (!GetStackInfoHandler.isCalledFromDataFactory()) {
                StringHandler.jsonFormatOutput(result);
            }
        }
        return result;
    }

    private static void recordHttpCall(ApiBase ro, String response, long costMs) {
        if (ro == null || GetStackInfoHandler.isCalledFromDataFactory()) {
            return;
        }
        String requestBody = "";
        try {
            if (ro.getBody() != null && !ro.getBody().isEmpty()) {
                requestBody = JSON.toJSONString(ro.getBody());
            } else if (ro.getParam() != null && !ro.getParam().isEmpty()) {
                requestBody = JSON.toJSONString(ro.getParam());
            }
        } catch (Exception e) {
            requestBody = String.valueOf(ro.getBody() != null ? ro.getBody() : ro.getParam());
        }
        ReportContext.addHttpCall(
                ro.getRequestType() == null ? "" : ro.getRequestType(),
                ro.getUrl() == null ? "" : ro.getUrl(),
                requestBody,
                response,
                costMs
        );
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
        return JsonHandler.readStringByJsonPath(rootPath, TestContext.getCacheDataAsString(cacheKey));
    }
}
