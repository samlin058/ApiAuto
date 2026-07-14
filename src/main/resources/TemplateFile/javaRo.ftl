package Data.Ro.${packageName};

import Data.ApisLayer.${packageName}.${ControllerName};
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Annotations.AnnotationLibrary.FileBody;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import Service.Annotations.AnnotationLibrary.Param;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Header;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationLibrary.PathParam;
import java.util.List;

<#if isJsonArray?has_content && isJsonArray =="true" >@JsonArray</#if>
@lombok.Data
public class ${roFileName} extends ${ControllerName} {

   static {
      DenpendencesProcessor.roDendenpence(${roFileName}.class);
   }

<#list roFieldList as roField>
    <#if roField.fieldAttribute =="parameters">@Param
    <#elseif roField.fieldAttribute =="requestBody">@Body
    <#elseif roField.fieldAttribute =="file">@FileBody
    <#elseif roField.fieldAttribute =="header">@Header
    <#elseif roField.fieldAttribute =="pathParameters">@PathParam
    </#if>
    public ${roField.fieldType} ${roField.fieldName};
</#list>


}


