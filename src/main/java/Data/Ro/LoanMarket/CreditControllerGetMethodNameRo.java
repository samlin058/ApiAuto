package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.CreditController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Header;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationLibrary.PathParam;

import java.util.List;


@lombok.Data
public class CreditControllerGetMethodNameRo extends CreditController {

    static {
        DenpendencesProcessor.roDendenpence(CreditControllerGetMethodNameRo.class);
    }

    @Param
    public String userName;
    @Param
    public String idNo;
    @Param
    public String mobile;
    @Param
    public String channelCode;
    @Param
    public String partnerCode;
    @Param
    public String userId;
    @Param
    public String status;
    @Param
    public String creditStartTime;
    @Param
    public String creditEndTime;
    @Param
    public String channelRequestId;
    @Param
    public String partnerRequestId;
    @Param
    public String partnerApplyNo;
    @Param
    public String current;
    @Param
    public String pageSize;


}


