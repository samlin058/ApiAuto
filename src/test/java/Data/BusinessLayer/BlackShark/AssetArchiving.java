package Data.BusinessLayer.BlackShark;

import Data.ApisLayer.BlackShark.PreAssetController;
import Data.ApisLayer.BlackShark.PreAssetPkgBatchController;
import Data.Ro.BlackShark.PreAssetControllerImportAssetRo;
import Data.Ro.BlackShark.PreAssetPkgBatchControllerCheckAssetRo;
import Data.Ro.BlackShark.PreAssetPkgBatchControllerUploadFileRo;
import Service.Util.variable.AMCloanGenerateHandler;
import Service.Util.Universal.JsonHandler;

import java.util.HashMap;
import java.util.List;

/**
 * 资产建档
 */
public class AssetArchiving {

    public static void main(String[] args) {
        AssetArchiving assetFiling = new AssetArchiving();
         assetFiling.importAsset("20251225_001", 10);
//          assetFiling.assetAudit();
    }

    PreAssetPkgBatchController preAssetPkgBatchController = new PreAssetPkgBatchController();

    PreAssetController preAssetController = new PreAssetController();

    /**
     * 资产导入——导入资产
     *
     * @param assetPkgNo 资产包编号
     * @param num        导入数量
     */
    public void importAsset(String assetPkgNo, int num) {
        AMCloanGenerateHandler.createLoanExcel(num);
        // 上传文件数据
        String fileData = preAssetPkgBatchController.uploadFile(new PreAssetPkgBatchControllerUploadFileRo("src/test/resources/CreditLoanAssetTemplate.xlsx"));
        HashMap attach = new HashMap<>();
        attach.put("attachName", JsonHandler.readValueByJsonPath("$.data.fileName", fileData));
        attach.put("attachId", JsonHandler.readValueByJsonPath("$.data.fileId", fileData));
        List<?> attachListList = JsonHandler.changeMapToList(attach);
        PreAssetControllerImportAssetRo preAssetControllerImportAssetRo = new PreAssetControllerImportAssetRo(assetPkgNo);
        preAssetControllerImportAssetRo.setAttachList(attachListList);
        preAssetController.importAsset(preAssetControllerImportAssetRo);
    }

    /**
     * 人工审核————资产审核(默认审核最新一条)
     */
    public void assetAudit() {
        preAssetPkgBatchController.checkAsset(new PreAssetPkgBatchControllerCheckAssetRo());
    }


}
