package Service.Core;

/**
 * 测试场景类型枚举，用于区分参数化测试数据的存放路径。
 * <p>
 * 例如 {@link #Single} 对应单一接口测试，{@link #complex} 对应多步骤场景测试。
 * </p>
 */
public enum TestScenarioEnum {
    Single("Single","单一"), complex("Scenario","场景测试");

    private String code;

    private String Desc;

    TestScenarioEnum(String code, String desc) {
        this.code = code;
        Desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return Desc;
    }
}
