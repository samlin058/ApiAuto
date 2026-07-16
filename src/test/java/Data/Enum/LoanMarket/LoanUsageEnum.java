package Data.Enum.LoanMarket;


import java.util.Arrays;
import java.util.Random;

/**
 * 借款用途
 */
public enum LoanUsageEnum {

    WSGW("网上购物", "WSGW"),
    STDGW("实体店购物", "STDGW"),
    FCXF("房地产", "FCXF"),
    CGLX("出国留学", "CGLX"),
    HQZX("婚庆装修", "HQZX"),
    CYYL("餐饮娱乐", "CYYL"),
    YLMR("医疗美容", "YLMR"),
    SHXF("生活消费", "SHXF"),
    HXYK("还信用卡", "HXYK"),
    QT("其他", "QT");

    private String name;
    private String value;

    private LoanUsageEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public String getValue() {
        return this.value;
    }


    public String getName() {
        return this.name;
    }

    public static LoanUsageEnum getCodeBy(String code) {
        return Arrays.stream(LoanUsageEnum.values()).filter(o -> o.getValue().equals(code)).findAny().orElse(null);
    }

    /**
     * 随机返回一个枚举值
     * @return 随机的LoanUsageEnum枚举值
     */
    public static LoanUsageEnum getRandom() {
        Random random = new Random();
        LoanUsageEnum[] values = LoanUsageEnum.values();
        return values[random.nextInt(values.length)];
    }

}

