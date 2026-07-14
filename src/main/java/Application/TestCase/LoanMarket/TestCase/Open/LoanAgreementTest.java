package Application.TestCase.LoanMarket.TestCase.Open;

import Application.DataFactory.LoanMarket.OpenApiScenario;
import Data.ApisLayer.LoanMarket.Open;
import Data.BusinessLayer.LoanMarket.QueryProtocol;
import Data.Ro.LoanMarket.OpenAgreementServiceRo;
import Service.Core.BaseTestCase;
import Service.Util.Universal.JsonHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * 资方协议查询测试
 */
public class LoanAgreementTest extends BaseTestCase {
    static Open open = new Open();
   // static LoanOrderQuery loanOrderQuery = new LoanOrderQuery();

    @DataProvider(name = "partnerCodes")
    public Object[][] partnerCodes() {
        return new Object[][]{
               // {"ZHONGXINHUA_HALF"},
                {"NIWODAI"}
                /*{"RENPINQIANBAO"},
                {"KAKADAI_HALF"},
                {"YOUJIE_HALF"}*/
        };
    }

    @Test(testName = "授信协议查询", dataProvider = "partnerCodes")
    public void getCreditAgreement(String partnerCode) {
        System.out.println("资方："+partnerCode);
        //1、根据渠道获取撞库成功的用户
        //2、授信协议查询
        QueryProtocol.openApiQueryProtocol(new OpenAgreementServiceRo("","","1",""));
        OpenApiScenario.LoanProcessContext loanProcessContext = OpenApiScenario.processByPartner("FENQILE", OpenApiScenario.ProcessStage.CREDIT_APPLY, partnerCode);
        String result = open.agreementService(new OpenAgreementServiceRo(loanProcessContext.getChannelCode(), loanProcessContext.getCreditOrderNo(), "1", ""));
        System.out.println("获取协议结果："+ JsonHandler.readStringByJsonPath("$.data",result));
    }

    @Test(testName = "绑卡协议查询", enabled = false, dataProvider = "partnerCodes")
    public void getBindAgreement(String partnerCode) {
        OpenApiScenario.LoanProcessContext loanProcessContext = OpenApiScenario.processByPartner("FENQILE", OpenApiScenario.ProcessStage.BIND_CARD, partnerCode);
        String result = open.agreementService(new OpenAgreementServiceRo(loanProcessContext.getChannelCode(), loanProcessContext.getCreditOrderNo(), "2", ""));
        System.out.println("授信结果："+ JsonHandler.readStringByJsonPath("$.data",result));
    }

    @Test(testName = "借款协议查询", enabled = false, dataProvider = "partnerCodes")
    public void getLoanAgreement(String partnerCode) {
        OpenApiScenario.LoanProcessContext loanProcessContext = OpenApiScenario.processByPartner("FENQILE", OpenApiScenario.ProcessStage.LOAN_APPLY, partnerCode);
        String result = open.agreementService(new OpenAgreementServiceRo(loanProcessContext.getChannelCode(), loanProcessContext.getCreditOrderNo(), "3", loanProcessContext.getLoanOrderNo()));
        System.out.println("授信结果："+ JsonHandler.readStringByJsonPath("$.data",result));
    }

    @Test(testName = "还款协议查询", enabled = false, dataProvider = "partnerCodes")
    public void getRepayAgreement(String partnerCode) {
        OpenApiScenario.LoanProcessContext loanProcessContext = OpenApiScenario.processByPartner("FENQILE", OpenApiScenario.ProcessStage.LOAN_REPAY, partnerCode);
        String result = open.agreementService(new OpenAgreementServiceRo(loanProcessContext.getChannelCode(), loanProcessContext.getCreditOrderNo(), "4", loanProcessContext.getLoanOrderNo()));
        System.out.println("授信结果："+ JsonHandler.readStringByJsonPath("$.data",result));
    }




}
