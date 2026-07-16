package Service.Pojo;

import java.util.List;

@lombok.Data
public class Method {
    public String clazz;
    public String method;
    public List<String> paramTypes;
    public String param;
}
