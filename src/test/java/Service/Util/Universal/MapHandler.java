package Service.Util.Universal;

import java.util.Iterator;
import java.util.Map;

public class MapHandler {
    /**
     * 移除掉map中value为空的情况
     *
     * @param map
     * @return
     */
    public static Map<String, Object> removeValueNull(Map<String, Object> map) {
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            if (entry.getValue() == null) {
                iterator.remove();
            }
        }
        return map;
    }
}
