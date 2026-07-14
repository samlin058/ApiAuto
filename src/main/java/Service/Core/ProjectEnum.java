package Service.Core;

/**
 * 业务项目枚举，定义框架支持的所有测试项目及其元信息。
 * <p>
 * 每个枚举值包含项目代码（code）、对应数据库标识（databaseCode）及中文名称（projectName）。
 * </p>
 */
public enum ProjectEnum {
    BlackShark("BlackShark", "hainan_asset_mgmt", "资管"),
    Alchemist("Alchemist", "wac_loan_alchemist", "电销"),
    Goblin("Goblin", "amc_wac_loan_goblin", "催收"),
    LoanMarket("LoanMarket", "torch_market", "信用家"),
    LoanMarketRust("LoanMarketRust", null, "信用家rust服务");

    private final String code;
    private final String databaseCode;
    private final String projectName;

    ProjectEnum(String code, String databaseCode, String projectName) {
        this.code = code;
        this.databaseCode = databaseCode;
        this.projectName = projectName;
    }

    public String getCode() {
        return code;
    }

    public String getDatabaseCode() {
        return databaseCode;
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
        return null;
    }
}
