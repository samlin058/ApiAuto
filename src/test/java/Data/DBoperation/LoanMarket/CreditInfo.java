package Data.DBoperation.LoanMarket;

import Service.Util.Universal.JDBCHandler;

public class CreditInfo {
    public static String getNewestCreditInfo(String uid) {
        String sql = "select * from tbl_credit_limit where uid = ?";
        return JDBCHandler.queryRowsAsJsonArray(sql, uid);
    }

    /**
     * 测试方法，验证自动推断数据库功能
     */
    public static void main(String[] args) {
        String result = getNewestCreditInfo("841082");
        System.out.println("CreditInfo查询结果: " + result);
    }

}
