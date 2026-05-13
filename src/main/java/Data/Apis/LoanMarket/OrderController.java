package Data.Apis.LoanMarket;

import Data.Ro.LoanMarket.OrderControllerCardReconfirmRo;
import Data.Ro.LoanMarket.OrderControllerCreateRo;

import Data.Ro.LoanMarket.OrderControllerMyListRo;


import Data.Ro.LoanMarket.OrderControllerDetailRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class OrderController extends ApiBase {

    /**
     * 借款申请
     */
    @Mapping(sub_path = "/order/create", requestType = "post", contentTpye = "application/json")
    public String create(OrderControllerCreateRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/order/my-list", requestType = "get")
    public String myList(OrderControllerMyListRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/order/transit-order", requestType = "get")
    public String transitOrder() {
        return http();
    }

    /**
     * 借款订单查询
     */
    @Mapping(sub_path = "/order/detail", requestType = "get")
    public String detail(OrderControllerDetailRo ro) {
        return http(ro);
    }

    @Mapping(sub_path = "/order/create/card-reconfirm", requestType = "post", contentTpye = "application/json")
    public String cardReconfirm(OrderControllerCardReconfirmRo ro) {
        return http(ro);
    }
}



