package Service.Listen;

import Service.Util.Universal.PropertiesHandle;
import com.alibaba.fastjson.JSON;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestRunner;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.io.FilenameFilter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义 HTML 报告监听器：按当天日期目录输出，文件名为 id_时间戳.html。
 */
public class HtmlReportListener implements ISuiteListener, ITestListener, IInvokedMethodListener, IReporter {

    private static final String ATTR_HTTP_CALLS = "apiAuto.httpCalls";
    private static final String ATTR_ASSERTION = "apiAuto.assertionDetail";
    private static final String ATTR_TEST_DATA = "apiAuto.testData";
    private static final String TEMPLATE_DIR = "src/main/resources/TemplateFile";
    private static final String TEMPLATE_NAME = "testReport.ftl";
    /** 报告文件名：{id}_{yyyyMMdd_HHmmss}.html */
    private static final Pattern REPORT_FILE_PATTERN = Pattern.compile("^(\\d+)_.+\\.html$", Pattern.CASE_INSENSITIVE);

    private volatile String reportDir;
    private volatile String reportFile;
    private volatile long suiteStartMs;

    @Override
    public void onStart(ISuite suite) {
        File dayDir = resolveDayReportDir();
        if (!dayDir.exists() && !dayDir.mkdirs()) {
            System.err.println("[HtmlReportListener] 无法创建报告目录: " + dayDir.getAbsolutePath());
        }
        reportDir = dayDir.getAbsolutePath();
        reportFile = null;
        suiteStartMs = System.currentTimeMillis();
        System.out.println("[HtmlReportListener] 本轮报告目录: " + reportDir);
    }

    @Override
    public void onFinish(ISuite suite) {
        if (reportFile != null) {
            System.out.println("[HtmlReportListener] 报告已生成: " + reportFile);
        } else if (reportDir != null) {
            System.out.println("[HtmlReportListener] 报告目录: " + reportDir + "（尚未生成 HTML）");
        }
    }

    private File resolveDayReportDir() {
        String base = "TestReports";
        if (PropertiesHandle.configuration != null) {
            String configured = PropertiesHandle.configuration.getProperty("path.report");
            if (configured != null && !configured.trim().isEmpty()) {
                base = configured.trim();
            }
        }
        String day = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return new File(System.getProperty("user.dir"), base + File.separator + day);
    }

    /**
     * 扫描当天目录下已有报告，取最大 id + 1；删除中间 id 也不会复用空缺。
     */
    private int nextReportId(File dayDir) {
        int maxId = 0;
        if (dayDir == null || !dayDir.isDirectory()) {
            return 1;
        }
        File[] files = dayDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name != null && REPORT_FILE_PATTERN.matcher(name).matches();
            }
        });
        if (files != null) {
            for (File file : files) {
                Matcher matcher = REPORT_FILE_PATTERN.matcher(file.getName());
                if (matcher.matches()) {
                    try {
                        maxId = Math.max(maxId, Integer.parseInt(matcher.group(1)));
                    } catch (NumberFormatException ignored) {
                        // ignore malformed numeric prefix
                    }
                }
            }
        }
        return maxId + 1;
    }

    @Override
    public void onStart(ITestContext context) {
        if (reportDir != null && context instanceof TestRunner) {
            ((TestRunner) context).setOutputDirectory(reportDir);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {
    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            ReportContext.clear();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            attachContext(testResult);
            ReportContext.clear();
        }
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        String targetDir = reportDir;
        if (targetDir == null) {
            File dayDir = resolveDayReportDir();
            targetDir = dayDir.getAbsolutePath();
            reportDir = targetDir;
        }
        try {
            List<CaseResult> cases = collectCases(suites);
            Map<String, Object> model = buildModel(suites, cases);
            renderHtml(targetDir, model);
        } catch (Exception e) {
            System.err.println("[HtmlReportListener] 生成报告失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void attachContext(ITestResult result) {
        List<ReportContext.HttpCall> calls = ReportContext.snapshotHttpCalls();
        if (!calls.isEmpty()) {
            result.setAttribute(ATTR_HTTP_CALLS, calls);
        }
        ReportContext.AssertionDetail assertion = ReportContext.getAssertionDetail();
        if (assertion != null) {
            result.setAttribute(ATTR_ASSERTION, assertion);
        }
        Object[] params = result.getParameters();
        if (params != null && params.length > 0) {
            result.setAttribute(ATTR_TEST_DATA, serializeParams(params));
        }
    }

    private String serializeParams(Object[] params) {
        try {
            return ReportContext.truncate(JSON.toJSONString(params, true));
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < params.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(params[i]);
            }
            return ReportContext.truncate(sb.toString());
        }
    }

    private List<CaseResult> collectCases(List<ISuite> suites) {
        List<CaseResult> cases = new ArrayList<>();
        for (ISuite suite : suites) {
            for (org.testng.ISuiteResult suiteResult : suite.getResults().values()) {
                ITestContext context = suiteResult.getTestContext();
                addResults(cases, context.getPassedTests(), "PASS");
                addResults(cases, context.getFailedTests(), "FAIL");
                addResults(cases, context.getSkippedTests(), "SKIP");
            }
        }
        // 失败优先，其次跳过，最后通过；同状态内按开始时间
        cases.sort(Comparator
                .comparingInt((CaseResult c) -> statusOrder(c.getStatus()))
                .thenComparingLong(CaseResult::getStartMs));
        return cases;
    }

    private static int statusOrder(String status) {
        if ("FAIL".equals(status)) {
            return 0;
        }
        if ("SKIP".equals(status)) {
            return 1;
        }
        return 2;
    }

    private void addResults(List<CaseResult> cases, IResultMap resultMap, String status) {
        if (resultMap == null) {
            return;
        }
        Set<ITestResult> results = resultMap.getAllResults();
        if (results == null) {
            return;
        }
        for (ITestResult result : results) {
            cases.add(toCaseResult(result, status));
        }
    }

    @SuppressWarnings("unchecked")
    private CaseResult toCaseResult(ITestResult result, String status) {
        CaseResult c = new CaseResult();
        c.setStatus(status);
        c.setClassName(result.getTestClass() != null ? result.getTestClass().getName() : "");
        c.setMethodName(result.getMethod() != null ? result.getMethod().getMethodName() : "");
        String testName = result.getName();
        if (result.getMethod() != null && result.getMethod().getDescription() != null
                && !result.getMethod().getDescription().isEmpty()) {
            testName = result.getMethod().getDescription();
        }
        if (result.getTestName() != null && !result.getTestName().isEmpty()) {
            testName = result.getTestName();
        }
        c.setTestName(testName);
        c.setStartMs(result.getStartMillis());
        c.setEndMs(result.getEndMillis());
        c.setDurationMs(Math.max(0, result.getEndMillis() - result.getStartMillis()));

        Object testData = result.getAttribute(ATTR_TEST_DATA);
        if (testData == null) {
            Object[] params = result.getParameters();
            if (params != null && params.length > 0) {
                testData = serializeParams(params);
            }
        }
        c.setTestData(testData == null ? "" : String.valueOf(testData));

        Object assertionAttr = result.getAttribute(ATTR_ASSERTION);
        if (assertionAttr instanceof ReportContext.AssertionDetail) {
            ReportContext.AssertionDetail ad = (ReportContext.AssertionDetail) assertionAttr;
            c.setAssertionActual(ad.getActual());
            c.setAssertionExpected(ad.getExpected());
            c.setAssertionMessage(ad.getMessage());
        }

        Object callsAttr = result.getAttribute(ATTR_HTTP_CALLS);
        if (callsAttr instanceof List) {
            c.setHttpCalls((List<ReportContext.HttpCall>) callsAttr);
        } else {
            c.setHttpCalls(Collections.emptyList());
        }

        Throwable throwable = result.getThrowable();
        if (throwable != null) {
            c.setErrorMessage(throwable.getMessage() == null ? throwable.toString() : throwable.getMessage());
            c.setStackTrace(stackTraceToString(throwable));
        } else {
            c.setErrorMessage("");
            c.setStackTrace("");
        }
        c.setFailed("FAIL".equals(status));
        return c;
    }

    private String stackTraceToString(Throwable t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t.toString()).append('\n');
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append("    at ").append(element.toString()).append('\n');
            if (sb.length() > ReportContext.MAX_BODY_LENGTH) {
                sb.append("...[truncated]\n");
                break;
            }
        }
        Throwable cause = t.getCause();
        if (cause != null && cause != t) {
            sb.append("Caused by: ").append(ReportContext.truncate(cause.toString())).append('\n');
        }
        return sb.toString();
    }

    private Map<String, Object> buildModel(List<ISuite> suites, List<CaseResult> cases) {
        int pass = 0;
        int fail = 0;
        int skip = 0;
        for (CaseResult c : cases) {
            if ("PASS".equals(c.getStatus())) {
                pass++;
            } else if ("FAIL".equals(c.getStatus())) {
                fail++;
            } else if ("SKIP".equals(c.getStatus())) {
                skip++;
            }
        }
        int total = pass + fail + skip;
        String passRate = total == 0 ? "N/A" : String.format("%.2f%%", pass * 100.0 / total);

        long start = suiteStartMs > 0 ? suiteStartMs : Long.MAX_VALUE;
        long end = System.currentTimeMillis();
        for (ISuite suite : suites) {
            for (org.testng.ISuiteResult suiteResult : suite.getResults().values()) {
                ITestContext ctx = suiteResult.getTestContext();
                if (ctx.getStartDate() != null) {
                    start = Math.min(start, ctx.getStartDate().getTime());
                }
                if (ctx.getEndDate() != null) {
                    end = Math.max(end, ctx.getEndDate().getTime());
                }
            }
        }
        if (start == Long.MAX_VALUE) {
            start = end;
        }
        long durationMs = Math.max(0, end - start);

        String suiteName = suites.isEmpty() ? "Suite" : suites.get(0).getName();
        List<CaseResult> failedCases = new ArrayList<>();
        List<CaseResult> skippedCases = new ArrayList<>();
        List<CaseResult> passedCases = new ArrayList<>();
        for (CaseResult c : cases) {
            if (c.isFailed()) {
                failedCases.add(c);
            } else if ("SKIP".equals(c.getStatus())) {
                skippedCases.add(c);
            } else {
                passedCases.add(c);
            }
        }

        Map<String, Object> model = new HashMap<>();
        model.put("suiteName", suiteName);
        model.put("generatedAt", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        model.put("total", total);
        model.put("pass", pass);
        model.put("fail", fail);
        model.put("skip", skip);
        model.put("passRate", passRate);
        model.put("durationMs", durationMs);
        model.put("durationText", formatDuration(durationMs));
        model.put("cases", cases);
        model.put("failedCases", failedCases);
        model.put("skippedCases", skippedCases);
        model.put("passedCases", passedCases);
        model.put("reportDir", reportDir == null ? "" : reportDir);
        return model;
    }

    private String formatDuration(long ms) {
        long seconds = ms / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        long millis = ms % 1000;
        if (hours > 0) {
            return String.format("%dh %dm %ds", hours, minutes, secs);
        }
        if (minutes > 0) {
            return String.format("%dm %ds", minutes, secs);
        }
        if (seconds > 0) {
            return String.format("%d.%03ds", seconds, millis);
        }
        return ms + "ms";
    }

    private void renderHtml(String targetDir, Map<String, Object> model) throws Exception {
        File outDir = new File(targetDir);
        if (!outDir.exists() && !outDir.mkdirs()) {
            throw new IllegalStateException("无法创建目录: " + targetDir);
        }
        int nextId = nextReportId(outDir);
        String stamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = nextId + "_" + stamp + ".html";
        File report = new File(outDir, fileName);

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_DIR));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = cfg.getTemplate(TEMPLATE_NAME);
        try (Writer writer = new OutputStreamWriter(Files.newOutputStream(report.toPath()), StandardCharsets.UTF_8)) {
            template.process(model, writer);
        }
        reportFile = report.getAbsolutePath();
        System.out.println("[HtmlReportListener] HTML 报告: " + reportFile);
    }

    public static class CaseResult {
        private String status;
        private String className;
        private String methodName;
        private String testName;
        private long startMs;
        private long endMs;
        private long durationMs;
        private String testData;
        private String assertionActual;
        private String assertionExpected;
        private String assertionMessage;
        private List<ReportContext.HttpCall> httpCalls = Collections.emptyList();
        private String errorMessage;
        private String stackTrace;
        private boolean failed;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getMethodName() {
            return methodName;
        }

        public void setMethodName(String methodName) {
            this.methodName = methodName;
        }

        public String getTestName() {
            return testName;
        }

        public void setTestName(String testName) {
            this.testName = testName;
        }

        public long getStartMs() {
            return startMs;
        }

        public void setStartMs(long startMs) {
            this.startMs = startMs;
        }

        public long getEndMs() {
            return endMs;
        }

        public void setEndMs(long endMs) {
            this.endMs = endMs;
        }

        public long getDurationMs() {
            return durationMs;
        }

        public void setDurationMs(long durationMs) {
            this.durationMs = durationMs;
        }

        public String getTestData() {
            return testData;
        }

        public void setTestData(String testData) {
            this.testData = testData;
        }

        public String getAssertionActual() {
            return assertionActual;
        }

        public void setAssertionActual(String assertionActual) {
            this.assertionActual = assertionActual;
        }

        public String getAssertionExpected() {
            return assertionExpected;
        }

        public void setAssertionExpected(String assertionExpected) {
            this.assertionExpected = assertionExpected;
        }

        public String getAssertionMessage() {
            return assertionMessage;
        }

        public void setAssertionMessage(String assertionMessage) {
            this.assertionMessage = assertionMessage;
        }

        public List<ReportContext.HttpCall> getHttpCalls() {
            return httpCalls;
        }

        public void setHttpCalls(List<ReportContext.HttpCall> httpCalls) {
            this.httpCalls = httpCalls;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public String getStackTrace() {
            return stackTrace;
        }

        public void setStackTrace(String stackTrace) {
            this.stackTrace = stackTrace;
        }

        public boolean isFailed() {
            return failed;
        }

        public void setFailed(boolean failed) {
            this.failed = failed;
        }
    }
}
