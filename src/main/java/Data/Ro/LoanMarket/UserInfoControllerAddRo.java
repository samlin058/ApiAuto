package Data.Ro.LoanMarket;

import Data.ApisLayer.LoanMarket.EnumListController;
import Data.ApisLayer.LoanMarket.UserInfoController;
import Service.Annotations.AnnotationLibrary.Body;
import Service.Annotations.AnnotationLibrary.Header;
import Service.Annotations.AnnotationProcessor.DenpendencesProcessor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@lombok.Data
public class UserInfoControllerAddRo extends UserInfoController {

    static {
        DenpendencesProcessor.roDendenpence(UserInfoControllerAddRo.class);
    }

    @Header
    public String X_Login_Uid;
    @Body
    public String area;
    @Body
    public String address;
    @Body
    public String occupation;// OccupationType
    @Body
    public String eduLevel;// 值为EduLevel的value
    @Body
    public String city;
    @Body
    public String provinceCode;
    @Body
    public String cityCode;
    @Body
    public String coName;
    @Body
    public String coProvince;
    @Body
    public String coCityCode;
    @Body
    public String coAreaCode;
    @Body
    public String coProvinceCode;
    @Body
    public String coCity;
    @Body
    public String coArea;
    @Body
    public String areaCode;
    @Body
    public String industryType;//IndustryType
    @Body
    public String province;
    @Body
    public Integer expectLoanAmt;
    @Body
    public String marriage;// 值为Marriage的value
    @Body
    public String salaryLevel;
    @Body
    public String email;

    public UserInfoControllerAddRo() {
    }

    public UserInfoControllerAddRo(String uid) {
        this.X_Login_Uid = uid;
    }


    public enum Marriage {
        UNMARRIED("未婚", "UNMARRIED"),
        MARRIED("已婚", "MARRIED"),
        DIVORCE("离异", "DIVORCE"),
        BEREAVE("丧偶", "BEREAVE"),
        REMARRY("再婚", "REMARRY"),
        UNKNOWN("未知", "UNKNOWN"),
        ;

        private String name;
        private String value;

        Marriage(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static Marriage getByCode(String code) {
            return Arrays.stream(Marriage.values()).filter(o -> o.getValue().equals(code)).findAny().orElse(null);
        }
    }

    public enum EduLevel {

        XX("小学", "XX"),
        CZ("初中", "CZ"),
        GZ("高中", "GZ"),

        JX("技校", "JX"),
        ZZ("中专", "ZZ"),

        DZ("大专", "DZ"),
        BK("本科", "BK"),
        SZ("硕士", "SZ"),
        BS("博士", "BS");

        private String name;
        private String value;

        EduLevel(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String getName() {
            return this.name;
        }

        public static EduLevel getCodeBy(String code) {
            return Arrays.stream(EduLevel.values()).filter(o -> o.getValue().equals(code)).findAny().orElse(null);
        }

    }


    public enum IndustryType {
        BEAUTY_CLEANING_SECURITY("BEAUTY_CLEANING_SECURITY", "美容美发/保洁/家政/安保"),
        RESTAURANT_ENTERTAINMENT_TOURISM_HOTEL("RESTAURANT_ENTERTAINMENT_TOURISM_HOTEL", "餐饮/娱乐/旅游/酒店"),
        RETAIL_SUPERMARKET_DEPARTMENT_STORE("RETAIL_SUPERMARKET_DEPARTMENT_STORE", "商超/百货/零售"),
        REAL_ESTATE_CONSTRUCTION_PROPERTY("REAL_ESTATE_CONSTRUCTION_PROPERTY", "房地产/建筑/物业"),
        AUTOMOBILE_MECHANICAL_ELECTRONICS("AUTOMOBILE_MECHANICAL_ELECTRONICS", "汽车/机械/电子"),
        LOGISTICS_PROCUREMENT_TRADE("LOGISTICS_PROCUREMENT_TRADE", "物流/采购/贸易"),
        ENERGY_ENVIRONMENT_CHEMICAL("ENERGY_ENVIRONMENT_CHEMICAL", "能源/环境/化工"),
        TELECOMMUNICATION_INTERNET_ECOMMERCE("TELECOMMUNICATION_INTERNET_ECOMMERCE", "通信/互联网/电子商务"),
        HOSPITAL_MEDICAL_MEDICAL_DEVICE("HOSPITAL_MEDICAL_MEDICAL_DEVICE", "医院/医药/医药器械"),
        SCHOOL_RESEARCH_EDUCATION_TRAINING("SCHOOL_RESEARCH_EDUCATION_TRAINING", "学校/科研/教育/培训"),
        BANK_INSURANCE_FINANCIAL_INSTITUTION("BANK_INSURANCE_FINANCIAL_INSTITUTION", "银行/保险/金融机构"),
        HR_ADMINISTRATION_LEGAL_CONSULTING("HR_ADMINISTRATION_LEGAL_CONSULTING", "人力/行政/法律/咨询"),
        GOVERNMENT_NONPROFIT_ORGANIZATION("GOVERNMENT_NONPROFIT_ORGANIZATION", "政府机构/非营利组织"),
        MEDIA_PUBLISHING_ENTERTAINMENT_SPORTS("MEDIA_PUBLISHING_ENTERTAINMENT_SPORTS", "媒体/出版/演艺/体育"),
        SCIENCE_RESEARCH_TECHNOLOGY_SERVICE("SCIENCE_RESEARCH_TECHNOLOGY_SERVICE", "科学研究/技术服务业"),
        AGRICULTURE_FORESTRY_ANIMAL_HUSBANDRY_FISHERY_OTHER("AGRICULTURE_FORESTRY_ANIMAL_HUSBANDRY_FISHERY_OTHER",
                "农林牧渔/其他");


        public String code;

        public String desc;

        IndustryType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getValue() {
            return this.code;
        }

        public String getDesc() {
            return this.desc;
        }

        public static List<IndustryType> exceptList() {
            return Arrays.asList(IndustryType.BANK_INSURANCE_FINANCIAL_INSTITUTION, IndustryType.HR_ADMINISTRATION_LEGAL_CONSULTING, IndustryType.GOVERNMENT_NONPROFIT_ORGANIZATION);
        }

        public static IndustryType random() {
            List<IndustryType> validTypes = Arrays.stream(IndustryType.values())
                    .filter(x -> !exceptList().contains(x))
                    .collect(Collectors.toList());
            return validTypes.get(new Random().nextInt(validTypes.size()));
        }

        public static IndustryType getByCode(String code) {
            return Arrays.stream(IndustryType.values()).filter(o -> o.getValue().equals(code)).findAny().orElse(null);
        }

    }

    public enum OccupationType {
        FARMER_WORKERS("FARMER_WORKERS", "农民、工人"),
        TEACHER("TEACHER", "教师"),
        OFFICE_WORKER("OFFICE_WORKER", "公司职员"),
        ENTREPRENEUR("ENTREPRENEUR", "私营业主"),
        FREELANCER("FREELANCER", "自由职业"),
        STUDENT("STUDENT", "在校学生"),
        SOLDIER("SOLDIER", "军人"),
        PROFESSIONAL_TECHNICIAN("PROFESSIONAL_TECHNICIAN", "专业技术人员"),
        CIVIL_SERVANT_OR_MANAGER("CIVIL_SERVANT_OR_MANAGER", "公务员或企事业单位负责人"),
        SERVICE_PERSONNEL("SERVICE_PERSONNEL", "社会生产服务及生活服务人员"),
        MANUFACTURING_WORKER("MANUFACTURING_WORKER", "生产制造及有关人员"),
        UNEMPLOYED_JOB_SEEKER("UNEMPLOYED_JOB_SEEKER", "无业待业"),
        OTHER("OTHER", "其他");

        public String code;

        public String desc;

        OccupationType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getValue() {
            return code;
        }

        public String getName() {
            return desc;
        }

        public static OccupationType getByCode(String code) {
            return Arrays.stream(OccupationType.values()).filter(o -> o.getValue().equals(code)).findAny().orElse(null);
        }

        public static List<OccupationType> exceptList() {
            return Arrays.asList(OccupationType.STUDENT);
        }

        public static OccupationType random() {
            List<OccupationType> validTypes = Arrays.stream(OccupationType.values())
                    .filter(x -> !exceptList().contains(x))
                    .collect(Collectors.toList());
            return validTypes.get(new Random().nextInt(validTypes.size()));
        }
    }

    public static void main(String[] args) {
        EnumListController enumListController = new EnumListController();
        System.out.println(enumListController.chinaArea());
    }

}


