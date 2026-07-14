package Application.DataFactory.BlackShark;

import Data.ApisLayer.BlackShark.AssetDisposeBatchController;
import Data.ApisLayer.BlackShark.AssetPkgCycleController;
import Data.ApisLayer.BlackShark.PreAssetController;
import Data.Ro.BlackShark.*;
import Data.Enum.BlackShark.Disposed;
import Service.Util.Universal.JsonHandler;

/**
 * 资产管理
 */
public class AssetManagement {
    PreAssetController preAssetController = new PreAssetController();

    AssetDisposeBatchController assetDisposeBatchController = new AssetDisposeBatchController();

    /**
     * 资产列表——资产处置
     *
     * @param assetPkgNo   资产包编号
     * @param type         处置类型
     * @param disposerName 处置方名字
     */
    public void assetDisposed(String assetPkgNo, Disposed type, String... disposerName) {
        String result = preAssetController.getAssetPackageList(new PreAssetControllerGetAssetPackageListRo());
        String Data = JsonHandler.readStringByJsonPath("$.data.data", result);
        String assetPkgNoJson = JsonHandler.filterJsonArrayRetrunOne(Data, "assetPkgNo", assetPkgNo);
        String pkgId = JsonHandler.readValueByJsonPath("$.pkgId", assetPkgNoJson).toString();
        String AssetStatusJson = preAssetController.queryAssetStatus(new PreAssetControllerQueryAssetStatusRo(pkgId));
        Integer toDisposeAmt = (Integer) JsonHandler.readValueByJsonPath("$.data.toDisposeAmt", AssetStatusJson);
        if (toDisposeAmt > 0) {
            PreAssetControllerDisposeRo preAssetControllerDisposeRo = new PreAssetControllerDisposeRo(disposerName);
            preAssetControllerDisposeRo.setPreAssetPkgId(pkgId);
            switch (type.getCode()) {
                case "SINGLE":
                    preAssetControllerDisposeRo.setDisposeMethod("SINGLE");
                    preAssetController.dispose(preAssetControllerDisposeRo);
                    break;
                case "SPLIT":
                    preAssetControllerDisposeRo.setDisposeMethod("SPLIT");
                    preAssetControllerDisposeRo.setSplitType("EVENLY");
                    preAssetController.dispose(preAssetControllerDisposeRo);
                    break;
                case "SMART":
                    System.out.println("智能处置");
                    break;
                case "CUSTOMER_STRATEGY":
                    System.out.println("客群处置");
                    break;
                case "CUSTOMIZE":
                    System.out.println("自定义处置");
                    assetDisposeBatchController.assignment(new AssetDisposeBatchControllerAssignmentRo());
                    break;
            }
        } else {
            System.out.println("资产包暂无可处置案件");
        }
    }

    /**
     * 资产列表——设置资产处置周期（修改当前周期）
     *
     * @param pkgId     资产包id
     * @param startDate 周期开始时间
     * @param endDate   周期结束时间
     */
    public void setDisposedCycle(String pkgId, String startDate, String endDate) {
        AssetPkgCycleController assetPkgCycleController = new AssetPkgCycleController();
        AssetPkgCycleControllerSaveRo assetPkgCycleControllerSaveRo = new AssetPkgCycleControllerSaveRo(pkgId);
        assetPkgCycleControllerSaveRo.setStartDate(startDate);
        assetPkgCycleControllerSaveRo.setEndDate(endDate);
        assetPkgCycleController.save(assetPkgCycleControllerSaveRo);
    }

    /**
     * 新建/编辑客群策略
     */
    public void setCustomerStrategy() {

    }


    public static void main(String[] args) {
        AssetManagement assetManagement = new AssetManagement();
        assetManagement.assetDisposed("20250526_001", Disposed.SINGLE_PARTY_DISPOSAL, "demo机构");
    }
}
