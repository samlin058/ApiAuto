package Data.BusinessLayer.LoanMarket;

import Data.DBoperation.LoanMarket.routing;
import Data.ApisLayer.LoanMarket.BackDoorContoller;
import Data.ApisLayer.LoanMarket.UserController;
import Data.Ro.LoanMarket.BackDoorContollerEncryptRo;
import Data.Ro.LoanMarket.UserControllerLoginRo;
import Service.Core.ProjectEnum;
import Service.Util.Universal.*;
import Service.Util.variable.IDNoGeneratorHandler;
import Service.Util.variable.PhoneNumberGeneratorHandler;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XYJH5User {

    public static void main(String[] args) {
        // 获取这个渠道用户，且规避掉该渠道当前配置资方的前筛条件
        getUserIdByChannel("luandaoTest2", true);
        // 获取这个渠道用户（完整信息）
        // getUserId("luandaoTest2", true);
        // 获取这个渠道下没有用户信息的用户（有身份信息和ocr）
        // getUserIdWithoutUserInfo("luandaoTest2",true);
        // 指定渠道生成指定手机号用户
        // getUserId("13705710000","luandaoTest2",true);
        // getUserWithoutUserData("luandaoTest2",true);
        // getUser("13120250827", "luandaoTest", true);
        /*deleteUserByPhone("18555020689");
        deleteUserByPhone("18326044030");*/
    }

    public static String getUserIdByChannel(String channelCode, boolean isAllUserInfo) {
        List<String> productList = routing.getRoutingCapital(channelCode);
        for (int i = 0; i < 100; i++) {
            String uid = getUserId(channelCode, isAllUserInfo);
            boolean hitAny = false;
            if (productList != null && !productList.isEmpty()) {
                for (String code : productList) {
                    if (isPreScreened(uid, code)) {
                        hitAny = true;
                        break;
                    }
                }
            }
            if (!hitAny) {
                System.out.println("获取到未命中渠道可路由资方前筛的用户 uid=" + uid);
                return uid;
            }
            System.out.println("第" + (i + 1) + "次生成用户命中前筛，重新生成...");
        }
        throw new IllegalStateException("连续50次生成的用户均命中前置校验，停止生成");
    }

    /**
     * 指定渠道获得一个随机号码的用户（有身份证，ocr，个人信息）
     *
     * @param channelCode   渠道code
     * @param isAllUserInfo 是否填充完整用户信息
     * @return 用户uid
     */
    public static String getUserId(String channelCode, boolean isAllUserInfo) {
        String phone = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket);
        // 如果是T3渠道或者返利渠道,则走联登注册,否则走正常注册登录接口
        return getUserUid(phone, channelCode, isAllUserInfo);
    }


    /**
     * 指定渠道获得一个随机号码的用户（无个人信息）
     *
     * @param channelCode   渠道code
     * @param isAllUserInfo 是否填充完整用户信息
     * @return 用户uid
     */
    public static String getUserIdWithoutUserInfo(String channelCode, boolean isAllUserInfo) {
        String phone = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket);
        // 如果是T3渠道或者返利渠道,则走联登注册,否则走正常注册登录接口
        return getUserUidWithoutUserInfo(phone, channelCode, isAllUserInfo);
    }

    /**
     * 指定渠道获取一个能通过productCode资方前筛的用户，该用户无个人信息
     *
     * @param channelCode   渠道code
     * @param isAllUserInfo 是否填充完整用户信息
     * @return 用户uid
     */
    public static String getUserIdWithoutUserInfoByProductCode(String channelCode, String productCode, boolean isAllUserInfo) {
        for (int i = 0; i < 50; i++) {
            String phone = PhoneNumberGeneratorHandler.findAvailablePhoneByProjectCode(ProjectEnum.LoanMarket);
            String uid = getUserUid(phone, channelCode, isAllUserInfo);
            if (!isPreScreened(uid, productCode)) {
                System.out.println("获取到未命中前筛的用户 uid=" + uid);
                return uid;
            }
            System.out.println("第" + (i + 1) + "次生成用户命中前筛，重新生成...");
        }
        throw new IllegalStateException("连续50次生成的用户均命中前置校验，停止生成");
    }

    /**
     * 指定手机和渠道生成用户
     *
     * @param phone         手机号
     * @param channelCode   渠道code
     * @param isAllUserInfo 是否全量用户信息
     * @return 用户Uid
     */
    public static String getUserId(String phone, String channelCode, boolean isAllUserInfo) {
        String sql = "SELECT id FROM tbl_user WHERE mobile = ? and platform=?";
        String queryResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, phone, channelCode);
        if (!"{}".equals(queryResult)) {
            return JsonHandler.readStringByJsonPath("$.id", queryResult);
        } else {
            return getUserUid(phone, channelCode, isAllUserInfo);
        }
    }

    /**
     * 根据渠道生成用户，且能通过下游资方的前筛规则
     *
     * @param channelCode   渠道
     * @param productCode   资方
     * @param isAllUserInfo 是否全信息
     * @return uid
     */
    public static String getAvailableUsersByProductCode(String channelCode, String productCode, boolean isAllUserInfo) {
        for (int i = 0; i < 50; i++) {
            String uid = getUserId(channelCode, isAllUserInfo);
            if (!isPreScreened(uid, productCode)) {
                System.out.println("获取到未命中前筛的用户 uid=" + uid);
                return uid;
            }
            System.out.println("第" + (i + 1) + "次生成用户命中前筛，重新生成...");
        }
        throw new IllegalStateException("连续50次生成的用户均命中前置校验，停止生成");
    }

    /**
     * 根据渠道指定手机号生成用户，且能通过下游资方的前筛规则
     *
     * @param channelCode   渠道
     * @param productCode   资方
     * @param isAllUserInfo 是否全信息
     * @return uid
     */
    public static String getAvailableUsersByProductCode(String phone, String channelCode, String productCode, boolean isAllUserInfo) {
        String uid = getUserId(phone, channelCode, isAllUserInfo);
        if (!isPreScreened(uid, productCode)) {
            System.out.println("获取到未命中前筛的用户 uid=" + uid);
            return uid;
        }
        return null;
    }

    public static String getAvailableUsersByProductCodeList(String phone, String channelCode, List<String> productCode, boolean isAllUserInfo) {
        String uid = getUserId(phone, channelCode, isAllUserInfo);
        if (uid == null) {
            return null;
        }
        if (productCode == null || productCode.isEmpty()) {
            return uid;
        }
        for (String code : productCode) {
            if (isPreScreened(uid, code)) {
                return null;
            }
        }
        System.out.println("获取到未命中所有资方前筛的用户 uid=" + uid);
        return uid;
    }


    /**
     * 根据指定渠道，获取指定数量用户
     *
     * @param num         数量
     * @param channelCode 渠道
     * @return uid数组
     */
    public static List<String> getUserByNum(int num, String channelCode) {
        List<String> uidList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            uidList.add(XYJH5User.getUserId(channelCode, false));
        }
        return uidList;
    }

    /**
     * 通过手机号删除用户所有相关的数据
     *
     * @param phone 手机号
     */
    public static void deleteUserByPhone(String phone) {
        String getUidSql = "select id from tbl_user where mobile=? ";
        String uidJson = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, getUidSql, phone);
        String uid = JsonHandler.readStringByJsonPath("$.id", uidJson);
        if (!StringHandler.isEmpty(uid)) {
            extracted(uid);
            System.out.println("删除完成");
        } else {
            System.out.println("未找到" + phone + "用户");
        }
    }

    /**
     * 通过uid删除用户所有相关的数据
     *
     * @param uid 用户id
     */
    public static void deleteUserByUid(String uid) {
        if (!StringHandler.isEmpty(uid)) {
            extracted(uid);
            System.out.println("删除完成");
        } else {
            System.out.println("未找到" + uid + "用户");
        }
    }

    /**
     * sql删除用户相关表数据
     *
     * @param uid 用户id
     */
    private static void extracted(String uid) {
        String deleteSql1 = " delete from tbl_user  where id=? ";
        String deleteSql2 = " delete from tbl_user_info  where uid=? ";
        String deleteSql3 = " delete from tbl_user_contacts  where uid=? ";
        String deleteSql4 = " delete from tbl_ocr_record where uid =? ";
        String deleteSql5 = " delete from tbl_faceid_record where uid=? ";
        String deleteSql6 = " delete FROM tbl_user_real_name where uid=? ";
        String deleteSql7 = " delete FROM tbl_user_live where uid =? ";
        String deleteSql8 = " delete FROM tbl_user_card  where uid=? ";
        String deleteSql9 = " delete from tbl_user_card_bind_task  where uid=? ";
        String deleteSql10 = " delete from tbl_user_device_info where uid=?  ";
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql1, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql2, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql3, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql4, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql5, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql6, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql7, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql8, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql9, uid);
        JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, deleteSql10, uid);
    }

    /**
     * 根据手机号和渠道创建用户UID
     *
     * @param phone       手机号
     * @param channelCode 渠道号
     * @return 用户uid
     */
    private static String getUserUid(String phone, String channelCode, boolean isAllUserInfo) {
        String uid;
        if ("ZY_T3".equals(channelCode) || "ZY_FL".equals(channelCode)) {
            String url = "http://lumos-h5.torch.wke-office.test.wacai.info/lumos/home?channelCode=" + channelCode + "&jointToken=" + getEncryptReq(phone, channelCode);
            HttpHandler.getHttp(url, null);
            String sql = "select id from tbl_user where mobile = ?";
            String queryResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, phone);
            if (StringHandler.isEmpty(queryResult)) {
                System.out.println(channelCode + "渠道注册失败，请检查联登服务");
                return null;
            }
            uid = JsonHandler.readStringByJsonPath("$.id", queryResult);
        } else {
            UserController userController = new UserController();
            // 用户注册
            String result = userController.login(new UserControllerLoginRo(phone, channelCode));
            uid = String.valueOf(JsonHandler.readValueByJsonPath("$.data.id", result));
        }
        if (uid == null) {
            return null;
        } else {
            // 补充用户信息
            fillUserInfo(uid, isAllUserInfo);
        }
        System.out.println("注册手机号: " + phone + "，" + "该用户id：" + uid);
        return uid;
    }

    /**
     * 根据手机号和渠道创建用户UID
     *
     * @param phone       手机号
     * @param channelCode 渠道号
     * @return 用户uid
     */
    private static String getUserUidWithoutUserInfo(String phone, String channelCode, boolean isAllUserInfo) {
        String uid;
        if ("ZY_T3".equals(channelCode) || "ZY_FL".equals(channelCode)) {
            String url = "http://lumos-h5.torch.wke-office.test.wacai.info/lumos/home?channelCode=" + channelCode + "&jointToken=" + getEncryptReq(phone, channelCode);
            HttpHandler.getHttp(url, null);
            String sql = "select id from tbl_user where mobile = ?";
            String queryResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, phone);
            if (StringHandler.isEmpty(queryResult)) {
                System.out.println(channelCode + "渠道注册失败，请检查联登服务");
                return null;
            }
            uid = JsonHandler.readStringByJsonPath("$.id", queryResult);
        } else {
            UserController userController = new UserController();
            // 用户注册
            String result = userController.login(new UserControllerLoginRo(phone, channelCode));
            uid = String.valueOf(JsonHandler.readValueByJsonPath("$.data.id", result));
        }
        if (uid == null) {
            return null;
        } else {
            // 补充用户信息
            fillUserInfoWithoutUserData(uid, isAllUserInfo);
        }
        System.out.println("注册手机号: " + phone + "，" + "该用户id：" + uid);
        return uid;
    }

    /**
     * 完善用户信息（ocr认证，人脸识别，设备信息）
     *
     * @param uid 用户id
     */
    private static void fillUserInfo(String uid, Boolean isAllUserInfo) {
        if (isAllUserInfo) {
            String sql = "insert into tbl_user_device_info  (uid,os,os_version,version,ip,gps_lat,gps_lng) values(?,'iOS','16.6','iPhone 16','172.18.32.104','30.22034','120.27657');";
            JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, sql, uid);
        }
        // 用户资料
        HashMap<String, String> params = new HashMap<>();
        // 模拟人脸和OCR认证
        params.put("uid", uid);
        params.put("mockType", "ALL");
        HttpHandler.getHttp("http://automation-web.wacai-amc.wke-office.test.wacai.info/sandals-hermes/torch/mockFaceAndOcr", params);
        // 创建mock用户数据
        params.remove("mockType");
        HttpHandler.getHttp("http://automation-web.wacai-amc.wke-office.test.wacai.info/sandals-hermes/torch/creatUserData", params);
    }

    private static void fillUserInfoWithoutUserData(String uid, Boolean isAllUserInfo) {
        if (isAllUserInfo) {
            String sql = "insert into tbl_user_device_info  (uid,os,os_version,version,ip,gps_lat,gps_lng) values(?,'iOS','16.6','iPhone 16','172.18.32.104','30.22034','120.27657');";
            JDBCHandler.AddDeleteAlter(ProjectEnum.LoanMarket, sql, uid);
        }
        // 用户资料
        HashMap<String, String> params = new HashMap<>();
        // 模拟人脸和OCR认证
        params.put("uid", uid);
        params.put("mockType", "ALL");
        HttpHandler.getHttp("http://automation-web.wacai-amc.wke-office.test.wacai.info/sandals-hermes/torch/mockFaceAndOcr", params);
    }

    /**
     * 特定渠道请求参数加密
     *
     * @param phone       手机号
     * @param channelCode 渠道号
     * @return 加密请求
     */
    private static String getEncryptReq(String phone, String channelCode) {
        BackDoorContoller backDoorContoller = new BackDoorContoller();
        if ("ZY_T3".equals(channelCode) || "ZY_FL".equals(channelCode)) {
            String result = backDoorContoller.encrypt(new BackDoorContollerEncryptRo(phone, channelCode));
            return JsonHandler.readStringByJsonPath("$.data", result);
        } else {
            System.out.println("该渠道暂无加密接口");
            return null;
        }
    }

    /**
     * 是否命中前筛
     *
     * @param uid         用户id
     * @param productCode 资方code
     * @return Boolean
     */
    private static Boolean isPreScreened(String uid, String productCode) {
        UserInfo userInfo = getUserCheckInfo(uid);
        boolean hit = false;
        String precheckRuleSql = "select  * from tbl_precheck_rule_config where product_code = ?";
        String precheckRuleSqlResult = JDBCHandler.queryRowsAsJsonArray(ProjectEnum.LoanMarket, precheckRuleSql, productCode);
        if (!"[]".equals(precheckRuleSqlResult)) {
            // 遍历规则列表（仅处理启用的 status=1）
            List<String> types = JsonHandler.readStringListByJsonPath("$[*].type", precheckRuleSqlResult);
            for (int i = 0; i < types.size(); i++) {
                Integer status = JsonHandler.readIntegerByJsonPath("$[" + i + "].status", precheckRuleSqlResult);
                if (status == null || status != 1) {
                    continue;
                }
                String type = types.get(i);
                String value = JsonHandler.readStringByJsonPath("$[" + i + "].value", precheckRuleSqlResult);
                if (StringHandler.isEmpty(type)) {
                    continue;
                }

                switch (type) {
                    case "AGE": {
                        // value 形如: {"ageMin":23,"ageMax":55}
                        Integer min = JsonHandler.readIntegerByJsonPath("$.ageMin", value);
                        Integer max = JsonHandler.readIntegerByJsonPath("$.ageMax", value);
                        if (min != null && userInfo.AGE < min) {
                            System.out.println("预筛命中 AGE: age<min -> " + userInfo.AGE + "<" + min);
                            hit = true;
                        }
                        if (max != null && userInfo.AGE > max) {
                            System.out.println("预筛命中 AGE: age>max -> " + userInfo.AGE + ">" + max);
                            hit = true;
                        }
                        break;
                    }
                    case "CONTACT_SAME_MOBILE": {
                        // value: "true" 表示开启校验：联系人手机号与本人相同则命中
                        Boolean on = JsonHandler.readBooleanByJsonPath("$", value);
                        if (Boolean.TRUE.equals(on)) {
                            if (userInfo.contact_mobile != null && userInfo.MOBILE != null) {
                                for (String c : userInfo.contact_mobile) {
                                    if (userInfo.MOBILE.equals(c)) {
                                        System.out.println("预筛命中 CONTACT_SAME_MOBILE");
                                        hit = true;
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case "FACE_SCORE": {
                        // value 为最低分阈值，低于则命中
                        Double threshold = JsonHandler.readDoubleByJsonPath("$", value);
                        if (threshold != null && userInfo.FACE_SCORE != null && userInfo.FACE_SCORE < threshold) {
                            System.out.println("预筛命中 FACE_SCORE: " + userInfo.FACE_SCORE + "<" + threshold);
                            hit = true;
                        }
                        break;
                    }
                    case "ID_CARD_ADDR": {
                        // value 为逗号分隔禁用地区，命中任一即命中
                        List<String> blocks = StringHandler.getValueByRegex(value, "([^,，]+)");
                        if (blocks == null || blocks.isEmpty()) {
                            // 简单分割回退
                            String[] arr = value.split(",");
                            blocks = new ArrayList<>();
                            for (String s : arr) blocks.add(s);
                        }
                        if (userInfo.ID_CARD_ADDR != null) {
                            for (String b : blocks) {
                                if (userInfo.ID_CARD_ADDR.contains(b.trim())) {
                                    System.out.println("预筛命中 ID_CARD_ADDR: " + b);
                                    hit = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "ID_CARD_PREFIX": {
                        // value 为逗号分隔前缀（如 "11"），若身份证以任一此前缀开头则命中
                        String[] prefixes = value.split(",");
                        if (!StringHandler.isEmpty(userInfo.ID_CARD)) {
                            for (String p : prefixes) {
                                String t = p.trim();
                                if (t.isEmpty()) continue;
                                if (userInfo.ID_CARD.startsWith(t)) {
                                    System.out.println("预筛命中 ID_CARD_PREFIX: " + t);
                                    hit = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case "ID_CARD_VALID_DATE": {
                        // value 为最小剩余有效天数阈值，若剩余天数 < 阈值则命中
                        Integer minRemain = JsonHandler.readIntegerByJsonPath("$", value);
                        if (minRemain != null && userInfo.ID_CARD_VALID_DATE < minRemain) {
                            System.out.println("预筛命中 ID_CARD_VALID_DATE: " + userInfo.ID_CARD_VALID_DATE + "<" + minRemain);
                            hit = true;
                        }
                        break;
                    }
                    case "MOBILE_PREFIX": {
                        // value 为逗号分隔手机号前缀（如 170,171,...），命中任一则命中
                        String[] prefixes = value.split(",");
                        if (!StringHandler.isEmpty(userInfo.MOBILE)) {
                            for (String p : prefixes) {
                                String t = p.trim();
                                if (t.isEmpty()) continue;
                                if (userInfo.MOBILE.startsWith(t)) {
                                    System.out.println("预筛命中 MOBILE_PREFIX: " + t);
                                    hit = true;
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    default:
                        // 未识别类型，跳过
                        break;
                }
            }
        }
        return hit;
    }

    /**
     * @param uid 用户id
     */
    public static UserInfo getUserCheckInfo(String uid) {
        String sql = "select tu.mobile,tic.idno, tic.identification_address ,tfr.confidence ,tic.validity_end_date from tbl_user tu join tbl_user_info tui on tu.id =tui.uid join tbl_id_card tic on tu.id =tic.uid join tbl_faceid_record tfr on tu.id =tfr.uid where tu.id=?;";
        String sqlResult = JDBCHandler.queryFirstRowAsJson(ProjectEnum.LoanMarket, sql, uid);
        String idno = JsonHandler.readStringByJsonPath("$.idno", sqlResult);
        String validity_end_date = JsonHandler.readStringByJsonPath("$.validity_end_date", sqlResult);
        String getContactSql = "select mobile from tbl_user_contacts tuc where uid = ?";
        String getContactSqlResult = JDBCHandler.queryRowsAsJsonArray(ProjectEnum.LoanMarket, getContactSql, uid);
        UserInfo userInfo = new UserInfo();
        userInfo.setAGE(IDNoGeneratorHandler.calculateAgeFromIdNo(idno));
        userInfo.setContact_mobile(JsonHandler.readStringListByJsonPath("$.*.mobile", getContactSqlResult));
        userInfo.setFACE_SCORE(JsonHandler.readDoubleByJsonPath("$.confidence", sqlResult));
        userInfo.setMOBILE(JsonHandler.readStringByJsonPath("$.mobile", sqlResult));
        userInfo.setID_CARD_ADDR(JsonHandler.readStringByJsonPath("$.identification_address", sqlResult));
        userInfo.setID_CARD(idno);
        userInfo.setID_CARD_VALID_DATE(DateHandler.daysBetween(validity_end_date));
        return userInfo;
    }

    /*public static void main(String[] args) {
        String a =  "山西省忻州市掸炙怎路4477号砰胜小区3单元256室";
        System.out.println(getProvince(a));
    }*/


    @Data
    public static class UserInfo {
        //1、年龄
        public int AGE;
        //2、手机号
        public String MOBILE;
        //3、联系人手机号
        public List<String> contact_mobile;
        //4、活体验证分值
        public Double FACE_SCORE;
        //5、户籍地区
        public String ID_CARD_ADDR;
        //6、身份证前缀
        public String ID_CARD;
        //7、身份证有效期X天
        public int ID_CARD_VALID_DATE;
    }


}
