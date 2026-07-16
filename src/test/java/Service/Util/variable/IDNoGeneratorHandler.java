package Service.Util.variable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 身份证生成器
 */
public class IDNoGeneratorHandler {

    // 预定义的地区码（行政区划代码）
    private static final String[] REGION_CODES = {
            "110101", "110102", "110105", // 北京市
            "310101", "310104", "310105", // 上海市
            "440103", "440104", "440106", // 广州市
            "440303", "440304", "440305", // 深圳市
            "330102", "330103", "330104", // 杭州市
            "420102", "420103", "420104", // 武汉市
            "510104", "510105", "510106", // 成都市
            "120101", "120102", "120103"  // 天津市
    };

    // 系数数组，用于计算校验码
    private static final int[] COEFFICIENTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 校验码映射表
    private static final char[] CHECK_CODES = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    // 默认年龄范围（18-60岁）
    private static final int DEFAULT_MIN_AGE = 18;
    private static final int DEFAULT_MAX_AGE = 60;

    /**
     * 生成一个随机身份证号码（默认年龄18-60岁）
     */
    public static String generateIDCard() {
        return generateIDCard(null, DEFAULT_MIN_AGE, DEFAULT_MAX_AGE);
    }

    /**
     * 生成指定性别的身份证号码（默认年龄18-60岁）
     *
     * @param gender 1=男性, 2=女性, null=随机
     */
    public static String generateIDCard(Integer gender) {
        return generateIDCard(gender, DEFAULT_MIN_AGE, DEFAULT_MAX_AGE);
    }

    /**
     * 生成指定年龄范围的身份证号码
     *
     * @param minAge 最小年龄（包含）
     * @param maxAge 最大年龄（包含）
     */
    public static String generateIDCard(int minAge, int maxAge) {
        return generateIDCard(null, minAge, maxAge);
    }

    /**
     * 生成指定性别和年龄范围的身份证号码
     *
     * @param gender 1=男性, 2=女性, null=随机
     * @param minAge 最小年龄（包含）
     * @param maxAge 最大年龄（包含）
     */
    public static String generateIDCard(Integer gender, int minAge, int maxAge) {
        // 验证年龄范围有效性
        if (minAge < 0 || maxAge < 0) {
            throw new IllegalArgumentException("年龄不能为负数");
        }
        if (minAge > maxAge) {
            throw new IllegalArgumentException("最小年龄不能大于最大年龄");
        }

        // 1. 随机选择地区码
        String region = REGION_CODES[new Random().nextInt(REGION_CODES.length)];

        // 2. 生成出生日期（根据年龄范围）
        LocalDate birthDate = generateRandomBirthDate(minAge, maxAge);
        String birthDateStr = birthDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 3. 生成顺序码 (3位数字)
        String sequenceCode = generateSequenceCode(gender);

        // 4. 组合前17位并计算校验码
        String first17 = region + birthDateStr + sequenceCode;
        char checkCode = calculateCheckCode(first17);

        return first17 + checkCode;
    }

    /**
     * 根据中国大陆居民身份证号码计算年龄（支持18位与15位）。
     *
     * @param idno 身份证号码
     * @return 年龄；无法解析返回null
     */
    public static Integer calculateAgeFromIdNo(String idno) {
        if (idno == null) {
            return null;
        }
        idno = idno.trim();
        String birth;
        if (idno.length() == 18) {
            birth = idno.substring(6, 14); // yyyyMMdd
        } else if (idno.length() == 15) {
            birth = "19" + idno.substring(6, 12); // yyMMdd -> 19yyMMdd
        } else {
            return null;
        }

        try {
            LocalDate birthDate = LocalDate.parse(birth, DateTimeFormatter.BASIC_ISO_DATE);
            LocalDate today = LocalDate.now();
            int age = today.getYear() - birthDate.getYear();
            if (today.getMonthValue() < birthDate.getMonthValue() ||
                    (today.getMonthValue() == birthDate.getMonthValue() && today.getDayOfMonth() < birthDate.getDayOfMonth())) {
                age--;
            }
            return age < 0 ? null : age;
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    // 生成随机出生日期（根据年龄范围）
    private static LocalDate generateRandomBirthDate(int minAge, int maxAge) {
        LocalDate today = LocalDate.now();

        // 计算最小出生日期（当前日期减去最大年龄）
        LocalDate minBirthDate = today.minusYears(maxAge + 1L).plusDays(1);

        // 计算最大出生日期（当前日期减去最小年龄）
        LocalDate maxBirthDate = today.minusYears(minAge);

        // 生成随机日期
        long minDay = minBirthDate.toEpochDay();
        long maxDay = maxBirthDate.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay + 1);

        return LocalDate.ofEpochDay(randomDay);
    }

    // 生成顺序码 (3位数字)
    private static String generateSequenceCode(Integer gender) {
        Random random = new Random();
        // 前两位随机 (00-99)
        int firstTwo = random.nextInt(100);

        // 第三位根据性别生成
        int thirdDigit;
        if (gender == null) {
            thirdDigit = random.nextInt(10); // 0-9随机
        } else if (gender == 1) {
            thirdDigit = 2 * random.nextInt(5) + 1; // 男性奇数 (1,3,5,7,9)
        } else {
            thirdDigit = 2 * random.nextInt(5);     // 女性偶数 (0,2,4,6,8)
        }

        return String.format("%02d", firstTwo) + thirdDigit;
    }

    // 计算校验码
    private static char calculateCheckCode(String first17) {
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (first17.charAt(i) - '0') * COEFFICIENTS[i];
        }
        return CHECK_CODES[sum % 11];
    }

    // 测试方法
    public static void main(String[] args) {
        // 生成10个随机身份证（默认年龄18-60岁）
        System.out.println("随机身份证 (18-60岁):");
        for (int i = 0; i < 10; i++) {
            System.out.println(generateIDCard());
        }

        // 生成5个男性身份证（指定年龄25-35岁）
        System.out.println("\n男性身份证 (25-35岁):");
        for (int i = 0; i < 5; i++) {
            System.out.println(generateIDCard(1, 25, 35));
        }

        // 生成5个女性身份证（指定年龄60-70岁）
        System.out.println("\n女性身份证 (60-70岁):");
        for (int i = 0; i < 5; i++) {
            System.out.println(generateIDCard(2, 60, 70));
        }

        // 生成5个儿童身份证（0-5岁）
        System.out.println("\n儿童身份证 (0-5岁):");
        for (int i = 0; i < 5; i++) {
            System.out.println(generateIDCard(0, 5));
        }
    }
}