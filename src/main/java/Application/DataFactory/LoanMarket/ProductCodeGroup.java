package Application.DataFactory.LoanMarket;

public enum ProductCodeGroup {

    ALL(new String[]{"NIWODAI", "RENPINQIANBAO", "LIHONG", "LEXIANGJIE"}),
    HALF(new String[]{"RONGAN_HALF", "LONGSHANJIE_HALF", "HEXIAOBAO_HALF", "BIDAI_HALF",
            "ZHONGLI_HALF", "ZHONGXINHUA_HALF", "BAIHEYOUPIN_HALF", "KAKADAI_HALF", "SHENGBEI_HALF", "YIXIANGHUA_HALF"}),
    API_H5(new String[]{"LONGSHANJIE_HALF", "YOUJIE_HALF"});

    private final String[] productCodes;

    ProductCodeGroup(String[] productCodes) {
        this.productCodes = productCodes;
    }

    public String[] getProductCodes() {
        return productCodes;
    }
}


