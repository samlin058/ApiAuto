package Data.Apis.BlackShark;
import Data.Ro.BlackShark.ComplaintControllerComplaintTrendRo;



import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class ComplaintController extends ApiBase {

    @Mapping(sub_path = "/complaint/trend", requestType = "get" )
    public String complaintTrend (ComplaintControllerComplaintTrendRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/complaint/export/orderList", requestType = "get" )
    public String exportOrderList() {
    return http();
    }



}



