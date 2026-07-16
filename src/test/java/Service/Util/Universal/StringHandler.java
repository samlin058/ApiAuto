package Service.Util.Universal;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandler {

    /**
     * 判断字符串是否为空
     * 1、等于null
     * 2、"   "等于空
     * 3、""等于空
     *
     * @return boolean
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            // 对象为 null
            return true;
        }
        if (obj instanceof String) {
            // 字符串为空或全是空白字符
            return ((String) obj).trim().isEmpty();
        }
        if (obj instanceof CharSequence) {
            // 支持 CharSequence 的其他子类
            return ((CharSequence) obj).length() == 0;
        }
        if (obj.getClass().isArray()) {
            // 数组长度为 0
            return ((Object[]) obj).length == 0;
        }
        if (obj instanceof Collection) {
            // 集合为空
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            // Map 为空
            return ((Map<?, ?>) obj).isEmpty();
        }

        return false;
    }


    /**
     * 根据regex规则进行分割，然后获取分割后的最后一个值
     *
     * @param str   被分割的字符串
     * @param regex 规则,例如："\\."
     * @return 分割后的最后一个值
     */
    public static String splitByRegexAndGetLast(String str, String regex) {
        String[] parts = str.split(regex);
        String lastElement = null;
        if (parts.length > 0) {
            lastElement = parts[parts.length - 1];
        }
        return lastElement;
    }

    public static List<String> splitByRegex(String str, String regex) {
        if (str == null) {
            return new ArrayList<>();
        }
        if (regex == null || regex.isEmpty()) {
            List<String> only = new ArrayList<>(1);
            only.add(str);
            return only;
        }
        String[] parts = str.split(regex, -1); // -1 保留尾部空字符串
        List<String> result = new ArrayList<>(parts.length);
        for (String p : parts) {
            result.add(p);
        }
        return result;
    }


    public static List<String> getValueByRegex(String inputString, String regex) {
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建匹配器
        Matcher matcher = pattern.matcher(inputString);
        List<String> key = new ArrayList<>();
        // 查找匹配的值
        while (matcher.find()) {
            String extractedValue = matcher.group(1);
            key.add(extractedValue);
        }
        return key;
    }


    /**
     * 用json2中的值，替换json1字符串中的占位符 例如下面参数
     *
     * @param json1 例如：{"account": "${user}","x-tenant": "${business}"}
     * @param json2 例如：{"user": "luandao","business": "YUERONG"}
     * @return Map<String, String>
     */
    public static Map<String, String> getLoginInfo(String json1, String json2) {
        if (StringHandler.isEmpty(json1) || StringHandler.isEmpty(json2)) {
            return null;
        }
        Map<String, String> mapResult = null;
        // 解析第二个 JSON 字符串
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode2 = objectMapper.readTree(json2);
            // 正则表达式匹配 ${key} 形式的占位符
            Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");
            Matcher matcher = pattern.matcher(json1);
            // 替换第一个 JSON 字符串的值
            StringBuffer result = new StringBuffer();
            while (matcher.find()) {
                String placeholder = matcher.group(0);  // 匹配到的占位符，例如 ${user}
                String key = matcher.group(1);          // 匹配到的键，例如 user
                String replacement = jsonNode2.has(key) ? jsonNode2.get(key).asText() : placeholder;
                matcher.appendReplacement(result, replacement);
            }
            matcher.appendTail(result);
            // 输出替换后的结果
            mapResult = (Map<String, String>) JSONObject.parse(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapResult;
    }

    /**
     * 将输入字符串中第 index 个字符（从1开始计数）转换为大写。
     * 如果 index 非法（<=0 或 > 字符串长度），返回原字符串。
     *
     * @param input 原始字符串
     * @param index 位置（从1开始）
     * @return 转换后的字符串
     */
    public static String uppercaseNthChar(String input, int index) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        if (index <= 0 || index > input.length()) {
            return input;
        }
        int pos = index - 1;
        char[] chars = input.toCharArray();
        chars[pos] = Character.toUpperCase(chars[pos]);
        return new String(chars);
    }

    /**
     * 兼容旧方法：首字母大写，等价于 uppercaseNthChar(input, 1)
     */
    public static String capitalizeFirstLetter(String input) {
        return uppercaseNthChar(input, 1);
    }

    /**
     * 将第一个字母小写
     *
     * @param input
     * @return
     */
    public static String lowercaseFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char firstChar = Character.toLowerCase(input.charAt(0));
        return firstChar + input.substring(1);
    }

    /**
     * 判断字符串是否是Json数组
     *
     * @param json
     * @return 是，否
     */
    public static boolean isJsonArrayString(String json) {
        if (json.startsWith("[") && json.endsWith("]")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 去除字符串后NUM位
     *
     * @param inputString 输入的字符串
     * @param Num         数量
     * @return
     */
    public static String cutAfterStringByNum(String inputString, int Num) {
        if (inputString.length() >= Num) {
            return inputString.substring(0, inputString.length() - Num);
        }
        System.out.println("字符串长度不足，无法截取 " + Num + " 位");
        return null;
    }


    /**
     * 根据输入的位数，将输入的字符串前后截取同等的字符
     *
     * @param str
     * @param trimLength
     * @return
     */
    public static String trimString(String str, int trimLength) {
        // 检查字符串长度，确保不是空字符串且长度大于等于截取长度的两倍
        if (str != null && str.length() >= trimLength * 2) {
            // 使用substring方法分别从头尾截取相同长度的字符串，并拼接起来
            return str.substring(trimLength, str.length() - trimLength);
        } else {
            // 如果字符串为空或长度小于截取长度的两倍，则返回空字符串或原字符串
            return "";
        }
    }

    /**
     * 在控制台格式化输出json
     *
     * @param json
     */
    public static void jsonFormatOutput(String json) {
        // 创建 Gson 对象并启用格式化输出
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        // 格式化 JSON 字符串并输出到控制台
        String formattedJsonString = gson.toJson(gson.fromJson(json, Object.class));
        System.out.println(formattedJsonString);
        System.out.println("--------------------------------------------------");
    }

    public static void jsonPrint(String tips, Object object) {
        System.out.println(tips + " : " + DataConverterHandler.objectToJson(object));
    }


    /**
     * 根据规则分割字符串并返回指定索引的值
     *
     * @param input     输入字符串
     * @param delimiter 分隔符
     * @param num       指定的索引，从1开始
     * @return 返回指定索引的值，若索引超出范围，返回null
     */
    public static String getNthValue(String input, String delimiter, int num) {
        if (input == null || delimiter == null || num <= 0) {
            throw new IllegalArgumentException("参数不合法");
        }
        // 分割字符串
        String[] parts = input.split(delimiter);
        // 返回指定索引的值
        if (num > parts.length) {
            return null; // 索引超出范围返回null
        }
        return parts[num - 1]; // 转为从1开始的索引
    }

    /**
     * 将中文转成拼音
     *
     * @param chinese 中文
     * @return 拼音
     */
    public static String getPinyin(String chinese) {
        StringBuilder pinyinBuilder = new StringBuilder();
        char[] chars = chinese.toCharArray();
        for (char c : chars) {
            // 获取每个汉字的拼音
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c);
            if (pinyinArray != null) {
                String pinyinWithoutTone = pinyinArray[0].replaceAll("[0-9]", "");
                pinyinBuilder.append(pinyinWithoutTone);
            }
        }
        return pinyinBuilder.toString();
    }

    /**
     * 将字符串中的字母统一转换为指定大小写
     *
     * @param input   原始字符串
     * @param toUpper true=转大写，false=转小写
     * @return 转换后的字符串（输入为null时返回null）
     */
    public static String convertLettersCase(String input, boolean toUpper) {
        if (input == null) return null;

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!Character.isLetter(chars[i])) continue;

            // 根据参数选择转换方向
            chars[i] = toUpper ?
                    Character.toUpperCase(chars[i]) :
                    Character.toLowerCase(chars[i]);
        }
        return new String(chars);
    }

    /**
     * 从左往右截取字符串的前index位
     *
     * @param inputStr 输入字符串
     * @param index    要保留的字符数量
     * @return 截取后的字符串。如果输入为null则返回空字符串，如果index大于字符串长度则返回整个字符串
     */
    public static String leftSubstring(String inputStr, int index) {
        // 处理null和空字符串情况
        if (inputStr == null || inputStr.isEmpty()) {
            return "";
        }
        // 处理index小于0的情况
        if (index < 0) {
            throw new IllegalArgumentException("索引值不能为负数");
        }
        // 如果index大于字符串长度，返回整个字符串
        if (index >= inputStr.length()) {
            return inputStr;
        }
        // 正常情况：截取前index位
        return inputStr.substring(0, index);
    }

    /**
     * 从字符串的前面和后面同时删除指定数量的相同字节
     * 例如：输入"SSSAAA"，1，则返回"SSAA"
     *
     * @param s   输入字符串
     * @param num 要删除的字节数量
     * @return 删除后的字符串
     */
    public static String trimSameBytesFromBothEnds(String s, int num) {
        // 处理null和空字符串情况
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        // 处理num小于等于0的情况
        if (num <= 0) {
            return s;
        }
        
        // 如果字符串长度小于等于要删除的字节数的两倍，返回空字符串
        if (s.length() <= num * 2) {
            return "";
        }
        
        // 从前面和后面同时删除指定数量的字节
        return s.substring(num, s.length() - num);
    }

    public static void main(String[] args) {
       String a =  trimSameBytesFromBothEnds("{aaasss}",1);
        System.out.println(a);
    }


}
