package Service.Util.Universal;

import Service.Core.ApiBase;
import Service.Core.Login;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpHandler {

    private static final int RETRY_TIMES = Integer.parseInt((String) PropertiesHandle.configuration.get("retry"));

    public static String getHttp(String url, HashMap request) {
        HttpRequestBase httpRequestBase = buildGetRequest(url, request);
        return sendPackage(httpRequestBase);
    }

    public static String http(ApiBase ro) {
        HttpRequestBase request = buildRequest(ro);
        String ProjectName = ApiBase.getRoBelongProject(ro);
        boolean hasLoginData = Login.hasData(ProjectName);
        boolean isNullHead = !StringHandler.isEmpty(ApiBase.header);
        if (hasLoginData && isNullHead) {
            for (String key : ApiBase.header.keySet()) {
                Object value = ApiBase.header.get(key);
                request.addHeader(key, String.valueOf(value));
            }
        } else {
            for (String key : ApiBase.defaultHeader.keySet()) {
                Object value = ApiBase.defaultHeader.get(key);
                request.addHeader(key, String.valueOf(value));
            }
        }
        return sendPackage(request);
    }

    private static HttpRequestBase buildRequest(ApiBase ro) {
        if ("get".equalsIgnoreCase(ro.getRequestType())) {
            return buildGetRequest(ro.getUrl(), ro.getParam());
        } else if ("post".equalsIgnoreCase(ro.getRequestType())) {
            return buildPostRequest(ro);
        }
        throw new IllegalArgumentException("Unsupported HTTP request type: " + ro.getRequestType());
    }

    private static HttpRequestBase buildGetRequest(String url, Map<String, Object> paramsMap) {
        if (paramsMap == null || paramsMap.isEmpty()) {
            return new HttpGet(url);
        }
        List<NameValuePair> parameters = new ArrayList<>();
        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (!StringHandler.isEmpty(value)) {
                String valueStr = value.toString();
                parameters.add(new BasicNameValuePair(key, valueStr));
            }
        }
        String paramStr = URLEncodedUtils.format(parameters, "utf-8");
        return new HttpGet(url + "?" + paramStr);
    }

    private static HttpRequestBase buildPostRequest(ApiBase ro) {
        HttpPost postRequest = new HttpPost(ro.getUrl());

        // 处理带 query 的 POST 请求
        Map<String, Object> paramsMap = ro.getParam();
        if (paramsMap != null && !paramsMap.isEmpty()) {
            try {
                URIBuilder uriBuilder = new URIBuilder(ro.getUrl());
                paramsMap.forEach((key, value) -> uriBuilder.addParameter(key, value != null ? value.toString() : ""));
                postRequest.setURI(uriBuilder.build());
            } catch (Exception e) {
                System.err.println("========== HTTP 请求构建失败 ==========");
                System.err.println("请求地址 : " + ro.getUrl());
                System.err.println("异常信息 : " + e.getMessage());
                System.err.println("=====================================");
                e.printStackTrace();
            }
        }

        // 根据 Content-Type 选择不同的处理方式
        String contentType = ro.getContentType();
        Map<String, Object> bodyMap = ro.getBody();

        switch (contentType.toLowerCase()) {
            case "application/x-www-form-urlencoded":
                handleFormUrlEncoded(postRequest, bodyMap);
                break;

            case "application/json":
                handleJson(postRequest, bodyMap);
                break;

            case "multipart/form-data":
                handleMultipartFormData(postRequest, bodyMap, ro.getFileObject());
                break;

            default:
                throw new UnsupportedOperationException("Unsupported Content-Type: " + contentType);
        }

        return postRequest;
    }

    private static String sendPackage(HttpUriRequest request) {
        String method = request.getMethod();
        String uri = String.valueOf(request.getURI());
        try (CloseableHttpClient httpClient = HttpClients.custom().setRetryHandler(new StandardHttpRequestRetryHandler(RETRY_TIMES, true)).build();
             CloseableHttpResponse response = httpClient.execute(request)) {
            int statusCode = response.getStatusLine().getStatusCode();
            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            String responseBody = response.getEntity() != null ? EntityUtils.toString(response.getEntity()) : "";
            if (statusCode != 200) {
                printHttpError(method, uri, statusCode, reasonPhrase, responseBody);
                throw new RuntimeException("HTTP request failed with status " + statusCode + ": " + uri + " | response: " + responseBody);
            }
            return responseBody;
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            printHttpError(method, uri, e);
            throw new RuntimeException("HTTP request execution error: " + uri, e);
        }
    }

    private static void printHttpError(String method, String uri, int statusCode, String reasonPhrase, String responseBody) {
        System.err.println("========== HTTP 请求失败 ==========");
        System.err.println("请求方式 : " + method);
        System.err.println("请求地址 : " + uri);
        System.err.println("状态码   : " + statusCode);
        System.err.println("状态描述 : " + reasonPhrase);
        System.err.println("响应内容 : " + responseBody);
        System.err.println("===================================");
    }

    private static void printHttpError(String method, String uri, Exception e) {
        System.err.println("========== HTTP 请求异常 ==========");
        System.err.println("请求方式 : " + method);
        System.err.println("请求地址 : " + uri);
        System.err.println("异常信息 : " + e.getMessage());
        System.err.println("===================================");
        e.printStackTrace();
    }

    // 表单数据处理
    private static void handleFormUrlEncoded(HttpPost postRequest, Map<String, Object> bodyMap) {
        List<NameValuePair> parameters = new ArrayList<>();
        if (bodyMap != null) {
            bodyMap.forEach((key, value) -> parameters.add(new BasicNameValuePair(key, value != null ? value.toString() : "")));
        }
        postRequest.setEntity(new UrlEncodedFormEntity(parameters, StandardCharsets.UTF_8));
    }

    // JSON 数据处理
    private static void handleJson(HttpPost postRequest, Map<String, Object> bodyMap) {
        if (bodyMap != null && bodyMap.containsKey("JsonList")) {
            String jsonList = bodyMap.get("JsonList").toString();
            postRequest.setEntity(new StringEntity(jsonList, ContentType.APPLICATION_JSON));
        } else {
            String json = DataConverterHandler.mapToJson(bodyMap);
            postRequest.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        }
    }

    // 多部分表单数据处理
    private static void handleMultipartFormData(HttpPost postRequest, Map<String, Object> bodyMap, Map<String, String> fileMap) {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

        if (fileMap != null && !fileMap.isEmpty()) {
            fileMap.forEach((key, path) -> {
                File file = new File(path);
                if (file.exists() && file.isFile()) {
                    entityBuilder.addPart(key, new FileBody(file, ContentType.DEFAULT_BINARY));
                } else {
                    System.out.printf("文件路径无效或文件不存在: %s%n", path);
                }
            });
        }

        if (bodyMap != null) {
            bodyMap.forEach((key, value) -> entityBuilder.addTextBody(key, value != null ? value.toString() : "", ContentType.TEXT_PLAIN));
        }
        postRequest.setEntity(entityBuilder.build());
    }

    public static void main(String[] args) {
        String url = "http://lumos-h5.torch.wke-office.test.wacai.info/lumos/home";
        HashMap<Object, Object> request = new HashMap<>();
        Long mobilePhone =  13310000001L;
        for (int i = 1; i < 501; i++) {
            String mobile = String.valueOf(mobilePhone+i);
            String token = DecryptionHandler.getToken(mobile);
            request.put("jointToken", token);
            request.put("channelCode", "ZY_FL");
            String html = getHttp(url, request);
            Pattern pattern = Pattern.compile("\"notifyUrl\"\\s*:\\s*\"(https?://[^\"]+)\"");
            Matcher matcher = pattern.matcher(html);
            List<String> totalUrl = new ArrayList<>();
            if (matcher.find()) {
                String notifyUrl = matcher.group(1);
                totalUrl.add(notifyUrl);
            }
        }
        System.out.println("执行完毕");
    }

}
