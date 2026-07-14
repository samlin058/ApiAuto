package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.CommissionManageControllerDisposeSaveRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class CommissionManageController extends ApiBase {

    @Mapping(sub_path = "/commission-manage/dispose/save", requestType = "post" ,contentTpye = "Application/json")
    public String disposeSave (CommissionManageControllerDisposeSaveRo ro) {
    return http(ro);
    }


}



