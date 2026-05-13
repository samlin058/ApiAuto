package Service.Util.variable;

import Service.Util.Universal.DateHandler;

import java.util.Random;


public class GenerationHandler {

    /**
     * 随机生成一个资产包名，例如：test_20241217152936
     *
     * @return
     */
    public static String getAssetPkgName() {
        return "test_" + DateHandler.getTodayYMDHMSString();
    }

    /**
     * 根据位数的范围，随机生成一个整数
     *
     * @param minDigits 最小位数
     * @param maxDigits 最大位数
     * @return 随机整数
     */
    public static int generateRandomInteger(int minDigits, int maxDigits) {
        Random random = new Random();
        if (minDigits > maxDigits) {
            throw new IllegalArgumentException("minDigits cannot be greater than maxDigits");
        }
        // 计算可能的位数总数
        int numDigitsRange = maxDigits - minDigits + 1;
        // 随机选择一个位数
        int selectedDigits = random.nextInt(numDigitsRange) + minDigits;
        // 计算该位数的范围
        int lowerBound = (int) Math.pow(10, selectedDigits - 1);  // 例如 100
        int upperBound = (int) Math.pow(10, selectedDigits) - 1; // 例如 9999
        // 生成该位数范围内的随机整数
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    /**
     * 根据位数的范围，随机生成一个整数（Long）
     * @param minDigits 最小位数
     * @param maxDigits 最大位数
     * @return Long类型的整数
     */
    public static long generateRandomLong(int minDigits, int maxDigits) {
        Random random = new Random();
        if (minDigits > maxDigits) {
            throw new IllegalArgumentException("minDigits cannot be greater than maxDigits");
        }
        // 计算可能的位数总数
        int numDigitsRange = maxDigits - minDigits + 1;
        // 随机选择一个位数
        int selectedDigits = random.nextInt(numDigitsRange) + minDigits;
        // 计算该位数的范围
        long lowerBound = (long) Math.pow(10, selectedDigits - 1);  // 例如 100
        long upperBound = (long) Math.pow(10, selectedDigits) - 1; // 例如 9999
        // 生成该位数范围内的随机整数
        return lowerBound + (long)(random.nextDouble() * (upperBound - lowerBound + 1));
    }

    /**
     * 生成一个指定范围内的随机整数，包含最小值和最大值
     *
     * @param min 最小值（包含）
     * @param max 最大值（包含）
     * @return 随机整数
     */
    public static int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;  // 生成 [min, max] 范围的随机整数
    }

    /**
     * 生成合同号
     *
     * @return 合同编号
     */
    public static String generateOrderNo() {
        return "Auto_" + DateHandler.getTimestamp();
    }


    public static String generateUniqueString() {
        long timestamp = System.currentTimeMillis();                      // 获取当前时间戳
        String randomUUID = String.valueOf(generateRandomInteger(4, 5));  // 随机生成UUID
        return timestamp + "_" + randomUUID;                              // 将时间戳与UUID拼接
    }


    public static void main(String[] args) {
        System.out.println( generateRandomLong(10,10));
    }
}

