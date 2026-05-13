package Data.Enum.BlackShark;


public enum Disposed {
    SINGLE_PARTY_DISPOSAL("单方处置","SINGLE"),
    SPLIT_DISPOSAL("拆分处置","SPLIT"),
    CUSTOM_DISPOSAL("自定义处置","CUSTOMIZE"),
    CUSTOMER_GROUP_DISPOSAL("客群处置","CUSTOMER_STRATEGY"),
    INTELLIGENT_DISPOSAL("智能处置","SMART");

    private final String description;

    private final String code;

    Disposed(String description, String code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
