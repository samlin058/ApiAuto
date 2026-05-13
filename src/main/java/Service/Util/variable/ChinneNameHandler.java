package Service.Util.variable;

import java.util.Random;

public class ChinneNameHandler {
    // 常见姓氏
    private static final String[] SURNAMES = {
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
            "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏"
    };

    // 常见名字的汉字集合
    private static final String[] FIRST_NAME_PARTS = {
            "子", "明", "文", "勇", "俊", "磊", "浩", "伟", "强", "鹏",
            "杰", "娜", "静", "婷", "艳", "丽", "梅", "丹", "红", "兰",
            "芳", "冰", "雪", "琴", "怡", "欣", "慧", "瑶", "琳", "璇",
            "宇", "菲", "彤", "悦", "玥", "琳", "梦", "晴", "萍", "雅",
            "婷", "佳", "薇", "怡", "璐", "昕", "怡", "婧", "娜", "阳",
            "凯", "桐", "宏", "波", "翔", "洋", "萱", "乐", "文", "思",
            "浩", "欣", "辰", "聪", "华", "奕", "淇", "鑫", "言", "靖",
            "志", "泽", "睿", "琪", "睿", "莉", "雪", "萧", "雪", "洁",
            "宇", "阳", "烨", "韵", "成", "达", "晨", "凡", "瑾", "韦",
            "亮", "泰", "朗", "天", "智", "波"
    };

    private static final String[] SECOND_NAME_PARTS = {
            "东", "南", "西", "北", "川", "林", "海", "山", "田", "国",
            "峰", "月", "星", "涛", "光", "飞", "阳", "林", "达", "宇",
            "瑞", "杰", "栋", "乐", "辰", "鑫", "林", "瑞", "枫", "睿",
            "辰", "澄", "云", "韬", "乐", "程", "思", "鑫", "豪", "智",
            "凯", "飞", "天", "航", "翔", "昌", "亮", "博", "晟", "卓",
            "泽", "海", "岩", "雄", "林", "俊", "卓", "群", "庆", "寒",
            "轩", "泽", "腾", "荣", "毅", "凌", "德", "志", "雄", "才",
            "飞", "豪", "运", "天", "振", "超", "伟", "瑜", "广", "志",
            "诚", "天", "睿", "冠", "天", "博", "泽", "耀", "宇", "琪",
            "思", "浩", "泽", "卓", "林", "远", "豪", "风", "涛", "伟"
    };

    /**
     * 随机获取一个中国名字
     * @return 中国名
     */
    public static String generateName() {
        Random random = new Random();
        // 选择姓氏（2字或3字，通常姓氏只用一个字）
        String surname = SURNAMES[random.nextInt(SURNAMES.length)];
        // 选择名字的长度，随机生成2-4个字
        int nameLength = 2 + random.nextInt(3); // 2、3或4个字
        // 生成名字的各个部分
        StringBuilder nameBuilder = new StringBuilder(surname);
        // 随机选取名字的部分
        if (nameLength == 2) {
            nameBuilder.append(FIRST_NAME_PARTS[random.nextInt(FIRST_NAME_PARTS.length)]);
        } else if (nameLength == 3) {
            nameBuilder.append(FIRST_NAME_PARTS[random.nextInt(FIRST_NAME_PARTS.length)]);
            nameBuilder.append(SECOND_NAME_PARTS[random.nextInt(SECOND_NAME_PARTS.length)]);
        } else if (nameLength == 4) {
            nameBuilder.append(FIRST_NAME_PARTS[random.nextInt(FIRST_NAME_PARTS.length)]);
            nameBuilder.append(SECOND_NAME_PARTS[random.nextInt(SECOND_NAME_PARTS.length)]);
            nameBuilder.append(FIRST_NAME_PARTS[random.nextInt(FIRST_NAME_PARTS.length)]);
        }
        return nameBuilder.toString();
    }
}
