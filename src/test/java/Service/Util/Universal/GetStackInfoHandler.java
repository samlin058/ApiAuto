package Service.Util.Universal;

public class GetStackInfoHandler {

    /**
     *  根据当前线程的栈轨迹，通过指定索引（num）获取调用方法的名称
     * @param num
     * @return
     */
    public static String getCallerMethodNameByStackIndex(int num) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String callerMethodName= "";
        // 假设调用http()的方法在栈轨迹的第3个元素中
        if (stackTrace.length >= num) {
            StackTraceElement caller = stackTrace[num];
            callerMethodName = caller.getMethodName();
        }
        return callerMethodName;
    }

    /**
     * 返回调用者类路径
     * @return 类路径
     */
    public static String getCallerClassPath() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // 栈轨迹中索引 2 是当前方法，索引 3 是调用者
        if (stackTrace.length > 3) {
            return  stackTrace[3].getClassName();
        }
        return "无法确定调用者类";
    }

    /**
     * 判断调用栈中是否有类属于Application.DataFactory目录
     * @return true-有，false-无
     */
    public static boolean isCalledFromDataFactory() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            if (className.startsWith("Application.DataFactory")) {
                return true;
            }
        }
        return false;
    }
}
