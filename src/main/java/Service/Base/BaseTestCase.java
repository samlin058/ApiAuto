package Service.Base;

import Service.Enum.ProjectEnum;
import Service.Enum.TestScenarioEnum;
import Service.Pojo.Method;
import Service.Util.Universal.CompareHandler;
import Service.Util.Universal.DataConverterHandler;
import Service.Util.Universal.FileHandler;
import Service.Util.Universal.ReflectHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class BaseTestCase {

    /**
     * equals
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertEq(Object actualValue, Object expectedValue) {
        boolean flag = CompareHandler.compareObjectsEq(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertEq(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = CompareHandler.compareObjectsEq(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }

    /**
     * greater than
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertGt(Object actualValue, Object expectedValue) {
        boolean flag = CompareHandler.compareObjectsGt(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertGt(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = CompareHandler.compareObjectsGt(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }

    /**
     * less than
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertLt(Object actualValue, Object expectedValue) {
        boolean flag = CompareHandler.compareObjectsLt(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertLt(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = CompareHandler.compareObjectsLt(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }

    /**
     * not equals
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertNeq(Object actualValue, Object expectedValue) {
        boolean flag = !CompareHandler.compareObjectsEq(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertNeq(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = !CompareHandler.compareObjectsEq(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }

    /**
     * greater than or equals
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertGe(Object actualValue, Object expectedValue) {
        boolean flag = CompareHandler.compareObjectsGe(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertGe(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = CompareHandler.compareObjectsGe(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }

    /**
     * less than or equals
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertLe(Object actualValue, Object expectedValue) {
        boolean flag = CompareHandler.compareObjectsLe(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertLe(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = CompareHandler.compareObjectsLe(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }

    /**
     * 实际值是否包含期望值
     *
     * @param actualValue   实际值
     * @param expectedValue 期望值
     */
    public static void assertContains(Object actualValue, Object expectedValue) {
        boolean flag = CompareHandler.compareObjectsCon(actualValue, expectedValue);
        extracted(flag, actualValue, expectedValue);
        Assert.assertTrue(flag);
    }

    public static void assertContains(String result) {
        if (result == null || !result.contains("|")) {
            throw new IllegalArgumentException("输入字符串格式错误，应包含'|'并且不为空。");
        }
        // 按照'|'切分字符串
        String[] parts = result.split("\\|");
        // 验证是否切分为两个部分
        if (parts.length != 2) {
            throw new IllegalArgumentException("输入字符串应只包含一个'|'以分隔两个部分。");
        }
        boolean flag = CompareHandler.compareObjectsCon(parts[0], parts[1]);
        extracted(flag, parts[0], parts[1]);
        Assert.assertTrue(flag);
    }



    private static void extracted(boolean flag, Object actualValue, Object expectedValue) {
        if (flag == false) {
            System.out.println("actualValue:  " + actualValue);
            System.out.println("expectedValue:  " + expectedValue);
        }
    }

    /**
     * 获取参数化测试数据
     *
     * @param scenario 场景
     * @param projectEnum  项目
     * @param fileName 文件名
     * @return Object[][]
     */
    public static Object[][] getTestData(ProjectEnum projectEnum, TestScenarioEnum scenario, String fileName) {
        String projectName = projectEnum.name();
        String scenarioPath = scenario.getCode();
        String path = "src/main/java/Application/TestCase/" + projectName + "/TestData/" + scenarioPath + "/" + fileName + ".json";
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
        Object[][] dataArray = new Object[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> map = dataList.get(i);

            try {
                testDataAsString = objectMapper.writeValueAsString(map.get("testData")); // Serialize testData to JSON string
                beforeReadyAsString = objectMapper.writeValueAsString(map.get("beforeReady")); // Serialize testData to JSON string
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            dataArray[i] = new Object[]{
                    beforeReadyAsString,
                    testDataAsString,
                    map.get("actualValue").toString(),
                    map.get("expectedResult").toString()
            };
        }
        return dataArray;
    }

    public static String beforeDo(String doInfo, String testData) {
        try {
            // 创建 ObjectMapper 实例
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 JSON 转换为 Java 对象
            Method method = objectMapper.readValue(doInfo, Method.class);
            Class<?>[] paramTypes = DataConverterHandler.convertToClassArray(method.getParamTypes());
            Object[] param = DataConverterHandler.convertToObjectArray(method.getParam());
            ReflectHandler.invokeMethod(method.getClazz(), method.getMethod(), paramTypes, param);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "123";
    }

    public String executeTest(String beforeReady, String testData, String targetValueRule, String expectedValueRule, Class<?> roClass, ApiBase ao) {
        ApiBase api ;
        try {
            api  =  ao.getClass().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String requestResult = api.convert2RoAndRequest(beforeDo(beforeReady, testData), roClass);
        String targetValue = getRuleValue(requestResult, targetValueRule);
        String expectedValue = getRuleValue(requestResult, expectedValueRule);
        return targetValue+"|"+expectedValue;
       // logTestDetails(beforeReady, testData, requestResult);
       // Assert.assertEquals(targetValue, expectedValue, "Validation failed for target and expected value.");
    }

    public static String getRuleValue(String source, String targetValueRule) {
        System.out.println("原值：" + source);
        System.out.println("取值规则：" + targetValueRule);
        return "";
    }

}
