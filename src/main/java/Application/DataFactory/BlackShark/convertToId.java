package Application.DataFactory.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposerController;
import Service.Util.Universal.JsonHandler;

/**
 * 根据各个模块的名称转换成对应系统的code或id
 */
public class convertToId {

    public static Object getDisposerId(String disposerName, String filed) {
        AssetDisposerController assetDisposerController = new AssetDisposerController();
        String listResult = assetDisposerController.list();
        String JsonArray = JsonHandler.readStringByJsonPath("$.data", listResult);
        return  JsonHandler.readValueByJsonPath("$."+filed,JsonHandler.filterJsonArrayRetrunOne(JsonArray, "disposerName", disposerName));
    }
}
