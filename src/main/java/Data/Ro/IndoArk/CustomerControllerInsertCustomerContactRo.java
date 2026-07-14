package Data.Ro.IndoArk;

import Data.ApisLayer.IndoArk.CustomerController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;


@lombok.Data
public class CustomerControllerInsertCustomerContactRo extends CustomerController {

    @Param
    public String id;
    @Body
    public String name;
    @Body
    public String mobile;
    @Body
    public String remark;
    @Body
    public String source;
    @Body
    public Integer loanId;
    @Body
    public String relation;

   static {
      DenpendencesProcessor.roDendenpence(CustomerControllerInsertCustomerContactRo.class);
   }
}


