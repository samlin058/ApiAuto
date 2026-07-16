package Data.Ro.BlackShark;

import Data.ApisLayer.BlackShark.ProjectUserController;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.Body;

import java.util.List;


@lombok.Data
public class ProjectUserControllerAddUserRo extends ProjectUserController {

    @Body
    public List<?> userList;
    @Body
    public List<?> projectCodeList;

    static {
        DenpendencesProcessor.roDendenpence(ProjectUserControllerAddUserRo.class);
    }

    public ProjectUserControllerAddUserRo(List<?> userList, List<?> projectCodeList) {
        this.userList = userList;
        this.projectCodeList = projectCodeList;
    }
}


