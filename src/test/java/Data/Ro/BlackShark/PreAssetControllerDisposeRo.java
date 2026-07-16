package Data.Ro.BlackShark;

import Data.BusinessLayer.BlackShark.convertToId;
import Data.ApisLayer.BlackShark.PreAssetController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.StringHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@lombok.Data
//@Dependence(project = "blackShark", Class = "AssetDisposerController", method = "list", keys = "disposerIds_PreAssetControllerDisposeRo")
public class PreAssetControllerDisposeRo extends PreAssetController {

    @Body
    public List<String> bizScope = new ArrayList<>(Arrays.asList("PHONE_COLLECTION", "WEIWO"));
    @Body
    public String assignBatchNo;
    @Body
    public String disposeMethod;
    @Body
    public List<String> disposerIds;
    @Body
    public String preAssetPkgId;
    @Body
    public String splitType ="EVENLY";

    static {
        DenpendencesProcessor.roDendenpence(PreAssetControllerDisposeRo.class);
    }

    public PreAssetControllerDisposeRo(String[] disposerName) {
        List<String> disposerIdList = new ArrayList<>();
        for (String disposer : disposerName) {
            disposerIdList.add((String) convertToId.getDisposerId(disposer,"id"));
        }
        this.disposerIds = disposerIdList;
    }

    public static void main(String[] args) {
        String [] data = {"留案机构","demo机构"};
        PreAssetControllerDisposeRo preAssetControllerDisposeRo  = new PreAssetControllerDisposeRo(data);
        StringHandler.jsonPrint("json",preAssetControllerDisposeRo);
    }

}


