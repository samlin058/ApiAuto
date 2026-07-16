package Service.Pojo;

import lombok.Data;

@Data
public class ContractInfo {
    private Integer serialNo;            // 序号
    private String contractType;         // 合同类型
    private String contractNo;           // 合同编号
    private String signType;             // 签署类型
    private String signatoryName;        // 签署名
    private String signatoryIdCardNo;    // 签署证件号
    private String signDate;               // 签署日期
}
