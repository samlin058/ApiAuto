package Service.Core;


import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试运行时上下文，提供线程安全的全局数据存储。
 * <p>
 * 分为三类存储：配置数据（configData）、缓存数据（cacheData）、全局数据（globalData），
 * 用于在测试步骤之间传递和共享中间结果。
 * </p>
 */
public class TestContext {
    private static final ConcurrentHashMap<String, Object> configData = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Object> cacheData = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Object> globalData = new ConcurrentHashMap<>();
    public static void setConfigData(String key, Object value) {
        configData.put(key, value);
    }
    public static void setCacheData(String key, Object value) {
        cacheData.put(key, value);
    }
    public static void setGlobalData(String key, Object value) {
        globalData.put(key, value);
    }
    public static Object getConfigData(String key) {
        return configData.get(key);
    }
    public static Object getCacheData(String key) {
        return cacheData.get(key);
    }
    public static Object getGlobalData(String key) {
        return globalData.get(key);
    }
    public static String getCacheDataAsString(String key) {
        Object value = cacheData.get(key);
        return value != null ? value.toString() : null;
    }

}
