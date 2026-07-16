package Service.Listen;

import java.util.ArrayList;
import java.util.List;

/**
 * 当前测试线程的报告上下文：收集 HTTP 调用与断言详情，供自定义 HTML 报告使用。
 */
public final class ReportContext {

    public static final int MAX_BODY_LENGTH = 8192;

    private static final ThreadLocal<List<HttpCall>> HTTP_CALLS = ThreadLocal.withInitial(ArrayList::new);
    private static final ThreadLocal<AssertionDetail> ASSERTION = new ThreadLocal<>();

    private ReportContext() {
    }

    public static void clear() {
        HTTP_CALLS.get().clear();
        ASSERTION.remove();
    }

    public static void addHttpCall(String method, String url, String requestBody, String responseBody, long costMs) {
        HTTP_CALLS.get().add(new HttpCall(
                method,
                url,
                truncate(requestBody),
                truncate(responseBody),
                costMs
        ));
    }

    public static void setAssertionDetail(Object actual, Object expected, String message) {
        ASSERTION.set(new AssertionDetail(
                actual == null ? "null" : String.valueOf(actual),
                expected == null ? "null" : String.valueOf(expected),
                message == null ? "" : message
        ));
    }

    public static List<HttpCall> snapshotHttpCalls() {
        return new ArrayList<>(HTTP_CALLS.get());
    }

    public static AssertionDetail getAssertionDetail() {
        return ASSERTION.get();
    }

    public static String truncate(String value) {
        if (value == null) {
            return "";
        }
        if (value.length() <= MAX_BODY_LENGTH) {
            return value;
        }
        return value.substring(0, MAX_BODY_LENGTH) + "...[truncated]";
    }

    public static class HttpCall {
        private final String method;
        private final String url;
        private final String requestBody;
        private final String responseBody;
        private final long costMs;

        public HttpCall(String method, String url, String requestBody, String responseBody, long costMs) {
            this.method = method;
            this.url = url;
            this.requestBody = requestBody;
            this.responseBody = responseBody;
            this.costMs = costMs;
        }

        public String getMethod() {
            return method;
        }

        public String getUrl() {
            return url;
        }

        public String getRequestBody() {
            return requestBody;
        }

        public String getResponseBody() {
            return responseBody;
        }

        public long getCostMs() {
            return costMs;
        }
    }

    public static class AssertionDetail {
        private final String actual;
        private final String expected;
        private final String message;

        public AssertionDetail(String actual, String expected, String message) {
            this.actual = actual;
            this.expected = expected;
            this.message = message;
        }

        public String getActual() {
            return actual;
        }

        public String getExpected() {
            return expected;
        }

        public String getMessage() {
            return message;
        }
    }
}
