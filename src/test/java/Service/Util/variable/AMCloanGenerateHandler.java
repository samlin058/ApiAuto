package Service.Util.variable;

import Service.Pojo.*;
import Service.Util.Universal.ExcelHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.FileHandler;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AMCloanGenerateHandler {

    static int emergencyContactRowNum = 1;
    static int repaymentPlanRowNum = 1;
    static int repaymentRecordRowNum = 1;
    static int transactionRecordRowNum = 1;
    static int contractInfoRowNum = 1;
    static int bankCardInfoRowNum = 1;

    public static void exportCreditLoanToExcel(List<CreditLoan> creditLoanList, String fileName) {
        String templatePath = "src/test/resources/LoanModules/" + fileName + ".xlsx";
        // 1. 读取模板文件
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(templatePath);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 2. 获取所有 Sheet
        Sheet loanInfoSheet = workbook.getSheetAt(0); // 借款信息
        Sheet customerInfoSheet = workbook.getSheetAt(1); // 基本信息
        Sheet emergencyContactsSheet = workbook.getSheetAt(2); // 紧急联系人信息
        Sheet repaymentPlanSheet = workbook.getSheetAt(3); // 还款计划
        Sheet repaymentRecordSheet = workbook.getSheetAt(4); // 还款记录
        Sheet transactionRecordSheet = workbook.getSheetAt(5); // 流水信息
        Sheet contractInfoSheet = workbook.getSheetAt(6); // 合同信息
        Sheet bankCardInfoSheet = workbook.getSheetAt(7); // 扣款卡信息

        // 3. 清空每个 Sheet 中从第二行开始的内容
        ExcelHandler.clearSheetDataExceptHeader(loanInfoSheet);
        ExcelHandler.clearSheetDataExceptHeader(customerInfoSheet);
        ExcelHandler.clearSheetDataExceptHeader(emergencyContactsSheet);
        ExcelHandler.clearSheetDataExceptHeader(repaymentPlanSheet);
        ExcelHandler.clearSheetDataExceptHeader(repaymentRecordSheet);
        ExcelHandler.clearSheetDataExceptHeader(transactionRecordSheet);
        ExcelHandler.clearSheetDataExceptHeader(contractInfoSheet);
        ExcelHandler.clearSheetDataExceptHeader(bankCardInfoSheet);

        // 3. 遍历 CreditLoan 列表并填充每个 Sheet
        int rowNum = creditLoanList.size();  // 一共几条数据
        for (int i = 0; i < rowNum; i++) {
            CreditLoan creditLoan = creditLoanList.get(i);
            // 填充借款信息 Sheet
            fillLoanInfoSheet(loanInfoSheet, creditLoan, i);
            // 填充基本信息 Sheet
            fillCustomerInfoSheet(customerInfoSheet, creditLoan, i);
            // 填充紧急联系人信息 Sheet
            emergencyContactRowNum = fillEmergencyContactsSheet(emergencyContactsSheet, creditLoan, emergencyContactRowNum);
            // 填充还款计划 Sheet
            repaymentPlanRowNum = fillRepaymentPlanSheet(repaymentPlanSheet, creditLoan, repaymentPlanRowNum);
            // 填充还款记录 Sheet
            repaymentRecordRowNum = fillRepaymentRecordSheet(repaymentRecordSheet, creditLoan, repaymentRecordRowNum);
            // 填充流水信息 Sheet
            transactionRecordRowNum = fillTransactionRecordSheet(transactionRecordSheet, creditLoan, transactionRecordRowNum);
            // 填充合同信息 Sheet
            contractInfoRowNum = fillContractInfoSheet(contractInfoSheet, creditLoan, contractInfoRowNum);
            // 填充扣款卡信息 Sheet
            bankCardInfoRowNum = fillBankCardInfoSheet(bankCardInfoSheet, creditLoan, bankCardInfoRowNum);
        }

        String outPutPath = "src/test/resources/" + fileName + ".xlsx";
        FileHandler.deleteFile(outPutPath);
        // 4. 将修改后的 Excel 保存为新文件
        try {
            FileOutputStream fos = new FileOutputStream(outPutPath);
            workbook.write(fos);
            fos.close();
            workbook.close();
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    // 填充每个 Sheet 的方法
    private static void fillLoanInfoSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充借款信息
        Row row = sheet.createRow(rowNum + 1);// 第一行为表头，从第二行开始写
        // 获取列，然后填写数据
        row.createCell(0).setCellValue(creditLoan.OrderNo);
        row.createCell(1).setCellValue(creditLoan.loanInfo.getProductType());
        row.createCell(2).setCellValue(creditLoan.loanInfo.getLoanTerm());
        row.createCell(3).setCellValue(creditLoan.loanInfo.getLoanAmount() * 100); // 金额转成分
        row.createCell(4).setCellValue(creditLoan.loanInfo.getLoanInterestRate()+"%");
        row.createCell(5).setCellValue(creditLoan.loanInfo.getDisbursementDate());
        row.createCell(6).setCellValue(creditLoan.loanInfo.getDisbursementEntity());
        row.createCell(7).setCellValue(creditLoan.loanInfo.getIsSettled());
        row.createCell(8).setCellValue(creditLoan.loanInfo.getOverduePeriods());
        row.createCell(9).setCellValue(creditLoan.loanInfo.getRemainingPrincipal() * 100);// 金额转成分
        row.createCell(10).setCellValue(creditLoan.loanInfo.getRemainingInterest() * 100);// 金额转成分
        row.createCell(11).setCellValue(creditLoan.loanInfo.getRemainingServiceFee() * 100);
        row.createCell(12).setCellValue(creditLoan.loanInfo.getRemainingOverdueInterest() * 100);
        row.createCell(13).setCellValue(creditLoan.loanInfo.getRemainingTotalAmount() * 100);// 金额转成分
        row.createCell(14).setCellValue(creditLoan.loanInfo.getOverdueDays());
        row.createCell(15).setCellValue(creditLoan.loanInfo.getRemainingPrincipalNotDue());
        row.createCell(16).setCellValue(creditLoan.loanInfo.getTotalSubstitutionAmount());
        row.createCell(17).setCellValue(creditLoan.loanInfo.getBankCardReservedNumber());
        row.createCell(18).setCellValue(creditLoan.loanInfo.getBankCardNumber());
        row.createCell(19).setCellValue(creditLoan.loanInfo.getBindCardDate());
        row.createCell(20).setCellValue(creditLoan.loanInfo.getBankName());
        row.createCell(21).setCellValue(creditLoan.loanInfo.getLoanStartDate());
        row.createCell(22).setCellValue(creditLoan.loanInfo.getLoanEndDate());
        row.createCell(23).setCellValue(creditLoan.loanInfo.getMonthlyRepaymentDate());
        row.createCell(24).setCellValue(creditLoan.loanInfo.getWithdrawalAmount() * 100);
        row.createCell(25).setCellValue(creditLoan.loanInfo.getWithdrawalDate());
        row.createCell(26).setCellValue(creditLoan.loanInfo.getRepaymentMethod());
        row.createCell(27).setCellValue(creditLoan.loanInfo.getLoanPlatform());
        row.createCell(28).setCellValue(creditLoan.loanInfo.getLoanPlatformName());
        row.createCell(29).setCellValue(creditLoan.loanInfo.getCurrentCreditorEntity());
        row.createCell(30).setCellValue(creditLoan.loanInfo.getAssetParty());
        row.createCell(31).setCellValue(creditLoan.loanInfo.getOverdueInterestRate());
        row.createCell(32).setCellValue(creditLoan.loanInfo.getInvestorCount());
        row.createCell(33).setCellValue(creditLoan.loanInfo.getDisbursementEntityName());
    }

    private static void fillCustomerInfoSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充基本信息
        Row row = sheet.createRow(rowNum + 1);// 第一行为表头，从第二行开始写
        row.createCell(0).setCellValue(creditLoan.OrderNo);
        row.createCell(1).setCellValue(creditLoan.customerInfo.getName());
        row.createCell(2).setCellValue(creditLoan.customerInfo.getIdCardNumber());
        row.createCell(3).setCellValue(creditLoan.customerInfo.getPhoneNumber());
        row.createCell(4).setCellValue(creditLoan.customerInfo.getGender());
        row.createCell(5).setCellValue(creditLoan.customerInfo.getEthnicity());
        row.createCell(6).setCellValue(creditLoan.customerInfo.getIdCardStartDate());
        row.createCell(7).setCellValue(creditLoan.customerInfo.getIdCardEndDate());
        row.createCell(8).setCellValue(creditLoan.customerInfo.getBirthDate());
        row.createCell(9).setCellValue(creditLoan.customerInfo.getIdCardAddress());
        row.createCell(10).setCellValue(creditLoan.customerInfo.getHighestEducation());
        row.createCell(11).setCellValue(creditLoan.customerInfo.getResidenceProvince());
        row.createCell(12).setCellValue(creditLoan.customerInfo.getResidenceCity());
        row.createCell(13).setCellValue(creditLoan.customerInfo.getResidenceDistrict());
        row.createCell(14).setCellValue(creditLoan.customerInfo.getResidenceAddress());
        row.createCell(15).setCellValue(creditLoan.customerInfo.getCompanyName());
        row.createCell(16).setCellValue(creditLoan.customerInfo.getCompanyIndustry());
        row.createCell(17).setCellValue(creditLoan.customerInfo.getPosition());
        row.createCell(18).setCellValue(creditLoan.customerInfo.getMonthlyIncome());
        row.createCell(19).setCellValue(creditLoan.customerInfo.getCompanyProvince());
        row.createCell(20).setCellValue(creditLoan.customerInfo.getCompanyCity());
        row.createCell(21).setCellValue(creditLoan.customerInfo.getCompanyDistrict());
        row.createCell(22).setCellValue(creditLoan.customerInfo.getCompanyAddress());
        row.createCell(23).setCellValue(creditLoan.customerInfo.getMaritalStatus());
        row.createCell(24).setCellValue(creditLoan.customerInfo.getEmail());
        row.createCell(25).setCellValue(creditLoan.customerInfo.getCustomerId());
    }

    private static int fillEmergencyContactsSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充紧急联系人信息
        List<EmergencyContacts> emergencyContacts = creditLoan.emergencyContactsList;
        if (!StringHandler.isEmpty(emergencyContacts)) {
            int num = emergencyContacts.size();
            for (int i = 0; i < num; i++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum); // 如果该行为空，创建新行
                }
                EmergencyContacts emergencyContact = emergencyContacts.get(i);
                row.createCell(0).setCellValue(creditLoan.OrderNo);
                row.createCell(1).setCellValue(emergencyContact.getRelationship());
                row.createCell(2).setCellValue(emergencyContact.getName());
                row.createCell(3).setCellValue(emergencyContact.getPhoneNumber());
                rowNum++;
            }
            return rowNum;
        } else {
            return 0;
        }
    }

    private static int fillRepaymentPlanSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        List<RepaymentInfo> repaymentInfoList = creditLoan.repaymentInfoList;
        if (!StringHandler.isEmpty(repaymentInfoList)) {
            for (RepaymentInfo repaymentInfo : repaymentInfoList) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum); // 如果该行为空，创建新行
                }
                row.createCell(0).setCellValue(creditLoan.OrderNo);
                row.createCell(1).setCellValue(repaymentInfo.getPeriod());
                row.createCell(2).setCellValue(repaymentInfo.getDueDate());
                row.createCell(3).setCellValue(repaymentInfo.getStatus());
                row.createCell(4).setCellValue(repaymentInfo.getDueAmount() * 100);
                row.createCell(5).setCellValue(repaymentInfo.getDuePrincipal() * 100);
                row.createCell(6).setCellValue(repaymentInfo.getDueInterest() * 100);
                row.createCell(7).setCellValue(repaymentInfo.getDueServiceFee() * 100);
                row.createCell(8).setCellValue(repaymentInfo.getDueOverdueInterest() * 100);
                row.createCell(9).setCellValue(repaymentInfo.getActualRepaymentDate());
                row.createCell(10).setCellValue(repaymentInfo.getActualRepaymentAmount() * 100);
                row.createCell(11).setCellValue(repaymentInfo.getActualRepaymentPrincipal() * 100);
                row.createCell(12).setCellValue(repaymentInfo.getActualRepaymentInterest() * 100);
                row.createCell(13).setCellValue(repaymentInfo.getActualRepaymentServiceFee() * 100);
                row.createCell(14).setCellValue(repaymentInfo.getActualRepaymentOverdueInterest() * 100);
                rowNum++;
            }
            return rowNum;
        }
        return 0;
    }

    private static int fillRepaymentRecordSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充还款记录
        List<RepaymentRecord> repaymentRecordList = creditLoan.repaymentRecordList;
        if (!StringHandler.isEmpty(repaymentRecordList)) {
            int num = repaymentRecordList.size();
            for (int i = 0; i < num; i++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum); // 如果该行为空，创建新行
                }
                RepaymentRecord repaymentRecord = repaymentRecordList.get(i);
                row.createCell(0).setCellValue(creditLoan.OrderNo);
                row.createCell(1).setCellValue(repaymentRecord.getRepaymentType());
                row.createCell(2).setCellValue(repaymentRecord.getBusinessOrderNo());
                row.createCell(3).setCellValue(repaymentRecord.getPeriod());
                row.createCell(4).setCellValue(repaymentRecord.getRepaymentAmount());
                row.createCell(5).setCellValue(repaymentRecord.getRepaymentDate());
                rowNum++;
            }
            return rowNum;
        }
        return 0;
    }

    private static int fillTransactionRecordSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充流水记录
        List<TransactionRecord> transactionRecordList = creditLoan.transactionRecordList;
        if (!StringHandler.isEmpty(transactionRecordList)) {
            int num = transactionRecordList.size();
            for (int i = 0; i < num; i++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum); // 如果该行为空，创建新行
                }
                TransactionRecord transactionRecord = transactionRecordList.get(i);
                row.createCell(0).setCellValue(transactionRecord.getPeriod());
                row.createCell(1).setCellValue(transactionRecord.getTransactionDate());
                row.createCell(2).setCellValue(transactionRecord.getTransactionAmount());
                row.createCell(3).setCellValue(transactionRecord.getTransactionType());
                row.createCell(4).setCellValue(transactionRecord.getRemark());
                rowNum++;
            }
            return rowNum;
        }
        return 0;
    }

    private static int fillContractInfoSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充合同信息
        List<ContractInfo> contractInfoList = creditLoan.contractInfoList;
        if (!StringHandler.isEmpty(contractInfoList)) {
            int num = contractInfoList.size();
            for (int i = 0; i < num; i++) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum); // 如果该行为空，创建新行
                }
                ContractInfo contractInfo = contractInfoList.get(i);
                row.createCell(0).setCellValue(creditLoan.OrderNo);
                row.createCell(1).setCellValue(contractInfo.getSerialNo());
                row.createCell(3).setCellValue(contractInfo.getContractNo());
                row.createCell(2).setCellValue(contractInfo.getContractType());
                row.createCell(4).setCellValue(contractInfo.getSignType());
                row.createCell(5).setCellValue(contractInfo.getSignatoryName());
                row.createCell(6).setCellValue(contractInfo.getSignatoryIdCardNo());
                row.createCell(7).setCellValue(contractInfo.getSignDate());
                rowNum++;
            }
            return rowNum;
        }
        return 0;
    }

    private static int fillBankCardInfoSheet(Sheet sheet, CreditLoan creditLoan, int rowNum) {
        // 填充扣款卡信息
        List<BankCardInfo> bankCardInfoList = creditLoan.bankCardInfoList;
        if (!StringHandler.isEmpty(bankCardInfoList)) {
            for (BankCardInfo bankCardInfo : bankCardInfoList) {
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    row = sheet.createRow(rowNum); // 如果该行为空，创建新行
                }
                row.createCell(0).setCellValue(creditLoan.OrderNo);
                row.createCell(1).setCellValue(bankCardInfo.getReservedPhoneNumber());
                row.createCell(2).setCellValue(bankCardInfo.getBankCardNumber());
                row.createCell(3).setCellValue(bankCardInfo.getBindingDate());
                row.createCell(4).setCellValue(bankCardInfo.getBankName());
                rowNum++;
            }
            return rowNum;
        } else {
            return 0;
        }
    }

    public static void createLoanExcel(int num) {
        if (num < 0) {
            System.out.println("数量不能小于0");
        } else {
            List<CreditLoan> creditLoanList = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                String orderNo = GenerationHandler.generateOrderNo() + i;
                CreditLoan creditLoan = new CreditLoan(orderNo);
                creditLoanList.add(creditLoan);
            }
            exportCreditLoanToExcel(creditLoanList, "CreditLoanAssetTemplate");
            System.out.println("数据生成完成，一共生成：" + creditLoanList.size() + "个案件");
        }
    }

    public static void main(String[] args) {
        createLoanExcel(5);
    }

}
