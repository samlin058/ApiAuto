package Service.Util.variable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BankCardHandler {
    public static void main(String[] args) {
        System.out.println(generate19DigitCardNumber());
    }

    // 模拟的银行卡号前6位（IIN）与银行名称的映射
    private static final Map<String, String> bankIINMap = new HashMap<>();
    // 19位银行卡号的银行IIN映射
    private static final Map<String, String> bank19DigitIINMap = new HashMap<>();

    static {
        // 预填充一些常见的银行和其对应的IIN（16位卡号）
        bankIINMap.put("622202", "中国工商银行");
        bankIINMap.put("622848", "中国农业银行");
        bankIINMap.put("622700", "中国建设银行");
        bankIINMap.put("622260", "中国银行");
        bankIINMap.put("621226", "中国邮政储蓄银行");
        bankIINMap.put("621093", "招商银行");
        bankIINMap.put("620200", "中信银行");
        bankIINMap.put("622125", "光大银行");
        bankIINMap.put("622262", "交通银行");
        bankIINMap.put("622600", "中国民生银行");
        bankIINMap.put("622908", "兴业银行");
        bankIINMap.put("622521", "浦发银行");
        bankIINMap.put("622155", "平安银行");
        bankIINMap.put("622568", "广发银行");
        bankIINMap.put("622630", "华夏银行");
        bankIINMap.put("622388", "北京银行");
        bankIINMap.put("622267", "上海银行");
        bankIINMap.put("622188", "宁波银行");
        bankIINMap.put("622169", "江苏银行");
        bankIINMap.put("622012", "浙江银行");

        // 19位银行卡号的银行IIN映射（借记卡）
        bank19DigitIINMap.put("621226", "中国工商银行");
        bank19DigitIINMap.put("622848", "中国农业银行");
        bank19DigitIINMap.put("621700", "中国建设银行");
        bank19DigitIINMap.put("621660", "中国银行");
        bank19DigitIINMap.put("621098", "中国邮政储蓄银行");
        bank19DigitIINMap.put("621286", "招商银行");
        bank19DigitIINMap.put("621768", "中信银行");
        bank19DigitIINMap.put("621489", "光大银行");
        bank19DigitIINMap.put("621661", "交通银行");
        bank19DigitIINMap.put("621558", "民生银行");
        bank19DigitIINMap.put("621466", "兴业银行");
        bank19DigitIINMap.put("621299", "平安银行");
        bank19DigitIINMap.put("621468", "浦发银行");
        bank19DigitIINMap.put("621222", "华夏银行");
        bank19DigitIINMap.put("621462", "广发银行");
        bank19DigitIINMap.put("621421", "北京银行");
        bank19DigitIINMap.put("621500", "上海银行");
        bank19DigitIINMap.put("621259", "南京银行");
        bank19DigitIINMap.put("621662", "交通银行");
        bank19DigitIINMap.put("621559", "民生银行");
        bank19DigitIINMap.put("621626", "兴业银行");
        bank19DigitIINMap.put("621517", "平安银行");
        bank19DigitIINMap.put("621793", "浦发银行");
        bank19DigitIINMap.put("621428", "宁波银行");
        bank19DigitIINMap.put("621579", "江苏银行");
        bank19DigitIINMap.put("621534", "浙江银行");
        bank19DigitIINMap.put("621463", "渤海银行");
        bank19DigitIINMap.put("621239", "恒丰银行");
        bank19DigitIINMap.put("621418", "浙商银行");
        bank19DigitIINMap.put("621289", "广州银行");
        bank19DigitIINMap.put("621260", "杭州银行");
        bank19DigitIINMap.put("621486", "厦门银行");
        bank19DigitIINMap.put("621256", "苏州银行");
        bank19DigitIINMap.put("621277", "徽商银行");
        bank19DigitIINMap.put("621520", "盛京银行");
        bank19DigitIINMap.put("621437", "哈尔滨银行");
        bank19DigitIINMap.put("621382", "成都银行");
        bank19DigitIINMap.put("621252", "西安银行");
        bank19DigitIINMap.put("621212", "青岛银行");
        bank19DigitIINMap.put("621492", "东莞银行");
        bank19DigitIINMap.put("621316", "宁夏银行");
        bank19DigitIINMap.put("621588", "福建海峡银行");
        bank19DigitIINMap.put("621573", "郑州银行");
        bank19DigitIINMap.put("621338", "重庆银行");
        bank19DigitIINMap.put("621215", "长沙银行");
        bank19DigitIINMap.put("621236", "河北银行");
        bank19DigitIINMap.put("621483", "天津银行");
        bank19DigitIINMap.put("621405", "吉林银行");
        bank19DigitIINMap.put("621413", "辽宁银行");
        bank19DigitIINMap.put("621448", "大连银行");
        bank19DigitIINMap.put("621453", "齐鲁银行");
        bank19DigitIINMap.put("621458", "青海银行");
        bank19DigitIINMap.put("621473", "江西银行");
        bank19DigitIINMap.put("621497", "南昌银行");
        bank19DigitIINMap.put("621302", "温州银行");
        bank19DigitIINMap.put("621303", "绍兴银行");
        bank19DigitIINMap.put("621305", "嘉兴银行");
        bank19DigitIINMap.put("621307", "台州银行");
        bank19DigitIINMap.put("621309", "湖州银行");
        bank19DigitIINMap.put("621311", "金华银行");
        bank19DigitIINMap.put("621318", "桂林银行");
        bank19DigitIINMap.put("621320", "北部湾银行");
        bank19DigitIINMap.put("621321", "昆仑银行");
        bank19DigitIINMap.put("621322", "兰州银行");
        bank19DigitIINMap.put("621324", "贵阳银行");
        bank19DigitIINMap.put("621325", "昆明银行");
        bank19DigitIINMap.put("621327", "太原银行");
        bank19DigitIINMap.put("621328", "晋商银行");
        bank19DigitIINMap.put("621330", "柳州银行");
        bank19DigitIINMap.put("621331", "日照银行");
        bank19DigitIINMap.put("621332", "潍坊银行");
        bank19DigitIINMap.put("621333", "烟台银行");
        bank19DigitIINMap.put("621334", "威海银行");
        bank19DigitIINMap.put("621335", "济宁银行");
        bank19DigitIINMap.put("621336", "东营银行");
        bank19DigitIINMap.put("621339", "临沂银行");
        bank19DigitIINMap.put("621340", "滨州银行");
        bank19DigitIINMap.put("621341", "聊城银行");
        bank19DigitIINMap.put("621342", "常州银行");
        bank19DigitIINMap.put("621343", "无锡银行");
        bank19DigitIINMap.put("621344", "镇江银行");
        bank19DigitIINMap.put("621345", "南通银行");
        bank19DigitIINMap.put("621346", "连云港银行");
        bank19DigitIINMap.put("621347", "盐城银行");
        bank19DigitIINMap.put("621348", "扬州银行");
        bank19DigitIINMap.put("621349", "泰州银行");
    }


    /**
     * 根据银行卡号获取发卡银行
     *
     * @param cardNumber 银行卡号
     * @return 发卡银行名称，如果找不到则返回"未知银行"
     */
    public static String getBankNameFromCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 6) {
            return "无效银行卡号";
        }
        // 获取银行卡号的前6位
        String iin = cardNumber.substring(0, 6);

        // 先检查19位卡号映射
        String bankName = bank19DigitIINMap.get(iin);
        if (bankName != null) {
            return bankName;
        }

        // 再检查16位卡号映射
        bankName = bankIINMap.get(iin);
        if (bankName != null) {
            return bankName;
        } else {
            return "未知银行";
        }
    }

    /**
     * 生成16位银行卡号（信用卡）
     */
    public static String generateCardNumber() {
        return generateCardNumber(16);
    }

    /**
     * 生成指定长度的银行卡号
     *
     * @param length 银行卡号长度（16或19）
     * @return 银行卡号
     */
    public static String generateCardNumber(int length) {
        if (length != 16 && length != 19) {
            throw new IllegalArgumentException("银行卡号长度只能是16或19位");
        }

        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        if (length == 16) {
            // 16位卡号：6位IIN + 9位随机数 + 1位校验位
            cardNumber.append("622202"); // 默认使用工商银行的IIN
            for (int i = 0; i < 9; i++) {
                cardNumber.append(random.nextInt(10));
            }
        } else {
            // 19位卡号：6位IIN + 12位随机数 + 1位校验位
            cardNumber.append("621226"); // 默认使用工商银行的19位IIN
            for (int i = 0; i < 12; i++) {
                cardNumber.append(random.nextInt(10));
            }
        }

        // 根据Luhn算法生成校验位
        int checkDigit = calculateLuhnCheckDigit(cardNumber.toString());
        cardNumber.append(checkDigit);
        return cardNumber.toString();
    }

    /**
     * 生成19位银行卡号（借记卡）
     */
    public static String generate19DigitCardNumber() {
        return generateCardNumber(19);
    }

    /**
     * 根据支持的银行名称列表，生成一个不在该列表中的19位银行卡号。
     *
     * @param supportedBankNames 已支持的银行名称列表（如："工商银行"、"中国银行" 等）
     * @return 首个不在支持列表中的银行所对应的19位银行卡号
     * @throws IllegalStateException 如果所有已知银行均在支持列表中
     */
    public static String generate19DigitCardNumberNotInSupportedBanks(List<String> supportedBankNames) {
        if (supportedBankNames == null) {
            throw new IllegalArgumentException("supportedBankNames 不能为空");
        }

        java.util.Set<String> supportedSet = new java.util.HashSet<>(supportedBankNames);

        for (Map.Entry<String, String> entry : bank19DigitIINMap.entrySet()) {
            String bankName = entry.getValue();
            if (!supportedSet.contains(bankName)) {
                String iin = entry.getKey();
                String cardNumber = generateCardNumberByIIN(iin, 19);
                System.out.println("生成的银行卡所属银行: " + bankName);
                return cardNumber;
            }
        }

        throw new IllegalStateException("没有可用的未支持银行IIN可以生成银行卡号");
    }

    /**
     * 根据提供的银行名称列表，生成一个在该列表中的19位银行卡号。
     * <p>
     * 优先使用列表中第一个能在已知映射中找到的银行。
     *
     * @param supportedBankNames 目标银行名称列表（如："工商银行"、"中国银行" 等）
     * @return 列表中某个已知银行所对应的19位银行卡号
     * @throws IllegalArgumentException 列表为空或为 null
     * @throws IllegalStateException    列表中没有任何已知银行
     */
    public static String generate19DigitCardNumberInSupportedBanks(List<String> supportedBankNames) {
        if (supportedBankNames == null) {
            throw new IllegalArgumentException("supportedBankNames 不能为空");
        }

        for (String targetBankName : supportedBankNames) {
            for (Map.Entry<String, String> entry : bank19DigitIINMap.entrySet()) {
                if (entry.getValue().equals(targetBankName)) {
                    String iin = entry.getKey();
                    String cardNumber = generateCardNumberByIIN(iin, 19);
                    System.out.println("生成的银行卡所属银行: " + targetBankName);
                    return cardNumber;
                }
            }
        }

        throw new IllegalStateException("提供的银行列表中没有任何已知银行");
    }

    /**
     * 根据指定银行生成19位银行卡号
     *
     * @param bankName 银行名称
     * @return 银行卡号
     */
    public static String generate19DigitCardNumberByBank(String bankName) {
        // 根据银行名称找到对应的IIN
        String iin = null;
        for (Map.Entry<String, String> entry : bank19DigitIINMap.entrySet()) {
            if (entry.getValue().equals(bankName)) {
                iin = entry.getKey();
                break;
            }
        }

        if (iin == null) {
            throw new IllegalArgumentException("不支持的银行名称: " + bankName);
        }

        return generateCardNumberByIIN(iin, 19);
    }

    /**
     * 根据IIN生成指定长度的银行卡号
     *
     * @param iin    发卡行标识符（前6位）
     * @param length 银行卡号长度
     * @return 银行卡号
     */
    public static String generateCardNumberByIIN(String iin, int length) {
        if (iin == null || iin.length() != 6) {
            throw new IllegalArgumentException("IIN必须是6位数字");
        }

        if (length != 16 && length != 19) {
            throw new IllegalArgumentException("银行卡号长度只能是16或19位");
        }

        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder(iin);

        int randomDigits = length - 7; // 总长度 - IIN长度(6) - 校验位(1)
        for (int i = 0; i < randomDigits; i++) {
            cardNumber.append(random.nextInt(10));
        }

        // 根据Luhn算法生成校验位
        int checkDigit = calculateLuhnCheckDigit(cardNumber.toString());
        cardNumber.append(checkDigit);
        return cardNumber.toString();
    }

    /**
     * 验证银行卡号是否有效（使用Luhn算法）
     *
     * @param cardNumber 银行卡号
     * @return 是否有效
     */
    public static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }

        // 检查是否都是数字
        if (!cardNumber.matches("\\d+")) {
            return false;
        }

        // 使用Luhn算法验证
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }

    // 使用Luhn算法计算校验位
    private static int calculateLuhnCheckDigit(String cardNumberWithoutCheckDigit) {
        int sum = 0;
        boolean alternate = false;
        // 从右至左遍历数字
        for (int i = cardNumberWithoutCheckDigit.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(cardNumberWithoutCheckDigit.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        // 计算校验位，使得总和为10的倍数
        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }

    /**
     * 获取所有支持的银行列表
     */
    public static String[] getSupportedBanks() {
        return bank19DigitIINMap.values().toArray(new String[0]);
    }

}
