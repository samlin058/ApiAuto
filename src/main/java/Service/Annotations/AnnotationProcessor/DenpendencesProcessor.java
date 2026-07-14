package Service.Annotations.AnnotationProcessor;

import Service.Annotations.AnnotationLibrary.Dependence;
import Service.Annotations.AnnotationLibrary.Dependences;
import Service.Core.TestContext;
import Service.Util.Universal.PropertiesHandle;
import Service.Util.Universal.StringHandler;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;

public class DenpendencesProcessor {

    /**
     * 对类的依赖进行检查，然后执行对应的依赖逻辑
     *
     * @param roClazz
     */
    public static void roDendenpence(Class<?> roClazz) {
        Dependence dependenceAnnotation = roClazz.getAnnotation(Dependence.class);
        if (dependenceAnnotation != null) {
            apiDependence(dependenceAnnotation);
        }
        Dependences dependencesAnnotation = roClazz.getAnnotation(Dependences.class);
        if (dependencesAnnotation != null) {
            Arrays.stream(dependencesAnnotation.value()).forEach(dependence -> apiDependence(dependence));
        }
    }

    public static void apiDependence(Dependence dependenceAnnotation) {
        try {
            // 获取必要参数
            String classPath = getClassPath(dependenceAnnotation);
            String methodName = dependenceAnnotation.method();
            String param = dependenceAnnotation.param();
            String keys = dependenceAnnotation.keys();

            // 将类路径转换为类名
            String className = classPath.replace(File.separator, ".").replace(".java", "");

            // 加载目标类
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            String result = null;

            // 遍历目标类的所有方法
            for (Method method : clazz.getMethods()) {
                if (method.getName().equals(methodName)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    if (parameterTypes.length == 0) {
                        // 无参数方法
                        result = (String) method.invoke(instance);
                    } else if (parameterTypes.length == 1) {
                        // 单参数方法
                        if (!StringHandler.isEmpty(param)) {
                            // 解析param为JSON对象，并转换为目标参数类型
                            Object parsedParam = JSON.parseObject(param, parameterTypes[0]);
                            result = (String) method.invoke(instance, parsedParam);
                        } else {
                            throw new IllegalArgumentException("单参数方法必须提供非空的param值");
                        }
                    } else {
                        throw new IllegalArgumentException("方法参数数量超出预期，仅支持无参数或单参数方法");
                    }
                    break;
                }
            }
            // 缓存结果
            if (!StringHandler.isEmpty(keys) && !StringHandler.isEmpty(result)) {
                TestContext.setCacheData(keys, result);
            }
        } catch (Exception e) {
            System.err.println("处理异常：" + e.getMessage());
            e.printStackTrace();
        }
    }


    private static String getClassPath(Dependence dependenceAnnotation) {
        if (dependenceAnnotation == null || dependenceAnnotation.project() == null) {
            throw new IllegalArgumentException("dependenceAnnotation or project cannot be null");
        }
        // 获取项目code
        String project = dependenceAnnotation.project();
        // 从配置文件动态读取项目路径映射
        String projectPath = PropertiesHandle.configuration.getProperty("project.path." + project, "");
        // 拼接路径
        return Paths.get(projectPath, dependenceAnnotation.Class() + ".java").toString();
    }

}
