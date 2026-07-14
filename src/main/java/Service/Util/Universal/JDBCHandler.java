
package Service.Util.Universal;

import Service.Core.ProjectEnum;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JDBC数据库操作工具类
 * 提供数据库连接、查询、更新等操作的统一处理
 * 优化版本：添加连接池、日志记录、性能优化
 *
 * @author ApiAuto
 * @version 3.0
 */
public class JDBCHandler {
    private static final Logger logger = Logger.getLogger(JDBCHandler.class);
    // 数据库连接超时配置（毫秒）
    private static final int CONNECTION_TIMEOUT_MS = 10000; // 10秒
    private static final int SOCKET_TIMEOUT_MS = 10000;     // 10秒

    // 连接池缓存
    private static final Map<String, DataSource> dataSourceCache = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> connectionCounters = new ConcurrentHashMap<>();
    // 调用者类名缓存，避免重复解析
    private static final Map<String, ProjectEnum> callerDatabaseCache = new ConcurrentHashMap<>();

    // 数据库驱动加载
    static {
        try {
            String driver = PropertiesHandle.jdbcProperties.getProperty("jdbc.driver");
            if (driver == null || driver.trim().isEmpty()) {
                throw new RuntimeException("Database driver configuration is missing or empty");
            }
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error("Database driver not found: " + e.getMessage(), e);
            throw new RuntimeException("Database driver not found: " + e.getMessage(), e);
        }
    }

    /**
     * 获取或创建数据源
     *
     * @param database 数据库标识
     * @return 数据源对象
     */
    private static DataSource getDataSource(String database) {
        return dataSourceCache.computeIfAbsent(database, key -> {
            try {
                String url = PropertiesHandle.jdbcProperties.getProperty("jdbc." + key + ".url");
                String user = PropertiesHandle.jdbcProperties.getProperty("jdbc." + key + ".user");
                String password = PropertiesHandle.jdbcProperties.getProperty("jdbc." + key + ".password");

                if (url == null || url.trim().isEmpty()) {
                    throw new RuntimeException("Database URL not found for database: " + key);
                }
                if (user == null || user.trim().isEmpty()) {
                    throw new RuntimeException("Database user not found for database: " + key);
                }
                if (password == null) {
                    throw new RuntimeException("Database password not found for database: " + key);
                }

                // 创建简单的连接池数据源
                return new SimpleDataSource(url, user, password);
            } catch (Exception e) {
                logger.error("Failed to create data source for database: " + key, e);
                throw new RuntimeException("Failed to create data source for database: " + key, e);
            }
        });
    }

    /**
     * 获取数据库连接（使用连接池）
     *
     * @param database 数据库标识
     * @return 数据库连接对象
     * @throws RuntimeException 当连接失败或超时时抛出
     */
    private static Connection getConn(String database) {
        validateDatabaseParameter(database);

        try {
            DataSource dataSource = getDataSource(database);
            Connection connection = dataSource.getConnection();

            if (connection == null) {
                throw new RuntimeException("Failed to establish database connection for: " + database);
            }

            return connection;
        } catch (SQLException e) {
            String errorMsg = "Database connection failed for " + database + ": " + e.getMessage();
            if (e.getMessage().contains("timeout") || e.getMessage().contains("timed out")) {
                errorMsg += " (连接超时，超过 " + CONNECTION_TIMEOUT_MS + "ms)";
            }
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    /**
     * 验证数据库参数
     *
     * @param database 数据库标识
     */
    private static void validateDatabaseParameter(String database) {
        if (database == null || database.trim().isEmpty()) {
            throw new RuntimeException("Database identifier cannot be null or empty");
        }

        if (PropertiesHandle.jdbcProperties == null) {
            throw new RuntimeException("JDBC properties not initialized");
        }
    }

    /**
     * 为数据库URL添加超时参数
     *
     * @param url 原始数据库URL
     * @return 添加了超时参数的URL
     */
    private static String addTimeoutToUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            return url;
        }

        StringBuilder urlBuilder = new StringBuilder(url);

        // 检查URL是否已经包含参数
        if (url.contains("?")) {
            // 如果已经有参数，检查是否已经包含超时参数
            if (!url.contains("connectTimeout") && !url.contains("socketTimeout")) {
                urlBuilder.append("&connectTimeout=").append(CONNECTION_TIMEOUT_MS);
                urlBuilder.append("&socketTimeout=").append(SOCKET_TIMEOUT_MS);
            }
        } else {
            // 如果没有参数，直接添加超时参数
            urlBuilder.append("?connectTimeout=").append(CONNECTION_TIMEOUT_MS);
            urlBuilder.append("&socketTimeout=").append(SOCKET_TIMEOUT_MS);
        }

        return urlBuilder.toString();
    }

    /**
     * 简单的数据源实现
     */
    private static class SimpleDataSource implements DataSource {
        private final String user;
        private final String password;
        private final String urlWithTimeout;

        public SimpleDataSource(String url, String user, String password) {
            this.user = user;
            this.password = password;
            this.urlWithTimeout = addTimeoutToUrl(url);
        }

        @Override
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(urlWithTimeout, user, password);
        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            return DriverManager.getConnection(urlWithTimeout, username, password);
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            return DriverManager.getLogWriter();
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {
            DriverManager.setLogWriter(out);
        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {
            DriverManager.setLoginTimeout(seconds);
        }

        @Override
        public int getLoginTimeout() throws SQLException {
            return DriverManager.getLoginTimeout();
        }

        @Override
        public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
            throw new SQLFeatureNotSupportedException("getParentLogger not supported");
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            throw new SQLException("unwrap not supported");
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            return false;
        }
    }

    /**
     * 设置PreparedStatement参数
     *
     * @param pstmt  PreparedStatement对象
     * @param params 参数数组
     * @throws SQLException SQL异常
     */
    private static void setParameters(PreparedStatement pstmt, Object... params) throws SQLException {
        if (pstmt == null) {
            throw new SQLException("PreparedStatement cannot be null");
        }

        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                // 参数验证，防止SQL注入
                Object param = params[i];
                if (param instanceof String) {
                    String strParam = (String) param;
                    // 检查是否包含潜在的SQL注入字符
                    if (strParam.contains("'") || strParam.contains("\"") || strParam.contains(";") ||
                            strParam.contains("--") || strParam.contains("/*") || strParam.contains("*/")) {
                        logger.warn("Potential SQL injection detected in parameter: " + strParam);
                    }
                }
                pstmt.setObject(i + 1, param);
            }
        }
    }

    /**
     * 验证SQL语句
     *
     * @param sql SQL语句
     */
    private static void validateSql(String sql) {
        if (sql == null || sql.trim().isEmpty()) {
            throw new RuntimeException("SQL statement cannot be null or empty");
        }

        // 基本的SQL注入检查
        String upperSql = sql.toUpperCase().trim();
        if (upperSql.contains("DROP ") || upperSql.contains("DELETE ") ||
                upperSql.contains("TRUNCATE ") || upperSql.contains("ALTER ")) {
            logger.warn("Potentially dangerous SQL statement detected: " + sql);
        }
    }

    /**
     * 执行增删改操作
     *
     * @param database 数据库枚举
     * @param sql      SQL语句
     * @param params   参数数组
     * @return 受影响的行数
     * @throws RuntimeException 当操作失败时抛出
     */
    public static int AddDeleteAlter(ProjectEnum project, String sql, Object... params) {
        if (project == null) {
            throw new RuntimeException("Project enum cannot be null");
        }
        validateProjectDatabase(project);
        validateSql(sql);

        try (Connection conn = getConn(project.getDatabaseCode());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setParameters(pstmt, params);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows < 0) {
                throw new RuntimeException("SQL execution failed: " + sql);
            }

            return affectedRows;
        } catch (SQLException e) {
            throw new RuntimeException("Database operation failed: " + e.getMessage(), e);
        }
    }

    /**
     * 执行查询操作
     *
     * @param database 数据库枚举
     * @param sql      SQL查询语句
     * @param params   参数数组
     * @return 查询结果列表
     * @throws RuntimeException 当查询失败时抛出
     */
    private static List<Map<String, Object>> executeQuery(ProjectEnum project, String sql, Object... params) {
        if (project == null) {
            throw new RuntimeException("Project enum cannot be null");
        }
        validateProjectDatabase(project);
        validateSql(sql);

        List<Map<String, Object>> recordList = new ArrayList<>();

        try (Connection conn = getConn(project.getDatabaseCode());
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            setParameters(pstmt, params);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                if (metaData == null) {
                    throw new RuntimeException("Failed to get result set metadata");
                }

                int columnCount = metaData.getColumnCount();
                int rowCount = 0;

                while (resultSet.next()) {
                    Map<String, Object> record = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnLabel(i);
                        Object columnValue = resultSet.getObject(i);
                        record.put(columnName, columnValue);
                    }
                    recordList.add(record);
                    rowCount++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query execution failed: " + e.getMessage(), e);
        }

        return recordList;
    }

    /**
     * 查询结果转换为JSON字符串
     *
     * @param database 数据库枚举
     * @param sql      SQL查询语句
     * @param params   参数数组
     * @return JSON字符串[{"card_no":"6212264963288427184"},{"card_no":"6212264963288427184"}]，查询失败时返回"[]"
     */
    public static String queryRowsAsJsonArray(ProjectEnum project, String sql, Object... params) {
        try {
            List<Map<String, Object>> queryResultList = executeQuery(project, sql, params);
            String result = DataConverterHandler.objectToJson(queryResultList);
            return (result != null && !result.isEmpty()) ? result : "[]";
        } catch (Exception e) {
            logger.error("Query to JSON conversion failed: " + e.getMessage(), e);
            return "[]";
        }
    }

    /**
     * 查询结果转换为第一条记录的JSON字符串
     *
     * @param database 数据库枚举
     * @param sql      SQL查询语句
     * @param params   参数数组
     * @return 第一条记录的JSON字符串，例如：{"card_no":"6212264963288427184"},无结果时返回"{}"
     */
    public static String queryFirstRowAsJson(ProjectEnum project, String sql, Object... params) {
        try {
            List<Map<String, Object>> queryResultList = executeQuery(project, sql, params);

            if (queryResultList == null || queryResultList.isEmpty()) {
                return "{}";
            }

            Map<String, Object> first = queryResultList.get(0);
            if (first == null) {
                return "{}";
            }

            String result = DataConverterHandler.mapToJson(first);
            return result != null ? result : "{}";
        } catch (Exception e) {
            logger.error("Query to first JSON conversion failed: " + e.getMessage(), e);
            return "{}";
        }
    }

    /**
     * 获取调用者对应的数据库枚举（带缓存）
     *
     * @return 数据库枚举
     */
    private static ProjectEnum getCallerDatabase() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        // 跳过当前方法和getStackTrace方法，找到真正的调用者
        for (int i = 3; i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();

            // 跳过JDBCHandler类本身
            if (!className.equals(JDBCHandler.class.getName())) {
                // 使用缓存避免重复解析
                return callerDatabaseCache.computeIfAbsent(className, JDBCHandler::getDatabaseFromClassName);
            }
        }

        throw new RuntimeException("无法获取调用者类名");
    }

    /**
     * 查询SQL并根据规则提取数据
     *
     * @param database 数据库枚举
     * @param sql      SQL查询语句
     * @param params   参数字符串
     * @param rule     JSON路径规则
     * @return 提取的数据，失败时返回null
     */
    public static String querySqlAndFetchByRule(ProjectEnum project, String sql, String params, String rule) {
        if (rule == null || rule.trim().isEmpty()) {
            throw new RuntimeException("JSON path rule cannot be null or empty");
        }

        try {
            List<Map<String, Object>> queryResultList = executeQuery(project, sql, params);
            String resultJson = DataConverterHandler.listToJsonStr(queryResultList);

            if (resultJson == null || resultJson.trim().isEmpty()) {
                return null;
            }

            return JsonHandler.readStringByJsonPath(rule, resultJson);
        } catch (Exception e) {
            logger.error("Query and fetch by rule failed: " + e.getMessage(), e);
            return null;
        }
    }

    /**
     * 查询结果转换为JSON字符串（自动推断数据库）
     * 通过调用栈获取调用者的包路径，自动映射到对应的数据库枚举
     *
     * @param sql SQL查询语句
     * @param uid 用户ID参数
     * @return JSON字符串，查询失败时返回"[]"
     */
    public static String queryRowsAsJsonArray(String sql, String uid) {
        try {
            ProjectEnum project = getCallerDatabase();
            return queryRowsAsJsonArray(project, sql, uid);
        } catch (Exception e) {
            logger.error("自动推断数据库查询失败: " + e.getMessage(), e);
            return "[]";
        }
    }

    /**
     * 查询结果转换为JSON字符串（自动推断数据库）
     * 通过调用栈获取调用者的包路径，自动映射到对应的数据库枚举
     *
     * @param sql SQL查询语句
     * @param uid 用户ID参数
     * @return JSON字符串，查询失败时返回"{}"
     */
    public static String queryFirstRowAsJson(String sql, String uid) {
        try {
            ProjectEnum project = getCallerDatabase();
            return queryFirstRowAsJson(project, sql, uid);
        } catch (Exception e) {
            logger.error("自动推断数据库查询失败: " + e.getMessage(), e);
            return "{}";
        }
    }

    /**
     * 根据类名获取对应的数据库枚举
     *
     * @param className 完整的类名
     * @return 对应的数据库枚举
     */
    private static ProjectEnum getDatabaseFromClassName(String className) {
        // 解析包路径，提取项目名
        String[] packageParts = className.split("\\.");

        // 查找包含项目名的包路径
        for (String part : packageParts) {
            ProjectEnum project = ProjectEnum.getByCode(part);
            if (project != null && project.getDatabaseCode() != null) {
                return project;
            }
        }
        // 如果没有找到匹配的项目名，抛出异常
        throw new RuntimeException("无法从类名 '" + className + "' 中推断出对应的项目枚举");
    }

    private static void validateProjectDatabase(ProjectEnum project) {
        if (project.getDatabaseCode() == null || project.getDatabaseCode().trim().isEmpty()) {
            throw new RuntimeException("Project '" + project.getCode() + "' has no database configured");
        }
    }

}

