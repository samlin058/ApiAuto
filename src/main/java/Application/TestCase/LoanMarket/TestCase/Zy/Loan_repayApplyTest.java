package Application.TestCase.LoanMarket.TestCase.Zy;

import Application.DataFactory.LoanMarket.H5ProcessContext;
import Application.DataFactory.LoanMarket.XYJH5Process;
import Data.ApisLayer.LoanMarket.RepayPlanController;
import Data.Ro.LoanMarket.RepayPlanControllerPaymentRo;
import Service.Core.BaseTestCase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 还款测试 - 数据驱动优化版本
 */
public class Loan_repayApplyTest extends BaseTestCase {
    static RepayPlanController repayPlanController = new RepayPlanController();

    // 产品代码配置，方便维护和扩展
    private static final String[] PRODUCT_CODES = {"NIWODAI", "HALUO"};

    /**
     * 统一的还款测试方法 - 使用数据驱动
     *
     * @param scenarioName 测试场景名称
     * @param product      产品代码
     * @param periods      还款期数
     * @param settleAll    是否提前结清
     */
    @Test(dataProvider = "DP_RepayApply", enabled = false)
    public static void TC_RepayApply(String scenarioName, String product, Integer periods,  Boolean settleAll) {
        // 执行H5流程到放款完成
        H5ProcessContext context = XYJH5Process.H5processByPartner(product, XYJH5Process.H5ProcessStage.LOAN_APPLY);
        // 构建还款请求对象
        RepayPlanControllerPaymentRo ro = new RepayPlanControllerPaymentRo();
        ro.setCardId(parseInt(context.getCardId()));
        ro.setOrderId(context.getOrderId());
        ro.setPeriods(periods);
        ro.setSettleAll(settleAll);
//        repayPlanController.planCalculate(new RepayPlanControllerPlanCalculateRo());
        // 执行还款
        repayPlanController.payment(ro);
    }

    /**
     * 数据提供者 - 定义所有测试场景
     */
    @DataProvider(name = "DP_RepayApply")
    private Object[][] DP_RepayApply() {
        List<Object[]> testData = new ArrayList<>();

        // 定义测试场景：场景名称、还款期数、是否提前结清
        Object[][] scenarios = {
                {"逾期一期，还款一期——还款成功", 1, false},
                {"逾期多期，还款多期——还款成功", 3, false},
                {"逾期多期，提前结清——还款成功", null, true},
                {"未逾期，还款一期——还款成功", 1, false},
                {"未逾期，还款多期——还款成功", 3, false},
                {"未逾期，提前结清——还款成功", null, true}
        };

        // 为每个产品和每个场景生成测试数据
        for (String productCode : PRODUCT_CODES) {
            for (Object[] scenario : scenarios) {
                String scenarioName = (String) scenario[0];
                Integer periods = (Integer) scenario[1];
                Boolean settleAll = (Boolean) scenario[2];
                testData.add(new Object[]{scenarioName, productCode, periods, settleAll});
            }
        }

        return testData.toArray(new Object[0][]);
    }

    /**
     * 字符串转整数
     */
    private static int parseInt(String number) {
        return Integer.parseInt(number);
    }
}
