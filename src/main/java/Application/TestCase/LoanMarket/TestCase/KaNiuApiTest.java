package Application.TestCase.LoanMarket.TestCase;

import Data.ApisLayer.LoanMarket.TestKaniuController;
import Data.Ro.LoanMarket.TestKaniuControllerKaNiuUserCheckServiceRo;
import Data.Ro.LoanMarket.TestKaniuControllerKaNiuUserCreditServiceRo;
import Service.Core.ProjectEnum;
import Service.Util.Universal.DateHandler;
import Service.Util.Universal.JDBCHandler;
import Service.Util.Universal.JsonHandler;
import Service.Util.variable.ChinneNameHandler;
import Service.Util.variable.GenerationHandler;
import Service.Util.variable.IDNoGeneratorHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;

public class KaNiuApiTest {
    TestKaniuController testKaniuController = new TestKaniuController();

    public void testIncoming() {
        String name = ChinneNameHandler.generateName();
        String phone = PhoneNumberGeneratorHandler.generatePhoneNumber();
        String idNo = IDNoGeneratorHandler.generateIDCard(22, 50);
        String userId = "KN" + GenerationHandler.generateRandomInteger(5, 7);
        String orderId = "KN" + DateHandler.getTodayYMDString() + GenerationHandler.generateRandomInteger(3, 5);
        String loanId = "KN" + DateHandler.getTimestamp();
        // 撞库
        String checkResult = testKaniuController.kaNiuUserCheckService(TestKaniuControllerKaNiuUserCheckServiceRo.changeRequestRo(phone, idNo, name));
        int a = (int) JsonHandler.readValueByJsonPath("$.checkLoan", checkResult);
        // a =1 撞库通过
        if (a == 1) {
            // 授信
            String creditResult = testKaniuController.kaNiuUserCreditService(new TestKaniuControllerKaNiuUserCreditServiceRo(name, phone, idNo, orderId, userId));
            try {
                Thread.sleep(10000);
                String sql = "select t1.status  from tbl_credit_assessment_detail t1 join tbl_user tu on t1.uid =tu.id where tu.mobile  =?;";
                String statusJson = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, phone);
                System.out.println("路由状态：" + JsonHandler.readValueByJsonPath("$.status",statusJson));
               /* // 提现
                String LoanConfirmUrl =testKaniuController.KaNiuLoanConfirmService(new TestKaniuControllerKaNiuLoanConfirmServiceRo(userId, orderId, loanId));
                System.out.println("提现url："+LoanConfirmUrl);*/
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            /*if ("true".equals(JsonHandler.readValueByJsonPath("$.success", creditResult))) {
                boolean flag = false;
                while (flag == false) {
                    try {
                        Thread.sleep(5000);
                        String sql = "select t1.status  from tbl_credit_assessment_detail t1 join tbl_user tu on t1.uid =tu.id where tu.mobile  =?;";
                        String statusJson = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, phone);
                        System.out.println("statusJson" + statusJson);
                        if (statusJson.equals("SUCCEED")) {
                            // 提现
                            String LoanConfirmUrl =testKaniuController.KaNiuLoanConfirmService(new TestKaniuControllerKaNiuLoanConfirmServiceRo(userId, orderId, loanId));
                            System.out.println("提现url："+LoanConfirmUrl);
                            break;
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }*/
        } else {
            System.out.println("撞库不通过");
        }
        System.out.println("手机号：" + phone);
        System.out.println("\"userId\":\"" + userId+"\",");
        System.out.println("\"orderId\":\"" + orderId+"\",");
        System.out.println("\"loanId\":\"" + loanId+"\",");
    }

    public static void main(String[] args) {
        KaNiuApiTest kaNiuApiTest = new KaNiuApiTest();
        kaNiuApiTest.testIncoming();
    }

}
