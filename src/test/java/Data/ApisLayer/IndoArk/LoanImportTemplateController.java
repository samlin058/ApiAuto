package Data.ApisLayer.IndoArk;


















import Data.Ro.IndoArk.LoanImportTemplateControllerLoanImportTemplateFieldImportRo;





import Service.Annotations.AnnotationLibrary.Mapping;
import Service.Core.ApiBase;


public class LoanImportTemplateController extends ApiBase {

    @Mapping(sub_path = "/loan/import/template", requestType = "post" ,contentTpye = "application/json")
    public String addLoanImportTemplate() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/{id}", requestType = "post" ,contentTpye = "application/json")
    public String updateLoanImportTemplate() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/delete/{id}", requestType = "post" )
    public String deleteLoanImportTemplate() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/{id}/download", requestType = "get" )
    public String downloadLoanImportTemplate() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/all", requestType = "get" )
    public String getLoanImportTemplateAll() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/{id}/field", requestType = "post" ,contentTpye = "application/json")
    public String addLoanImportTemplateField() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/{id}/field/{fieldId}", requestType = "post" ,contentTpye = "application/json")
    public String updateLoanImportTemplateField() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/field/{id}/delete", requestType = "post" )
    public String deleteLoanImportTemplateField() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/field/{id}/order/{code}/{index}", requestType = "post" )
    public String orderLoanImportTemplateField() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/{id}/field/import", requestType = "post" ,contentTpye = "multipart/form-data")
    public String loanImportTemplateFieldImport (LoanImportTemplateControllerLoanImportTemplateFieldImportRo ro) {
    return http(ro);
    }

    @Mapping(sub_path = "/loan/import/template/{id}/active", requestType = "post" ,contentTpye = "application/json")
    public String activeLoanImportTemplate() {
    return http();
    }


    @Mapping(sub_path = "/loan/import/template/{id}/field/system", requestType = "get" )
    public String getLoanFields() {
    return http();
    }



}



