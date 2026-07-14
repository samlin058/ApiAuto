package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ReceiptController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;


@lombok.Data
public class ReceiptControllerRepayReceiptRo extends ReceiptController {

    @Param
    public String beginDate;
    @Param
    public String endDate;
    @Param
    public String dateType;
    @Param
    public String current;
    @Param
    public String pageSize;

   static {
      DenpendencesProcessor.roDendenpence(ReceiptControllerRepayReceiptRo.class);
   }
}


