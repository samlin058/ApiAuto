package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ProjectUserController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

import java.util.List;


@lombok.Data
public class ProjectUserControllerQueryRo extends ProjectUserController {

    @Body
    public List<?> projectCodeList;

    static {
        DenpendencesProcessor.roDendenpence(ProjectUserControllerQueryRo.class);
    }

    public ProjectUserControllerQueryRo(List<?> projectCodeList) {
        this.projectCodeList = projectCodeList;
    }
}


