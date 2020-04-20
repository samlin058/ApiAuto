package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

	/**
	 *  获取json中指定key的值
	 * @param key  
	 * @param json
	 * @return
	 */
	public static String getKeyVal(String key, String json) {
		String resultStr = "";
		JSONObject jsonObject = JSONObject.parseObject(json);
		if (jsonObject.containsKey(key)) {
			resultStr = jsonObject.getString(key);
		} else {
			resultStr = getJsonVal(key, jsonObject);
		}
		return resultStr;
	}

	/**
	 *   获得json中key的值
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	private static String getJsonVal(String key, JSONObject jsonObject) {
		//创建一个结果对象
		String resultStr = "";
		//将jsonObject转化成一个map对象
		Map<String, Object> map = jsonObject.getInnerMap();
		//获得map的键集合
		Set<String> mapKey = map.keySet();
		//遍历Set
		for (String keyStr : mapKey) {
			//获得map的值
			String value = map.get(keyStr).toString();
			//如果值中包含{和：，那这个值是一个实例，如果值中包含[和]，那这个值是一个包含多个对象的数组
			if (keyStr.equals(key)) {
				resultStr = value;
				break;
			} else if (value.substring(0, 1).equals("[")) {
				JSONArray jsonArray = JSONObject.parseArray(value);
				for (Object object : jsonArray) {
					JSONObject innerObject = JSONObject.parseObject(object.toString());
					if (innerObject.containsKey(key)) {
						resultStr = innerObject.getString(key);
					}

				}
			} else if (value.substring(0, 1).equals("{")) {
				JSONObject innerObject = JSONObject.parseObject(value);
				if (innerObject.containsKey(key)) {
					resultStr = innerObject.getString(key);
				} else {
					resultStr = getJsonVal(key, innerObject);
				}
			}
		}
		return resultStr;
	}

	/**
	 * 例子
	 * @param args
	 */
	public static void main(String[] args) {
		String key = "message";
		String json = "{\"code\":0,\"message\":\"成功\",\"data\":{\"studetn\":[{\"name\":\"xiaoming\",\"age\":3,\"sex\":\"man\"},{\"name\":\"xiaohong\",\"age\":3,\"sex\":\"man\"},{\"name\":\"xiaolan\",\"age\":3,\"sex\":\"man\",\"scoce\":[{\"money\":20000,\"deadline\":\"3期\",\"title\":\"最优推荐\"},{\"money\":8000,\"deadline\":\"30天\",\"title\":\"灵活周转\"},{\"money\":8000,\"deadline\":\"30天\",\"title\":\"极速放款\"}]},{\"name\":\"xiaolv\",\"age\":3,\"sex\":\"man\"}],\"teacher\":[{\"name\":\"xiaoming\",\"age\":18,\"sex\":\"man\"},{\"name\":\"xiaohong\",\"age\":18,\"sex\":\"man\"},{\"name\":\"xiaolan\",\"age\":18,\"sex\":\"man\"},{\"name\":\"xiaolv\",\"age\":18,\"sex\":\"man\"}],\"is_reloan\":false,\"is_new\":true,\"product_code\":\"4000_3_30_distribute\",\"product_id\":670,\"redirect_url\":\"http://paydayloan.samlin.dsqtest.kuainiujinke.com/m/#{/process\"}}";
		String aString = getKeyVal(key, json);
		System.out.println(aString);

	}

	public static void jsonUtil(String key, String json) {
		JSONObject jsonObject = JSON.parseObject(json);
		if (jsonObject.containsKey(key)) {
			System.out.println(jsonObject.getString(key));
		} else {
			// isInnerJson(key, json);
		}
	}

	private static List<String> getOneKeyAllValue(String key, String json) {
		//创建一个对象接收key的值
		List<String> keyValueList = new ArrayList<String>();
		//将json字符串转化为JSONObject对象
		JSONObject jsonObject = JSON.parseObject(json);
		//转为map数据形式
		HashMap<String, Object> map = (HashMap<String, Object>) jsonObject.getInnerMap();
		//获取map的key集合
		Set<String> mapKey = map.keySet();
		//遍历key
		for (String keyString : mapKey) {
			if (keyString.equals(key)) {
				keyValueList.add((String) map.get(keyString));
			}
			//遍历第一层map，获得key的值
			String value = map.get(keyString).toString();
			//System.out.println(value);
			//如果值中间还有jsons数组，继续遍历
			if (value.substring(0).equals("[")) {
				System.out.println(value);
				JSONArray jsonArray = JSON.parseArray(value);
				for (Object object : jsonArray) {
					System.out.println(object.toString());
					JSONObject InnerjsonObject = JSON.parseObject(object.toString());
					//转为map数据形式
					HashMap<String, Object> InnerMap = (HashMap<String, Object>) InnerjsonObject.getInnerMap();
					//获取map的key集合
					Set<String> InnerMapKey = map.keySet();
					//遍历key
					for (String InnerKey : InnerMapKey) {
						if (InnerKey.equals(key)) {
							keyValueList.add((String) map.get(InnerKey));
						}
					}
				}
			} else if (value.substring(0).equals("{")) { //如果值是一个json字符串
				System.out.println(value);
				JSONObject InnerjsonObject = JSON.parseObject(value);
				if (InnerjsonObject.containsKey(key)) {
					System.out.println(InnerjsonObject.getString(key));
				} else {
					getOneKeyAllValue(key, value);
				}
			}
		}
		return keyValueList;
	}

	private static List<String> getOneKeyAllValue2(String key, String json) {
		//创建一个对象接收key的值
		List<String> keyValueList = new ArrayList<String>();
		//将json字符串转化为JSONObject对象
		JSONObject jsonObject = JSON.parseObject(json);
		//转为map数据形式
		HashMap<String, Object> map = (HashMap<String, Object>) jsonObject.getInnerMap();
		if (jsonObject.containsKey(key)) {
			keyValueList.add((String) map.get(key));
		}
		//获取map的key集合
		Set<String> mapKey = map.keySet();
		//遍历key
		for (String keyString : mapKey) {
			//遍历第一层map，获得key的值
			String value = map.get(keyString).toString();
			//System.out.println(value);
			//如果值中间还有jsons数组，继续遍历
			if (value.substring(0, 1).equals("[")) {
				System.out.println(value);
				JSONArray jsonArray = JSON.parseArray(value);
				for (Object object : jsonArray) {
					System.out.println(object.toString());
					JSONObject InnerjsonObject = JSON.parseObject(object.toString());
					if (InnerjsonObject.containsKey(key)) {
						keyValueList.add((String) map.get(key));
					} else {
						// TODO 这里有问题，需要解决
						getOneKeyAllValue(key, value);
					}
				}
			} else if (value.substring(0, 1).equals("{")) { //如果值是一个json字符串
				System.out.println(value);
				JSONObject InnerjsonObject = JSON.parseObject(value);
				if (InnerjsonObject.containsKey(key)) {
					System.out.println(InnerjsonObject.getString(key));
					keyValueList.add((String) map.get(key));
				} else {
					// TODO 这里有问题，需要解决
					getOneKeyAllValue(key, value);
				}
			}
		}
		return keyValueList;
	}

	/*public static void main(String[] args) {
		String key = "image_url";
		String json = "{\"code\":0,\"message\":\"成功\",\"data\":{\"is_login\":false,\"is_new_user\":false,\"banner\":[{\"image_url\":\"https://cashtest-1251122539.cossh.myqcloud.com/app/20190410182040976123336.png\",\"redirect_url\":\"http://paydayloan.xmw.dsqtest.kuainiujinke.com/active/oldUserInvite/#/invite\",\"title\":\"首页banner老拉新\"}],\"wechat_account\":{\"account\":\"DSQ科技\",\"qrcode_url\":\"https://paydayloanv4-1251122539.cossh.myqcloud.com/app/2018122513073383888923.jpg\",\"msg\":\"关注微信公众号【DSQ科技】\\n提额、免息等优惠券正在发放中！\",\"save_qrcode_header\":\"保存图片成功\",\"save_qrcode_text\":\"打开微信-扫一扫\\n从相册选取二维码-关注公众号\",\"copy_account_header\":\"复制公众号成功\",\"copy_account_text\":\"请在微信-添加朋友-公众号粘贴并\\n搜索-关注微信号，超多奖品等你来领\"},\"config\":{\"tip\":\"不向学生提供服务\"},\"multi\":{\"max_money\":20000,\"borrowed_money\":0,\"click_url\":\"http://paydayloan.samlin.dsqtest.kuainiujinke.com/m/#/auth\",\"click_limit\":0,\"apply_code\":\"\",\"apply_status\":0,\"loan_channel\":0,\"onclick_tip\":\"我要借款\",\"button_color\":\"#fff\",\"is_loan_market\":true,\"show_info\":{\"show_text\":\"\",\"icon_url\":\"\"},\"repay_date\":\"\",\"apply_count\":0,\"extra_tip_info\":{},\"home_test\":\"no\",\"home_test_url\":\"\",\"is_queuing\":false},\"pre_loan\":{\"can_loan\":false},\"reloan\":{\"can_loan\":false},\"hide_banner\":false,\"new_user_button\":[{\"money\":20000,\"deadline\":\"3期\",\"title\":\"最优推荐\"},{\"money\":8000,\"deadline\":\"30天\",\"title\":\"灵活周转\"},{\"money\":8000,\"deadline\":\"30天\",\"title\":\"极速放款\"}],\"is_old_pull_new\":true,\"is_refuse\":false,\"refuse_end_time\":\"\",\"banner_diversion\":{},\"service\":{},\"is_overdue\":false,\"is_order\":false,\"is_overdue_0\":false,\"is_overdue_7\":false,\"scrollInfo\":[{\"info\":\"安徽孙先生1分钟前分期借款14000元\"},{\"info\":\"青海郑小姐1分钟前分期借款13000元\"},{\"info\":\"贵州孙先生1分钟前分期借款16000元\"},{\"info\":\"云南王小姐1分钟前成功借款6000元\"},{\"info\":\"安徽赵先生1分钟前分期借款14000元\"},{\"info\":\"吉林赵先生1分钟前分期借款19000元\"},{\"info\":\"吉林吴小姐1分钟前成功借款9000元\"},{\"info\":\"青海孙小姐1分钟前成功借款8000元\"},{\"info\":\"湖南李小姐1分钟前成功借款7000元\"},{\"info\":\"福建李小姐1分钟前成功借款9000元\"},{\"info\":\"山东王先生1分钟前分期借款16000元\"},{\"info\":\"四川王小姐1分钟前成功借款6000元\"},{\"info\":\"吉林孙先生1分钟前分期借款10000元\"},{\"info\":\"上海孙小姐1分钟前分期借款19000元\"},{\"info\":\"山西王先生1分钟前成功借款6000元\"},{\"info\":\"湖北周先生1分钟前分期借款12000元\"},{\"info\":\"河南吴小姐1分钟前成功借款10000元\"},{\"info\":\"重庆赵先生1分钟前分期借款19000元\"},{\"info\":\"吉林孙小姐1分钟前分期借款20000元\"},{\"info\":\"青海孙小姐1分钟前分期借款14000元\"}],\"advanced_is_show\":0,\"next_allow_apply_time\":null}}";
				List<String> values = getOneKeyAllValue2(key, json);
				for (String string : values) {
					System.out.println(string);
				}*/

}
