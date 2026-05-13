package Service.Util.Universal;

import Service.Config.Config;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * JSON处理工具类
 * 提供JSON字符串解析、转换、查询等常用功能
 *
 * @author ApiAuto
 * @version 2.0
 */
public class JsonHandler {

    // 使用单例模式创建ObjectMapper，提高性能
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    // 私有构造函数，防止实例化
    private JsonHandler() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * 使用JsonPath从JSON字符串中读取值
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return 查询结果，如果路径不存在或发生异常则返回null
     */
    public static Object readValueByJsonPath(String path, String jsonString) {
        if (StringHandler.isEmpty(path) || StringHandler.isEmpty(jsonString)) {
            return null;
        }
        try {
            return JsonPath.read(jsonString, path);
        } catch (PathNotFoundException e) {
            // 路径不存在，返回null而不是打印异常
            return null;
        } catch (Exception e) {
            // 记录异常但不中断程序执行
            System.err.println("Error reading JSON path: " + path + ", Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * 使用JsonPath从JSON字符串中读取String值
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return String值，如果路径不存在或类型不匹配则返回null
     */
    public static String readStringByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        return result != null ? result.toString() : null;
    }

    /**
     * 使用JsonPath从JSON字符串中读取Integer值
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return Integer值，如果路径不存在或类型不匹配则返回null
     */
    public static Integer readIntegerByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof Number) {
            return ((Number) result).intValue();
        } else if (result instanceof String) {
            try {
                return Integer.parseInt((String) result);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 使用JsonPath从JSON字符串中读取Long值
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return Long值，如果路径不存在或类型不匹配则返回null
     */
    public static Long readLongByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof Number) {
            return ((Number) result).longValue();
        } else if (result instanceof String) {
            try {
                return Long.parseLong((String) result);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 使用JsonPath从JSON字符串中读取Double值
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return Double值，如果路径不存在或类型不匹配则返回null
     */
    public static Double readDoubleByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        } else if (result instanceof String) {
            try {
                return Double.parseDouble((String) result);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 使用JsonPath从JSON字符串中读取Boolean值
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return Boolean值，如果路径不存在或类型不匹配则返回null
     */
    public static Boolean readBooleanByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof Boolean) {
            return (Boolean) result;
        } else if (result instanceof String) {
            String str = (String) result;
            if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
                return true;
            } else if ("false".equalsIgnoreCase(str) || "0".equals(str)) {
                return false;
            }
        } else if (result instanceof Number) {
            return ((Number) result).intValue() != 0;
        }
        return null;
    }

    /**
     * 使用JsonPath从JSON字符串中读取String数组
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return String列表，如果路径不存在或不是数组则返回空列表
     */
    public static List<String> readStringArrayByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<String> stringList = new ArrayList<>(list.size());
            for (Object item : list) {
                stringList.add(item != null ? item.toString() : null);
            }
            return stringList;
        }
        return Collections.emptyList();
    }

    /**
     * 使用JsonPath从JSON字符串中读取值并以字符串列表形式返回
     * - 如果结果为数组：转换为字符串列表返回
     * - 如果结果为单个值：返回只包含该值的列表
     * - 如果路径不存在或结果为空：返回空列表
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return 字符串列表
     */
    public static List<String> readStringListByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result == null) {
            return Collections.emptyList();
        }

        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<String> stringList = new ArrayList<>(list.size());
            for (Object item : list) {
                stringList.add(item != null ? item.toString() : null);
            }
            return stringList;
        }

        List<String> single = new ArrayList<>(1);
        single.add(result.toString());
        return single;
    }

    /**
     * 使用JsonPath从JSON字符串中读取String数组并返回字符串格式
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return 字符串格式的数组，如果路径不存在或不是数组则返回"[]"
     */
    public static String readStringArrayAsStringByJsonPath(String path, String jsonString) {
        List<String> stringList = readStringArrayByJsonPath(path, jsonString);
        return convertObjectToJsonString(stringList);
    }

    /**
     * 使用JsonPath从JSON字符串中读取数组并返回标准JSON格式字符串
     * 直接返回原始JSON数组格式，不进行类型转换
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return 标准JSON格式的数组字符串，如果路径不存在或不是数组则返回"[]"
     */
    public static String readArrayAsJsonStringByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof List) {
            return convertObjectToJsonString(result);
        }
        return "[]";
    }

    /**
     * 使用JsonPath从JSON字符串中读取Integer数组
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return Integer列表，如果路径不存在或不是数组则返回空列表
     */
    public static List<Integer> readIntegerArrayByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<Integer> integerList = new ArrayList<>(list.size());
            for (Object item : list) {
                if (item instanceof Number) {
                    integerList.add(((Number) item).intValue());
                } else if (item instanceof String) {
                    try {
                        integerList.add(Integer.parseInt((String) item));
                    } catch (NumberFormatException e) {
                        integerList.add(null);
                    }
                } else {
                    integerList.add(null);
                }
            }
            return integerList;
        }
        return Collections.emptyList();
    }

    /**
     * 使用JsonPath从JSON字符串中读取Integer数组并返回字符串格式
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return 字符串格式的数组，如果路径不存在或不是数组则返回"[]"
     */
    public static String readIntegerArrayAsStringByJsonPath(String path, String jsonString) {
        List<Integer> integerList = readIntegerArrayByJsonPath(path, jsonString);
        return convertObjectToJsonString(integerList);
    }

    /**
     * 使用JsonPath从JSON字符串中读取任意数组并返回字符串格式
     *
     * @param path       JsonPath表达式
     * @param jsonString JSON字符串
     * @return 字符串格式的数组，如果路径不存在或不是数组则返回"[]"
     */
    public static String readArrayAsStringByJsonPath(String path, String jsonString) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof List) {
            return convertObjectToJsonString(result);
        }
        return "[]";
    }

    /**
     * 使用JsonPath从JSON字符串中读取对象并转换为指定类型
     *
     * @param path        JsonPath表达式
     * @param jsonString  JSON字符串
     * @param targetClass 目标类型
     * @param <T>         泛型类型
     * @return 转换后的对象，如果路径不存在或转换失败则返回null
     */
    public static <T> T readObjectByJsonPath(String path, String jsonString, Class<T> targetClass) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result == null) {
            return null;
        }

        try {
            if (result instanceof String) {
                return OBJECT_MAPPER.readValue((String) result, targetClass);
            } else {
                return OBJECT_MAPPER.convertValue(result, targetClass);
            }
        } catch (Exception e) {
            System.err.println("Error converting object to " + targetClass.getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * 使用JsonPath从JSON字符串中读取对象数组并转换为指定类型
     *
     * @param path        JsonPath表达式
     * @param jsonString  JSON字符串
     * @param targetClass 目标类型
     * @param <T>         泛型类型
     * @return 转换后的对象列表，如果路径不存在或不是数组则返回空列表
     */
    public static <T> List<T> readObjectArrayByJsonPath(String path, String jsonString, Class<T> targetClass) {
        Object result = readValueByJsonPath(path, jsonString);
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<T> objectList = new ArrayList<>(list.size());
            for (Object item : list) {
                try {
                    T converted = OBJECT_MAPPER.convertValue(item, targetClass);
                    objectList.add(converted);
                } catch (Exception e) {
                    objectList.add(null);
                }
            }
            return objectList;
        }
        return Collections.emptyList();
    }


    /**
     * 从文件路径读取JSON并使用JsonPath查询
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return 查询结果
     * @throws RuntimeException 如果文件读取失败
     */
    public static Object readJsonFromFilePath(String filePath, String path) {
        if (StringHandler.isEmpty(filePath) || StringHandler.isEmpty(path)) {
            throw new IllegalArgumentException("File path and JSON path cannot be null or empty");
        }

        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File does not exist: " + filePath);
        }

        if (!file.canRead()) {
            throw new IllegalArgumentException("File cannot be read: " + filePath);
        }

        try {
            return JsonPath.read(file, path);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON from file: " + filePath, e);
        } catch (Exception e) {
            throw new RuntimeException("Error processing JSON from file: " + filePath + ", path: " + path, e);
        }
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回String值
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return String值，如果路径不存在或类型不匹配则返回null
     */
    public static String readStringFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        return result != null ? result.toString() : null;
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回Integer值
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return Integer值，如果路径不存在或类型不匹配则返回null
     */
    public static Integer readIntegerFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof Number) {
            return ((Number) result).intValue();
        } else if (result instanceof String) {
            try {
                return Integer.parseInt((String) result);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回Long值
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return Long值，如果路径不存在或类型不匹配则返回null
     */
    public static Long readLongFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof Number) {
            return ((Number) result).longValue();
        } else if (result instanceof String) {
            try {
                return Long.parseLong((String) result);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回Double值
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return Double值，如果路径不存在或类型不匹配则返回null
     */
    public static Double readDoubleFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof Number) {
            return ((Number) result).doubleValue();
        } else if (result instanceof String) {
            try {
                return Double.parseDouble((String) result);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回Boolean值
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return Boolean值，如果路径不存在或类型不匹配则返回null
     */
    public static Boolean readBooleanFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof Boolean) {
            return (Boolean) result;
        } else if (result instanceof String) {
            String str = (String) result;
            if ("true".equalsIgnoreCase(str) || "1".equals(str)) {
                return true;
            } else if ("false".equalsIgnoreCase(str) || "0".equals(str)) {
                return false;
            }
        } else if (result instanceof Number) {
            return ((Number) result).intValue() != 0;
        }
        return null;
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回String数组
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return String列表，如果路径不存在或不是数组则返回空列表
     */
    public static List<String> readStringArrayFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<String> stringList = new ArrayList<>(list.size());
            for (Object item : list) {
                stringList.add(item != null ? item.toString() : null);
            }
            return stringList;
        }
        return Collections.emptyList();
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回Integer数组
     *
     * @param filePath 文件路径
     * @param path     JsonPath表达式
     * @return Integer列表，如果路径不存在或不是数组则返回空列表
     */
    public static List<Integer> readIntegerArrayFromFilePath(String filePath, String path) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<Integer> integerList = new ArrayList<>(list.size());
            for (Object item : list) {
                if (item instanceof Number) {
                    integerList.add(((Number) item).intValue());
                } else if (item instanceof String) {
                    try {
                        integerList.add(Integer.parseInt((String) item));
                    } catch (NumberFormatException e) {
                        integerList.add(null);
                    }
                } else {
                    integerList.add(null);
                }
            }
            return integerList;
        }
        return Collections.emptyList();
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回对象并转换为指定类型
     *
     * @param filePath    文件路径
     * @param path        JsonPath表达式
     * @param targetClass 目标类型
     * @param <T>         泛型类型
     * @return 转换后的对象，如果路径不存在或转换失败则返回null
     */
    public static <T> T readObjectFromFilePath(String filePath, String path, Class<T> targetClass) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result == null) {
            return null;
        }

        try {
            if (result instanceof String) {
                return OBJECT_MAPPER.readValue((String) result, targetClass);
            } else {
                return OBJECT_MAPPER.convertValue(result, targetClass);
            }
        } catch (Exception e) {
            System.err.println("Error converting object to " + targetClass.getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    /**
     * 从文件路径读取JSON并使用JsonPath查询，返回对象数组并转换为指定类型
     *
     * @param filePath    文件路径
     * @param path        JsonPath表达式
     * @param targetClass 目标类型
     * @param <T>         泛型类型
     * @return 转换后的对象列表，如果路径不存在或不是数组则返回空列表
     */
    public static <T> List<T> readObjectArrayFromFilePath(String filePath, String path, Class<T> targetClass) {
        Object result = readJsonFromFilePath(filePath, path);
        if (result instanceof List) {
            List<?> list = (List<?>) result;
            List<T> objectList = new ArrayList<>(list.size());
            for (Object item : list) {
                try {
                    T converted = OBJECT_MAPPER.convertValue(item, targetClass);
                    objectList.add(converted);
                } catch (Exception e) {
                    objectList.add(null);
                }
            }
            return objectList;
        }
        return Collections.emptyList();
    }

    /**
     * 从JSON数组字符串中根据指定key的值找到对应对象，并返回该对象的指定字段值
     *
     * @param jsonArrayStr JSON数组字符串
     * @param searchKey    用于搜索的字段名
     * @param searchValue  搜索字段的值
     * @param returnKey    要返回的字段名
     * @return 匹配对象的指定字段值，如果没有找到匹配项则返回null
     */
    public static String findValueByKeyInArray(String jsonArrayStr, String searchKey, String searchValue, String returnKey) {
        if (StringHandler.isEmpty(jsonArrayStr) || StringHandler.isEmpty(searchKey) ||
                StringHandler.isEmpty(searchValue) || StringHandler.isEmpty(returnKey)) {
            return null;
        }

        try {
            // 解析JSON数组
            JsonNode arrayNode = OBJECT_MAPPER.readTree(jsonArrayStr);

            if (!arrayNode.isArray()) {
                System.err.println("Input is not a JSON array");
                return null;
            }

            // 遍历数组中的每个对象
            for (JsonNode node : arrayNode) {
                if (node.isObject()) {
                    // 检查搜索字段是否存在且值匹配
                    JsonNode searchNode = node.get(searchKey);
                    if (searchNode != null && searchValue.equals(searchNode.asText())) {
                        // 找到匹配项，返回指定字段的值
                        JsonNode returnNode = node.get(returnKey);
                        if (returnNode != null) {
                            return returnNode.asText();
                        }
                    }
                }
            }

            return null;
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON array: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error finding value in array: " + e.getMessage());
            return null;
        }
    }

    /**
     * 从JSON数组字符串中根据指定key的值找到对应对象，并返回该对象的指定字段值（支持多种返回类型）
     *
     * @param jsonArrayStr JSON数组字符串
     * @param searchKey    用于搜索的字段名
     * @param searchValue  搜索字段的值
     * @param returnKey    要返回的字段名
     * @param returnType   返回值的类型
     * @param <T>          泛型类型
     * @return 匹配对象的指定字段值，如果没有找到匹配项则返回null
     */
    public static <T> T findValueByKeyInArray(String jsonArrayStr, String searchKey, String searchValue, String returnKey, Class<T> returnType) {
        if (StringHandler.isEmpty(jsonArrayStr) || StringHandler.isEmpty(searchKey) ||
                StringHandler.isEmpty(searchValue) || StringHandler.isEmpty(returnKey)) {
            return null;
        }

        try {
            // 解析JSON数组
            JsonNode arrayNode = OBJECT_MAPPER.readTree(jsonArrayStr);

            if (!arrayNode.isArray()) {
                System.err.println("Input is not a JSON array");
                return null;
            }

            // 遍历数组中的每个对象
            for (JsonNode node : arrayNode) {
                if (node.isObject()) {
                    // 检查搜索字段是否存在且值匹配
                    JsonNode searchNode = node.get(searchKey);
                    if (searchNode != null && searchValue.equals(searchNode.asText())) {
                        // 找到匹配项，返回指定字段的值
                        JsonNode returnNode = node.get(returnKey);
                        if (returnNode != null) {
                            try {
                                return OBJECT_MAPPER.convertValue(returnNode, returnType);
                            } catch (Exception e) {
                                System.err.println("Error converting return value to " + returnType.getSimpleName() + ": " + e.getMessage());
                                return null;
                            }
                        }
                    }
                }
            }

            return null;
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON array: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error finding value in array: " + e.getMessage());
            return null;
        }
    }

    /**
     * 从JSON数组字符串中根据指定key的值找到对应对象，并返回整个匹配的对象
     *
     * @param jsonArrayStr JSON数组字符串
     * @param searchKey    用于搜索的字段名
     * @param searchValue  搜索字段的值
     * @param targetClass  目标对象类型
     * @param <T>          泛型类型
     * @return 匹配的对象，如果没有找到匹配项则返回null
     */
    public static <T> T findObjectByKeyInArray(String jsonArrayStr, String searchKey, String searchValue, Class<T> targetClass) {
        if (StringHandler.isEmpty(jsonArrayStr) || StringHandler.isEmpty(searchKey) ||
                StringHandler.isEmpty(searchValue)) {
            return null;
        }

        try {
            // 解析JSON数组
            JsonNode arrayNode = OBJECT_MAPPER.readTree(jsonArrayStr);

            if (!arrayNode.isArray()) {
                System.err.println("Input is not a JSON array");
                return null;
            }

            // 遍历数组中的每个对象
            for (JsonNode node : arrayNode) {
                if (node.isObject()) {
                    // 检查搜索字段是否存在且值匹配
                    JsonNode searchNode = node.get(searchKey);
                    if (searchNode != null && searchValue.equals(searchNode.asText())) {
                        // 找到匹配项，返回整个对象
                        try {
                            return OBJECT_MAPPER.convertValue(node, targetClass);
                        } catch (Exception e) {
                            System.err.println("Error converting object to " + targetClass.getSimpleName() + ": " + e.getMessage());
                            return null;
                        }
                    }
                }
            }

            return null;
        } catch (JsonProcessingException e) {
            System.err.println("Error parsing JSON array: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("Error finding object in array: " + e.getMessage());
            return null;
        }
    }

    /**
     * 替换JSON字符串中的键值对
     * 将第二个JSON中的键值对替换第一个JSON中相同键的值
     *
     * @param sourceJson      源JSON字符串
     * @param replacementJson 替换用的JSON字符串
     * @return 更新后的JSON字符串，如果发生错误则返回null
     */
    public static String replaceJsonKeyValues(String sourceJson, String replacementJson) {
        if (StringHandler.isEmpty(sourceJson) || StringHandler.isEmpty(replacementJson)) {
            return sourceJson;
        }

        try {
            Map<String, Object> sourceMap = OBJECT_MAPPER.readValue(sourceJson, new TypeReference<Map<String, Object>>() {
            });
            Map<String, Object> replacementMap = OBJECT_MAPPER.readValue(replacementJson, new TypeReference<Map<String, Object>>() {
            });

            // 替换值
            sourceMap.putAll(replacementMap);

            return OBJECT_MAPPER.writeValueAsString(sourceMap);
        } catch (JsonProcessingException e) {
            System.err.println("Error replacing JSON key values: " + e.getMessage());
            return null;
        }
    }

    /**
     * 在HTTP响应结果的data字段中搜索指定字段值
     *
     * @param jsonStr      HTTP响应JSON字符串
     * @param key          要搜索的字段名
     * @param compareField 比较字段名
     * @param compareValue 比较值
     * @return 如果找到匹配值则返回true，否则返回false
     */
    public static boolean existsInHttpResult(String jsonStr, String key, String compareField, String compareValue) {
        if (StringHandler.isEmpty(jsonStr) || StringHandler.isEmpty(key) ||
                StringHandler.isEmpty(compareField) || StringHandler.isEmpty(compareValue)) {
            return false;
        }

        try {
            Object dataObj = readValueByJsonPath("$.data", jsonStr);
            if (dataObj == null) {
                return false;
            }

            String dataJson = dataObj.toString();
            JsonNode jsonArray = OBJECT_MAPPER.readTree(dataJson);

            List<String> keyValues = new ArrayList<>();

            // 收集所有匹配的值
            for (JsonNode node : jsonArray) {
                JsonNode targetNode = node.get(key);
                if (targetNode != null && targetNode.isArray()) {
                    for (JsonNode itemNode : targetNode) {
                        JsonNode valueNode = itemNode.get(compareField);
                        if (valueNode != null) {
                            keyValues.add(valueNode.asText());
                        }
                    }
                }
            }

            return keyValues.contains(compareValue);
        } catch (Exception e) {
            System.err.println("Error checking existence in HTTP result: " + e.getMessage());
            return false;
        }
    }

    /**
     * 根据指定字段值筛选JSON数组
     *
     * @param jsonArray JSON数组字符串
     * @param field     字段名
     * @param value     字段值
     * @return 匹配的JsonNode列表
     */
    public static List<JsonNode> filterJsonArray(String jsonArray, String field, String value) {
        if (StringHandler.isEmpty(jsonArray) || StringHandler.isEmpty(field)) {
            return Collections.emptyList();
        }

        try {
            JsonNode arrayNode = OBJECT_MAPPER.readTree(jsonArray);
            List<JsonNode> result = new ArrayList<>();

            for (JsonNode node : arrayNode) {
                if (node.has(field) && value.equals(node.get(field).asText())) {
                    result.add(node);
                }
            }

            return result;
        } catch (JsonProcessingException e) {
            System.err.println("Error filtering JSON array: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * 根据条件过滤JSON数组，返回第一个匹配项
     *
     * @param jsonArray JSON数组字符串
     * @param field     字段名
     * @param value     字段值
     * @return 第一个匹配的JSON字符串，如果没有匹配项则返回null
     */
    public static String filterJsonArrayReturnFirst(String jsonArray, String field, String value) {
        List<JsonNode> result = filterJsonArray(jsonArray, field, value);
        return result.isEmpty() ? null : result.get(0).toString();
    }

    /**
     * 根据字段值包含关键字过滤JSON数组
     *
     * @param jsonArrayStr JSON数组字符串
     * @param fieldName    字段名
     * @param keyword      关键字
     * @return 包含关键字的对象列表
     */
    public static List<Object> filterByFieldValue(String jsonArrayStr, String fieldName, String keyword) {
        if (StringHandler.isEmpty(jsonArrayStr) || StringHandler.isEmpty(fieldName) || StringHandler.isEmpty(keyword)) {
            return Collections.emptyList();
        }

        try {
            JsonNode arrayNode = OBJECT_MAPPER.readTree(jsonArrayStr);
            List<Object> result = new ArrayList<>();

            for (JsonNode node : arrayNode) {
                if (node.has(fieldName)) {
                    String fieldValue = node.get(fieldName).asText();
                    if (fieldValue != null && fieldValue.contains(keyword)) {
                        result.add(node);
                    }
                }
            }

            return result;
        } catch (JsonProcessingException e) {
            System.err.println("Error filtering by field value: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * 从缓存中获取数据并使用JsonPath解析
     *
     * @param path JsonPath表达式
     * @param key  缓存键
     * @return 解析后的值
     */
    public static String getCacheDataByPathAndKey(String path, String key) {
        String jsonData = Config.getCacheDataAsString(key);
        Object result = readValueByJsonPath(path, jsonData);
        return result != null ? result.toString() : null;
    }

    /**
     * 将JSON字符串转换为JSON数组
     *
     * @param jsonStr JSON字符串
     * @return JSON数组
     */
    public static List<?> convertJsonStrToJsonArray(String jsonStr) {
        if (StringHandler.isEmpty(jsonStr)) {
            return Collections.emptyList();
        }

        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(jsonStr);
            ArrayNode arrayNode = OBJECT_MAPPER.createArrayNode();
            arrayNode.add(jsonNode);
            return OBJECT_MAPPER.convertValue(arrayNode, List.class);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting JSON string to array: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * 将Map转换为List
     *
     * @param map 要转换的Map
     * @return 包含Map的List
     */
    public static List<?> convertMapToList(Map<?, ?> map) {
        if (map == null) {
            return Collections.emptyList();
        }

        try {
            List<Map<?, ?>> list = new ArrayList<>();
            list.add(map);
            return OBJECT_MAPPER.convertValue(list, List.class);
        } catch (Exception e) {
            System.err.println("Error converting map to list: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * 将JSON字符串转换为指定类型的对象
     *
     * @param targetClass 目标类型
     * @param json        JSON字符串
     * @param <T>         泛型类型
     * @return 转换后的对象
     * @throws IllegalArgumentException 如果参数无效或转换失败
     */
    public static <T> T convertToObject(Class<T> targetClass, String json) {
        if (targetClass == null || StringHandler.isEmpty(json)) {
            throw new IllegalArgumentException("Target class and JSON string cannot be null or empty");
        }

        try {
            return OBJECT_MAPPER.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Failed to parse JSON to " + targetClass.getSimpleName(), e);
        }
    }

    /**
     * 将对象转换为JSON字符串
     *
     * @param object 要转换的对象
     * @return JSON字符串
     */
    public static String convertObjectToJsonString(Object object) {
        if (object == null) {
            return null;
        }

        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.err.println("Error converting object to JSON string: " + e.getMessage());
            return null;
        }
    }

    /**
     * 从JSON数组中随机选择一个元素
     *
     * @param jsonStr JSON字符串
     * @param path    JsonPath表达式，指向数组
     * @return 随机选择的元素字符串
     * @throws IllegalArgumentException 如果路径不指向数组或数组为空
     */
    public static String getRandomElementFromJsonArrayByPath(String jsonStr, String path) {
        Object arrayObj = readValueByJsonPath(path, jsonStr);
        if (!(arrayObj instanceof List)) {
            throw new IllegalArgumentException("The value extracted by path is not a JSON array");
        }

        List<?> array = (List<?>) arrayObj;
        if (array.isEmpty()) {
            throw new IllegalArgumentException("The extracted array is empty");
        }

        int randomIndex = (int) (Math.random() * array.size());
        Object element = array.get(randomIndex);
        return element != null ? element.toString() : null;
    }

    /**
     * 处理标准接口响应中的data字段
     *
     * @param response 响应字符串
     * @param jsonPath 获取data中值的JsonPath
     * @return 处理后的对象
     */
    public static Object processOpenApiResponseData(String response, String jsonPath) {
        if (StringHandler.isEmpty(response)) {
            return "";
        }

        Object dataObj = readValueByJsonPath("$.data", response);
        if (dataObj == null) {
            return "";
        }

        if (StringHandler.isEmpty(jsonPath)) {
            return dataObj;
        } else {
            return readValueByJsonPath(jsonPath, dataObj.toString());
        }
    }

    /**
     * 验证JSON字符串是否有效
     *
     * @param json JSON字符串
     * @return 如果JSON有效则返回true
     */
    public static boolean isValidJson(String json) {
        if (StringHandler.isEmpty(json)) {
            return false;
        }

        try {
            OBJECT_MAPPER.readTree(json);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    /**
     * 获取JSON字符串的格式化版本
     *
     * @param json 原始JSON字符串
     * @return 格式化后的JSON字符串
     */
    public static String formatJson(String json) {
        if (!isValidJson(json)) {
            return json;
        }

        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(json);
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (JsonProcessingException e) {
            System.err.println("Error formatting JSON: " + e.getMessage());
            return json;
        }
    }

    // 保留原有方法名以保持向后兼容性
    @Deprecated
    public static String JsonKeyValueReplacer(String json1, String json2) {
        return replaceJsonKeyValues(json1, json2);
    }

    @Deprecated
    public static String filterJsonArrayRetrunOne(String jsonArray, String field, String value) {
        return filterJsonArrayReturnFirst(jsonArray, field, value);
    }

    @Deprecated
    public static List<?> changeJsonStrToJsonAraay(String jsonStr) {
        return convertJsonStrToJsonArray(jsonStr);
    }

    @Deprecated
    public static List<?> changeMapToList(Map map) {
        return convertMapToList(map);
    }

    @Deprecated
    public static <T> T change2Object(Object templateClass, String json) {
        if (templateClass instanceof Class) {
            return convertToObject((Class<T>) templateClass, json);
        } else {
            return convertToObject((Class<T>) templateClass.getClass(), json);
        }
    }

    @Deprecated
    public static String objectChange2JsonStr(Object object) {
        return convertObjectToJsonString(object);
    }

    @Deprecated
    public static Object dealOpenApiResponseData(String response, String jsonPath) {
        return processOpenApiResponseData(response, jsonPath);
    }


}






