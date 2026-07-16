package Service.Annotations.AnnotationProcessor;

import Service.Annotations.AnnotationLibrary.Required;
import Service.Util.Universal.StringHandler;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * required装饰器处理（请求参数如果为空抛异常）
 */
public class RequiredProcessor {

    public static void RequiredCheck(Object obj) {
        if (obj != null) {
            Field[] fields = obj.getClass().getDeclaredFields();
            HashMap<String, String> filedMap = new HashMap<>();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Required.class)) {
                    field.setAccessible(true);
                    try {
                        filedMap.put(field.getName(), (String) field.get(obj));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            SoftAssert softAssert = new SoftAssert();
            // 创建一个 List，用于存放空值字段名
            List<String> keysWithEmptyValues = new ArrayList<>();
            // 遍历 HashMap 的 entrySet
            for (Map.Entry<String, String> entry : filedMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                // 判断值是否为空
                softAssert.assertNotNull(value);
                if (StringHandler.isEmpty(value)) {
                    keysWithEmptyValues.add(key);
                }
            }
            // 打印包含空值字段名的列表
            if (!keysWithEmptyValues.isEmpty()) {
                System.out.println("以下字段值为空: " + keysWithEmptyValues);
            }
            softAssert.assertAll();
        }
    }

}
