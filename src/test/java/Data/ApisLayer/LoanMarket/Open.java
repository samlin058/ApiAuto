package Data.ApisLayer.LoanMarket;

import Data.Ro.LoanMarket.*;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class Open extends ApiBase {
    /**
     * 协议查询
     */
    @Mapping(sub_path = "/test/open/api/getContract", requestType = "post", contentTpye = "application/json")
    public String agreementService(OpenAgreementServiceRo ro) {
        return http(ro);
    }

    /**
     * 用户准入（撞库）
     */
    @Mapping(sub_path = "/test/open/api/checkUser", requestType = "post", contentTpye = "application/json")
    public String userCheckService(OpenUserCheckServiceRo ro) {
        return http(ro);
    }

    /**
     * 授信申请
     */
    @Mapping(sub_path = "/test/open/api/creditApply", requestType = "post", contentTpye = "application/json")
    public String creditApplyService(OpenCreditApplyServiceRo ro) {
        return http(ro);
    }

    /**
     * 授信结果查询
     */
    @Mapping(sub_path = "/test/open/api/creditResult", requestType = "post", contentTpye = "application/json")
    public String creditResultQueryService(OpenCreditResultQueryServiceRo ro) {
        return http(ro);
    }

    /**
     * 绑卡查询
     */
    @Mapping(sub_path = "/test/open/api/queryBindCard", requestType = "post", contentTpye = "application/json")
    public String bindCardListService(OpenBindCardListServiceRo ro) {
        return http(ro);
    }

    /**
     * 绑卡申请
     */
    @Mapping(sub_path = "/test/open/api/bindCardApply", requestType = "post", contentTpye = "application/json")
    public String bindCardApplyService(OpenBindCardApplyServiceRo ro) {
        return http(ro);
    }

    /**
     * 绑卡确认
     */
    @Mapping(sub_path = "/test/open/api/bindCardConfirm", requestType = "post", contentTpye = "application/json")
    public String bindCardConfirmService(OpenBindCardConfirmServiceRo ro) {
        return http(ro);
    }

    /**
     * 借款试算
     */
    @Mapping(sub_path = "/test/open/api/loanTrial", requestType = "post", contentTpye = "application/json")
    public String loanTrialService(OpenLoanTrialServiceRo ro) {
        return http(ro);
    }

    /**
     * 借款申请
     */
    @Mapping(sub_path = "/test/open/api/submitLoan", requestType = "post", contentTpye = "application/json")
    public String loanApplyService(OpenLoanApplyServiceRo ro) {
        return http(ro);
    }

    /**
     * 借款结果查询
     */
    @Mapping(sub_path = "/test/open/api/queryLoan", requestType = "post", contentTpye = "application/json")
    public String loanResultQueryService(OpenLoanResultQueryServiceRo ro) {
        return http(ro);
    }

    /**
     * 还款计划查询
     */
    @Mapping(sub_path = "/test/open/api/queryRepayPlan", requestType = "post", contentTpye = "application/json")
    public String repayPlanQueryService(OpenRepayPlanQueryServiceRo ro) {
        return http(ro);
    }

    /**
     * 还款试算
     */
    @Mapping(sub_path = "/test/open/api/repayTrial", requestType = "post", contentTpye = "application/json")
    public String repayTrialService(OpenRepayTrialServiceRo ro) {
        return http(ro);
    }

    /**
     * 还款申请
     */
    @Mapping(sub_path = "/test/open/api/submitRepay", requestType = "post", contentTpye = "application/json")
    public String repayApplyService(OpenRepayApplyServiceRo ro) {
        return http(ro);
    }

    /**
     * 还款结果查询
     */
    @Mapping(sub_path = "/test/open/api/queryRepay", requestType = "post", contentTpye = "application/json")
    public String repayResultQueryService(OpenRepayResultQueryServiceRo ro) {
        return http(ro);
    }

    /**
     * 查询资方支持的银行机构
     */
    @Mapping(sub_path = "/test/open/api/supportBank", requestType = "post", contentTpye = "application/json")
    public String supportBankService(OpenSupportBankServiceRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/test/open/api/h5Link", requestType = "post", contentTpye = "application/json")
    public String OpenH5LinkService(OpenOpenH5LinkServiceRo ro) {
        return http(ro);
    }

}



