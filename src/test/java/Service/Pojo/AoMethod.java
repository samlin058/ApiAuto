package Service.Pojo;

import lombok.Data;

/**
 * apiObject
 */
@Data
public class AoMethod {
    public String packageName;
    public String belongController;
    public String methodDescription;
    public String subPath;
    public String requestTpye;
    public String methodName;
    public String paramName;
    public String aoIsList;
    public String contentType;

    public AoMethod(){

    }
    public AoMethod(AoMethod aoMethod) {
        this.packageName = aoMethod.packageName;
        this.belongController = aoMethod.belongController;
        this.methodDescription = aoMethod.methodDescription;
        this.subPath = aoMethod.subPath;
        this.requestTpye = aoMethod.requestTpye;
        this.methodName = aoMethod.methodName;
        this.paramName = aoMethod.paramName;
        this.aoIsList = aoMethod.aoIsList;
        this.contentType = aoMethod.contentType;
    }
}
