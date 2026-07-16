package Service.Util.variable;

import Service.Core.ProjectEnum;
import Service.Util.Universal.GetStackInfoHandler;
import Service.Util.Universal.JDBCHandler;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PhoneNumberGeneratorHandler {

    private static final String[] MOBILE_PREFIXES = {
            // 中国移动
            "134", "135", "136", "137", "138", "139",
            "147", "148", "150", "151", "152", "157", "158", "159",
            "165", "172", "178", "182", "183", "184", "187", "188",
            "195", "197", "198",
            // 虚拟运营商
            "1703", "1705", "1706"
    };

    private static final String[] UNICOM_PREFIXES = {
            // 中国联通
            "130", "131", "132",
            "145", "146", "155", "156", "166", "167",
            "171", "175", "176", "185", "186",
            "196",
            // 虚拟运营商
            "1704", "1707", "1708", "1709"
    };

    private static final String[] TELECOM_PREFIXES = {
            // 中国电信
            "133", "149", "153", "173", "177",
            "180", "181", "189", "190", "191", "193", "199",
            // 虚拟运营商
            "1700", "1701", "1702", "162"
    };

    /**
     * 随机生成手机号，并查看项目中是否已经存在该手机号，最终返回可用的手机号（项目中没有的手机号）
     * @param projectCode 项目code
     * @return 手机号
     */
    public static String findAvailablePhoneByProjectCode(ProjectEnum projectCode){
        String phone = null;
        boolean isPhoneAvailable = false;
        if ("LoanMarket".equalsIgnoreCase(projectCode.getCode())){
            // 循环生成并验证手机号
            while (!isPhoneAvailable) {
                phone = generatePhoneNumber();
                String sql = "SELECT mobile FROM tbl_user WHERE mobile = ?;";
                String queryResult = JDBCHandler.queryRowsAsJsonArray(ProjectEnum.LoanMarket, sql, phone);
                // 判断查询结果是否为空（JSON空数组长度为2）
                isPhoneAvailable = (queryResult.length() == 2);
                if (!isPhoneAvailable) {
                    System.out.println("手机号已被使用，重新生成: " + phone);
                }
            }
        }
        return phone;
    }

    /**
     * 随机生成手机号，并查看项目中是否已经存在该手机号，最终返回可用的手机号（项目中没有的手机号）
     * 参数projectCode为空时，自动识别项目名
     * @return 手机号
     */
    public static String findAvailablePhoneByProjectCode() {
        String projectCode = null;
        String className = GetStackInfoHandler.getCallerClassPath();
        // 解析className，提取TestCase后的项目名
        int idx = className.indexOf("TestCase");
        if (idx != -1) {
            String afterTestCase = className.substring(idx + "TestCase".length());
            if (afterTestCase.startsWith(".")) {
                afterTestCase = afterTestCase.substring(1);
            }
            int dotIdx = afterTestCase.indexOf(".");
            if (dotIdx != -1) {
                projectCode = afterTestCase.substring(0, dotIdx);
            } else {
                projectCode = afterTestCase;
            }
        }
        if (projectCode == null || projectCode.isEmpty()) {
            throw new RuntimeException("无法自动识别projectCode，请手动传入");
        }
        return findAvailablePhoneByProjectCode(ProjectEnum.getByCode(projectCode));
    }

    
    /**
     * 生成随机手机号（不指定运营商）
     * @return 手机号
     */
    public static String generatePhoneNumber() {
        int carrier = ThreadLocalRandom.current().nextInt(3);
        switch (carrier) {
            case 0:
                return generateMobileNumber();
            case 1:
                return generateUnicomNumber();
            default:
                return generateTelecomNumber();
        }
    }

    // 生成移动号码
    public static String generateMobileNumber() {
        return generateNumber(MOBILE_PREFIXES);
    }

    // 生成联通号码
    public static String generateUnicomNumber() {
        return generateNumber(UNICOM_PREFIXES);
    }

    // 生成电信号码
    public static String generateTelecomNumber() {
        return generateNumber(TELECOM_PREFIXES);
    }

    private static String generateNumber(String[] prefixes) {
        Random random = ThreadLocalRandom.current();
        String prefix = prefixes[random.nextInt(prefixes.length)];

        // 生成剩余位数
        int remaining = 11 - prefix.length();
        StringBuilder sb = new StringBuilder(prefix);

        for (int i = 0; i < remaining; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }




    // 测试
    public static void main(String[] args) {
        System.out.println("移动: " + generateMobileNumber());
        System.out.println("联通: " + generateUnicomNumber());
        System.out.println("电信: " + generateTelecomNumber());
        System.out.println("随机: " + generatePhoneNumber());
    }
}
