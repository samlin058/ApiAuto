package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.Open;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import Service.Util.Universal.TimestampHandler;


@lombok.Data
public class OpenCreditApplyServiceRo extends Open {

    @Body
    public Object data;
    @Body
    public String sign;
    @Body
    public String channelCode;
    @Body
    public String reqid;
    @Body
    public Long timestamp;
    @Body
    public String encryptAesKey;

    static {
        DenpendencesProcessor.roDendenpence(OpenCreditApplyServiceRo.class);
    }

    public OpenCreditApplyServiceRo(String channelCode, String creditOrderNo, String mobile, String idNo, String name) {
        this.channelCode = channelCode;
        this.reqid = channelCode + TimestampHandler.getCurrentTimestamp();
        if (!StringHandler.isEmpty(mobile) && !StringHandler.isEmpty(idNo)) {
            this.data = JsonHandler.objectChange2JsonStr(new CreditInfo(creditOrderNo, mobile, idNo, name));
        }
        this.timestamp = TimestampHandler.getCurrentTimestamp();
        this.encryptAesKey = "1";
        this.sign = "1";
    }

    @lombok.Data
    public class CreditInfo {

        public String creditOrderNo;
        public BaseInfo baseInfo;
        public IdCardInfo idCardInfo;
        public ContactInfo contactInfo;
        public DeviceInfo deviceInfo;
        public ExtendInfo extendInfo;
        public LivingBody livingBody;


        // 带默认值构造函数
        public CreditInfo(String creditOrderNo, String phone, String idNo, String name) {
            this.creditOrderNo = creditOrderNo;
            this.baseInfo = new BaseInfo(
                    phone,
                    "成都市高新区天府大道中段888号",
                    "510100",
                    "四川省",
                    "510100",
                    "成都市",
                    "510100",
                    "高新区",
                    "4",
                    "7",
                    "4",
                    "UNMARRIED",
                    "1",
                    "未来科技有限公司",
                    "510100",
                    "四川省",
                    "510100",
                    "成都市",
                    "510100",
                    "高新区",
                    "天府软件园D区5栋",
                    "028-87654321"
            );

            this.idCardInfo = new IdCardInfo(
                    name, idNo,
                    "四川省成都市高新区天府大道中段888号",
                    "1",
                    "汉",
                    "成都市公安局高新区分局",
                    "2010-01-01",
                    "2100-01-01",
                    "http://file.feidee.com/file-gateway/v2/link/ca35d786897adb21dzbfdc6e5a559dc1b7",
                    "http://file.feidee.com/file-gateway/v2/link/c5958c25be81c2240zd739a87e608961fa"
            );

            this.contactInfo = new ContactInfo(
                    "李四",
                    "13900139000",
                    "father",
                    "王五",
                    "13600136000",
                    "colleague"
            );

            this.deviceInfo = new DeviceInfo(
                    "192.168.1.1", "113.859339,22.569849",
                    "Xiaomi", "Redmi Note 9",
                    "Android 10", "5G",
                    "a1b2c3d4e5f6g7h8"
            );

            this.extendInfo = new ExtendInfo("预留扩展字段示例");

            this.livingBody = new LivingBody(
                    "http://file.feidee.com/file-gateway/v2/link/c5958c25be81c2240zd739a87e608961fa",
                    "2023-10-12 15:30:00",
                    "99.7"
            );
        }


        @lombok.Data
        public class BaseInfo {
            public String phoneNumber;
            public String address;
            public String addressProvinceCode;
            public String addressProvinceName;
            public String addressCityCode;
            public String addressCityName;
            public String addressDistrictCode;
            public String addressDistrictName;
            public String job;
            public String education;
            public String monthIncome;
            public String marriage;
            public String industryType;
            public String companyName;
            public String companyAddressProvince;
            public String companyAddressProvinceName;
            public String companyAddressCity;
            public String companyAddressCityName;
            public String companyAddressDistrict;
            public String companyAddressDistrictName;
            public String companyAddressDetails;
            public String companyPhone;

            public BaseInfo(String phoneNumber, String address, String addressProvinceCode, String addressProvinceName,
                            String addressCityCode, String addressCityName, String addressDistrictCode, String addressDistrictName,
                            String job, String education, String monthIncome, String marriage,String industryType, String companyName,
                            String companyAddressProvince, String companyAddressProvinceName, String companyAddressCity,
                            String companyAddressCityName, String companyAddressDistrict, String companyAddressDistrictName,
                            String companyAddressDetails, String companyPhone) {
                this.phoneNumber = phoneNumber;
                this.address = address;
                this.addressProvinceCode = addressProvinceCode;
                this.addressProvinceName = addressProvinceName;
                this.addressCityCode = addressCityCode;
                this.addressCityName = addressCityName;
                this.addressDistrictCode = addressDistrictCode;
                this.addressDistrictName = addressDistrictName;
                this.job = job;
                this.education = education;
                this.monthIncome = monthIncome;
                this.marriage = marriage;
                this.industryType =industryType;
                this.companyName = companyName;
                this.companyAddressProvince = companyAddressProvince;
                this.companyAddressProvinceName = companyAddressProvinceName;
                this.companyAddressCity = companyAddressCity;
                this.companyAddressCityName = companyAddressCityName;
                this.companyAddressDistrict = companyAddressDistrict;
                this.companyAddressDistrictName = companyAddressDistrictName;
                this.companyAddressDetails = companyAddressDetails;
                this.companyPhone = companyPhone;
            }
        }

        @lombok.Data
        public class IdCardInfo {
            public String name;
            public String idCardNumber;
            public String address;
            public String gender;
            public String nation;
            public String issuingAuthority;
            public String startDate;
            public String endDate;
            public String cardFront;
            public String cardBack;

            public IdCardInfo(String name, String idCardNumber, String address, String gender, String nation,
                              String issuingAuthority, String startDate, String endDate, String cardFront, String cardBack) {
                this.name = name;
                this.idCardNumber = idCardNumber;
                this.address = address;
                this.gender = gender;
                this.nation = nation;
                this.issuingAuthority = issuingAuthority;
                this.startDate = startDate;
                this.endDate = endDate;
                this.cardFront = cardFront;
                this.cardBack = cardBack;
            }
        }

        @lombok.Data
        public class ContactInfo {
            public String contactNameOne;
            public String contactPhoneOne;
            public String contactTypeOne;
            public String contactNameTwo;
            public String contactPhoneTwo;
            public String contactTypeTwo;

            public ContactInfo(String contactNameOne, String contactPhoneOne, String contactTypeOne,
                               String contactNameTwo, String contactPhoneTwo, String contactTypeTwo) {
                this.contactNameOne = contactNameOne;
                this.contactPhoneOne = contactPhoneOne;
                this.contactTypeOne = contactTypeOne;
                this.contactNameTwo = contactNameTwo;
                this.contactPhoneTwo = contactPhoneTwo;
                this.contactTypeTwo = contactTypeTwo;
            }
        }

        @lombok.Data
        public class DeviceInfo {
            public String ip;
            public String gps;
            public String phoneBrand;
            public String phoneModel;
            public String systemVersion;
            public String networkType;
            public String oaid;

            public DeviceInfo(String ip, String gps, String phoneBrand, String phoneModel,
                              String systemVersion, String networkType, String oaid) {
                this.ip = ip;
                this.gps = gps;
                this.phoneBrand = phoneBrand;
                this.phoneModel = phoneModel;
                this.systemVersion = systemVersion;
                this.networkType = networkType;
                this.oaid = oaid;
            }
        }

        @lombok.Data
        public class ExtendInfo {
            public String remark;

            public ExtendInfo(String remark) {
                this.remark = remark;
            }
        }

        @lombok.Data
        public class LivingBody {
            public String faceImgUrl;
            public String checkedFaceTime;
            public String faceScore;

            public LivingBody(String faceImgUrl, String checkedFaceTime, String faceScore) {
                this.faceImgUrl = faceImgUrl;
                this.checkedFaceTime = checkedFaceTime;
                this.faceScore = faceScore;
            }
        }


    }

}


