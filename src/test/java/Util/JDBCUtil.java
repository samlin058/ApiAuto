package Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {

	private static String url;
	private static String user;
	private static String password;

	static {
		Properties properties = new Properties();
		try {
			properties.load(JDBCUtil.class.getResourceAsStream("/jdbc.properties"));
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");

			// 加载驱动
			try {
				Class.forName(properties.getProperty("jdbc.driver"));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("文件加载出现异常");
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return conn;

	}

	public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
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

	public static void close(PreparedStatement preparedStatement, Connection connection) {
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

	/**
	 * 数据库的增、删、改
	 * 
	 * @param sql
	 *            操作的sql
	 * @param params
	 *            参数列表
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void zeng_shan_gai(String sql, Object... params) throws ClassNotFoundException, SQLException {

		// 获得连接
		Connection conn = getConnection();
		// sql预编译
		PreparedStatement pstmt = conn.prepareStatement(sql);
		// 参数的个数是不确定的，所以要用可变参数
		for (int i = 0; i < params.length; i++) {
			pstmt.setObject(i + 1, params[i]);
		}
		pstmt.setString(1, "happy");
		pstmt.setString(2, "qweqw");
		pstmt.setString(3, "13333333333");
		// 执行sql
		pstmt.execute();
		// 关闭资源
		close(pstmt, conn);
	}

	/**
	 * 查询
	 * 
	 * @param sql
	 *            执行的sql
	 * @param params
	 *            占位的参数
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static List<Map<String, String>> executeQuery(String sql, Object... params) {
		List<Map<String, String>> recordList = null;
		// 创建连接
		Connection conn = getConnection();
		// 准备陈述对象(预编译)
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			// 循环插入占位符的值
			for (int i = 0; i < params.length; i++) {
				pstmt.setObject(i + 1, params[i]);
			}
			ResultSet resultSet = pstmt.executeQuery();
			// 获得元数据:通过这个元数据对象，可以拿到结果集列的一些信息，例如每个字段的类型，名称，列数
			ResultSetMetaData metaData = resultSet.getMetaData();
			// 获得多少列（列的个数）
			int numberOfColumns = metaData.getColumnCount();
			// 遍历查询结果
			recordList = new ArrayList<Map<String, String>>();
			while (resultSet.next()) {
				Map<String, String> recordMap = new HashMap<String, String>();
				for (int i = 0; i < numberOfColumns; i++) {
					// 获得某列的名称
					String columnName = metaData.getColumnName(i + 1);
					// 获得列的值
					String columnValue =  resultSet.getString(i + 1);
					// 把该行数据的当前列的信息保存在Map对象中
					recordMap.put(columnName, columnValue);
				}
				// 把这行数据（map）添加到列表中
				recordList.add(recordMap);
			}
			// 关闭资源
			close(resultSet, pstmt, conn);
		} catch (Exception e) {

		}

		return recordList;
	}
}
