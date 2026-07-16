package Service.Core;

import Service.Listen.ReportContext;
import Service.Pojo.Method;
import Service.Util.Universal.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 测试用例基类，提供通用断言、参数化测试数据加载及接口测试执行能力。
 * <p>
 * 所有 TestNG 测试类应继承此类，可使用 {@link #assertEq(Object, Object)} 等断言方法，
 * 以及 {@link #getTestData(TestScenarioEnum, String)} 从 JSON 文件加载参数化测试数据。
 * </p>
 */
public class BaseTestCase {

    //  private static final Logger logger = Logger.getLogger(BaseTestCase.class.getName());

    /**
     * 通用断言方法
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     * @param comparator    比较逻辑
     * @param errorMessage  错误提示信息
     */
    private static void assertWithComparator(Object actualValue, Object expectedValue, BiFunction<Object, Object, Boolean> comparator, String errorMessage) {
        boolean result = comparator.apply(actualValue, expectedValue);
        if (!result) {
            System.out.println("实际值：[" + actualValue + "]，期望值：[" + expectedValue + "]");
            String detailMessage = errorMessage + " | 实际值：[" + actualValue + "]，期望值：[" + expectedValue + "]";
            ReportContext.setAssertionDetail(actualValue, expectedValue, detailMessage);
            Assert.fail(detailMessage);
        }
    }

    /**
     * 解析字符串并进行断言
     *
     * @param result       字符串输入
     * @param comparator   比较逻辑
     * @param errorMessage 错误提示信息
     */
    private static void assertWithParsedString(String result, BiFunction<Object, Object, Boolean> comparator, String errorMessage) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        String[] parts = result.split("\\|");
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        assertWithComparator(parts[0], parts[1], comparator, errorMessage);
    }

    public static void assertEq(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, CompareHandler::compareObjectsEq, "Values are not equal");
    }

    public static void assertEq(String result) {
        assertWithParsedString(result, CompareHandler::compareObjectsEq, "Values are not equal");
    }

    public static void assertGt(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, CompareHandler::compareObjectsGt, "Actual value is not greater");
    }

    public static void assertGt(String result) {
        assertWithParsedString(result, CompareHandler::compareObjectsGt, "Actual value is not greater");
    }

    public static void assertLt(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, CompareHandler::compareObjectsLt, "Actual value is not less");
    }

    public static void assertLt(String result) {
        assertWithParsedString(result, CompareHandler::compareObjectsLt, "Actual value is not less");
    }

    public static void assertNeq(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, (a, b) -> !CompareHandler.compareObjectsEq(a, b), "Values are equal");
    }

    public static void assertNeq(String result) {
        assertWithParsedString(result, (a, b) -> !CompareHandler.compareObjectsEq(a, b), "Values are equal");
    }

    public static void assertGe(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, CompareHandler::compareObjectsGe, "Actual value is not greater or equal");
    }

    public static void assertGe(String result) {
        assertWithParsedString(result, CompareHandler::compareObjectsGe, "Actual value is not greater or equal");
    }

    public static void assertLe(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, CompareHandler::compareObjectsLe, "Actual value is not less or equal");
    }

    public static void assertLe(String result) {
        assertWithParsedString(result, CompareHandler::compareObjectsLe, "Actual value is not less or equal");
    }

    public static void assertContains(Object actualValue, Object expectedValue) {
        assertWithComparator(actualValue, expectedValue, CompareHandler::compareObjectsCon, "Actual value does not contain expected");
    }

    public static void assertContains(String result) {
        assertWithParsedString(result, CompareHandler::compareObjectsCon, "Actual value does not contain expected");
    }

    /**
     * 获取参数化测试数据
     *
     * @param scenario 场景
     * @param fileName 文件名
     * @return Object[][]
     */
    public static Object[][] getTestData(TestScenarioEnum scenario, String fileName) {
        // 获取调用类路径
        String beCallClassPath = GetStackInfoHandler.getCallerClassPath();
        String projectName = StringHandler.getNthValue(beCallClassPath, "\\.", 3);
        String scenarioPath = scenario.getCode();
        String path = "src/test/java/Application/TestCase/" + projectName + "/TestData/" + scenarioPath + "/" + fileName + ".json";
        String jsonString = FileHandler.loadJsonFile(path);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> dataList = null;
        try {
            dataList = objectMapper.readValue(jsonString, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String testDataAsString = null;
        String beforeReadyAsString = null;
        String afterDo = null;
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> map = dataList.get(i);

            try {
                testDataAsString = objectMapper.writeValueAsString(map.get("testData")); // Serialize testData to JSON string
                beforeReadyAsString = objectMapper.writeValueAsString(map.get("beforeReady")); // Serialize beforeReady to JSON string
                afterDo = objectMapper.writeValueAsString(map.get("afterDo")); // Serialize afterDo to JSON string
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            dataArray[i] = new Object[]{
                    beforeReadyAsString,
                    testDataAsString,
                    map.get("actualValue").toString(),
                    map.get("expectedResult").toString(),
                    afterDo
            };
        }
        return dataArray;
    }

    public static String beforeDo(String doInfo, String testData) {
        String replaceValue = null;
        try {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 JSON 转换为 Java 对象
            Method method = objectMapper.readValue(doInfo, Method.class);
            Class<?>[] paramTypes = DataConverterHandler.convertToClassArray(method.getParamTypes());
            Object[] param = DataConverterHandler.convertToObjectArray(method.getParam());
            Object result = ReflectHandler.invokeMethod(method.getClazz(), method.getMethod(), paramTypes, param);
            String json = DataConverterHandler.objectToJson(result);
            replaceValue = JsonHandler.JsonKeyValueReplacer(testData, json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return replaceValue;
    }

    public String executeTest(String beforeReady, String testData, String targetValueRule, String expectedValueRule, Class<?> roClass, ApiBase ao) {
        ApiBase api;
        try {
            api = ao.getClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String requestResult = api.convert2RoAndRequest(beforeDo(beforeReady, testData), roClass);
        String targetValue = getRuleValue(requestResult, targetValueRule);
        String expectedValue = getRuleValue(requestResult, expectedValueRule);
        return targetValue + "|" + expectedValue;
    }

    public void afterDo(String data) {

    }

    public static String getRuleValue(String source, String targetValueRule) {
        System.out.println("原值：" + source);
        System.out.println("取值规则：" + targetValueRule);
        return "";
    }

}
