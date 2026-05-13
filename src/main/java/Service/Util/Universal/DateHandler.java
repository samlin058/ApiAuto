package Service.Util.Universal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Random;

public class DateHandler {
    // 日期格式化器
    private static final DateTimeFormatter YMD_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter YYYY_MM_DD_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter YMD_HMS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
    private static final DateTimeFormatter YMDHMS_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static long getTimestamp() {
        return System.currentTimeMillis();
    }
    /**
     * 将指定日期转换为 yyyyMMdd 格式字符串
     */
    public static String getYMDString(LocalDate date) {
        return date.format(YMD_FORMAT);
    }

    /**
     * 将指定日期转换为 yyyy-MM-dd 格式字符串
     */
    public static String getY_M_DString(LocalDate date) {
        return date.format(YYYY_MM_DD_FORMAT);
    }

    /**
     * 将指定日期转换为 yyyyMMdd-HHmmss 格式字符串
     */
    public static String getYMD_HHmmssString(LocalDateTime date) {
        return date.format(YMD_HMS_FORMAT);
    }

    public static String getYMDhhmmssString(LocalDateTime date) {
        return date.format(YMDHMS_FORMAT);
    }

    /**
     * 将当前日期转换为 yyyyMMdd 格式字符串
     */
    public static String getTodayYMDString() {
        return LocalDate.now().format(YMD_FORMAT);
    }

    /**
     * 将当前日期转换为 yyyy-MM-dd 格式字符串
     */
    public static String getTodayY_M_DString() {
        return LocalDate.now().format(YYYY_MM_DD_FORMAT);
    }

    /**
     * 获取当前日期时间的 yyyyMMdd-HHmmss 格式字符串
     */
    public static String getTodayYMD_HMSString() {
        return LocalDateTime.now().format(YMD_HMS_FORMAT);
    }

    public static String getTodayYMDHMSString() {
        return LocalDateTime.now().format(YMDHMS_FORMAT);
    }

    /**
     * 获取当前月份的第一天，格式为 yyyy-MM-dd
     */
    public static String firstDate() {
        LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        return firstDay.format(YYYY_MM_DD_FORMAT);
    }

    /**
     * 计算指定日期加 num 个月后的日期，格式为 yyyy-MM-dd
     *
     * @param dateStr 初始日期字符串 (yyyy-MM-dd)
     * @param num     增加的月份数
     * @return 结果日期字符串 (yyyy-MM-dd)
     */
    public static String nextSomeMonth(String dateStr, int num) {
        LocalDate date = LocalDate.parse(dateStr, YYYY_MM_DD_FORMAT);
        LocalDate resultDate = date.plusMonths(num);
        return resultDate.format(YYYY_MM_DD_FORMAT);
    }

    /**
     * 计算从当前日期开始的 num 个月后，该月的最后一天，格式为 yyyy-MM-dd
     *
     * @param num 增加的月份数
     * @return 结果日期字符串 (yyyy-MM-dd)
     */
    public static String nextSomeMonthLastDay(int num) {
        YearMonth yearMonth = YearMonth.now().plusMonths(num);
        LocalDate lastDay = yearMonth.atEndOfMonth();
        return lastDay.format(YYYY_MM_DD_FORMAT);
    }

    /**
     * 生成一个从今天往回推1到3年之间的随机日期，并返回格式化为 YYYY-MM-DD 字符串
     * @return 格式化的随机日期字符串
     */
    public static String generateRandomDateString() {
        // 获取今天的日期
        LocalDate today = LocalDate.now();

        // 生成一个随机数，决定往回推多少年（1年到3年之间）
        Random random = new Random();
        int yearsBack = 1 + random.nextInt(3); // 随机生成 1 到 3 之间的整数

        // 计算目标年份，即从今天往回推1、2或3年
        LocalDate startDate = today.minusYears(3);  // 3年前的日期
        LocalDate endDate = today.minusYears(1);    // 1年前的日期

        // 随机生成一个日期在 startDate 和 endDate 之间
        long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = random.nextInt((int) daysBetween + 1);  // 生成一个随机天数
        LocalDate randomDate = startDate.plusDays(randomDays);    // 通过加上随机天数得到日期

        return randomDate.format(YYYY_MM_DD_FORMAT);
    }

    /**
     * 计算给定日期与当前日期之间的天数差（绝对值）
     *
     * @param dateStr 输入的日期字符串，格式为 "yyyy-MM-dd"
     * @return 返回当前日期与输入日期之间的天数差；若输入日期是未来日期，也返回正值
     * @throws IllegalArgumentException 如果输入日期格式不正确
     */
    public static int daysBetween(String dateStr) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将输入的字符串转换为 LocalDate
        LocalDate inputDate = LocalDate.parse(dateStr, formatter);
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 计算并返回天数差
        return Math.abs((int) ChronoUnit.DAYS.between(inputDate, currentDate));
    }

    /**
     * 获取今天是几号
     *
     * @return 返回今天是几号（1到31之间的整数）
     */
    public static int getTodayDayOfMonth() {
        // 获取今天的日期
        LocalDate today = LocalDate.now();

        // 返回今天是几号（日期中的“日”部分）
        return today.getDayOfMonth();
    }

    /**
     * 根据给定的日期字符串，返回当天是几号
     *
     * @param dateStr 日期字符串，格式为 "yyyy-MM-dd"
     * @return 返回当天是几号（1到31之间的整数）
     * @throws IllegalArgumentException 如果日期字符串格式不正确
     */
    public static int getDayOfMonthFromString(String dateStr) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 将输入的字符串转换为 LocalDate
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // 返回该日期是几号（日期中的“日”部分）
        return date.getDayOfMonth();
    }

    /**
     * 计算从给定日期开始后 num 个月的日期
     *
     * @param dateStr 给定日期字符串，格式为 "yyyy-MM-dd"
     * @param num 要增加的月份数
     * @return 返回计算后的日期，格式为 "yyyy-MM-dd"
     * @throws IllegalArgumentException 如果日期字符串格式不正确
     */
    public static String calculateDateAfterMonths(String dateStr, int num) {
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 将输入的字符串转换为 LocalDate
        LocalDate date = LocalDate.parse(dateStr, formatter);

        // 计算 num 个月后的日期
        LocalDate newDate = date.plusMonths(num);

        // 将新的日期转换为字符串并返回
        return newDate.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(calculateDateAfterMonths("2025-01-02",-1));
    }


}
