package Application.DataFactory.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeBatchController;
import Data.ApisLayer.BlackShark.AssetDisposeRevokeController;
import Data.Ro.BlackShark.AssetDisposeBatchControllerDisposerPushRo;
import Data.Ro.BlackShark.AssetDisposeRevokeControllerDisposerRevokeRo;
import Service.Util.Universal.JsonHandler;

import java.util.List;

/**
 * 清收中心
 */
public class ClearingCenter {

    AssetDisposeRevokeController assetDisposeRevokeController = new AssetDisposeRevokeController();
    AssetDisposeBatchController assetDisposeBatchController = new AssetDisposeBatchController();

    /**
     * 一键退案
     *
     * @param disposerName 处置方名称
     */
    public void retractCaseByDisposer(String disposerName) {
        assetDisposeRevokeController.disposerRevoke(new AssetDisposeRevokeControllerDisposerRevokeRo(disposerName));
    }

    /**
     * 一键推送
     *
     * @param disposerName 处置方名称
     */
    public void pushCaseByDisposer(String disposerName) {
        assetDisposeBatchController.disposerPush(new AssetDisposeBatchControllerDisposerPushRo(disposerName));
    }


    public void scheduledTasks(String batchNo) {
        /*net.minidev.json.JSONArray batchList = (net.minidev.json.JSONArray) JsonHandler.readValueByJsonPath("$.data.providers.[0].assetPkgs.*.disposers.*.batches.*.batchNo", assetDisposeRevokeController.queryRevokeJob());
        List<String> batchNoList = JsonHandler.convertToStringList(batchList);*/
        List<String> batchNoList = JsonHandler.readStringArrayByJsonPath("$.data.providers.[0].assetPkgs.*.disposers.*.batches.*.batchNo", assetDisposeRevokeController.queryRevokeJob());
        System.out.println(batchNoList.contains(batchNo));
        /*List<String> batchNoList = Arrays.asList(batchList.toString());
        for (String s : batchNoList) {
            System.out.println(s);
        }*/
        //.contains(batchNo);
        // System.out.println(flag);
    }


    public static void main(String[] args) {
        ClearingCenter clearingCenter = new ClearingCenter();
        clearingCenter.scheduledTasks("20240301_002");
    }

}
