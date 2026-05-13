package Application.TestCase.LoanMarket.TestCase.Zy;

import Application.DataFactory.LoanMarket.H5ProcessContext;
import Application.DataFactory.LoanMarket.XYJH5Process;
import Data.Apis.LoanMarket.RepayPlanController;
import Data.Ro.LoanMarket.RepayPlanControllerPlanCalculateRo;
import Service.Base.BaseTestCase2;
import Service.Util.Universal.IntegerHandler;
import Service.Util.Universal.StringHandler;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

/**
 * 借款试算测试
 */
public class Loan_CalculationTest extends BaseTestCase2 {
    private static final RepayPlanController repayPlanController = new RepayPlanController();

    // 正向用例
    @Test(testName = "有效最小金额+最小期数进行试算：试算成功", dataProvider = "TC_Calculation")
    public void TC_Calculation_001(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int minPeriod = getMinInteger(getPeriods(context));
        planCalculate(context, partnerCode, minPeriod, parseInt(context.getMinBorrowingAmt()));
    }

    @Test(testName = "有效最大金额+最大期数进行试算：试算成功", dataProvider = "TC_Calculation")
    public void TC_Calculation_002(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int maxPeriod = getMaxInteger(getPeriods(context));
        planCalculate(context, partnerCode, maxPeriod, parseInt(context.getAvailableAmount()));
    }

    @Test(testName = "有效金额+期数进行试算：试算成功", dataProvider = "TC_Calculation")
    public void TC_Calculation_003(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int period = getRandomInteger(getPeriods(context));
        planCalculate(context, partnerCode, period, parseInt(context.getAvailableAmount()));
    }

    // 反向用例
    @Test(testName = "金额小于最低金额，期数正常：试算失败", dataProvider = "TC_Calculation")
    public void TC_Calculation_004(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int period = getRandomInteger(getPeriods(context));
        planCalculate(context, partnerCode, period, parseInt(context.getMinBorrowingAmt()) - 1);
    }

    @Test(testName = "金额大于上限金额，期数正常：试算失败", dataProvider = "TC_Calculation")
    public void TC_Calculation_005(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int period = getRandomInteger(getPeriods(context));
        planCalculate(context, partnerCode, period, parseInt(context.getAvailableAmount()) + 1);
    }

    @Test(testName = "金额不是100的倍数，期数正常：试算失败", dataProvider = "TC_Calculation")
    public void TC_Calculation_006(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int period = getRandomInteger(getPeriods(context));
        int loanAmt = parseInt(context.getMinBorrowingAmt()) + IntegerHandler.generateNonMultipleRandom(100, 4);
        planCalculate(context, partnerCode, period, loanAmt);
    }

    @Test(testName = "金额为负数，期数正常：试算失败", dataProvider = "TC_Calculation")
    public void TC_Calculation_007(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        int period = getRandomInteger(getPeriods(context));
        int loanAmt = -parseInt(context.getMinBorrowingAmt());
        planCalculate(context, partnerCode, period, loanAmt);
    }

    @Test(testName = "金额为0，期数正常：试算失败", dataProvider = "TC_Calculation")
    public void TC_Calculation_008(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        planCalculate(context, partnerCode, getRandomInteger(getPeriods(context)), 0);
    }

    @Test(testName = "金额正常，期数为非可选值范围内：试算失败", dataProvider = "TC_Calculation")
    public void TC_Calculation_009(String channelCode, String partnerCode) {
        H5ProcessContext context = buildContext(channelCode, partnerCode);
        planCalculate(context, partnerCode, getNoneInteger(getPeriods(context)), parseInt(context.getAvailableAmount()));
    }

    @DataProvider(name = "TC_Calculation")
    private Object[][] TC_Calculation() {
        return new Object[][]{
                {"luandaoTest2", "NIWODAI"},
                {"luandaoTest2", "HALUO"},
        };
    }


    /**
     * 从字符串数字列表中获取最大整数值
     *
     * @param periodList 字符串列表（每项应为可解析的整数）
     * @return 最大值；若列表为空或无有效数字则返回null
     */
    private static Integer getMaxInteger(List<String> periodList) {
        if (periodList == null || periodList.isEmpty()) return null;
        Integer max = null;
        for (String s : periodList) {
            if (s == null) continue;
            String v = s.trim();
            if (v.isEmpty()) continue;
            try {
                int n = Integer.parseInt(v);
                if (max == null || n > max) max = n;
            } catch (NumberFormatException ignored) {
            }
        }
        return max;
    }

    /**
     * 从字符串数字列表中获取最小整数值
     *
     * @param periodList 字符串列表（每项应为可解析的整数）
     * @return 最小值；若列表为空或无有效数字则返回null
     */
    private static Integer getMinInteger(List<String> periodList) {
        if (periodList == null || periodList.isEmpty()) return null;
        Integer min = null;
        for (String s : periodList) {
            if (s == null) continue;
            String v = s.trim();
            if (v.isEmpty()) continue;
            try {
                int n = Integer.parseInt(v);
                if (min == null || n < min) min = n;
            } catch (NumberFormatException ignored) {
            }
        }
        return min;
    }

    /**
     * 从字符串数字列表中随机获取一个整数值
     *
     * @param periodList 字符串列表（每项应为可解析的整数）
     * @return 随机值；若列表为空或无有效数字则返回null
     */
    private static Integer getRandomInteger(List<String> periodList) {
        if (periodList == null || periodList.isEmpty()) return null;

        // 先获取所有有效的整数
        List<Integer> validNumbers = new ArrayList<>();
        for (String s : periodList) {
            if (s == null) continue;
            String v = s.trim();
            if (v.isEmpty()) continue;
            try {
                int n = Integer.parseInt(v);
                validNumbers.add(n);
            } catch (NumberFormatException ignored) {
            }
        }

        if (validNumbers.isEmpty()) return null;

        // 随机选择一个数
        Random random = new Random();
        int randomIndex = random.nextInt(validNumbers.size());
        return validNumbers.get(randomIndex);
    }

    /**
     * 随机返回一个整数,要求这个整数值不在列表中
     *
     * @param periodList 字符串列表（每项应为可解析的整数）
     * @return 随机值；若列表为空或无有效数字则返回null
     */
    private static Integer getNoneInteger(List<String> periodList) {
        if (periodList == null || periodList.isEmpty()) return null;

        // 收集有效整数，并记录最小/最大值
        Set<Integer> existing = new HashSet<>();
        Integer min = null;
        Integer max = null;
        for (String s : periodList) {
            if (s == null) continue;
            String v = s.trim();
            if (v.isEmpty()) continue;
            try {
                int n = Integer.parseInt(v);
                existing.add(n);
                if (min == null || n < min) min = n;
                if (max == null || n > max) max = n;
            } catch (NumberFormatException ignored) {
            }
        }

        if (existing.isEmpty() || min == null || max == null) return null;

        // 在[max(1, min-100), max+100]范围内随机寻找一个不在集合中的正整数
        Random random = new Random();
        long lower = Math.max(1L, (long) min - 100L);
        long upper = (long) max + 100L;
        int attempts = 0;
        while (attempts < 1000) { // 尝试足够多次以获得随机性
            int candidate = (int) (lower + (long) Math.floor(random.nextDouble() * (upper - lower + 1)));
            if (candidate > 0 && !existing.contains(candidate)) {
                return candidate;
            }
            attempts++;
        }

        // 若范围非常稠密，使用确定性回退：优先返回max+1，否则min-1
        long fallbackUp = (long) max + 1L;
        if (fallbackUp <= Integer.MAX_VALUE && !existing.contains((int) fallbackUp)) {
            return (int) fallbackUp;
        }
        long fallbackDown = (long) min - 1L;
        if (fallbackDown >= 1L && fallbackDown <= Integer.MAX_VALUE && !existing.contains((int) fallbackDown)) {
            return (int) fallbackDown;
        }

        // 进一步回退：从1开始寻找第一个缺失的正整数
        int scanUpper = (int) Math.min((long) max + existing.size() + 1000L, Integer.MAX_VALUE);
        for (int i = 1; i <= scanUpper; i++) {
            if (!existing.contains(i)) return i;
        }

        // 极端情况下（几乎填满int范围）返回null
        return null;
    }

    // Helpers
    private static H5ProcessContext buildContext(String channelCode, String partnerCode) {
        return XYJH5Process.H5processByPartner(channelCode, partnerCode, XYJH5Process.H5ProcessStage.CREDIT_APPLY);
    }

    private static List<String> getPeriods(H5ProcessContext context) {
        return StringHandler.splitByRegex(context.getPeriod(), ",");
    }

    private static int parseInt(String number) {
        return Integer.parseInt(number);
    }

    private static void planCalculate(H5ProcessContext context, String partnerCode, Integer period, int loanAmt) {
        repayPlanController.planCalculate(new RepayPlanControllerPlanCalculateRo(context.getUid(), partnerCode, period, loanAmt));
    }

}
