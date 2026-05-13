package Service.Pojo;

import lombok.Data;

import java.util.List;

/**
 * Ao文件生成数据模板
 */
@Data
public class AoFileModel {
    public String packageName;
    public String aoFileName;
    public List<AoMethod> methodList;

    public String getIdentifier() {

        return this.packageName+this.aoFileName;
    }
}
