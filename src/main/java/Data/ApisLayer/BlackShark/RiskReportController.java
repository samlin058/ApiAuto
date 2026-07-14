package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.RiskReportControllerAllScoreReportPageRo;

import Data.Ro.BlackShark.RiskReportControllerPkgReportPageRo;

import Data.Ro.BlackShark.RiskReportControllerDisposerReportPageRo;

import Data.Ro.BlackShark.RiskReportControllerRiskReportContextRo;

import Data.Ro.BlackShark.RiskReportControllerSetCustomReportCreateTimeRo;



import Data.Ro.BlackShark.RiskReportControllerCreateManualRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class RiskReportController extends ApiBase {

    @Mapping(sub_path = "/report/risk-reveal/page/all-score", requestType = "post" ,contentTpye = "Application/json")
    public String allScoreReportPage (RiskReportControllerAllScoreReportPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/risk-reveal/page/pkg", requestType = "post" ,contentTpye = "Application/json")
    public String pkgReportPage (RiskReportControllerPkgReportPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/risk-reveal/page/disposer", requestType = "post" ,contentTpye = "Application/json")
    public String disposerReportPage (RiskReportControllerDisposerReportPageRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/risk-reveal/reportContext", requestType = "get" )
    public String riskReportContext (RiskReportControllerRiskReportContextRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/risk-reveal/custom/create/time/set", requestType = "post" ,contentTpye = "Application/json")
    public String setCustomReportCreateTime (RiskReportControllerSetCustomReportCreateTimeRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/report/risk-reveal/custom/create/time/get", requestType = "get" )
    public String getCustomReportCreateTime() {
    return http();
    }


    @Mapping(sub_path = "/report/risk-reveal-report/create/manual", requestType = "post" ,contentTpye = "Application/json")
    public String createManual (RiskReportControllerCreateManualRo ro) {
    return http(ro);
    }


}



