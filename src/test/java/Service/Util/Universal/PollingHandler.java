package Service.Util.Universal;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class PollingHandler {


    /**
     * 轮询任务，直到满足成功条件或超时。
     *
     * @param query      查询操作的 Supplier，通常为一个接口调用方法的 Lambda 表达式
     * @param success    判断查询结果是否满足成功条件的 Predicate
     * @param maxTries   最大轮询次数
     * @param intervalMs 每次轮询的间隔时间（毫秒）
     * @param methodName 查询方法名（用于超时日志打印）
     * @param <T>        查询结果类型
     * @return           满足成功条件时返回查询结果，否则超时返回 null
     */
    public static <T> T pollUntilSuccess(Supplier<T> query, Predicate<T> success, int maxTries, long intervalMs, String methodName) {
        for (int i = 0; i < maxTries; i++) {
            try {
                Thread.sleep(intervalMs);
            } catch (InterruptedException e) { /* log */ }
            T result = query.get();
            if (success.test(result)) return result;
        }
        System.out.println(methodName + " 轮询超时");
        return null;
    }
}
