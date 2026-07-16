package Service.Pojo;

import Service.Util.variable.BankCardHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BankCardInfo {
    private String reservedPhoneNumber;  // 银行卡预留号码
    private String bankCardNumber;       // 银行卡号
    private String bindingDate;            // 绑卡时间
    private String bankName;             // 所属银行

    public static List<BankCardInfo> getBankCardInfoList(LoanInfo loanInfo, CustomerInfo customerInfo) {
        List<BankCardInfo> bankCardInfos = new ArrayList<>();
        BankCardInfo bankCardInfo =new BankCardInfo();
        bankCardInfo.setReservedPhoneNumber(customerInfo.getPhoneNumber());
        bankCardInfo.setBindingDate(loanInfo.getDisbursementDate());
        bankCardInfo.setBankCardNumber(BankCardHandler.generate19DigitCardNumber());
        bankCardInfo.setBankName(BankCardHandler.getBankNameFromCardNumber(bankCardInfo.getBankCardNumber()));
        bankCardInfos.add(bankCardInfo);
        return bankCardInfos;
    }
}
