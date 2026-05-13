package Utils;

import TO.baseTable;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.ResultSetMetaData;

import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUtil {

	private static Connection conn;

	private static String driver;

	static {
		// 获取数据库驱动
		driver = PropertiesUtil.jdbcProperties.getProperty("jdbc.driver");
		// 加载驱动
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * 根据库名获取 Conn 连接对象
	 * @param library void
	 *
	 * @author       luandao
	 * @Date         2022-06-05
	 *
	 */
	private static void getConn(String library) {
		try {
			String urlkey = "jdbc." + library + ".url";
			String userkey = "jdbc." + library + ".user";
			String passwordkey = "jdbc." + library + ".password";
			String url = PropertiesUtil.jdbcProperties.getProperty(urlkey);
			String user = PropertiesUtil.jdbcProperties.getProperty(userkey);
			String password = PropertiesUtil.jdbcProperties.getProperty(passwordkey);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("获取 conn 失败，请检查 mysql 连接配置");
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过对象反射拿到库名并获取 conn
	 * @param table 
	 * void
	 * @author       luandao
	 * @Date         2022-03-15
	 *
	 */
	private static void getConnByObject(Object table) {
		try {
			Class<?> clazz = table.getClass();
			Method getterMethod = clazz.getMethod("getLibrary", null);
			String libName = (String) getterMethod.invoke(table);
			getConn(libName);
		} catch (Exception e) {
			System.out.println("获取 conn 失败，请检查 mysql 连接配置");
			e.printStackTrace();
		}
	}

	/**
	 * 根据 baseTable 的 libraryStr 获取 conn
	 * @param baseTable void
	 *
	 * @author       luandao
	 * @Date         2022-03-15
	 *
	 */
	private static void getConnByBaseTable(baseTable baseTable) {
		String libName = baseTable.getLibraryStr();
		getConn(libName);
	}

	/**
	 * 关闭资源
	 * @param resultSet
	 * @param preparedStatement
	 * @param connection void
	 *
	 * @author       luandao
	 * @Date         2021-12-04
	 *
	 */
	private static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭资源
	 * @param preparedStatement
	 * @param connection void
	 *
	 * @author       luandao
	 * @Date         2021-12-04
	 *
	 */
	private static void close(PreparedStatement preparedStatement, Connection connection) {
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
    
	private static List<Map<String, String>> excuteQuery(String sql) {
		List<Map<String, String>> recordList = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			// 通过元数据读取查询结果结构
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			// 获取列数
			int numberOfColumns = metaData.getColumnCount();
			// 组装查询结果
			recordList = new ArrayList<Map<String, String>>();
			while (resultSet.next()) {
				Map<String, String> recordMap = new HashMap<String, String>();
				for (int i = 0; i < numberOfColumns; i++) {
					// 获取列名
					String columnName = metaData.getColumnName(i + 1);
					// 获取列值
					String columnValue = resultSet.getString(i + 1);
					// 写入当前行 map
					recordMap.put(columnName, columnValue);
				}
				// 将当前行加入结果列表
				recordList.add(recordMap);
			}
			// 关闭资源
			close(resultSet, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}
	
	/**
	 * 数据库增删改
	 * 
	 * @param sql
	 *            待执行 sql
	 * @param params
	 *            参数列表
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void zeng_shan_gai(String sql, Object... params) throws ClassNotFoundException, SQLException {
		// sql 预处理
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 参数个数不确定，使用可变参数
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		} // 执行 sql
		pstmt.execute();
		close(pstmt, conn);
	}
    
	/**
	 * 更新 sql
	 * @param param
	 * @param condition void
	 *
	 * @author       luandao
	 * @Date         2022-06-05
	 *
	 */
	public static void update(Object param, Object condition) {
		// 获取 sql
		String sql = StringUtil.getUpdateSqlString(param, condition);
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			close(pstmt, conn);
			System.out.println("update ok!");
		} catch (SQLException e) {
			System.out.println("update error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insert(Object param) {
		getConnByObject(param);
		// 获取 sql
		String sql = GetSqlUtil.getInsertSqlString(param);

		// sql 预处理
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			close(pstmt, conn);
			System.out.println("insert ok!");
		} catch (SQLException e) {
			System.out.println("insert error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//TODO
	public static void insert2(List<Object> objects) {
		Object param=objects.get(0);
		getConnByObject(param);
		// 获取 sql
		String sql = GetSqlUtil.getInsertSqlString(param);

		// sql 预处理
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			close(pstmt, conn);
			System.out.println("insert ok!");
		} catch (SQLException e) {
			System.out.println("insert error");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	/**
	 * 删除 sql
	 * @param param void
	 *
	 * @author       luandao
	 * @Date         2022-06-05
	 *
	 */
	public static void delete(Object param) {
		if (StringUtil.isEmpty(StringUtil.getDeleteSqlString(param))) {
			System.out.println("当前条件为空，禁止直接全表删除");
		} else {
			try {
				// 获取 sql
				String sql = StringUtil.getDeleteSqlString(param);
				// sql 预处理
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 执行 sql
				pstmt.execute();
				// 关闭连接
				close(pstmt, conn);
				System.out.println("delete OK");
			} catch (Exception e1) {
				System.out.println("删除失败");
				e1.printStackTrace();
			}
		}
	}
    
	/**
	 * 根据 sql 查询数据库
	 * @param sql
	 * @param database
	 * @return List<Map<String,String>>
	 *
	 * @author       luandao
	 * @Date         2022-06-05
	 *
	 */
	public static List<Map<String, String>> executeQuery(String sql, String database) {
		List<Map<String, String>> recordList = null;
		PreparedStatement pstmt;
		try {
			getConn(database);
			pstmt = conn.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			// 通过元数据读取查询结果结构
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			// 获取列数
			int numberOfColumns = metaData.getColumnCount();
			// 组装查询结果
			recordList = new ArrayList<Map<String, String>>();
			while (resultSet.next()) {
				Map<String, String> recordMap = new HashMap<String, String>();
				for (int i = 0; i < numberOfColumns; i++) {
					// 获取列名
					String columnName = metaData.getColumnName(i + 1);
					// 获取列值
					String columnValue = resultSet.getString(i + 1);
					// 写入当前行 map
					recordMap.put(columnName, columnValue);
				}
				// 将当前行加入结果列表
				recordList.add(recordMap);
			}
			// 关闭资源
			close(resultSet, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}

	public static List<Map<String, String>> executeQuery2(String sql, Object... params) {
		List<Map<String, String>> recordList = null;
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			// 循环填充占位符参数
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			ResultSet resultSet = pstmt.executeQuery();
			// 通过元数据读取查询结果结构
			ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
			// 获取列数
			int numberOfColumns = metaData.getColumnCount();
			// 组装查询结果
			recordList = new ArrayList<Map<String, String>>();
			while (resultSet.next()) {
				Map<String, String> recordMap = new HashMap<String, String>();
				for (int i = 0; i < numberOfColumns; i++) {
					// 获取列名
					String columnName = metaData.getColumnName(i + 1);
					// 获取列值
					String columnValue = resultSet.getString(i + 1);
					// 写入当前行 map
					recordMap.put(columnName, columnValue);
				}
				// 将当前行加入结果列表
				recordList.add(recordMap);
			}
			// 关闭资源
			close(resultSet, pstmt, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return recordList;
	}

	public static List<Map<String, String>> query(baseTable tableObject) {
		String sql = GetSqlUtil.getSelectString(tableObject);
		getConnByObject(tableObject);
		List<Map<String, String>> recordList = excuteQuery(sql);
		return recordList;
	}
    
	/**
	 * 通过 sql 查询值
	 * @param tableObject
	 * @return List<Map<String,String>>
	 *
	 * @author       luandao
	 * @Date         2022-02-25
	 *
	 */
	public static List<Map<String, String>> queryForSql(baseTable baseTable) {
		String sql = baseTable.getSql();
		getConn(baseTable.getLibraryStr());
		List<Map<String, String>> recordList = excuteQuery(sql);
		return recordList;
	}

	public static void queryForSql(String library, String sql) {
		getConn(library);
		List<Map<String, String>> recordList = excuteQuery(sql);
		String a = JSONObject.toJSONString(recordList);
		System.out.println(a);
	}

	
	
	public static void main(String[] args) {
//		baseTable a = new baseTable();
//		a.setSql("SELECT * FROM gbl_collection_case WHERE id = 93850");
//		a.setLibraryStr("amc_wac_loan_goblin");
		/*a.setLibraryStr("amc_wac_loan_goblin");
		a.setSql("SELECT * FROM gbl_assignment WHERE collection_id IN (SELECT id FROM gbl_collection_case WHERE loan_id IN (SELECT id FROM gbl_loan WHERE channel ='4_wacao' )) ORDER BY collection_id DESC limit 0,2;");
		List<Map<String, String>> b=queryForSql(a);

		System.out.println(JSONObject.toJSONString(b));*/
//		JDBCUtil.queryForSql("amc_wac_loan_goblin", "SELECT * FROM strategy_dict WHERE remark LIKE '%映射%'");

	}
}
