package Application.TestCase.LoanMarket.TestCase.Zy;

import Data.Apis.LoanMarket.UserController;
import Data.Ro.LoanMarket.UserControllerLoginRo;
import Service.Base.BaseTestCase2;
import Service.Util.Universal.JsonHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 信用家H5页面用户登录接口测试
 */
public class User_LoginTest extends BaseTestCase2 {
    private final UserController userController = new UserController();

    /**
     * 合并用例：注册相关正反用例，参数化测试
     */
    @Test(dataProvider = "loginDataProvider", description = "注册相关正反用例，参数化测试")
    public void testLoginCases(String phone, String channel, String verifyCode, Integer expectResult, String casePoint) {
        String result = userController.login(new UserControllerLoginRo(phone, channel, verifyCode));
        Integer code = JsonHandler.readIntegerByJsonPath("$.code", result);
        System.out.println(casePoint);
        BaseTestCase2.assertEq(code, expectResult);
    }

    @DataProvider(name = "loginDataProvider")
    public Object[][] loginDataProvider() {
        return new Object[][]{
                // 正向用例：自营渠道+新手机号+正确验证码，注册成功
                {PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(), "luandaoTest", "888888", 0, "自营渠道新手机号注册成功"},
                // 正向用例：非自营渠道+新手机号+正确验证码，注册成功
                {PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(), "KN-MC", "888888", 0, "非自营渠道新手机号注册成功"},
                // 反向用例：未配置渠道+新手机号+正确验证码，注册失败
                {PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(), "NOT_EXIST", "888888", 1, "未配置渠道应注册失败"},
                // 反向用例：手机号为空，注册失败
                {"", "luandaoTest", "888888", 1, "手机号为空应注册失败"},
                // 反向用例：验证码为空，注册失败
                {PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(), "luandaoTest", "", 1, "验证码为空应注册失败"},
                // 边界用例：手机号长度不足，注册失败
                {"12345", "luandaoTest", "888888", 1, "手机号长度不足应注册失败"},
                // 边界用例：验证码长度不足，注册失败
                {PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(), "luandaoTest", "12", 1, "验证码长度不足应注册失败"},
        };
    }

    /**
     * 特殊流程：自营注册后用同手机号在非自营注册，生成新用户id
     */
    @Test(description = "自营注册后用同手机号在非自营注册，生成新用户id")
    public void testLogin_SelfThenNonSelf_NewUid() {
        String phone = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode();
        String channelSelf = "luandaoTest";
        String channelNonSelf = "KN-MC";
        // 先自营注册
        String resultSelf = userController.login(new UserControllerLoginRo(phone, channelSelf));
        Integer codeSelf = JsonHandler.readIntegerByJsonPath("$.code", resultSelf);
        Assert.assertEquals(codeSelf, Integer.valueOf(0), "自营注册应成功");
        Object uidSelf = JsonHandler.readValueByJsonPath("$.data.id", resultSelf);
        // 在非自营注册
        String resultNonSelf = userController.login(new UserControllerLoginRo(phone, channelNonSelf));
        Integer codeNonSelf = JsonHandler.readIntegerByJsonPath("$.code", resultNonSelf);
        Assert.assertEquals(codeNonSelf, Integer.valueOf(0), "非自营注册应成功");
        Object uidNonSelf = JsonHandler.readValueByJsonPath("$.data.id", resultNonSelf);
        Assert.assertNotEquals(uidSelf, uidNonSelf, "自营和非自营注册应生成不同用户id");
    }

} 