package Data.Apis.Alchemist;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class BusinessController extends ApiBase {

    @Mapping(sub_path = "/business/coupon/list", requestType = "get" )
    public String retrieveAllCouponBusiness() {
    return http();
    }



}



