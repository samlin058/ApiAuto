package Data.Ro.LoanMarket;

import Data.Apis.LoanMarket.TestKaniuController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.variable.ChinneNameHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class TestKaniuControllerKaNiuUserCreditServiceRo extends TestKaniuController {

    static {
        DenpendencesProcessor.roDendenpence(TestKaniuControllerKaNiuUserCreditServiceRo.class);
    }

    @Body
    public String orderId;
    @Body
    public String phoneNo;
    @Body
    public String channelCode = "KN-MC";
    @Body
    public UserAuthInfo userAuthInfo = new UserAuthInfo();
    @Body
    public List<UserContactInfo> userContactInfos = UserContactInfo.getUserContactInfo();
    @Body
    public UserDeviceInfo userDeviceInfo = new UserDeviceInfo();
    @Body
    public UserFaceInfo userFaceInfo = new UserFaceInfo();
    @Body
    public String userId;
    @Body
    public UserPersonInfo userPersonInfo = new UserPersonInfo();
    @Body
    public UserWorkUnitInfo userWorkUnitInfo = new UserWorkUnitInfo();

    public TestKaniuControllerKaNiuUserCreditServiceRo(String name, String phoneNo, String idNo, String orderId, String userId) {
        this.phoneNo = phoneNo;
        this.orderId = orderId;
        this.userId = userId;
        this.userAuthInfo.setIdNo(idNo);
        this.userAuthInfo.setName(name);
    }

    @Data
    public static class UserAuthInfo {
        private String address = "江西省新余市分宜县分宜镇钤阳东路347号2栋1单元301室";
        private String authority = "分宜县公安局";
        private String backImage = "http://file.feidee.com/file-gateway/v2/link/c5958c25be81c2240zd739a87e608961fa";
        private String birthDay = "1984-09-22";
        private String frontImage = "http://file.feidee.com/file-gateway/v2/link/ca35d786897adb21dzbfdc6e5a559dc1b7";
        private String gender = "男";
        private String idNo;
        private String name;
        private String nation = "汉";
        private String validDate = "20250515-20450515";
    }

    @Data
    public static class UserContactInfo {
        private String contactName = ChinneNameHandler.generateName();
        private String contactPhone = PhoneNumberGeneratorHandler.generatePhoneNumber();
        private String contactRelationCode;
        private Integer orderNo;

        public static List<UserContactInfo> getUserContactInfo() {
            List<UserContactInfo> userContactInfoList = new ArrayList<>();
            UserContactInfo one = new UserContactInfo();
            one.setContactRelationCode("1");
            one.setOrderNo(1);
            userContactInfoList.add(one);
            UserContactInfo two = new UserContactInfo();
            two.setContactRelationCode("4");
            two.setOrderNo(2);
            userContactInfoList.add(two);
            return userContactInfoList;
        }
    }

    @Data
    public static class UserDeviceInfo {
        private String deviceModel = "LGN-AN00";
        private String gpsAddress = "江西省新余市分宜县钤阳东路347号二栋一单元301室";
        private String gpsArea = "360502";
        private String gpsCity = "360500";
        private String gpsLat = "";
        private String gpsLng = "";
        private String gpsProvince = "360000";
        private String osType = "android";
        private String osVersion = "11";
        private String userIp = "117.163.244.0";
    }

    @Data
    public static class UserFaceInfo {
        private String confidence = "97.230000";
        private String livingImage1 = "http://file.feidee.com/file-gateway/v2/link/cf124c7b208ae6929z211e3eb4dce498e3";
    }

    @Data
    public static class UserPersonInfo {
        private String educationCode = "2";
        private String email = "42342342@qq.com";
        private String liveAddress = "江西省新余市分宜县钤阳东路347号二栋一单元301室";
        private String liveAreaCode = "360502";
        private String liveAreaName = "渝水区";
        private String liveCityCode = "360500";
        private String liveCityName = "新余市";
        private String liveProvinceCode = "360000";
        private String liveProvinceName = "江西省";
    }

    @Data
    public static class UserWorkUnitInfo {
        private String industryCode = "0";
        private String positonCode = "12";
        private String workAddress = "新余市分宜县雅保锂业工业园区";
        private String workAreaCode = "360521";
        private String workAreaName = "分宜县";
        private String workCityCode = "360100";
        private String workCityName = "南昌市";
        private String workCompany = "江西雅保锂业有限公司";
        private String workIncome = "10000";
        private String workProvinceCode = "360000";
        private String workProvinceName = "江西省";
        private String workTel = "15807903913";
        private String workYears = "1";
    }

}



