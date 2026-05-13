package Application.DBoperation.LoanMarket;

import Service.Util.Universal.JDBCHandler;

public class User {
    /**
     * 通过手机号查uid，可能为null
     * @param phone 手机号
     * @return uid
     */
    public static String getUid(String phone) {
        String sql = "select id from tbl_user where mobile = ?";
        return JDBCHandler.queryRowsAsJsonArray(sql, phone);
    }

    public static void main(String[] args) {
        System.out.println(getUid("18173492955"));
    }
}
