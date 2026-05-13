package Application.TestCase.LoanMarket.TestCase.Open;

import Application.DataFactory.LoanMarket.OpenApiScenario;
import org.testng.annotations.Test;

/**
 * 上游标准对接接口，还款测试
 */
public class OpenRepaymentTest {

    @Test(testName = "当期未逾期还款")
    public void test1() {

    }

    @Test(testName = "当期逾期还款")
    public void test2() {

    }

    @Test(testName = "连续两期未逾期还款")
    public void test3() {

    }

    @Test(testName = "连续两期逾期还款")
    public void test4() {

    }

    @Test(testName = "提前结清还款成功")
    public void test5() {
        OpenApiScenario.loanRepay("840930", OpenApiScenario.OpenRepayTypeEnum.EARLY, "12");
    }


    @Test(testName = "提前结清还款失败", enabled = false)
    public void test6() {

    }
}
