package Service.Pojo;

import lombok.Data;

@Data
public class RepaymentRecord {
    private String repaymentType;     // 还款类型
    private String businessOrderNo;   // 业务单号
    private Integer period;           // 期次
    private Integer repaymentAmount; // 还款金额
    private String repaymentDate;       // 还款时间
}
