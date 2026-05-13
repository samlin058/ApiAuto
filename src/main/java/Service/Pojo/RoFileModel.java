package Service.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class RoFileModel {
    public String packageName;
    public String aoFileName;
    public String roFileName;
    public List<FieldInfo> roFieldList;
    public String isJsonArray;

    // 生成唯一标识符
    public String getIdentifier() {
        return packageName + aoFileName + roFileName;
    }

}
