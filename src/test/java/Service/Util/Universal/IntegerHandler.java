package Service.Util.Universal;

import java.util.Random;

public class IntegerHandler {
    public static void main(String[] args) {
        System.out.println(generateNonMultipleRandom(100,4));

    }
    
    /**
     * 生成一个非指定倍数的随机整数，位数在指定范围内
     * @param multiple 倍数（如100）
     * @param digits 位数（如5）
     * @return 返回一个非multiple倍数的随机整数，位数在digits范围内
     */
    public static int generateNonMultipleRandom(int multiple, int digits) {
        if (multiple <= 0 || digits <= 0) {
            throw new IllegalArgumentException("倍数和位数必须大于0");
        }
        
        Random random = new Random();
        int minValue = (int) Math.pow(10, digits - 1); // 最小位数对应的最小值
        int maxValue = (int) Math.pow(10, digits) - 1; // 最大位数对应的最大值
        
        int result;
        do {
            // 生成指定范围内的随机数
            result = random.nextInt(maxValue - minValue + 1) + minValue;
        } while (result % multiple == 0); // 确保不是multiple的倍数
        
        return result;
    }
    
    /**
     * 生成一个非指定倍数的随机整数，位数在指定范围内（重载方法，支持long类型）
     * @param multiple 倍数（如100）
     * @param digits 位数（如5）
     * @return 返回一个非multiple倍数的随机长整数，位数在digits范围内
     */
    public static long generateNonMultipleRandomLong(long multiple, int digits) {
        if (multiple <= 0 || digits <= 0) {
            throw new IllegalArgumentException("倍数和位数必须大于0");
        }
        
        Random random = new Random();
        long minValue = (long) Math.pow(10, digits - 1); // 最小位数对应的最小值
        long maxValue = (long) Math.pow(10, digits) - 1; // 最大位数对应的最大值
        
        long result;
        do {
            // 生成指定范围内的随机数
            result = minValue + (long) (random.nextDouble() * (maxValue - minValue + 1));
        } while (result % multiple == 0); // 确保不是multiple的倍数
        
        return result;
    }
}

