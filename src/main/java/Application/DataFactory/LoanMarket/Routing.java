package Application.DataFactory.LoanMarket;

import Data.ApisLayer.LoanMarket.AssessmentController;
import Data.Ro.LoanMarket.AssessmentControllerInitRo;
import Service.Util.Universal.TimeHandler;

import java.util.List;

public class Routing {
    public static void main(String[] args) {
        // Routing.newRouting("838505");
        /*int a =2;
        if (a == 1) {
            ConcurrentRouting(1, "luandaoTest");
        } else if (a == 2) {
            ConcurrentRouting(1, "luandaoTest2");
        } else if (a == 3) {
            ConcurrentRouting(1, "luandaoTest3");
        }*/

    }
    static AssessmentController assessmentController = new AssessmentController();

    /**
     * 用户创建新的路由任务
     *
     * @param uid 用户id
     */
    public static void newRouting(String uid) {
        assessmentController.init(new AssessmentControllerInitRo(uid));
    }

    /**
     * 并发路由测试
     *
     * @param num         并发数
     * @param channelCode 并发渠道
     */
    public static void ConcurrentRouting(int num, String channelCode) {
        TimeHandler.measure(() -> {
            List<String> uidList = XYJH5User.getUserByNum(num, channelCode);
            for (String uid : uidList) {
                newRouting(uid);
            }
        }, "ConcurrentRouting");
    }

    private static Boolean checkUserCredit(String uid) {
        String sql = "";
        return true;
    }


}
