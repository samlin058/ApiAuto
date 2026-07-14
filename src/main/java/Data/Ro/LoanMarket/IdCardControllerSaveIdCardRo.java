package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.IdCardController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class IdCardControllerSaveIdCardRo extends IdCardController {

   static {
      DenpendencesProcessor.roDendenpence(IdCardControllerSaveIdCardRo.class);
   }

    @Body
    public String birthday;
    @Body
    public String updatedTime;
    @Body
    public String identificationAddress;
    @Body
    public String validityStartDate;
    @Body
    public String gender;
    @Body
    public String nation;
    @Body
    public String backUuid;
    @Body
    public String idNo;
    @Body
    public String validityEndDate;
    @Body
    public String signOrganization;
    @Body
    public String realName;
    @Body
    public Integer uid;
    @Body
    public String createdTime;
    @Body
    public String frontUuid;
    @Body
    public Integer id;
    @Body
    public String status;


}


