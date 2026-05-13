package Service.Pojo;

import java.util.List;
import java.util.Map;

@lombok.Data
public class LoginObject {
    String project;
    Map<String, String> template;
    List<Map<String, String>> loginInfo;
}
