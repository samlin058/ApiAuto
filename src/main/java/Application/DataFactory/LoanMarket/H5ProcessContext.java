package Application.DataFactory.LoanMarket;

import Service.Core.ProjectEnum;
import Service.Util.Universal.JDBCHandler;
import Service.Util.Universal.JsonHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import Service.Util.Universal.StringHandler;
import lombok.Data;

import java.util.List;

import static Application.DataFactory.LoanMarket.XYJH5User.*;

@Data
public class H5ProcessContext {
    // 手机号
    String mobile;
    // 渠道号
    String channelCode;
    // 用户id
    String uid;
    // 授信任务Id
    Integer assessmentId;
    // 授信结果
    String creditResult;
    // 资方code
    String productCode;
    // 绑卡结果
    String bindCardResult;
    // 绑卡id
    String cardId;
    // 借款试算结果
    String loanPlanCalculateResult;
    // 借款申请结果
    String loanApplyResult;
    // 借款订单id
    String orderId;
    // 期次
    String period;
    // 可借最大金额
    String availableAmount;
    // 可借最小金额
    String minBorrowingAmt;

    /**
     * 获取H5流程上下文对象
     *
     * @param channelCode 渠道
     * @param partnerCode 资方
     * @return H5ProcessContext
     */
    public static H5ProcessContext getH5ProcessContext(String channelCode, String partnerCode) {
        if (!StringHandler.isEmpty(channelCode) && !StringHandler.isEmpty(partnerCode)) {
            //根据channelCode创建用户
            H5ProcessContext context = H5ProcessContext(channelCode, partnerCode);
            //修改渠道的路由资方
            ChannelRoutingConfiguration.changeCapital(context.getChannelCode(), partnerCode);
            return context;
        } else {
            System.out.println("所有入参不能为空");
            return null;
        }
    }

    /**
     * 获取H5流程上下文对象
     *
     * @param uid         用户id
     * @param partnerCode 资方
     * @return H5ProcessContext
     */
    public static H5ProcessContext getH5ProcessContext(int uid, String partnerCode) {
        if (!StringHandler.isEmpty(uid) && !StringHandler.isEmpty(partnerCode)) {
            //1、根据uid获取手机号和渠道
            H5ProcessContext context = H5ProcessContextByUid(uid + "");
            //2、根据资方code修改渠道路由
            ChannelRoutingConfiguration.changeCapital(context.channelCode, partnerCode);
            return context;
        } else {
            System.out.println("所有入参不能为空");
            return null;
        }
    }

    /**
     * 获取H5流程上下文对象
     *
     * @param phone       用户uid
     * @param channelCode 渠道
     * @param partnerCode 资方
     * @return H5ProcessContext
     */
    public static H5ProcessContext getH5ProcessContext(String phone, String channelCode, String partnerCode) {
        if (!StringHandler.isEmpty(phone) && !StringHandler.isEmpty(channelCode) && !StringHandler.isEmpty(partnerCode)) {
            H5ProcessContext context = H5ProcessContext.H5ProcessContextByMobile(phone, channelCode);
            return context;
        } else {
            System.out.println("所有入参不能为空");
            return null;
        }
    }

    /**
     * 根据渠道code获取上下文
     *
     * @param channelCode 渠道code
     * @return H5ProcessContext
     */
    public static H5ProcessContext getH5ProcessContext(String channelCode) {
        if (!StringHandler.isEmpty(channelCode)) {
            H5ProcessContext context = H5ProcessContext(channelCode);
            return context;
        } else {
            System.out.println("所有入参不能为空");
            return null;
        }
    }

    /**
     * 根据渠道code获取上下文
     *
     * @param channelCode 渠道code
     * @return H5ProcessContext
     */
    public static H5ProcessContext getH5ProcessContextByProductList(String channelCode, List<String> productList) {
        if (!StringHandler.isEmpty(channelCode)) {
            H5ProcessContext context = H5ProcessContext(channelCode, productList);
            return context;
        } else {
            System.out.println("所有入参不能为空");
            return null;
        }
    }

    /**
     * 根据渠道code获取上下文对象（创建用户走流程）
     *
     * @param channelCode 渠道
     * @return H5ProcessContext
     */
    private static H5ProcessContext H5ProcessContext(String channelCode) {
        if (!StringHandler.isEmpty(channelCode)) {
            H5ProcessContext context = new H5ProcessContext();
            context.setChannelCode(channelCode);
            String mobile = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket);
            context.setMobile(mobile);
            context.setUid(getUserId(mobile, channelCode, true));
            return context;
        } else {
            return null;
        }
    }

    /**
     * 根据渠道code获取上下文对象（创建用户走流程）
     *
     * @param channelCode 渠道
     * @return H5ProcessContext
     */
    private static H5ProcessContext H5ProcessContext(String channelCode, String partnerCode) {
        if (!StringHandler.isEmpty(channelCode)) {
            H5ProcessContext context = new H5ProcessContext();
            context.setChannelCode(channelCode);
            for (int i = 0; i < 50; i++) {
                String mobile = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket);
                String uid = getAvailableUsersByProductCode(mobile, channelCode, partnerCode, true);
                if (!StringHandler.isEmpty(uid)) {
                    context.setMobile(mobile);
                    context.setUid(uid);
                    return context;
                }
            }
            System.out.println("50次都未获取到合适的用户");
            return null;
        } else {
            return null;
        }
    }

    /**
     * 根据渠道code获取上下文对象（创建用户走流程）
     *
     * @param channelCode 渠道
     * @param partnerCode 资方list
     * @return H5ProcessContext
     */
    private static H5ProcessContext H5ProcessContext(String channelCode, List<String> partnerCode) {
        if (!StringHandler.isEmpty(channelCode)) {
            H5ProcessContext context = new H5ProcessContext();
            context.setChannelCode(channelCode);
            for (int i = 0; i < 100; i++) {
                String mobile = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket);
                String uid = getAvailableUsersByProductCodeList(mobile, channelCode, partnerCode, true);
                if (!StringHandler.isEmpty(uid)) {
                    context.setMobile(mobile);
                    context.setUid(uid);
                    return context;
                }
            }
            System.out.println("100次都未获取到合适的用户");
            return null;
        } else {
            return null;
        }
    }

    private static H5ProcessContext H5ProcessContextByUid(String uid) {
        String sql = "select mobile,platform from tbl_user where id = ?";
        String queryResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, uid);
        if (!"{}".equals(queryResult)) {
            H5ProcessContext h5ProcessContext = new H5ProcessContext();
            h5ProcessContext.setChannelCode(JsonHandler.readStringByJsonPath("$.platform", queryResult));
            h5ProcessContext.setMobile(JsonHandler.readStringByJsonPath("$.mobile", queryResult));
            h5ProcessContext.setUid(uid);
            return h5ProcessContext;
        }
        System.out.println("查不到该用户:" + uid);
        return null;
    }

    private static H5ProcessContext H5ProcessContextByMobile(String mobile, String channelCode) {
        H5ProcessContext h5ProcessContext = new H5ProcessContext();
        String sql = "select id from tbl_user where mobile = ? and platform= ? ";
        String queryResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, mobile, channelCode);
        if (!"{}".equals(queryResult)) {
            String uid = JsonHandler.readArrayAsStringByJsonPath("$.id", queryResult);
            h5ProcessContext.setChannelCode(channelCode);
            h5ProcessContext.setMobile(mobile);
            h5ProcessContext.setUid(uid);
            return h5ProcessContext;
        } else {
            h5ProcessContext.setChannelCode(channelCode);
            h5ProcessContext.setMobile(mobile);
            h5ProcessContext.setUid(XYJH5User.getUserId(mobile, channelCode, true));
            return h5ProcessContext;
        }
    }
}
