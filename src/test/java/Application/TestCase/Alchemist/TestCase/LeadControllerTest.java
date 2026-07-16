package Application.TestCase.Alchemist.TestCase;


import Data.ApisLayer.Alchemist.LeadController;
import Data.Ro.Alchemist.LeadControllerCreateShortLinkRo;
import Data.Ro.Alchemist.getApplicationStatuesRo;
import Service.Core.BaseTestCase;
import Service.Core.TestScenarioEnum;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LeadControllerTest extends BaseTestCase {

    LeadController leadController = new LeadController();

    @Test(dataProvider = "getApplicationStatues_testData")
    public void testGetApplicationStatues(String beforeReady, String testData, String targetValueRule, String expectedValueRule, String afterDo) {
        String result = executeTest(beforeReady, testData, targetValueRule, expectedValueRule, getApplicationStatuesRo.class, leadController);
        assertEq(result);
        afterDo(afterDo);
    }

    @Test(dataProvider = "createShortLink_testData", enabled = false)
    public void testCreateShortLink(String beforeReady, String testData, String targetValueRule, String expectedValueRule, String afterDo) {
        String result = executeTest(beforeReady, testData, targetValueRule, expectedValueRule, LeadControllerCreateShortLinkRo.class, leadController);
        assertEq(result);
        afterDo(afterDo);
    }

    /*@Test(dataProvider = "getApplicationStatues_testData")
    public void getApplicationStatues(String beforeReady, String testData, String targetValueRule, String expectedValueRule) {
        String requestResult = leadController.convert2RoAndRequest(beforeDo(beforeReady, testData), getApplicationStatuesRo.class);
        String targetValue = StringHandler.getRuleValue(requestResult, targetValueRule);
        String expectedValue = StringHandler.getRuleValue(requestResult, expectedValueRule);
        assertEq(targetValue, expectedValue);
    }

    @Test(dataProvider = "createShortLink_testData", enabled = false)
    public void createShortLink(String beforeReady, String testData, String targetValueRule, String expectedValueRule) {
        String requestResult = leadController.convert2RoAndRequest(beforeDo(beforeReady, testData), LeadControllerCreateShortLinkRo.class);
        String targetValue = StringHandler.getRuleValue(requestResult, targetValueRule);
        String expectedValue = StringHandler.getRuleValue(requestResult, expectedValueRule);
        assertEq(targetValue, expectedValue);
    }*/

    @DataProvider
    public static Object[][] getApplicationStatues_testData() {
        return getTestData(TestScenarioEnum.Single, "getApplicationStatues_testData");
    }

    @DataProvider
    public static Object[][] createShortLink_testData() {
        return getTestData(TestScenarioEnum.Single, "createShortLink_testData");
    }

}



