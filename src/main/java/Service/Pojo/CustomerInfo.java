package Service.Pojo;

import Service.Util.Universal.DateHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Data
public class CustomerInfo {

    private String name;                       // 姓名
    private String idCardNumber;               // 身份证号
    private String phoneNumber;                // 手机号
    private String gender;                     // 性别
    private String ethnicity;                  // 民族
    private String idCardStartDate;              // 身份证起始日
    private String idCardEndDate;                // 身份证到期日
    private String birthDate;                    // 出生日期
    private String idCardAddress;              // 证件地址
    private String highestEducation;           // 最高学历
    private String residenceProvince;          // 居住地-省
    private String residenceCity;              // 居住地-市
    private String residenceDistrict;          // 居住地-区
    private String residenceAddress;           // 居住地-地址
    private String companyName;                // 公司名称
    private String companyIndustry;            // 公司行业
    private String position;                   // 职务
    private Integer monthlyIncome;              // 月收入
    private String companyProvince;            // 公司-省
    private String companyCity;                // 公司-市
    private String companyDistrict;            // 公司-区
    private String companyAddress;             // 公司-地址
    private String maritalStatus;              // 婚姻状况
    private String email;                      // 邮箱
    private String customerId;                 // 客户号

    public CustomerInfo() {
        this.name = generateRandomName();
        this.phoneNumber = PhoneNumberGeneratorHandler.generatePhoneNumber();  // 随机手机号
        this.ethnicity = generateRandomEthnicity(); // 随机民族
        this.idCardStartDate = DateHandler.getTodayY_M_DString(); // 当前日期
        this.idCardEndDate = DateHandler.nextSomeMonth(idCardStartDate, 120); // 默认有效期10年
        this.birthDate = generateRandomBirthday(); // 随机出生日期
        this.idCardNumber = generateRandomIdCard(birthDate);
        this.gender = generateGenderFromIdCard(idCardNumber); // 随机性别
        this.idCardAddress = generateRandomAddress(); // 随机地址
        this.highestEducation = generateRandomEducation(); // 随机学历
        this.residenceProvince = generateRandomProvince(); // 随机省份
        this.residenceCity = generateRandomCity(); // 随机城市
        this.residenceDistrict = generateRandomDistrict(); // 随机区
        this.residenceAddress = generateRandomResidenceAddress(); // 随机居住地址
        this.companyName = generateRandomCompanyName(); // 随机公司名
        this.companyIndustry = generateRandomIndustry(); // 随机行业
        this.position = generateRandomPosition(); // 随机职务
        this.monthlyIncome = generateRandomIncome(); // 随机月收入
        this.companyProvince = generateRandomProvince(); // 随机公司省份
        this.companyCity = generateRandomCity(); // 随机公司城市
        this.companyDistrict = generateRandomDistrict(); // 随机公司区
        this.companyAddress = generateRandomCompanyAddress(); // 随机公司地址
        this.maritalStatus = generateRandomMaritalStatus(); // 随机婚姻状况
        this.email = generateRandomEmail(); // 随机邮箱
        this.customerId = generateRandomCustomerId(); // 随机客户ID
    }

    // 随机生成姓名
    private static final String[] FIRST_NAMES;
    private static final String[] LAST_NAMES;
    private static final Random RANDOM = new Random();

    static {
        // 70个常见姓氏
        FIRST_NAMES = new String[] {
                "张", "李", "王", "赵", "钱", "孙", "周", "吴", "郑", "冯",
                "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤",
                "许", "何", "吕", "施", "孔", "曹", "严", "华", "金", "魏",
                "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
                "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦",
                "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
                "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺"
        };

        // 200个名字常用字
        LAST_NAMES = new String[] {
                "伟", "芳", "娜", "秀英", "敏", "静", "丽", "强", "磊", "军",
                "洋", "勇", "艳", "杰", "娟", "涛", "明", "超", "秀兰", "霞",
                "平", "刚", "桂英", "鹏", "华", "红", "玉兰", "文", "建", "欣",
                "桂兰", "云", "建国", "宁", "龙", "桂珍", "凤英", "梅", "金凤",
                "健", "玉梅", "秀珍", "海燕", "晨", "冬梅", "文华", "荣", "婷婷",
                "雪", "玉英", "旭", "红梅", "佳", "倩", "琴", "瑜", "晶", "华",
                "颖", "凡", "璐", "玮", "琳", "璇", "琬", "琪", "琛", "瑶",
                "瑾", "璋", "珏", "琦", "玮", "琮", "珉", "珏", "珈", "珺",
                "航", "宇", "昊", "浩", "博", "然", "轩", "宸", "睿", "哲",
                "俊", "峻", "熙", "煜", "熠", "煊", "灿", "炎", "焱", "磊",
                "森", "林", "彬", "松", "柏", "枫", "桐", "梓", "楠", "楷",
                "杨", "柳", "桂", "梅", "兰", "竹", "菊", "荷", "莲", "蓉",
                "芷", "若", "蕙", "茜", "菲", "薇", "蕾", "菁", "蔓", "蕙",
                "超", "越", "飞", "翔", "鹏", "程", "展", "翼", "冲", "霄",
                "诚", "信", "仁", "义", "礼", "智", "勇", "忠", "孝", "节",
                "毅", "刚", "强", "坚", "韧", "恒", "勤", "奋", "进", "取",
                "安", "宁", "静", "和", "顺", "康", "泰", "祥", "瑞", "福",
                "乐", "欣", "悦", "怡", "恬", "愉", "畅", "达", "通", "明",
                "智", "慧", "聪", "敏", "捷", "灵", "巧", "妙", "玄", "奥",
                "文", "雅", "儒", "士", "彦", "卿", "君", "子", "儒", "侠"
        };
    }

    private String generateRandomName() {
        return FIRST_NAMES[RANDOM.nextInt(FIRST_NAMES.length)] +
                LAST_NAMES[RANDOM.nextInt(LAST_NAMES.length)];
    }

    // 随机生成身份证号（模拟）
    private String generateRandomIdCard(String birthDate) {
        String outputDate = null;
        // 定义输入和输出的日期格式
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            // 将字符串解析为日期
            java.util.Date date = inputFormat.parse(birthDate);
            // 格式化日期为新的格式
            outputDate = outputFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder idCard = new StringBuilder(outputDate); // 开头8位是出生日期
        // 生成前6位地址码，模拟为随机值（假设为北京市）
        idCard.insert(0, "110101"); // 110101代表北京市
        // 生成随机的8位序列号
        Random random = new Random();
        String sequenceNumber = String.format("%03d", random.nextInt(1000)); // 3位序列号
        idCard.append(sequenceNumber);
        // 最后一位是性别位（奇数表示男性，偶数表示女性）
        int genderFlag = random.nextInt(2); // 0为男，1为女
        char genderChar = (genderFlag == 0) ? '1' : '2'; // 偶数是女，奇数是男
        idCard.append(genderChar);
        return idCard.toString();
    }

    /*// 随机生成手机号
    private String generateRandomPhoneNumber() {
        return "138" + (100000000 + new Random().nextInt(900000000));
    }*/

    // 根据身份证号的第17位来判断性别
    private String generateGenderFromIdCard(String idCardNumber) {
        char genderChar = idCardNumber.charAt(16); // 第17位 (注意：Java中是从0开始的)
        return (genderChar % 2 == 0) ? "女" : "男";  // 偶数为女，奇数为男
    }

    // 随机生成民族
    private String generateRandomEthnicity() {
        String[] ethnicities = {"汉族", "蒙古族", "回族", "藏族", "维吾尔族"};
        return ethnicities[new Random().nextInt(ethnicities.length)];
    }

    private static String generateRandomBirthday() {
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 计算出最小允许出生日期（18年前）
        LocalDate minBirthDate = today.minusYears(18);
        // 计算出最大允许出生日期（60年前）
        LocalDate maxBirthDate = today.minusYears(60);
        // 随机生成一个出生日期，范围在 maxBirthDate 和 minBirthDate 之间
        Random random = new Random();
        int year = random.nextInt(minBirthDate.getYear() - maxBirthDate.getYear() + 1) + maxBirthDate.getYear();
        // 随机生成一个月（1-12）
        int month = random.nextInt(12) + 1;
        // 获取该月的最大天数
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();
        // 随机生成一个天数（1到最大天数之间）
        int day = random.nextInt(maxDay) + 1;
        // 创建一个随机的出生日期
        LocalDate randomBirthday = LocalDate.of(year, month, day);
        // 格式化为 "YYYY-MM-DD"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return randomBirthday.format(formatter);
    }

    // 随机生成地址
    private String generateRandomAddress() {
        String[] addresses = {"北京市朝阳区", "上海市浦东新区", "广州市天河区", "深圳市南山区"};
        return addresses[new Random().nextInt(addresses.length)];
    }

    // 随机生成学历
    private String generateRandomEducation() {
        String[] educations = {"博士", "硕士", "本科", "大专", "中专"};
        return educations[new Random().nextInt(educations.length)];
    }

    // 随机生成省份
    private String generateRandomProvince() {
        String[] provinces = {"北京市", "广东省", "浙江省", "江苏省", "四川省"};
        return provinces[new Random().nextInt(provinces.length)];
    }

    // 随机生成城市
    private String generateRandomCity() {
        String[] cities = {"北京市", "上海市", "广州市", "深圳市", "杭州市"};
        return cities[new Random().nextInt(cities.length)];
    }

    // 随机生成区
    private String generateRandomDistrict() {
        String[] districts = {"朝阳区", "浦东新区", "天河区", "南山区", "西湖区"};
        return districts[new Random().nextInt(districts.length)];
    }

    // 随机生成居住地址
    private String generateRandomResidenceAddress() {
        String[] addresses = {"某街道某小区", "某小区B栋", "某街道C单元", "某大厦一层"};
        return addresses[new Random().nextInt(addresses.length)];
    }

    // 随机生成公司名称
    private String generateRandomCompanyName() {
        String[] companies = {"百度", "阿里巴巴", "腾讯", "京东", "华为"};
        return companies[new Random().nextInt(companies.length)];
    }

    // 随机生成公司行业
    private String generateRandomIndustry() {
        String[] industries = {"互联网", "金融", "制造", "教育", "健康"};
        return industries[new Random().nextInt(industries.length)];
    }

    // 随机生成公司地址
    private String generateRandomCompanyAddress() {
        String[] addresses = {
                "北京市朝阳区建国路88号华贸大厦",
                "上海市浦东新区陆家嘴金融区88号世纪大厦",
                "广州市天河区珠江新城5号中信大厦",
                "深圳市南山区科技园12号华为大厦",
                "杭州市西湖区西溪路18号阿里大厦"
        };
        return addresses[new Random().nextInt(addresses.length)];
    }

    // 随机生成职务
    private String generateRandomPosition() {
        String[] positions = {"工程师", "经理", "开发者", "设计师", "产品经理"};
        return positions[new Random().nextInt(positions.length)];
    }

    // 随机生成月收入
    private Integer generateRandomIncome() {
        Random random = new Random();
        // 生成一个不小于2000的四位数
        return 2000 + random.nextInt(8000);  // 8000 是 9999 - 2000 + 1
    }

    // 随机生成婚姻状况
    private String generateRandomMaritalStatus() {
        Random random = new Random();
        int status = random.nextInt(3);  // 生成一个 0 到 2 之间的随机整数
        switch (status) {
            case 0:
                return "已婚";
            case 1:
                return "未婚";
            case 2:
                return "离异";
            default:
                return "未知";  // 这个情况理论上不会发生，只是为了保证方法完整性
        }
    }

    // 随机生成邮箱
    private String generateRandomEmail() {
        String[] domains = {"example.com", "test.com", "demo.com"};
        return "user" + new Random().nextInt(1000) + "@" + domains[new Random().nextInt(domains.length)];
    }

    // 随机生成客户ID
    private String generateRandomCustomerId() {
        return "CUST" + new Random().nextInt(100000);
    }

    public static void main(String[] args) {
       /* CustomerInfo customerInfo = new CustomerInfo();
        System.out.println(DataConverterHandler.objectToJson(customerInfo));*/
    }


}

