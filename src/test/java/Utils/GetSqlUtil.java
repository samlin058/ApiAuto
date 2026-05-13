package Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import TO.baseTable;
import Utils.StringUtil;

public class GetSqlUtil {
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

		// 获取逻辑符号
		String logicalSymbolValue = getLogicalSymbolValue(clazz);
		// 将sql参数存入map
		Map<String, String> fieldValueMap = tableChangeToMap(table);
		// 过滤值为null的map字段
		Map<String, String> filterMap = mapFilterNull(fieldValueMap);

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
			} else if (fliedNum > 1 && !StringUtil.isEmpty(logicalSymbolValue)) {
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
	 * 获取Delete-sql
	 * @param param  删除表对象
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年1月10日 
	 *
	 */
	public static String getDeleteSqlString(Object tableParam) {
		// 将入参转为字节码
		Class<?> clazz = tableParam.getClass();
		// 初始化sql
		String tableName = clazz.getSimpleName();
		String sql = "delete from " + tableName + " where ";
		// 获取逻辑符
		String logicalSymbolValue = getLogicalSymbolValue(clazz);
		// 将表对象参数转成map格式
		Map<String, String> fieldValueMap = tableChangeToMap(tableParam);
		// 过滤map，将空的值和逻辑符号去掉
		Map<String, String> filterMap = mapFilterNull(fieldValueMap);
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
			} else if (fliedNum > 1 && !StringUtil.isEmpty(logicalSymbolValue)) {
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

	/**
	 * 获取Insert-sql
	 * @param param
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年2月7日 
	 *
	 */
	public static String getInsertSqlString(Object param) {
		// 将入参转为字节码
		Class<?> clazz = param.getClass();
		// 初始化sql
		String tableName = clazz.getSimpleName();
		String init = "INSERT INTO " + tableName + " (";
		// table对象转化成map格式
		HashMap<String, String> fieldMap = tableChangeToMap(param);
		// 过滤值为null的字段
		removeNullValue(fieldMap);
		/*
		 * Set<String> aSet=fieldMap.keySet(); for (String string : aSet) {
		 * System.out.println(string+":"+fieldMap.get(string));
		 * 
		 * }
		 */
		// 拼接插入sql
		if (!fieldMap.isEmpty()) {
			StringBuffer nameSb = new StringBuffer(init);
			StringBuffer valueSb = new StringBuffer(" VALUES (");
			Set<String> fieldNameSet = fieldMap.keySet();
			for (String fieldName : fieldNameSet) {
				nameSb.append("`" + fieldName + "`,");
				valueSb.append("'" + fieldMap.get(fieldName) + "',");

			}

			String sql = nameSb.toString().substring(0, nameSb.toString().length() - 1) + ")"
					+ valueSb.toString().substring(0, valueSb.length() - 1) + ")";

			return sql;
		}
		System.out.println("sql无参数插入");
		return null;
	}

	/**
	 * 获取更新SQL
	 * @param result
	 * @param condition
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年2月7日 
	 *
	 */
	public static String getUpdateSqlString(Object updateParam, Object condition) {
		String updateString = "";
		// 获取表入参字节码
		Class<?> resultClazz = updateParam.getClass();
		String tableName = resultClazz.getSimpleName();
		Field[] fields = resultClazz.getFields();
		int fieldNum = fields.length;

		StringBuffer resultsb = new StringBuffer("update " + tableName + " set ");

		// 组装修改的sql
		HashMap<String, String> resultMap = tableChangeToMap(updateParam);
		// 去空
		HashMap<String, String> resultFilterMap = mapFilterNull(resultMap);
		String resultString = mapChangeToKeyValue(resultFilterMap, "`");

		Set<String> resultFiledSet = resultMap.keySet();
		for (String key : resultFiledSet) {
			if ("logicalSymbol".equals(key)) {
				continue;
			} else if (!StringUtil.isEmpty(resultMap.get(key))) {
				resultsb.append(key + "='" + resultMap.get(key) + "',");
			}
		}

		// 去掉最后一位
		updateString = resultsb.toString().substring(0, resultsb.toString().length() - 1);

		// 将条件对象转化成map
		HashMap<String, String> conditionMap = StringUtil.objectToMap(condition);
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
				} else if (!StringUtil.isEmpty(conditionMap.get(fieldName))) {
					filterMap.put(fieldName, conditionMap.get(fieldName));
				}
			}

			Set<String> filterSet = filterMap.keySet();
			for (String string : filterSet) {
				System.out.println(string + ":" + filterMap.get(string));
			}

			int fliedNum = filterSet.size();
			if (fliedNum == 0) {
				return updateString;
			} else if (fliedNum == 1) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					conditionsb.append(fieldName).append("='").append(filterMap.get(filterMap.get(fieldName)) + "'");
				}
			} else if (fliedNum > 1 && !StringUtil.isEmpty(logicalSymbolValue)) {
				Iterator<String> Iterator = filterSet.iterator();
				while (Iterator.hasNext()) {
					String fieldName = (String) Iterator.next();
					conditionsb.append(fieldName).append("='" + filterMap.get(fieldName) + "'")
							.append(" " + logicalSymbolValue + " ");
				}

				// 删除最后一个逻辑符号
				conditionsb.delete(conditionsb.lastIndexOf(logicalSymbolValue), conditionsb.length());

				updateString = conditionsb.toString();
				System.out.println(updateString);
			} else {
				System.out.println("多个参数请设置逻辑符号");
			}
		}

		return updateString;
	}

	public static String getSelectString(baseTable baseTable) {
		// select * from table where a='',b=''
		String result = "";
		
		return result;
	}

	/**
	 * 获取逻辑符号
	 * @param tableClass 
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年2月7日 
	 *
	 */
	private static String getLogicalSymbolValue(Class<?> tableClass) {
		// 获取逻辑符
		String logicalSymbolValue = "";
		try {
			Method logicalSymbolMethod = tableClass.getMethod("getLogicalSymbol", null);
			Object table = tableClass.newInstance();
			logicalSymbolValue = (String) logicalSymbolMethod.invoke(table);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return logicalSymbolValue;
	}

	/**
	 * 将表数据对象转化成map格式
	 * @param param
	 * @param clazz
	 * @return Map<String,String>
	 *
	 * @author       luandao
	 * @Date         2022年2月7日 
	 *
	 */
	private static HashMap<String, String> tableChangeToMap(Object tableObject) {
		Class<?> clazz = tableObject.getClass();
		Field[] fields = clazz.getFields();
		int fieldNum = fields.length;
		// 将sql参数存入map
		HashMap<String, String> fieldValueMap = new HashMap<>();
		for (int i = 0; i < fieldNum; i++) {
			try {
				String fieldName = fields[i].getName();
				String getName = "get" + (new StringBuilder()).append(Character.toUpperCase(fieldName.charAt(0)))
						.append(fieldName.substring(1)).toString();
				Method getMethod = clazz.getMethod(getName, null);
				String fieldValue = (String) getMethod.invoke(tableObject);
				fieldValueMap.put(fieldName, fieldValue);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fieldValueMap;
	}

	/**
	 * 过滤为null值的map
	 * @param fieldValueMap
	 * @return Map<String,String>
	 *
	 * @author       luandao
	 * @Date         2022年2月7日 
	 *
	 */
	private static HashMap<String, String> mapFilterNull(Map<String, String> UnFilterMap) {
		HashMap<String, String> filterMap = new HashMap<>();
		Set<String> UnFilterSet = UnFilterMap.keySet();
		for (String UnfieldName : UnFilterSet) {
			String value = UnFilterMap.get(UnfieldName);
			if (!"null".equalsIgnoreCase(value)) {
				filterMap.put(UnfieldName, UnFilterMap.get(UnfieldName));
				System.out.println(UnfieldName + ":" + UnFilterMap.get(UnfieldName));
			}
		}
		return filterMap;
	}

	/**
	   * 移除map中的value空值
	   * @param map
	   * @return
	   */
	public static void removeNullValue(Map map) {
		Set set = map.keySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Object obj = (Object) iterator.next();
			Object value = (Object) map.get(obj);
			remove(value, iterator);
		}
	}

	/**
	 * 移除map中的空值
	 *
	 * Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。
	 * Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变，
	 * 所以当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。
	 * 所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。
	 * 但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。
	 * @param obj
	 * @param iterator
	 */
	private static void remove(Object obj, Iterator iterator) {
		if (obj instanceof String) {
			String str = (String) obj;
			if (isEmpty(str)) { // 过滤掉为null和""的值 主函数输出结果map：{2=BB, 1=AA, 5=CC,
								// 8= }
				// if("".equals(str.trim())){ //过滤掉为null、""和" "的值
				// 主函数输出结果map：{2=BB, 1=AA, 5=CC}
				iterator.remove();
			}
		} else if (obj instanceof Collection) {
			Collection col = (Collection) obj;
			if (col == null || col.isEmpty()) {
				iterator.remove();
			}
		} else if (obj instanceof Map) {
			Map temp = (Map) obj;
			if (temp == null || temp.isEmpty()) {
				iterator.remove();
			}
		} else if (obj instanceof Object[]) {
			Object[] array = (Object[]) obj;
			if (array == null || array.length <= 0) {
				iterator.remove();
			}
		} else {
			if (obj == null) {
				iterator.remove();
			}
		}
	}

	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().length() == 0;
	}

	/**
	 * 将map里的数据转化成key=value的字符串，例如student='samlin',
	 * @param map
	 * @param Symbol  符号
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年2月7日 
	 *
	 */
	private static String mapChangeToKeyValue(HashMap<String, String> map, String Symbol) {
		Set<String> field = map.keySet();
		StringBuffer sb = new StringBuffer();
		for (String fieldString : field) {
			sb.append(fieldString + " = " + Symbol + map.get(fieldString) + Symbol + ",");
		}
		sb.delete(sb.lastIndexOf(","), sb.length());
		return sb.toString();

	}
}
