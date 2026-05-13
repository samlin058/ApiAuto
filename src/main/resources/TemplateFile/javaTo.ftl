package Application.TestCase.${projectEnum}.ApiTest;


import Data.Apis.${projectEnum}.${controller};
<#list methods as method>
<#if method.roName != "noRo">import Data.Ro.${projectEnum}.${method.roName};</#if>
</#list>
import Service.Base.ApiBase;
import Service.Base.BaseTestCase;
import Service.Config.Login;
import Service.Enum.ProjectEnum;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class ${testName} extends BaseTestCase {

    ${controller} ${controllerLower} = new ${controller}();

    /*@BeforeClass
        public void login() {
            ApiBase.header = Login.getLoginByProjectAndUser(Project.${projectEnum}, "luandao");
        }*/

<#list methods as method>
    @Test
    public void ${method.methodName}() {
        <#if method.roName == "noRo">
          ${controllerLower}.${method.methodName}();
        <#else>
        ${controllerLower}.convert2RoAndRequest("",${method.roClazz});
        </#if>
    }
</#list>

}



