package Data.Apis.${packageName};
<#list methods as method>
<#if method.paramName !="NULL">import Data.Ro.${packageName}.${method.paramName};</#if>
<#if method.aoIsList == "true">import java.util.List;</#if>
<#sep></#sep>
</#list>
import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Base.ApiBase;


public class ${fileName} extends ApiBase {

<#list methods as method>
    @Mapping(sub_path = "${method.subPath}", requestType = "${method.requestTpye}" <#if method.contentType?has_content>,contentTpye = "${method.contentType}"</#if>)
    public String ${method.methodName}<#if method.aoIsList == "true">(List<${method.paramName}> ro) {
    return http(ro);
    }
<#elseif method.paramName == "NULL">() {
    return http();
    }
<#else> (${method.paramName} ro) {
    return http(ro);
    }</#if>

    <#sep></#sep>
</#list>

}



