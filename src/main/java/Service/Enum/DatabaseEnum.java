package Service.Enum;

public enum DatabaseEnum {
    BlackShark("hainan_asset_mgmt", "资管"),
    Alchemist("wac_loan_alchemist", "电销"),
    LoanMarkest("torch_market", "信用家"),
    Goblin("amc_wac_loan_goblin", "催收");
    private final String dataBaseCode;
    private final String projectName;

    DatabaseEnum(String dataBaseCode, String projectName) {
        this.dataBaseCode = dataBaseCode;
        this.projectName = projectName;
    }

    public String getDataBaseCode() {
        return dataBaseCode;
    }

    public String getProjectName() {
        return projectName;
    }
}
