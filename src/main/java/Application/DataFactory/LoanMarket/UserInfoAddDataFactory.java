package Application.DataFactory.LoanMarket;

import Data.Apis.LoanMarket.EnumListController;
import Data.Ro.LoanMarket.UserInfoControllerAddRo;
import Service.Util.variable.GenerationHandler;
import Service.Util.Universal.JsonHandler;
import Service.Util.Universal.StringHandler;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserInfoAddDataFactory {

    /**
     * 根据正交思路：
     * - 维度1：省份（全量覆盖，每省取一个市/区组合）
     * - 维度2：婚姻（覆盖全枚举）
     * - 维度3：学历（覆盖全枚举）
     * - 维度4：薪资（取一组代表值集合，或从示例/字典中给定）
     * - 维度5：行业类型（取一组代表值集合）
     * 通过轮转映射方式将维度2+分配到每个省，保证每个水平至少出现，减少组合爆炸。
     */
    public static List<UserInfoControllerAddRo> buildOrthogonalUserInfos() {
        List<AreaTriple> areas = loadProvinceCoverTriples();
        List<String> marriages = extractEnumValues(UserInfoControllerAddRo.Marriage.values());
        List<String> eduLevels = extractEnumValues(UserInfoControllerAddRo.EduLevel.values());
        List<String> industryTypes = extractEnumValues(UserInfoControllerAddRo.IndustryType.values());
        List<String> occupations = extractEnumValues(UserInfoControllerAddRo.OccupationType.values());

        // 其余枚举集合：根据需求可对接 /enum/list，这里使用代表集，确保多样性
        List<String> salaryLevels = Arrays.asList("L0_2K", "L2_3K", "L3_5K", "L5_8K", "L8_12K", "L12_20K", "L20K");

        List<UserInfoControllerAddRo> ros = new ArrayList<>();
        if (areas.isEmpty()) return ros;

        int m = marriages.size();
        int e = eduLevels.size();
        int s = salaryLevels.size();
        int ind = industryTypes.size();
        int occ = occupations.size();

        for (int i = 0; i < areas.size(); i++) {
            AreaTriple at = areas.get(i);
            UserInfoControllerAddRo ro = new UserInfoControllerAddRo();

            // 地址（居住地）
            ro.setProvince(at.provinceName);
            ro.setProvinceCode(at.provinceCode);
            ro.setCity(at.cityName);
            ro.setCityCode(at.cityCode);
            ro.setArea(at.areaName);
            ro.setAreaCode(at.areaCode);
            ro.setAddress(at.cityName + at.areaName + "测试路99号");

            // 公司地址（同省份覆盖，按规则映射）
            AreaTriple work = areas.get(i % areas.size());
            ro.setCoProvince(work.provinceName);
            ro.setCoProvinceCode(work.provinceCode);
            ro.setCoCity(work.cityName);
            ro.setCoCityCode(work.cityCode);
            ro.setCoArea(work.areaName);
            ro.setCoAreaCode(work.areaCode);
            ro.setCoName(work.cityName + "未来科技有限公司");

            // 正交轮转映射
            ro.setMarriage(marriages.get(i % m));
            ro.setEduLevel(eduLevels.get(i % e));
            ro.setSalaryLevel(salaryLevels.get(i % s));
            ro.setIndustryType(industryTypes.get(i % ind));
            ro.setOccupation(occupations.get(i % occ));

            // 其他字段
            ro.setEmail(buildEmail(GenerationHandler.generateRandomInt(100, 9999), i));
            ro.setExpectLoanAmt(buildLoanAmtByIndex(i));

            ros.add(ro);
        }
        return ros;
    }

    /**
     * 构建单个随机的UserInfoControllerAddRo对象
     * 所有属性值都是随机生成的
     */
    public static UserInfoControllerAddRo buildRandomUserInfo() {
        List<AreaTriple> areas = loadProvinceCoverTriples();
        List<String> marriages = extractEnumValues(UserInfoControllerAddRo.Marriage.values());
        List<String> eduLevels = extractEnumValues(UserInfoControllerAddRo.EduLevel.values());
        List<String> industryTypes = extractEnumValues(UserInfoControllerAddRo.IndustryType.values());
        List<String> occupations = extractEnumValues(UserInfoControllerAddRo.OccupationType.values());
        List<String> salaryLevels = Arrays.asList("L0_2K", "L2_3K", "L3_5K", "L5_8K", "L8_12K", "L12_20K", "L20K");
        UserInfoControllerAddRo ro = new UserInfoControllerAddRo();
        // 随机选择地址信息
        if (!areas.isEmpty()) {
            AreaTriple randomArea = areas.get(GenerationHandler.generateRandomInt(0, areas.size() - 1));
            ro.setProvince(randomArea.provinceName);
            ro.setProvinceCode(randomArea.provinceCode);
            ro.setCity(randomArea.cityName);
            ro.setCityCode(randomArea.cityCode);
            ro.setArea(randomArea.areaName);
            ro.setAreaCode(randomArea.areaCode);
            ro.setAddress(randomArea.cityName + randomArea.areaName + "测试路" + GenerationHandler.generateRandomInt(1, 999) + "号");
        }
        // 随机选择公司地址信息
        if (!areas.isEmpty()) {
            AreaTriple randomWorkArea = areas.get(GenerationHandler.generateRandomInt(0, areas.size() - 1));
            ro.setCoProvince(randomWorkArea.provinceName);
            ro.setCoProvinceCode(randomWorkArea.provinceCode);
            ro.setCoCity(randomWorkArea.cityName);
            ro.setCoCityCode(randomWorkArea.cityCode);
            ro.setCoArea(randomWorkArea.areaName);
            ro.setCoAreaCode(randomWorkArea.areaCode);
            ro.setCoName(randomWorkArea.cityName + "未来科技有限公司");
        }
        // 随机选择其他属性
        if (!marriages.isEmpty()) {
            ro.setMarriage(marriages.get(GenerationHandler.generateRandomInt(0, marriages.size() - 1)));
        }
        if (!eduLevels.isEmpty()) {
            ro.setEduLevel(eduLevels.get(GenerationHandler.generateRandomInt(0, eduLevels.size() - 1)));
        }
        if (!salaryLevels.isEmpty()) {
            ro.setSalaryLevel(salaryLevels.get(GenerationHandler.generateRandomInt(0, salaryLevels.size() - 1)));
        }
        if (!industryTypes.isEmpty()) {
            ro.setIndustryType(industryTypes.get(GenerationHandler.generateRandomInt(0, industryTypes.size() - 1)));
        }
        if (!occupations.isEmpty()) {
            ro.setOccupation(occupations.get(GenerationHandler.generateRandomInt(0, occupations.size() - 1)));
        }
        // 随机生成其他字段
        int randomUid = GenerationHandler.generateRandomInt(100, 9999);
        int randomIndex = GenerationHandler.generateRandomInt(0, 999);
        ro.setEmail(buildEmail(randomUid, randomIndex));
        ro.setExpectLoanAmt(buildRandomLoanAmt());
        return ro;
    }

    public static class AreaTriple {
        public final String provinceName;
        public final String provinceCode;
        public final String cityName;
        public final String cityCode;
        public final String areaName;
        public final String areaCode;

        public AreaTriple(String provinceName, String provinceCode, String cityName, String cityCode, String areaName, String areaCode) {
            this.provinceName = provinceName;
            this.provinceCode = provinceCode;
            this.cityName = cityName;
            this.cityCode = cityCode;
            this.areaName = areaName;
            this.areaCode = areaCode;
        }
    }

    /**
     * 拉取chinaArea并为每个省挑选一个代表性的市、区
     */
    private static List<AreaTriple> loadProvinceCoverTriples() {
        EnumListController enumListController = new EnumListController();
        String json = enumListController.chinaArea();
        String jsonArray = JsonHandler.readArrayAsJsonStringByJsonPath("$.data", json);
        System.out.println(jsonArray);
        JSONArray provinces = JSONArray.parseArray(jsonArray);
        List<AreaTriple> result = new ArrayList<>();
        if (provinces == null) return result;
        for (int i = 0; i < provinces.size(); i++) {
            JSONObject p = provinces.getJSONObject(i);
            if (p == null) continue;
            String pName = p.getString("name");
            String pCode = p.getString("code");
            JSONArray cities = p.getJSONArray("children");
            if (cities == null || cities.isEmpty()) continue;
            JSONObject c = cities.getJSONObject(0);
            String cName = c.getString("name");
            String cCode = c.getString("code");
            JSONArray areas = c.getJSONArray("children");
            if (areas == null || areas.isEmpty()) {
                // 某些直辖或地级结构可能无区，回退使用城市自身
                result.add(new AreaTriple(pName, pCode, cName, cCode, cName, cCode));
                continue;
            }
            JSONObject a = areas.getJSONObject(0);
            String aName = a.getString("name");
            String aCode = a.getString("code");
            result.add(new AreaTriple(pName, pCode, cName, cCode, aName, aCode));
        }
        return result;
    }

    private static String buildEmail(Integer uid, int index) {
        String prefix = StringHandler.getPinyin(String.valueOf(uid));
        if (StringHandler.isEmpty(prefix)) {
            prefix = "user";
        }
        return prefix + index + "@example.com";
    }

    private static int buildLoanAmtByIndex(int index) {
        // 在合理范围内离散分布
        int[] candidates = new int[]{100000, 300000, 500000, 800000, 1200000, 2000000, 3000000, 5000000, 20000000};
        return candidates[index % candidates.length];
    }


    /**
     * 随机生成贷款金额
     */
    private static int buildRandomLoanAmt() {
        int[] candidates = new int[]{100000, 300000, 500000, 800000, 1200000, 2000000, 3000000, 5000000, 20000000};
        return candidates[GenerationHandler.generateRandomInt(0, candidates.length - 1)];
    }

    private static List<String> extractEnumValues(Object[] enumConstants) {
        List<String> values = new ArrayList<>();
        for (Object o : enumConstants) {
            try {
                values.add((String) o.getClass().getMethod("getValue").invoke(o));
            } catch (Exception ignored) {
            }
        }
        return values;
    }
}


