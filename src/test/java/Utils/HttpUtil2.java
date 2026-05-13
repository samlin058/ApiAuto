package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import Utils.HttpUtil;
import Utils.StringUtil;

public class HttpUtil2 {
	private static Logger logger = Logger.getLogger(HttpUtil.class);
	/**
	 * post请求
	 * @param url
	 * @param paramsMap
	 * @param headers
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年4月19日 
	 *
	 */
	public static String post(String url, Map<String, Object> paramsMap, Map<String, Object> headers) {
		String resultStr = "";
		// 创建一个post请求
		HttpPost post = new HttpPost(url);
		// InputStreamEntity name = new InputStreamEntity(null);
		// post.setEntity(name);
		if (headers.size() != 0) {
			// 设置请求头
			Set<String> headersKey = headers.keySet();
			for (String headkeysName : headersKey) {
				post.addHeader(headkeysName, (String) headers.get(headkeysName));
			}
		}
		if (paramsMap.size() != 0) {
			// 解析params中的key
			Set<String> paramsKey = paramsMap.keySet();
			// 将参数传入list
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			// 设置参数
			for (String paramsName : paramsKey) {
				parameters.add(new BasicNameValuePair(paramsName, (String) paramsMap.get(paramsName)));
			}
			try {
				post.setEntity(new UrlEncodedFormEntity(parameters));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resultStr = sendPackage(post);
		return resultStr;
	}

	public static String postByJson(String url, Map<String, Object> paramsMap, Map<String, Object> headers) {
		String resultStr = "";
		// 创建一个post请求
		if (StringUtil.isEmpty(url)) {
			return null;
		}
		HttpPost post = new HttpPost(url);
		if (headers.size() != 0) {
			// 设置请求头
			Set<String> headersKey = headers.keySet();
			for (String headkeysName : headersKey) {
				post.addHeader(headkeysName, (String) headers.get(headkeysName));
			}
		}
		if (paramsMap.size() != 0) {
			String json = JSONObject.toJSONString(paramsMap);
			StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
			try {
				post.setEntity(entity);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		resultStr = sendPackage(post);
		return resultStr;
	}

	/**
	 * get带参带头请求
	 * 
	 * @param url
	 * @param paramsMap
	 * @param headers
	 * @return
	 */
	public static String get(String url, Map<String, Object> paramsMap, Map<String, Object> headers) {
		String resultStr = "";
		HttpGet get = null;
		if (paramsMap.size() == 0) {
			get = new HttpGet(url);
		} else {
			// 设置参数
			Set<String> keysets = paramsMap.keySet();
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			for (String key : keysets) {
				parameters.add(new BasicNameValuePair(key, (String) paramsMap.get(key)));
			}
			String paramStr = URLEncodedUtils.format(parameters, "utf-8");
			get = new HttpGet(url + "?" + paramStr);
		}

		if (headers.size() != 0) {
			// 设置头部信息
			Set<String> headersKey = headers.keySet();
			for (String headkeysName : headersKey) {
				get.addHeader(headkeysName, (String) headers.get(headkeysName));
			}
		}
		resultStr = sendPackage(get);
		return resultStr;
	}

	/**
	 * 发包动作
	 * @param request
	 * @return String
	 *
	 * @author       luandao
	 * @Date         2022年4月19日 
	 *
	 */
	private static String sendPackage(HttpUriRequest request) {
		String result = "";
		try {
			// 生成发包工具
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 发包并得到响应
			CloseableHttpResponse response = httpClient.execute(request);
			// 解析响应体
			result = EntityUtils.toString(response.getEntity());
			response.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("响应" + result);
		return result;
	}

	public static String postByFile(String url, File file, HashMap<String, String> headers)
			throws FileNotFoundException {
		HttpPost post = new HttpPost(url);
		if (headers.size() != 0) {
			// 设置头部信息
			Set<String> headersKey = headers.keySet();
			for (String headkeysName : headersKey) {
				post.addHeader(headkeysName, headers.get(headkeysName));
			}
		}
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("file", file, ContentType.MULTIPART_FORM_DATA, file.getName());
		HttpEntity multipart = builder.build();
		post.setEntity(multipart);
		String result = sendPackage(post);
		return result;
	}

	public static String request(String type, String url, Map<String, Object> paramsMap, Map<String, Object> headers) {
		String resultStr = "";
		if ("post".equalsIgnoreCase(type)) {
			resultStr = postByJson(url, paramsMap, headers);
		} else if ("get".equalsIgnoreCase(type)) {
			resultStr = get(url, paramsMap, headers);
		}
		return resultStr;
	}
	
	public static String request(String url, Map<String, Object> paramsMap, Map<String, Object> headers) {
		String resultStr = "";
		// 创建一个post请求
		HttpPost post = new HttpPost(url);
		// InputStreamEntity name = new InputStreamEntity(null);
		// post.setEntity(name);
		if (headers.size() != 0) {
			// 设置请求头
			Set<String> headersKey = headers.keySet();
			for (String headkeysName : headersKey) {
				post.addHeader(headkeysName, (String) headers.get(headkeysName));
			}
		}
		if (paramsMap.size() != 0) {
			// 解析params中的key
			Set<String> paramsKey = paramsMap.keySet();
			// 将参数传入list
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			// 设置参数
			for (String paramsName : paramsKey) {
				parameters.add(new BasicNameValuePair(paramsName, (String) paramsMap.get(paramsName)));
			}
			try {
				post.setEntity(new UrlEncodedFormEntity(parameters));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		resultStr = sendPackage(post);
		return resultStr;
	}
	
	
	public static void main(String[] args) {
//		String url="http://goblin-d-web-api.xh.wke-office.test.wacai.info/collections/assignments";
//		HashMap<String,Object> paramsMap = new HashMap<>();
//		Integer[] aIntegers={19285};
//		paramsMap.put("collectionIds", aIntegers);
//		paramsMap.put("agentId", 56868);
//		String aString= "{\"collectionIds\":[19285],\"agentId\":56868}";
//		Map<String, Object> aMap= JSONObject.parseObject(aString, Map.class);
//		StringUtil.displayMapValue(aMap);
//		Map<String, Object> headers = new HashMap<>();
//		headers.put("loginUser", "luandao");
		//String reseult=postByJson(url, paramsMap, headers );
		//System.out.println(reseult);
		test();
	}
	
	public  static void  test() {
//		List<String> strings = Arrays.asList("a","b","c");
//		strings.sort((name1,name2)-> name1.length() - name2.length());
//		Set<Character> aSet = strings.stream().flatMap(word -> word.chars().mapToObj(ch ->(char) ch)).collect(Collectors.toSet());
//		System.out.println(strings);
		List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
		names.sort((name1, name2) -> name1.length() - name2.length());
        System.out.println(names);
	}
    
	
}
