package Service.Util.Universal;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class CompareHandler {
    public static boolean compareObjectsEq(Object actualValue, Object expectedValue) {
        if (actualValue == null && expectedValue == null) {
            return true;
        }
        if (actualValue == null || expectedValue == null) {
            return false;
        }
        if (actualValue instanceof Number && expectedValue instanceof Number) {
            double value1 = ((Number) actualValue).doubleValue();
            double value2 = ((Number) expectedValue).doubleValue();
            return value1 == value2;
        }
        if (isSameObject(actualValue, expectedValue)) {
            return true;
        } else {
            return actualValue.equals(expectedValue);
        }
    }

    public static boolean isSameObject(Object obj1, Object obj2) {
        if (obj1.getClass() != obj2.getClass()) {
            return false;
        }
        List<Field> fields = ReflectHandler.getAllFields(obj1.getClass());
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);
                if (value1 == null) {
                    if (value2 != null) {
                        return false;
                    }
                } else if (!value1.equals(value2)) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean compareObjectsGt(Object actualValue, Object expectedValue) {
        if (actualValue == null || expectedValue == null) {
            System.out.println("任一参数为 null，无法比较大小");
            return false;
        }
        if (actualValue instanceof String && expectedValue instanceof String) {
            return false;
        }
        if (actualValue instanceof Number && expectedValue instanceof Number) {
            double actualValue1 = ((Number) actualValue).doubleValue();
            double expectedValue2 = ((Number) expectedValue).doubleValue();
            return actualValue1 > expectedValue2;
        }
        if (actualValue instanceof String && expectedValue instanceof Number) {
            try {
                double actualValue1 = Double.parseDouble((String) actualValue);
                double expectedValue2 = ((Number) expectedValue).doubleValue();
                return actualValue1 > expectedValue2;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("无法将字符串转换为数字");
            }
        } else if (actualValue instanceof Number && expectedValue instanceof String) {
            try {
                double actualValue1 = ((Number) actualValue).doubleValue();
                double expectedValue2 = Double.parseDouble((String) expectedValue);
                return actualValue1 > expectedValue2;
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("无法将字符串转换为数字");
            } finally {
                return false;
            }
        } else if (actualValue.getClass().equals(expectedValue.getClass())) {
            return false;
        } else {
            throw new IllegalArgumentException("无法比较不同类型的对象");
        }
    }

    public static boolean compareObjectsLt(Object actualValue, Object expectedValue) {
        if (actualValue == null || expectedValue == null) {
            return false;
        }
        if (actualValue instanceof String && expectedValue instanceof String) {
            try {
                double actualValue1 = Double.parseDouble((String) actualValue);
                double expectedValue1 = Double.parseDouble((String) expectedValue);
                return actualValue1 < expectedValue1;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (actualValue instanceof Number && expectedValue instanceof Number) {
            double actualValue2 = ((Number) actualValue).doubleValue();
            double expectedValue2 = ((Number) expectedValue).doubleValue();
            return actualValue2 < expectedValue2;
        }
        if (actualValue instanceof String && expectedValue instanceof Number) {
            try {
                // 尝试将字符串转换为数字
                double actualValue3 = Double.parseDouble((String) actualValue);
                double expectedValue3 = ((Number) expectedValue).doubleValue();
                return actualValue3 < expectedValue3;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (actualValue instanceof Number && expectedValue instanceof String) {
            try {
                double actualValue4 = ((Number) actualValue).doubleValue();
                double expectedValue4 = Double.parseDouble((String) expectedValue);
                return actualValue4 < expectedValue4;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (actualValue.getClass().equals(expectedValue.getClass())) {
            return false;
        }
        return false;
    }

    public static boolean compareObjectsGe(Object actualValue, Object expectedValue) {
        if (actualValue == null || expectedValue == null) {
            return false;
        }
        if (actualValue instanceof String && expectedValue instanceof String) {
            try {
                double actualValue1 = Double.parseDouble((String) actualValue);
                double expectedValue1 = Double.parseDouble((String) expectedValue);
                return actualValue1 >= expectedValue1;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (actualValue instanceof Number && expectedValue instanceof Number) {
            double actualValue2 = ((Number) actualValue).doubleValue();
            double expectedValue2 = ((Number) expectedValue).doubleValue();
            return actualValue2 >= expectedValue2;
        }
        if (actualValue instanceof String && expectedValue instanceof Number) {
            try {
                double actualValue3 = Double.parseDouble((String) actualValue);
                double expectedValue3 = ((Number) expectedValue).doubleValue();
                return actualValue3 >= expectedValue3;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (actualValue instanceof Number && expectedValue instanceof String) {
            try {
                double actualValue4 = ((Number) actualValue).doubleValue();
                double expectedValue4 = Double.parseDouble((String) expectedValue);
                return actualValue4 >= expectedValue4;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (actualValue.getClass().equals(expectedValue.getClass())) {
            return false;
        }
        return false;
    }

    public static boolean compareObjectsLe(Object actualValue, Object expectedValue) {
        if (actualValue == null || expectedValue == null) {
            return false;
        }
        if (actualValue instanceof String && expectedValue instanceof String) {
            try {
                double actualValue1 = Double.parseDouble((String) actualValue);
                double expectedValue1 = Double.parseDouble((String) expectedValue);
                return actualValue1 <= expectedValue1;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        if (actualValue instanceof Number && expectedValue instanceof Number) {
            double actualValue2 = ((Number) actualValue).doubleValue();
            double expectedValue2 = ((Number) expectedValue).doubleValue();
            return actualValue2 <= expectedValue2;
        }
        if (actualValue instanceof String && expectedValue instanceof Number) {
            try {
                double actualValue3 = Double.parseDouble((String) actualValue);
                double expectedValue3 = ((Number) expectedValue).doubleValue();
                return actualValue3 <= expectedValue3;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (actualValue instanceof Number && expectedValue instanceof String) {
            try {
                double actualValue4 = ((Number) actualValue).doubleValue();
                double expectedValue4 = Double.parseDouble((String) expectedValue);
                return actualValue4 <= expectedValue4;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else if (actualValue.getClass().equals(expectedValue.getClass())) {
            return false;
        }
        return false;
    }

    public static boolean compareObjectsCon(Object actualValue, Object expectedValue) {
        // 如果实际值或期望值为 null，直接返回 false（不包含）
        if (actualValue == null || expectedValue == null) {
            return false;
        }

        // 如果实际值和期望值都是字符串
        if (actualValue instanceof String && expectedValue instanceof String) {
            return ((String) actualValue).contains((String) expectedValue);
        }

        // 如果实际值是集合，检查集合是否包含期望值
        if (actualValue instanceof Collection<?>) {
            return ((Collection<?>) actualValue).contains(expectedValue);
        }

        // 如果实际值是 Map，检查是否包含期望值的键或值
        if (actualValue instanceof Map<?, ?>) {
            Map<?, ?> actualMap = (Map<?, ?>) actualValue;
            return actualMap.containsKey(expectedValue) || actualMap.containsValue(expectedValue);
        }

        // 如果实际值是数组，遍历检查是否包含期望值
        if (actualValue.getClass().isArray()) {
            int length = java.lang.reflect.Array.getLength(actualValue);
            for (int i = 0; i < length; i++) {
                Object element = java.lang.reflect.Array.get(actualValue, i);
                if (element != null && element.equals(expectedValue)) {
                    return true;
                }
            }
            return false;
        }
        // 如果都不是以上类型，直接用 equals 比较
        return actualValue.equals(expectedValue);
    }


    public static void main(String[] args) {
        Object obj1 = "aabbccdd";
        Object obj2 = "bcd";
        System.out.println(compareObjectsCon(obj1, obj2));
    }

}
