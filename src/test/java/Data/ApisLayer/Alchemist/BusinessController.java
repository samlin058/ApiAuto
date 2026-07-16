package Data.ApisLayer.Alchemist;


import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class BusinessController extends ApiBase {

    @Mapping(sub_path = "/business/coupon/list", requestType = "get" )
    public String retrieveAllCouponBusiness() {
    return http();
    }



}



