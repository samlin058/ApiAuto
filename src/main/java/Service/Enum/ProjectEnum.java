package Service.Enum;

public enum ProjectEnum {
    BlackShark("BlackShark", "资管"),
    Alchemist("Alchemist", "电销"),
    Goblin("Goblin", "催收"),
    LoanMarket("LoanMarket", "信用家"),
    LoanMarketRust("LoanMarketRust", "信用家rust服务");

    private final String code;
    private final String projectName;

    ProjectEnum(String code, String projectName) {
        this.code = code;
        this.projectName = projectName;
    }

    public String getCode() {
        return code;
    }

    public String getProjectName() {
        return projectName;
    }

    public static ProjectEnum getByCode(String code) {
        for (ProjectEnum projectEnum : ProjectEnum.values()) {
            if (projectEnum.getCode().equals(code)) {
                return projectEnum;
            }
        }
        return null; // 未找到时返回null
    }
}
