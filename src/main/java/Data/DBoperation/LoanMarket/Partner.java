package Application.DBoperation.LoanMarket;

import Service.Util.Universal.JDBCHandler;

/**
 * 下游
 */
public class Partner {
    /**
     * 获取撞库结果
     * @param uid
     * @return
     */
    public static String getNewestCheckInfo(String uid) {
        String sql = "select * from tbl_partner_check_record t1 where t1.mobile in(select  mobile_md5 from tbl_user tu where id =?);";
        return JDBCHandler.queryRowsAsJsonArray(sql, uid);
    }


    public static void main(String[] args) {
        String result = getNewestCheckInfo("841438");
        System.out.println("CreditInfo查询结果: " + result);
    }

}
