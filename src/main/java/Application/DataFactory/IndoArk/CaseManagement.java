package Application.DataFactory.IndoArk;

import Data.Apis.IndoArk.LoanBatchController;
import Data.Ro.IndoArk.LoanBatchControllerBatchAddRo;

/**
 * 案件管理
 */
public class CaseManagement {
    LoanBatchController loanBatchController = new LoanBatchController();

    /**
     * 创建批次和导入案件
     */
    public void addBatchAndCase(){
        loanBatchController.batchAdd(new LoanBatchControllerBatchAddRo());
    }

}
