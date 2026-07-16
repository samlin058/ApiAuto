package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.ContactsController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.JsonArray;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.variable.ChinneNameHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@JsonArray
@lombok.Data
public class ContactsControllerSaveContactsRo extends ContactsController {

    static {
        DenpendencesProcessor.roDendenpence(ContactsControllerSaveContactsRo.class);
    }

    @Body
    public String name= ChinneNameHandler.generateName();
    @Body
    public String mobile = PhoneNumberGeneratorHandler.generatePhoneNumber();
    @Body
    public String type;
    @Body
    public String relation;

    public enum  ContactsRelation  {
        /**
         * 配偶
         */
        SPOUSE("SPOUSE", "配偶",true),

        FATHER("FATHER", "父亲",true),

        MOTHER("MOTHER", "母亲",true),

        CHILD("CHILD", "子女",true),

        BOTHERS_OR_SISTERS("BOTHERS_OR_SISTERS", "兄弟姐妹",true),

        CLASSMATE("CLASSMATE","同学",false),


        COLLEAGUE("COLLEAGUE","同事",false),

        FRIEND("FRIEND","朋友",false),

        OTHER("OTHER","其他",false);

        private final String code;

        private final String desc;
        /**
         * 是否紧急联系人
         */
        private final boolean isEmergency;

        ContactsRelation(String code, String desc, boolean isEmergency) {
            this.code = code;
            this.desc = desc;
            this.isEmergency = isEmergency;
        }

        public static ContactsRelation getCode(String code){
            return Arrays.stream(ContactsRelation.values()).filter(o->o.getCode().equals(code)).findAny().orElse(OTHER);
        }

        public boolean isEmergency() {
            return isEmergency;
        }

        public String getCode(){
            return code;
        }

    }

    public static ArrayList<ContactsControllerSaveContactsRo> generateContactsList(){
        ArrayList<ContactsControllerSaveContactsRo> list = new ArrayList<>();

        ContactsControllerSaveContactsRo common = new ContactsControllerSaveContactsRo();
        common.type = "COMMON";
        List<ContactsRelation> commonCandidates = Arrays.stream(ContactsRelation.values())
                .filter(r -> !r.isEmergency())
                .collect(Collectors.toList());
        ContactsRelation commonRelation = commonCandidates.get(
                ThreadLocalRandom.current().nextInt(commonCandidates.size())
        );
        common.relation = commonRelation.getCode();
        list.add(common);

        ContactsControllerSaveContactsRo emergency = new ContactsControllerSaveContactsRo();
        emergency.type = "EMERGENCY";
        List<ContactsRelation> emergencyCandidates = Arrays.stream(ContactsRelation.values())
                .filter(ContactsRelation::isEmergency)
                .collect(Collectors.toList());
        ContactsRelation emergencyRelation = emergencyCandidates.get(
                ThreadLocalRandom.current().nextInt(emergencyCandidates.size())
        );
        emergency.relation = emergencyRelation.getCode();
        list.add(emergency);

        return list;
    }

    public static void main(String[] args) {
        generateContactsList().forEach(System.out::println);
    }

}


