package Application.TestCase.LoanMarket.TestCase.Zy;

import Data.DBoperation.LoanMarket.CreditInfo;
import Application.DataFactory.LoanMarket.ChannelRoutingConfiguration;
import Application.DataFactory.LoanMarket.ProductCodeGroup;
import Application.DataFactory.LoanMarket.UserInfoAddDataFactory;
import Application.DataFactory.LoanMarket.XYJH5User;
import Data.ApisLayer.LoanMarket.AssessmentController;
import Data.ApisLayer.LoanMarket.ContactsController;
import Data.ApisLayer.LoanMarket.UserInfoController;
import Data.Ro.LoanMarket.AssessmentControllerInitRo;
import Data.Ro.LoanMarket.AssessmentControllerStatusRo;
import Data.Ro.LoanMarket.UserInfoControllerAddRo;
import Service.Core.BaseTestCase;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.PollingHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static Data.Ro.LoanMarket.ContactsControllerSaveContactsRo.generateContactsList;

/**
 * 授信测试
 */
public class Loan_CreditTest extends BaseTestCase {

    // 默认渠道
    public static final String DEFAULT_CHANNEL_CODE = "luandaoTest2";
    // 默认全流程API资方（用于单资方数据提供者）
    public static final String DEFAULT_API_PRODUCT_CODE = "NIWODAI";
    // 默认半流程API资方（用于单资方数据提供者）
    public static final String DEFAULT_HALF_PRODUCT_CODE = "RONGAN_HALF";
    // 默认全流程API+H5资方（用于单资方数据提供者）
    public static final String DEFAULT_API_H5_PRODUCT_CODE = "LONGSHANJIE_HALF";


    @Test(testName = "授信成功-全流程api模式", dataProvider = "DP_UserInfo_Default_Api_Capital",enabled = false)
    public static void TC_Credit_01(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
        String creditResult = CreditInfo.getNewestCreditInfo(ro.getX_Login_Uid());
        BaseTestCase.assertContains(JsonHandler.readStringByJsonPath("$.*.status", creditResult), "PASSED");
    }

    @Test(testName = "授信成功-半流程模式", dataProvider = "DP_UserInfo_Default_Half_Capital",enabled = false)
    public static void TC_Credit_02(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
        String creditResult = CreditInfo.getNewestCreditInfo(ro.getX_Login_Uid());
        BaseTestCase.assertContains(JsonHandler.readStringByJsonPath("$.*.status", creditResult), "PASSED");
    }

    @Test(testName = "授信成功-全流程api+H5模式", dataProvider = "DP_UserInfo_Default_api_h5_Capital")
    public static void TC_Credit_03(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
        String creditResult = CreditInfo.getNewestCreditInfo(ro.getX_Login_Uid());
        BaseTestCase.assertContains(JsonHandler.readStringByJsonPath("$.*.status", creditResult), "PASSED");
    }

    @Test(testName = "授信失败-全流程api模式", dataProvider = "DP_UserInfo_Default_Api_Capital", enabled = false)
    public static void TC_Credit_04(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-半流程模式", dataProvider = "DP_UserInfo_Default_Half_Capital", enabled = false)
    public static void TC_Credit_05(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-全流程api+H5模式", dataProvider = "DP_UserInfo_Default_api_h5_Capital", enabled = false)
    public static void TC_Credit_06(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-前筛不通过-全流程api模式", dataProvider = "DP_UserInfo_Default_Api_Capital", enabled = false)
    public static void TC_Credit_07(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-前筛不通过-半流程模式", dataProvider = "DP_UserInfo_Default_Half_Capital", enabled = false)
    public static void TC_Credit_08(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-前筛不通过-全流程api+H5模式", dataProvider = "DP_UserInfo_Default_api_h5_Capital", enabled = false)
    public static void TC_Credit_09(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-撞库失败-全流程api模式", dataProvider = "DP_UserInfo_Default_Api_Capital", enabled = false)
    public static void TC_Credit_10(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-撞库失败-半流程模式", dataProvider = "DP_UserInfo_Default_Half_Capital", enabled = false)
    public static void TC_Credit_11(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }

    @Test(testName = "授信失败-撞库失败-全流程api+H5模式", dataProvider = "DP_UserInfo_Default_api_h5_Capital", enabled = false)
    public static void TC_Credit_12(String productCode, UserInfoControllerAddRo ro) {
        authorizeCreditForUser(productCode, ro);
    }


    /**
     * 用户信息多样性数据：
     * - 每个对象覆盖一个省（从/enum/china-area读取），并轮转覆盖婚姻、学历、薪资、行业、职业
     * - 支持多个资方：NIWODAI、RENPINQIANBAO、LIHONG、LEXIANGJIE
     * - 每条数据已包含资方与用户信息维度组合
     */
    @DataProvider(name = "DP_UserInfo_All")
    private Object[][] DP_UserInfo_All() {
        return buildUserInfoDataProvider(ProductCodeGroup.ALL.getProductCodes());
    }

    /**
     * 用户信息多样性数据（半流程资方）：
     * - 生成与 DP_UserInfo_All 相同的维度组合
     * - 仅资方集合改为 HALF_PRODUCT_CODES
     */
    @DataProvider(name = "DP_UserInfo_Half")
    private Object[][] DP_UserInfo_Half() {
        return buildUserInfoDataProvider(ProductCodeGroup.HALF.getProductCodes());
    }

    /**
     * 用户信息多样性数据（API+H5资方）：
     * - 生成与 DP_UserInfo_All 相同的维度组合
     * - 仅资方集合改为 API_H5_PRODUCT_CODES
     */
    @DataProvider(name = "DP_UserInfo_ApiH5")
    private Object[][] DP_UserInfo_ApiH5() {
        return buildUserInfoDataProvider(ProductCodeGroup.API_H5.getProductCodes());
    }

    /**
     * 单资方（全流程）的数据提供者：
     */
    @DataProvider(name = "DP_UserInfo_Default_Api_Capital")
    private Object[][] DP_UserInfo_Default_Api_Capital() {
        return buildUserInfoPairs(DEFAULT_API_PRODUCT_CODE);
    }

    /**
     * 单资方（半流程）的数据提供者：
     */
    @DataProvider(name = "DP_UserInfo_Default_Half_Capital")
    private Object[][] DP_UserInfo_Default_Half_Capital() {
        return buildUserInfoPairs(DEFAULT_HALF_PRODUCT_CODE);
    }

    /**
     * 单资方（API+H5模式）的数据提供者：
     */
    @DataProvider(name = "DP_UserInfo_Default_api_h5_Capital")
    private Object[][] DP_UserInfo_Default_api_h5_Capital() {
        return buildUserInfoPairs(DEFAULT_API_H5_PRODUCT_CODE);
    }

    /**
     * 为指定资方构建只有一个ro的二维数组
     */
    private Object[][] buildUserInfoPairs(String productCode) {
        UserInfoControllerAddRo ro = UserInfoAddDataFactory.buildRandomUserInfo();
        Object[][] data = new Object[][]{
                {productCode, ro}};
        return data;
    }

    private Object[][] buildUserInfoDataProvider(String[] productCodes) {
        List<UserInfoControllerAddRo> ros = UserInfoAddDataFactory.buildOrthogonalUserInfos();
        int totalSize = ros.size() * productCodes.length;
        Object[][] data = new Object[totalSize][];

        int dataIndex = 0;
        for (int i = 0; i < ros.size(); i++) {
            UserInfoControllerAddRo ro = ros.get(i);
            for (String productCode : productCodes) {
                data[dataIndex] = new Object[]{productCode, ro};
                dataIndex++;
            }
        }
        return data;
    }

    // 联系人接口
    public static ContactsController contactsController = new ContactsController();
    // 用户信息接口
    public static UserInfoController userInfoController = new UserInfoController();
    // 授信接口
    public static AssessmentController assessmentController = new AssessmentController();

    /**
     * 保存用户信息，并且路由授信productCode资方
     *
     * @param productCode 资方
     * @param ro          用户信息请求对象
     */
    private static void authorizeCreditForUser(String productCode, UserInfoControllerAddRo ro) {
        ChannelRoutingConfiguration.changeCapital(DEFAULT_CHANNEL_CODE, productCode);
        String uid = XYJH5User.getUserIdWithoutUserInfoByProductCode(DEFAULT_CHANNEL_CODE, productCode, true);
        ro.setX_Login_Uid(uid);
        // 保存用户信息
        userInfoController.add(ro);
        // 保存用户联系人信息
        contactsController.saveContacts(generateContactsList());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 发起授信
        String applyResult = assessmentController.init(new AssessmentControllerInitRo(uid));
        // 轮询
        PollingHandler.pollUntilSuccess(() -> assessmentController.status(new AssessmentControllerStatusRo(JsonHandler.readIntegerByJsonPath("$.data.id", applyResult))), res -> {
            try {
                String status = JsonHandler.readStringByJsonPath("$.data.status", res);
                if ("RUNNING".equalsIgnoreCase(status)) {
                    System.out.println("授信中...");
                }
                if ("SUCCEED".equalsIgnoreCase(status)) {
                    System.out.println("授信成功：" + res);
                }
                if ("FAILED".equalsIgnoreCase(status)) {
                    System.out.println("授信失败：" + res);
                }
                return status.equalsIgnoreCase("SUCCEED") || status.equalsIgnoreCase("FAILED"); // 成功或失败都停止轮询
            } catch (Exception e) {
                System.err.println("解析授信状态异常: " + e.getMessage());
                return false;
            }
        }, 30, 2000, "queryAssessmentStatus");
    }


}
