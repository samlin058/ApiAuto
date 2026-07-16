package Service.Pojo;

import Service.Util.Universal.MD5Handler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AlchemistT3EexcelTemplate {
    public String phoneMd5;

    public static List<AlchemistT3EexcelTemplate> getPhoneList(int num){
        List<AlchemistT3EexcelTemplate> phoneList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            AlchemistT3EexcelTemplate phone  = new AlchemistT3EexcelTemplate();
            phone.setPhoneMd5(MD5Handler.getMD5(PhoneNumberGeneratorHandler.generatePhoneNumber()));
            phoneList.add(phone);
        }
        return  phoneList;
    }
}
