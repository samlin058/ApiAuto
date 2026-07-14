package Data.ApisLayer.Alchemist;
import Data.Ro.Alchemist.TongDunControllerPushRo;
import java.util.List;
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class TongDunController extends ApiBase {

    @Mapping(sub_path = "/tongdun/push", requestType = "post" ,contentTpye = "Application/json")
    public String push(List<TongDunControllerPushRo> ro) {
    return http(ro);
    }



}



