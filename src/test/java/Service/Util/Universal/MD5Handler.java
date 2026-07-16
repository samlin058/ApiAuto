package Service.Util.Universal;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * MD5加密工具类
 */
public final class MD5Handler {

    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();

    // 禁止实例化
    private MD5Handler() {
        throw new UnsupportedOperationException("工具类不能实例化");
    }

    /**
     * 对字符串进行MD5加密
     * @param input 原始字符串
     * @return 32位小写MD5哈希值
     */
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            // 正常情况下不会发生，所有Java实现都应包含MD5算法
            throw new RuntimeException("MD5算法不存在", e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串（小写）
     */
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) {
        System.out.println(getMD5("13020250709"));       // 2e0180012d9452afdb8939f667231702
        System.out.println(getMD5("李一一"));       // 2e0180012d9452afdb8939f667231702
        System.out.println(getMD5("210522197603169670"));       // 2e0180012d9452afdb8939f667231702

    }
}
