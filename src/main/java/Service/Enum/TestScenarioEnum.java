package Service.Enum;

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
