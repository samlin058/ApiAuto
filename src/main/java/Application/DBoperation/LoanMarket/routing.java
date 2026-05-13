package Application.DBoperation.LoanMarket;

import Service.Enum.DatabaseEnum;
import Service.Util.Universal.JDBCHandler;
import Service.Util.Universal.JsonHandler;

import java.util.List;

public class routing {

    /**
     * 根据渠道code获取该渠道可用的路由资方code
     *
     * @param channelCode 渠道code
     * @return 资方列表
     */
    public static List<String> getRoutingCapital(String channelCode) {
        String sql = "select tp.code productCode from tbl_channel_partner t1 join tbl_channel tc on t1.channel_id =tc.id  join tbl_partner tp on t1.partner_id = tp.id where tc.code  =? and t1.enable =1 order by t1.id desc;";
        List<String> list = JsonHandler.readStringArrayByJsonPath("$.*.productCode", JDBCHandler.queryRowsAsJsonArray(DatabaseEnum.LoanMarkest, sql, channelCode));
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getRoutingCapital("luandaoTest3"));
    }
}
