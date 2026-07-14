package Application.TestCase.LoanMarket.TestCase;

import Data.DBoperation.LoanMarket.CreditInfo;
import Data.DBoperation.LoanMarket.Partner;
import Application.DataFactory.LoanMarket.H5ProcessContext;
import Application.DataFactory.LoanMarket.XYJH5Process;
import Data.ApisLayer.LoanMarket.AgreementController;
import Data.Ro.LoanMarket.AgreementControllerGetRo;
import Service.Core.BaseTestCase;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class HalfCapitalTest extends BaseTestCase {
    @DataProvider(name = "partnerData")
    private Object[][] partnerData() {
        return new Object[][]{
                {"ZHIJIE_HALF", "", ""}
        };
    }

    @Test(testName = "撞库失败", dataProvider = "partnerData")
    public static void test_01(String partnerCode, String protocol, String phone) {
        H5ProcessContext context;
        if (StringHandler.isEmpty(phone)) {
            context = XYJH5Process.H5processByPartner(partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
        } else {
            context = XYJH5Process.H5processByPartner(phone, "", partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
        }
        String checkResult = Partner.getNewestCheckInfo(context.getUid());
        String checkStatus = JsonHandler.readStringByJsonPath("$.*.result", checkResult);
        BaseTestCase.assertContains(checkStatus, "REJECT");
    }

    @Test(testName = "撞库通过，授信失败", dataProvider = "partnerData",enabled = false)
    public static void test_02(String partnerCode, String protocol, String phone) {
        H5ProcessContext context = XYJH5Process.H5processByPartner(partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
        String checkResult = Partner.getNewestCheckInfo(context.getUid());
        String checkStatus = JsonHandler.readStringByJsonPath("$.*.result", checkResult);
        String creditResult = CreditInfo.getNewestCreditInfo(context.getUid());
        String creditStatus = JsonHandler.readStringByJsonPath("$.*.status", creditResult);
        Boolean flag = checkStatus.equalsIgnoreCase("PASS") && creditStatus.equalsIgnoreCase("REJECT");
        BaseTestCase.assertEq(flag, true);
    }

    @Test(testName = "准入通过，授信通过", dataProvider = "partnerData", enabled = false)
    public static void test_03(String partnerCode, String protocol, String phone) {
        H5ProcessContext context = XYJH5Process.H5processByPartner(partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
        String checkResult = Partner.getNewestCheckInfo(context.getUid());
        System.out.println(checkResult);
        String checkStatus = JsonHandler.readStringByJsonPath("$.*.result", checkResult);
        System.out.println("撞库结果：" + checkStatus);
        String creditResult = CreditInfo.getNewestCreditInfo(context.getUid());
        String creditStatus = JsonHandler.readStringByJsonPath("$.*.status", creditResult);
        System.out.println("授信结果：" + creditStatus);
        Boolean flag = checkStatus.contains("PASS") && creditStatus.contains("PASSED");
        BaseTestCase.assertEq(flag, true);
    }

    @Test(testName = "查看授信协议", dataProvider = "partnerData", enabled = false)
    public static void test_04(String partnerCode, String protocol, String phone) {
        H5ProcessContext context = XYJH5Process.H5processByPartner(partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
        AgreementController agreementController = new AgreementController();
        String requestResult = agreementController.get(new AgreementControllerGetRo(context.getUid(), partnerCode));
        BaseTestCase.assertContains(requestResult, protocol);
    }



}
