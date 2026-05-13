package Service.Util.Universal;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataConverterHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final ObjectMapper objectMapper2 = createCustomObjectMapper();

    static {
        // 设置一些通用的 ObjectMapper 配置，比如允许空对象，或禁用失败的特性
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private static ObjectMapper createCustomObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // 注册自定义过滤器（过滤父类属性）
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("ignoreParentProperties", new IgnoreParentPropertiesFilter());
        mapper.setFilterProvider(filterProvider);

        // 动态注册过滤器到所有类（通过MixIn）
        mapper.addMixIn(Object.class, IgnoreParentPropertiesMixIn.class);

        // 配置序列化修饰器（可选：明确声明过滤器作用域）
        mapper.setSerializerFactory(mapper.getSerializerFactory()
                .withSerializerModifier(new BeanSerializerModifier() {
                    @Override
                    public List<BeanPropertyWriter> changeProperties(
                            SerializationConfig config,
                            BeanDescription beanDesc,
                            List<BeanPropertyWriter> beanProperties) {
                        return beanProperties;
                    }
                }));
        return mapper;
    }

    // MixIn注解（动态绑定过滤器）
    @JsonFilter("ignoreParentProperties")
    private interface IgnoreParentPropertiesMixIn {
    }

    // 自定义过滤器实现
    private static class IgnoreParentPropertiesFilter extends SimpleBeanPropertyFilter {
        @Override
        public void serializeAsField(Object pojo, JsonGenerator gen,
                                     SerializerProvider prov, PropertyWriter writer) throws Exception {
            // 为空或为 Map/Collection 等容器类型时，不做父类过滤，直接序列化字段
            if (pojo == null || pojo instanceof Map || pojo instanceof Iterable) {
                super.serializeAsField(pojo, gen, prov, writer);
                return;
            }

            // 获取当前对象类型和属性声明类型（需判空）
            Class<?> objClass = pojo.getClass();
            AnnotatedMember member = writer.getMember();
            Class<?> declaringClass = (member != null) ? member.getDeclaringClass() : null;

            // 判断声明类是否为父类
            if (declaringClass != null && isSuperClass(objClass, declaringClass)) {
                return; // 跳过父类属性
            }
            super.serializeAsField(pojo, gen, prov, writer);
        }

        private boolean isSuperClass(Class<?> objClass, Class<?> targetClass) {
            Class<?> superClass = objClass.getSuperclass();
            while (superClass != null) {
                if (superClass == targetClass) {
                    return true;
                }
                superClass = superClass.getSuperclass();
            }
            return false;
        }
    }

    public static Object jsonToObject(String json, Class<?> clazz) {
        try {
            if (StringHandler.isJsonArrayString(json)) {
                // 使用泛型推断直接反序列化为 List<T>
                return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
            } else {
                // 反序列化为单个对象 Object
                return objectMapper.readValue(json, clazz);
            }
            //return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, Object> objectToMapByReflection(Object obj) {
        HashMap<String, Object> map = new HashMap<>();
        Method[] methods = obj.getClass().getMethods();
        for (Method method : methods) {
            if (ReflectHandler.isGetter(method)) {
                try {
                    String propertyName = getPropertyName(method.getName());
                    Object propertyValue = method.invoke(obj);
                    if (!StringHandler.isEmpty(propertyValue)) {
                        map.put(propertyName, propertyValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        return objectMapper.convertValue(obj, Map.class);
    }

    public static <T> Object[][] jsonTo2DArray(String jsonString, Class<T> clazz) {
        Object[][] dataArray = null;
        try {
            // 获取传入类的所有字段数
            Field[] fields = clazz.getDeclaredFields();
            int columns = fields.length;

            // 解析JSON为对象列表
            List<T> jsonArray = JSON.parseArray(jsonString, clazz);
            dataArray = new Object[jsonArray.size()][columns];

            // 填充二维数组
            for (int i = 0; i < jsonArray.size(); i++) {
                T obj = jsonArray.get(i);
                for (int j = 0; j < columns; j++) {
                    Field field = fields[j];
                    field.setAccessible(true); // 允许访问private字段
                    dataArray[i][j] = field.get(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataArray;
    }

    /**
     * Map 转对象
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static String mapToJson(Map<String, Object> map) {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    public static String listToJsonStr(List<?> list) {
        try {
            return objectMapper2.writeValueAsString(list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * JSON 字符串转 Map
     */
    public static Map<String, Object> jsonToMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转 JSON 字符串
     */
    public static String objectToJson(Object obj) {
        try {
            return objectMapper2.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将 List<String> 类型的参数类型转换为 Class<?>[] 类型
     *
     * @param paramTypeStrings 参数类型的字符串表示列表，例如 ["java.lang.String", "int"]
     * @return 对应的 Class<?>[] 数组
     * @throws ClassNotFoundException 如果无法加载类
     */
    public static Class<?>[] convertToClassArray(List<String> paramTypeStrings) throws ClassNotFoundException {
        // 创建 Class<?> 数组用于存储转换后的类型
        Class<?>[] paramTypes = new Class<?>[paramTypeStrings.size()];

        for (int i = 0; i < paramTypeStrings.size(); i++) {
            String type = paramTypeStrings.get(i);

            // 判断并转换基本类型
            switch (type) {
                case "string":
                    paramTypes[i] = String.class;
                    break;
                case "byte":
                    paramTypes[i] = byte.class;
                    break;
                case "short":
                    paramTypes[i] = short.class;
                    break;
                case "int":
                    paramTypes[i] = int.class;
                    break;
                case "long":
                    paramTypes[i] = long.class;
                    break;
                case "float":
                    paramTypes[i] = float.class;
                    break;
                case "double":
                    paramTypes[i] = double.class;
                    break;
                case "char":
                    paramTypes[i] = char.class;
                    break;
                case "boolean":
                    paramTypes[i] = boolean.class;
                    break;
                case "void":
                    paramTypes[i] = void.class;
                    break;
                default:
                    // 对于非基本类型，通过 Class.forName 加载
                    paramTypes[i] = Class.forName(type);
                    break;
            }
        }
        return paramTypes;
    }

    /**
     * 将逗号分隔的字符串转换为 Object[] 类型
     *
     * @param input 输入字符串，例如 "张三,3,23,12.12"
     * @return 转换后的 Object[] 数组
     */
    public static Object[] convertToObjectArray(String input) {
        // 按逗号分隔字符串
        String[] parts = input.split(",");

        // 创建列表存储解析后的值
        List<Object> values = new ArrayList<>();

        for (String part : parts) {
            part = part.trim(); // 去除前后空格
            if (part.matches("-?\\d+")) {
                // 如果是整数
                values.add(Integer.parseInt(part));
            } else if (part.matches("-?\\d+\\.\\d+")) {
                // 如果是浮点数
                values.add(Double.parseDouble(part));
            } else {
                // 默认认为是字符串
                values.add(part);
            }
        }
        // 转换为 Object[]
        return values.toArray();
    }

    // 获取属性名
    private static String getPropertyName(String methodName) {
        return methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
    }


}
