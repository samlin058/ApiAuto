package Service.Config;


import java.util.concurrent.ConcurrentHashMap;

public class Config {
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
