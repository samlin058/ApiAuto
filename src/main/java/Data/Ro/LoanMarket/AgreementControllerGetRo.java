package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.AgreementController;
import Service.Annotations.AnnotationLibrary.Header;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class AgreementControllerGetRo extends AgreementController {

   static {
      DenpendencesProcessor.roDendenpence(AgreementControllerGetRo.class);
   }

    @Header
    public String X_Login_Uid;
    @Param
    public String noStoreTimeStamp;
    @Param
    public String typeList;
    @Param
    public String productCode;
    @Param
    public String loanCardId;
    @Param
    public String periods;
    @Param
    public String loanAmt;
    @Param
    public String groupType="QIANBAN_Personal_Information_Sharing_Authorization_Group";


    public AgreementControllerGetRo(String uid, String partnerCode) {
        this.X_Login_Uid=uid;
        this.productCode= partnerCode;
    }

}


