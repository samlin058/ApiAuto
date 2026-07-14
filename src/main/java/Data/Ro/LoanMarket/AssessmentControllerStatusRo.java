package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.AssessmentController;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;


@lombok.Data
public class AssessmentControllerStatusRo extends AssessmentController {

    static {
        DenpendencesProcessor.roDendenpence(AssessmentControllerStatusRo.class);
    }

    @Param
    public Integer id;

    public AssessmentControllerStatusRo(Integer id) {
        this.id = id;
    }
}


