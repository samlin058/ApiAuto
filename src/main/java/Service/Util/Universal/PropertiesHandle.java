package Service.Util.Universal;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesHandle {

    public static Properties hostProperties;// 访问配置
    public static Properties configuration;// 项目配置
    public static Properties jdbcProperties;// 数据库连接配置

    static {
        if (jdbcProperties == null) {
            jdbcProperties = new Properties();
        }

        if (hostProperties == null) {
            hostProperties = new Properties();
        }

        if (configuration == null) {
            configuration = new Properties();
        }

        hostProperties = getProperties("src/main/resources/host.properties");
        configuration = getProperties("src/main/resources/configuration.properties");
        jdbcProperties = getProperties("src/main/resources/jdbc.properties");
    }

    private static Properties getProperties(String path) {
        Properties properties = new Properties();
        File file = new File(path);
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static Map<String, String> PropertiesToHashMapExample(Properties properties) {
        Map<String, String> hashMap = new HashMap<>();
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            hashMap.put(key, value);
        }
        return hashMap;
    }
}
