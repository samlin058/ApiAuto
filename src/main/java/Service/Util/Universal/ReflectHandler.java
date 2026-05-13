package Service.Util.Universal;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectHandler {

    public static boolean isGetter(Method method) {
        return method.getName().startsWith("get") && method.getParameterCount() == 0
                && !void.class.equals(method.getReturnType())
                && !method.getName().endsWith("Class");
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {
            fields.addAll(getAllFields(superClass));
        }
        return fields;
    }


    /**
     * 使用反射调用指定类的指定方法
     *
     * @param className  完整类名（包括包名）
     * @param methodName 方法名
     * @param paramTypes 参数类型数组（按顺序）
     * @param params     参数值数组（按顺序）
     * @return 方法返回值
     * @throws Exception 如果发生反射异常
     */
    public static Object invokeMethod(String className, String methodName, Class<?>[] paramTypes, Object[] params) throws Exception {
        // 加载指定类
        Class<?> clazz = Class.forName(className);
        // 获取方法对象
        Method method = clazz.getDeclaredMethod(methodName, paramTypes);
        // 如果方法是私有的，设置为可访问
        method.setAccessible(true);
        // 创建类的实例（假设类有无参构造函数）
        Object instance = clazz.getDeclaredConstructor().newInstance();
        // 调用方法
        return method.invoke(instance, params);
    }
}
