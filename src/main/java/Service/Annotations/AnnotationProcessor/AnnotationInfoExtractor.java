package Service.Annotations.AnnotationProcessor;

import Service.Annotations.AnnotationLibrary.*;
import Service.Core.ApiBase;
import Service.Core.DataValidationException;
import Service.Util.Universal.DataConverterHandler;
import Service.Util.Universal.StringHandler;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnnotationInfoExtractor {
    /**
     * 根据传入的接口类和方法名提取对应的注解信息
     *
     * @param po         接口类
     * @param methodName 方法名
     */
    public static void po2Ro(ApiBase po, String methodName) {
        if (po == null) {
            throw new DataValidationException("数据不能为空");
        }
        Method[] methods = po.getClass().getDeclaredMethods();
        for (Method method : methods) {
            // 如果该方法存在 Mapping 注解且方法名匹配
            if (method.isAnnotationPresent(Mapping.class) && method.getName().equals(methodName)) {
                Mapping annotation = method.getAnnotation(Mapping.class);
                // 直接获取注解的值，无需额外的 null 判断
                String type = annotation.requestType();
                String url = getUrl(annotation, po.getClass());
                String contentType = annotation.contentTpye();
                po.setUrl(url);
                po.setRequestType(type);
                po.setContentType(contentType);
                // 找到后立即退出循环
                break;
            }
        }
    }

    public static void extractAnnotations(ApiBase ro) {
        if (ro != null) {
            HashMap<String, Object> paramMap = new HashMap<>();
            HashMap<String, Object> pathParamMap = new HashMap<>();
            Map<String, Object> bodyParamMap = new HashMap<>();
            processFieldAnnotations(ro, paramMap, bodyParamMap, pathParamMap);
            // 获取方法名
            String superName = StringHandler.cutAfterStringByNum(StringHandler.splitByRegexAndGetLast(ro.getClass().getSuperclass().getName(), "\\."), 0);
            String roName = StringHandler.cutAfterStringByNum(StringHandler.splitByRegexAndGetLast(ro.getClass().getName(), "\\."), 2);
            String methodName = roName.replaceFirst(superName, "");
            // 提取方法相关信息，构造http请求参数
            processMethodAnnotation(ro, methodName, pathParamMap);
            ro.setParam(paramMap);
            ro.setBody(bodyParamMap);
        }
    }


    public static ApiBase extractAnnotations(List<?> roList) {
        ApiBase firstRo = (ApiBase) roList.get(0);
        // 设置param
        HashMap<String, Object> paramMap = new HashMap<>();
        // 设置pathParam
        HashMap<String, Object> pathParamMap = new HashMap<>();
        // 设置body
        Map bodyparam = new HashMap<String, String>();

        processFieldAnnotations(firstRo, paramMap, null, pathParamMap);
        if (firstRo.getClass().isAnnotationPresent(JsonArray.class)) {
            String superName = StringHandler.cutAfterStringByNum(StringHandler.splitByRegexAndGetLast(firstRo.getClass().getSuperclass().getName(), "\\."), 0);
            String roName = StringHandler.cutAfterStringByNum(StringHandler.splitByRegexAndGetLast(firstRo.getClass().getName(), "\\."), 2);
            String methodName = StringHandler.lowercaseFirstLetter(roName.replaceFirst(superName, ""));
            // 获取api信息
            processMethodAnnotation(firstRo, methodName, pathParamMap);
            // 需要改,rolist只需ro对象属性进行toString
            bodyparam.put("JsonList", DataConverterHandler.listToJsonStr(roList));
            firstRo.setBody(bodyparam);
            firstRo.setParam(paramMap);
        }
        return firstRo;
    }

    private static void processFieldAnnotations(ApiBase ro, Map<String, Object> paramMap, Map<String, Object> bodyParamMap, Map<String, Object> pathParamMap) {
        Field[] fields = ro.getClass().getDeclaredFields();
        for (Field field : fields) {
            extractParam(field, ro, paramMap);
            extractPtahParam(field, ro, pathParamMap);
            extractHeader(field, ro);
            extractFileBody(field, ro);
        }
        if (bodyParamMap != null) {
            extractBodyParam(fields, ro, bodyParamMap);
        }
    }

    private static void extractPtahParam(Field field, ApiBase ro, Map<String, Object> pathParamMap) {
        if (field.isAnnotationPresent(PathParam.class)) {
            try {
                String fieldName = field.getName().substring(0, field.getName().length() - 5);
                //System.out.println("字段名"+fieldName);
                // 将ro中的请求字段_切回-
                String fieldStr = fieldName.replace("_", "-");
                Object fieldValue = field.get(ro);
                pathParamMap.put(dealFiledLower(fieldStr), fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void extractParam(Field field, ApiBase ro, Map<String, Object> param) {
        if (field.isAnnotationPresent(Param.class)) {
            try {
                String fieldName = field.getName();
                // 将ro中的请求字段_切回-
                String fieldStr = fieldName.replace("_", "-");
                Object fieldValue = field.get(ro);
                param.put(dealFiledLower(fieldStr), fieldValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void extractFileBody(Field field, ApiBase ro) {
        if (field.isAnnotationPresent(FileBody.class)) {
            try {
                String path = (String) field.get(ro);
                String key = field.getName();
                ro.setFileObject(key, path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void extractBodyParam(Field[] fields, ApiBase ro, Map<String, Object> bodyParam) {
        // 将对象中body注解的字段提取至bodyParam
        for (Field field : fields) {
            if (field.isAnnotationPresent(Body.class)) {
                try {
                    String key = field.getName();
                    String keyStr = key.replace("_", "-");
                    Object value = field.get(ro);
                    bodyParam.put(dealFiledLower(keyStr), value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 提取 @Header 注解的字段至 ApiBase.header
     * 保留大小写，仅将下划线转换为连字符，以便如 X_Login_Uid -> X-Login-Uid
     */
    private static void extractHeader(Field field, ApiBase ro) {
        if (field.isAnnotationPresent(Header.class)) {
            try {
                String rawName = field.getName();
                String headerKey = rawName.replace("_", "-");
                Object fieldValue = field.get(ro);
                if (!StringHandler.isEmpty(fieldValue)) {
                    if (ApiBase.header == null) {
                        ApiBase.header = new HashMap<>();
                    }
                    ApiBase.header.put(headerKey, String.valueOf(fieldValue));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void processMethodAnnotation(ApiBase ro,
                                                String methodName,
                                                HashMap<String, Object> pathParamMap) {
        if (ro == null || StringHandler.isEmpty(methodName)) {
            return;
        }

        Class<?> superclass = ro.getClass().getSuperclass();
        Method[] methods = superclass.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Mapping.class) && method.getName().equalsIgnoreCase(methodName)) {
                Mapping mapping = method.getAnnotation(Mapping.class);
                setApiAttributes(ro, mapping, superclass, pathParamMap);
                break; // 如果只匹配一个方法，可以在找到后直接退出循环
            }
        }
    }

    private static void setApiAttributes(ApiBase ro, Mapping mapping, Class<?> superclass, HashMap<String, Object> pathParamMap) {
        String type = mapping.requestType();
        String url = getUrl(mapping, superclass);
        String finalUrl = replaceUrlParameters2(url, pathParamMap);
        String contentTpye = mapping.contentTpye();
        ro.setUrl(finalUrl);
        ro.setRequestType(type);
        ro.setContentType(contentTpye);
    }

    /**
     * 替换url中的{field}字段值
     *
     * @param ro
     * @param url
     * @param pathParamMap
     * @return
     */
    private static String replaceUrlParameters(Object ro,
                                               String url,
                                               HashMap<String, Object> pathParamMap) {
        if (ro == null || StringHandler.isEmpty(pathParamMap)) {
            return url;
        }
        String regex = "\\{([^}]+)}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        StringBuffer replacedUrl = new StringBuffer();
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            // 从pathParamMap中移除已处理的参数
            if (pathParamMap.containsKey(placeholder)) {
                pathParamMap.remove(placeholder);
            }
            // 获取替换值
            String replacement = getReplacementValue(ro, placeholder);
            try {
                String encodedString = URLEncoder.encode(replacement, StandardCharsets.UTF_8.name());
                matcher.appendReplacement(replacedUrl, encodedString);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        matcher.appendTail(replacedUrl);
        url = replacedUrl.toString();
        return url;
    }

    /**
     * 替换url中的{field}字段值 - 直接从pathParamMap获取值
     *
     * @param url
     * @param pathParamMap
     * @return
     */
    private static String replaceUrlParameters2(String url,
                                               HashMap<String, Object> pathParamMap) {
        if (StringHandler.isEmpty(pathParamMap)) {
            return url;
        }
        String regex = "\\{([^}]+)}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        StringBuffer replacedUrl = new StringBuffer();
        while (matcher.find()) {
            String placeholder = matcher.group(1);
            String replacement;
            if (pathParamMap.containsKey(placeholder)) {
                Object value = pathParamMap.get(placeholder);
                replacement = value != null ? value.toString() : "{" + placeholder + "}";
            } else {
                replacement = "{" + placeholder + "}";
            }
            try {
                String encodedString = URLEncoder.encode(replacement, StandardCharsets.UTF_8.name());
                matcher.appendReplacement(replacedUrl, encodedString);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        matcher.appendTail(replacedUrl);
        url = replacedUrl.toString();
        return url;
    }

    private static String getUrl(Mapping annotation, Class<?> apiClazz) {
        String sub_path = annotation.sub_path();
        // 根据接口路径获取项目名
        String ProjectName = StringHandler.splitByRegexAndGetLast(apiClazz.getPackage().getName(), "\\.");
        String hostUrl = null;
        try {
            Field hostField = apiClazz.getSuperclass().getDeclaredField("host");
            Map<String, String> host = (Map<String, String>) hostField.get(apiClazz);
            hostUrl = host.get(ProjectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = hostUrl + sub_path;
        return url;
    }

    private static String getReplacementValue(Object obj, String propertyName) {
        try {
            String methodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
            Method method = obj.getClass().getMethod(methodName);
            Object value = method.invoke(obj);
            return value != null ? value.toString() : "{" + propertyName + "}";
        } catch (Exception e) {
            e.printStackTrace();
            return "{" + propertyName + "}";
        }
    }

    private static String dealFiledLower(String field) {
        if (field.equalsIgnoreCase("version") || field.equalsIgnoreCase("code")) {
            return StringHandler.lowercaseFirstLetter(field);
        }
        return field;
    }
}


