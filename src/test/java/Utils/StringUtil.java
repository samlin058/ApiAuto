package Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class StringUtil {
	/**
	 * 判断字符串是否为空
	 * 1、等于null
	 * 2、"   "等于空
	 * 3、""等于空
	 * @param str  字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		Boolean result = (str == null) || (str.replace("     ", "")).length() == 0;
		return result;
	}

	/**
	 * 分解期望jsonKey的值
	 * @param keyStr 
	 * @return
	 */
	public static List<Map<String, String>> analysisHopeStr(String keyStr) {
		List<Map<String, String>> resultList = new ArrayList<>();
		// 创建存放结果对象
		HashMap<String, String> resultMap = new HashMap<String, String>();
		// 创建存放key的list对象
		List<String> keyList = new ArrayList<String>();
		// 创建存放value的list对象
		List<String> valueList = new ArrayList<String>();
		// 分解key和value
		String[] oneList = keyStr.split(",");
		for (int i = 0; i < oneList.length; i++) {
			if (i % 2 == 0) {
				keyList.add(oneList[i].toString());
			} else {
				valueList.add(oneList[i].toString());
			}
		}
		// 将分解的值存入map对象
		int forNum = oneList.length / 2;
		for (int j = 0; j < forNum; j++) {
			String key = keyList.get(j);
			String value = valueList.get(j);
			resultMap.put(key, value);
			resultList.add(resultMap);
		}
		return resultList;
	}

	/**
	 * 根据table属性的个数转化成对应的占位符Str，例(?,?,?)
	 * @param table void
	 *
	 * @author       luandao
	 * @return 
	 * @Date         2021年11月27日 
	 *
	 */
	public static String oBjectChangeToPlaceholder(Object table) {
		String targerString = null;
		StringBuffer sb = new StringBuffer("(?");
		Field[] fields = table.getClass().getFields();
		int num = fields.length;
		for (int i = 1; i < num; i++) {
			sb.append(",?");
		}
		sb.append(")");
		targerString = sb.toString();
		return targerString;
	}

	public static String oBjectChangeDeleteSql(Object table) {
		// 获取参数相关信息
		Class<?> clazz = table.getClass();
		Field[] fields = clazz.getFields();
		int num = fields.length;

		// 获取逻辑符
		String logicalSymbolValue = "";
		try {
			Method logicalSymbolMethod = clazz.getMethod("getLogicalSymbol", null);
			logicalSymbolValue = (String) logicalSymbolMethod.invoke(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将sql参数存入map
		Map<String, String> fieldValueMap = new HashMap<>();
		for (int i = 0; i < num; i++) {
			try {
				String fieldName = fields[i].getName();
				String getName = "get" + (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0)))
						.append(fieldName.substring(1)).toString();
				Method getMethod = clazz.getMethod(getName, null);
				String fieldValue = (String) getMethod.invoke(table);
				fieldValueMap.put(fieldName, fieldValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 过滤map，将空的值和逻辑符号去掉
		Map<String, String> filterMap = new HashMap<>();
		Set<String> fieldSet = fieldValueMap.keySet();
		for (String fieldName : fieldSet) {
			if ("logicalSymbol".equals(fieldName)) {
				continue;
			} else if (!isEmpty(fieldValueMap.get(fieldName))) {
				filterMap.put(fieldName, fieldValueMap.get(fieldName));
			}
		}

		// 将sql条件参数组装
		String sql = "";
		StringBuilder sqlBuilder = new StringBuilder("where ");
		if (filterMap.isEmpty()) {
			return null;
		} else {
			Set<String> filterSet = filterMap.keySet();
			int fliedNum = filterSet.size();
			if (fliedNum == 0) {
				return sql;
			} else if (fliedNum == 1) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					if ("NULL".equalsIgnoreCase(filterMap.get(fieldName))) {
						sqlBuilder.append(fieldName).append(" IS NULL");
					} else {
						sqlBuilder.append(fieldName).append("=").append("?");
					}
					sql = sqlBuilder.toString();
				}
			} else if (fliedNum > 1 && !isEmpty(logicalSymbolValue)) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					if ("NULL".equalsIgnoreCase(filterMap.get(fieldName))) {
						sqlBuilder.append(fieldName).append(" IS NULL ").append(logicalSymbolValue + " ");
					} else {
						sqlBuilder.append(fieldName).append("=").append("? ").append(logicalSymbolValue + " ");
					}
				}
				// 删除最后一个逻辑符号
				sqlBuilder.delete(sqlBuilder.lastIndexOf(logicalSymbolValue), sqlBuilder.length());
				sql = sqlBuilder.toString();
			} else {
				System.out.println("多个参数请设置逻辑符号");
			}
		}

		// 判断是否有NULL--
		for (int i = 0; i < num; i++) {
			try {
				String fieldName = fields[i].getName();
				String getName = "get" + (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0)))
						.append(fieldName.substring(1)).toString();
				Method getMethod = clazz.getMethod(getName, null);
				String fieldValue = (String) getMethod.invoke(table, null);
				if (!"id".equalsIgnoreCase(fieldName)) {
					if ("NULL".equalsIgnoreCase(fieldValue)) {
						sqlBuilder.append(fieldName + " IS NULL ").append(logicalSymbolValue);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return sql;
	}

	/**
	 * 获取删除sql
	 * @param param  删除表对象
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年1月10日 
	 *
	 */
	public static String getDeleteSqlString(Object param) {
		Class<?> clazz = param.getClass();
		String tableName = clazz.getSimpleName();
		String sql = "delete from " + tableName + " where ";
		Field[] fields = clazz.getFields();
		int fieldNum = fields.length;

		// 获取逻辑符
		String logicalSymbolValue = "";
		try {
			Method logicalSymbolMethod = clazz.getMethod("getLogicalSymbol", null);
			logicalSymbolValue = (String) logicalSymbolMethod.invoke(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 将sql参数存入map
		Map<String, String> fieldValueMap = new HashMap<>();
		for (int i = 0; i < fieldNum; i++) {
			try {
				String fieldName = fields[i].getName();
				String getName = "get" + (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0)))
						.append(fieldName.substring(1)).toString();
				Method getMethod = clazz.getMethod(getName, null);
				String fieldValue = (String) getMethod.invoke(param);
				fieldValueMap.put(fieldName, fieldValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 过滤map，将空的值和逻辑符号去掉
		Map<String, String> filterMap = new HashMap<>();
		Set<String> fieldSet = fieldValueMap.keySet();
		for (String fieldName : fieldSet) {
			if ("logicalSymbol".equals(fieldName)) {
				continue;
			} else if (!isEmpty(fieldValueMap.get(fieldName))) {
				filterMap.put(fieldName, fieldValueMap.get(fieldName));
			}
		}

		// 组装sql
		StringBuilder sqlBuilder = new StringBuilder(sql);
		if (filterMap.isEmpty()) {
			return null;
		} else {
			Set<String> filterSet = filterMap.keySet();
			int fliedNum = filterSet.size();
			if (fliedNum == 0) {
				return sql;
			} else if (fliedNum == 1) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					if ("NULL".equalsIgnoreCase(filterMap.get(fieldName))) {
						sqlBuilder.append(fieldName).append(" IS NULL");
					} else {
						sqlBuilder.append(fieldName).append("=").append("'" + filterMap.get(fieldName) + "' ");
					}
					sql = sqlBuilder.toString();
				}
			} else if (fliedNum > 1 && !isEmpty(logicalSymbolValue)) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					if ("NULL".equalsIgnoreCase(filterMap.get(fieldName))) {
						sqlBuilder.append(fieldName).append(" IS NULL ").append(logicalSymbolValue + " ");
					} else {
						sqlBuilder.append(fieldName).append("=").append("'" + filterMap.get(fieldName) + "' ")
								.append(logicalSymbolValue + " ");
					}
				}
				// 删除最后一个逻辑符号
				sqlBuilder.delete(sqlBuilder.lastIndexOf(logicalSymbolValue), sqlBuilder.length());
				sql = sqlBuilder.toString();
			} else {
				System.out.println("多个参数请设置逻辑符号");
			}
		}

		return sql;
	}

	public static String getInsertSqlString(Object param) {
		Class<?> clazz = param.getClass();
		String tableName = clazz.getSimpleName();
		String init = "INSERT into " + tableName + " (";

		HashMap<String, String> fieldMap = objectToMap(param);
		StringBuffer nameSb = new StringBuffer(init);
		StringBuffer valueSb = new StringBuffer(" VALUES (");
		Set<String> fieldNameSet = fieldMap.keySet();
		for (String fieldName : fieldNameSet) {
			nameSb.append("`" + fieldName + "`,");
			if (!isEmpty(fieldMap.get(fieldName))) {
				valueSb.append("'" + fieldMap.get(fieldName) + "',");
			} else {
				valueSb.append("NULL,");
			}
		}
		String sql = nameSb.toString().substring(0, nameSb.toString().length() - 1) + ")"
				+ valueSb.toString().substring(0, valueSb.length() - 1) + ")";
		return sql;
	}
    
	
	public static String getUpdateSqlString(Object result, Object condition) {
		String updateString = "";
		Class<?> resultClazz = result.getClass();
		String tableName = resultClazz.getSimpleName();
		Field[] fields = resultClazz.getFields();
		int fieldNum = fields.length;
		StringBuffer resultsb = new StringBuffer("update " + tableName + " set ");
		// 组装修改的sqlstring
		HashMap<String, String> resultMap = objectToMap(result);
		Set<String> resultFiledSet = resultMap.keySet();
		for (String key : resultFiledSet) {
			if ("logicalSymbol".equals(key)) {
				continue;
			} else if (!isEmpty(resultMap.get(key))) {
				resultsb.append(key + "='" + resultMap.get(key) + "',");
			}
		}

		// 去掉最后一位
		updateString = resultsb.toString().substring(0, resultsb.toString().length() - 1);

		// 将条件对象转化成map
		HashMap<String, String> conditionMap = objectToMap(condition);
		if (conditionMap.isEmpty()) {
			return updateString;
		} else {
			String logicalSymbolValue = "";
			Class<?> conditionClazz = condition.getClass();
			try {
				Method logicalSymbolMethod = conditionClazz.getMethod("getLogicalSymbol", null);
				logicalSymbolValue = (String) logicalSymbolMethod.invoke(condition);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 组装条件的sqlstring
			StringBuffer conditionsb = new StringBuffer(updateString + " where ");

			// 过滤map，将空的值和逻辑符号去掉
			Map<String, String> filterMap = new HashMap<>();
			Set<String> fieldSet = conditionMap.keySet();		
			for (String fieldName : fieldSet) {
				if ("logicalSymbol".equals(fieldName)) {
					logicalSymbolValue = conditionMap.get(fieldName);
				} else if (!isEmpty(conditionMap.get(fieldName))) {
					filterMap.put(fieldName, conditionMap.get(fieldName));
				}
			}

			Set<String> filterSet = filterMap.keySet();
			int fliedNum = filterSet.size();
			if (fliedNum == 0) {
				return updateString;
			} else if (fliedNum == 1) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					conditionsb.append(fieldName).append("='").append(filterMap.get(filterMap.get(fieldName))+"'");
				}
			} else if (fliedNum > 1 && !isEmpty(logicalSymbolValue)) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					conditionsb.append(fieldName).append("='" + filterMap.get(fieldName)+"'")
							.append(" " +logicalSymbolValue + " ");
				}
				
				// 删除最后一个逻辑符号
				conditionsb.delete(conditionsb.lastIndexOf(logicalSymbolValue), conditionsb.length());
				
				updateString = conditionsb.toString();
			} else {
				System.out.println("多个参数请设置逻辑符号");
			}
		}

		return updateString;
	}

	/**
	 * 将对象的属性转化成map对象 
	 * @param obj
	 * @return HashMap<String,String>
	 *
	 * @author       luandao
	 * @Date         2022年1月14日 
	 *
	 */
	public static HashMap<String, String> objectToMap(Object obj) {
		Class<?> clazz = obj.getClass();
		Field[] fields = clazz.getFields();
		int fieldNum = fields.length;
		HashMap<String, String> fieldValueMap = new HashMap<>();
		for (int i = 0; i < fieldNum; i++) {
			try {
				String fieldName = fields[i].getName();
				String getName = "get" + (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0)))
						.append(fieldName.substring(1)).toString();
				Method getMethod = clazz.getMethod(getName, null);
				String fieldValue = (String) getMethod.invoke(obj);
				fieldValueMap.put(fieldName, fieldValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fieldValueMap;
	}
	
	/**
	 * 遍历map，打印map数据
	 * @param map void
	 *
	 * @author       luandao
	 * @Date         2022年6月18日 
	 *
	 */
	public static void  displayMapValue(Map<String, Object> map) {
		 Set<String> aSet = map.keySet();
		 for (String key : aSet) {
			System.out.println(key+":"+map.get(key));
		}
	}
	
	/**
	 * 遍历list，打印list的值
	 * @param list void
	 *
	 * @author       luandao
	 * @Date         2022年6月18日 
	 *
	 */
	public static void  displayListValue(List<Object> list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}
	
	/**
	 * 入参转成map
	 * @param requestStr
	 * @return HashMap<String,Object>
	 *
	 * @author       luandao
	 * @Date         2022年6月18日 
	 *
	 */
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> parseMap(String requestStr) {
		return (HashMap<String, Object>) JSONObject.parseObject(requestStr, Map.class);
	}
	

}
