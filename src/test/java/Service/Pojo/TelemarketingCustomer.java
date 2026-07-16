package Service.Pojo;

import Service.Util.variable.ChinneNameHandler;
import Service.Util.variable.GenerationHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import Service.Util.Universal.StringHandler;
import lombok.Data;


@Data
public class TelemarketingCustomer {
    private String UID;                // 客户ID
    private String LID;                // 合同编号
    private String customerName;       // 客户姓名
    private String phoneNumber;        // 手机号
    private String email;              // 邮箱
    private String idCardNumber;       // 身份证号
    private String gender;             // 性别
    private String profession;         // 客户职业
    private String city;               // 客户住址所在市
    private String detailedAddress;    // 客户详细住址
    private String companyName;        // 公司名字
    private String companyProvince;    // 公司地址所在省
    private String companyCity;        // 公司地址所在市
    private String companyDistrict;    // 公司地址所在区
    private String companyAddress;     // 公司详细地址
    private String companyPhone;       // 公司电话
    private String birthDate;          // 出生年月日
    private Long loanAmount;           // 放款金额
    private String overdueDays;        // 逾期天数
    private Long remainingPrincipal;   // 剩余本金
    private Long otherFees;            // 其他费用
    private String commissionAmount;   // 委案金额
    private String lendingPlatform;    // 借款平台

    public TelemarketingCustomer() {
        this.UID = GenerationHandler.generateUniqueString();
        this.LID = UID;
        this.customerName = ChinneNameHandler.generateName();
        this.phoneNumber = "8" + PhoneNumberGeneratorHandler.generatePhoneNumber();
        this.email = GenerationHandler.generateRandomLong(10,10)+"@qq.com";
        this.idCardNumber = String.valueOf(GenerationHandler.generateRandomLong(16, 16));
        this.gender = "female";
        this.profession = "Banjarsari";
        this.city = "Kota Surakarta";
        this.detailedAddress = "bibis wetan rt 03/21 gilingan banjarsari surakarta";
        this.companyName = "hepikido";
        this.companyProvince = "jl popda nusukan 111";
        this.companyCity = "Banjarsari";
        this.companyDistrict = "Kota Surakarta";
        this.companyAddress = "lorong 15 no 2, jl.ap.pettarani 2";
        this.companyPhone = "89666584561";
        this.birthDate = "1996/1/29";
        this.loanAmount = GenerationHandler.generateRandomLong(2,4)*1000;
        this.overdueDays = "255";
        this.remainingPrincipal = loanAmount;
        this.otherFees = GenerationHandler.generateRandomLong(2,4)*100;
        this.commissionAmount = String.valueOf(loanAmount + remainingPrincipal);
        this.lendingPlatform = "KTA Kilat";
    }

    public static void main(String[] args) {
        TelemarketingCustomer telemarketingCustomer = new TelemarketingCustomer();
        StringHandler.jsonPrint("数据：",telemarketingCustomer);
    }
}
