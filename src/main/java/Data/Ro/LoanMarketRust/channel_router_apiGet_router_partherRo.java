package Data.Ro.LoanMarketRust;

import Data.Apis.LoanMarketRust.channel_router_api;
import Service.Annotations.AnnotationLibrary.PathParam;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class channel_router_apiGet_router_partherRo extends channel_router_api {

    static {
        DenpendencesProcessor.roDendenpence(channel_router_apiGet_router_partherRo.class);
    }

    @PathParam
    public Integer id_path;

    public channel_router_apiGet_router_partherRo(String id) {
        this.id_path = Integer.parseInt(id) ;
    }
}


