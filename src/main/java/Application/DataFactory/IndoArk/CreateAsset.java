package Application.DataFactory.IndoArk;

import Service.Pojo.TelemarketingCustomer;

import java.util.ArrayList;
import java.util.List;

public class CreateAsset {
    public static List<TelemarketingCustomer> generateAsset(int num) {
        List<TelemarketingCustomer> assetList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            assetList.add(new TelemarketingCustomer());
        }
        return assetList;
    }
}
