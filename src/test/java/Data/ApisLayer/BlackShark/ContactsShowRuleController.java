package Data.ApisLayer.BlackShark;
import Data.Ro.BlackShark.ContactsShowRuleControllerRuleListRo;

import Data.Ro.BlackShark.ContactsShowRuleControllerUpdateStatusRo;



import Data.Ro.BlackShark.ContactsShowRuleControllerQueryRuleByAssetIdRo;

import Data.Ro.BlackShark.ContactsShowRuleControllerAddRuleRo;



import Data.Ro.BlackShark.ContactsShowRuleControllerAddRuleConfigRo;
import java.util.List;
import Data.Ro.BlackShark.ContactsShowRuleControllerRuleConfigListRo;

import Data.Ro.BlackShark.ContactsShowRuleControllerCheckRuleRo;

import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class ContactsShowRuleController extends ApiBase {

    @Mapping(sub_path = "/blackShark/contacts/show/ruleList", requestType = "get" )
    public String ruleList (ContactsShowRuleControllerRuleListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/contacts/show/updateStatus", requestType = "get" )
    public String updateStatus (ContactsShowRuleControllerUpdateStatusRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/contacts/show/showType", requestType = "get" )
    public String showType() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/contacts/show/queryRuleByAssetId", requestType = "get" )
    public String queryRuleByAssetId (ContactsShowRuleControllerQueryRuleByAssetIdRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/contacts/show/addRule", requestType = "post" ,contentTpye = "Application/json")
    public String addRule (ContactsShowRuleControllerAddRuleRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/contacts/show/contactType", requestType = "get" )
    public String contactType() {
    return http();
    }


    @Mapping(sub_path = "/blackShark/contacts/show/addRuleConfig", requestType = "post" ,contentTpye = "Application/json")
    public String addRuleConfig(List<ContactsShowRuleControllerAddRuleConfigRo> ro) {
    return http(ro);
    }


    @Mapping(sub_path = "/blackShark/contacts/show/findConfigs", requestType = "get" )
    public String ruleConfigList (ContactsShowRuleControllerRuleConfigListRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/blackShark/contacts/show/checkRule", requestType = "post" ,contentTpye = "Application/json")
    public String checkRule (ContactsShowRuleControllerCheckRuleRo ro) {
    return http(ro);
    }


}



