package Service.Util.Universal;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 时间戳工具类
 */
public class TimestampHandler {

    /**
     * 获取当前时间的时间戳（毫秒）
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 根据输入的日期时间返回对应的时间戳（毫秒）
     *
     * @param year   年份（正整数，例如：2024）
     * @param month  月份（1-12）
     * @param day    日（1-31，根据月份自动校验）
     * @param hour   小时（0-23）
     * @param minute 分钟（0-59）
     * @param second 秒（0-59）
     * @return 对应的时间戳（毫秒）
     * @throws java.time.DateTimeException 如果参数不符合日期规范时抛出
     */
    public static long getTimestamp(int year, int month, int day, int hour, int minute, int second) {
        // 使用系统默认时区转换时间
        LocalDateTime localDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime.toInstant().toEpochMilli();
    }

    // 可选：如果需要秒级时间戳，可以添加以下方法
    // public static long getCurrentTimestampSeconds() {
    //     return System.currentTimeMillis() / 1000;
    // }


    public static void main(String[] args) {
        // 获取当前时间戳
        System.out.println("当前时间戳: " + getCurrentTimestamp());

        // 获取指定时间的时间戳（2024年1月1日 12:30:45）
        long timestamp = getTimestamp(2025, 1, 1, 12, 30, 45);
        System.out.println("指定时间戳: " + timestamp);
    }

}