package Service.Pojo;

import lombok.Data;
@Data
public class TransactionRecord {
    private Integer period;          // 期次
    private String transactionDate;    // 交易日期
    private Integer transactionAmount; // 交易金额
    private String transactionType;  // 交易类型
    private String remark;           // 备注
}
