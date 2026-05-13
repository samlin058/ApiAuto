package Utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import Utils.HttpUtil;

public class HttpUtil {
	private static Logger logger = Logger.getLogger(HttpUtil.class);

	/**
	 * post请求
	 * 
	 * @param url       请求地址
	 * @param paramsMap 请求参数
	 * @param headers   请求头
	 * @return
	 */
	public static String post(String url, Map<String, String> paramsMap, Map<String, String> headers) {
		String resultStr = "";
		// 创建一个post请求
		HttpPost post = new HttpPost(url);
		// 解析params中的key
		Set<String> paramsKey = paramsMap.keySet();
		// 将参数传入list
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		// 设置参数
		for (String paramsName : paramsKey) {
			parameters.add(new BasicNameValuePair(paramsName, (String) paramsMap.get(paramsName)));
		}
		// 设置请求头
		Set<String> headersKey = headers.keySet();
		for (String headkeysName : headersKey) {
			post.addHeader(headkeysName, (String) headers.get(headkeysName));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultStr = sendPackage(post);
		logger.info("响应" + resultStr);
		return resultStr;
	}

	/**
	 * post不带参
	 * 
	 * @param url 请求地址
	 * @return
	 */
	public static String postUnParam(String url, Map<String, String> headers) {
		String result = "";
		HttpPost post = new HttpPost(url);
		Set<String> headersKey = headers.keySet();
		for (String headkeysName : headersKey) {
			post.addHeader(headkeysName, headers.get(headkeysName));
		}
		try {

			result = sendPackage(post);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("响应" + result);
		return result;
	}

	/**
	 * post带参数请求
	 * 
	 * @param url    请求地址
	 * @param params 请求参数
	 * @return
	 */
	public static String postByParams(String url, Map<String, String> paramsMap) {
		String resultStr = "";
		// 创建一个post请求
		HttpPost post = new HttpPost(url);
		// 解析params中的key
		Set<String> keyset = paramsMap.keySet();
		// 将参数传入list
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String name : keyset) {
			parameters.add(new BasicNameValuePair(name, paramsMap.get(name)));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		resultStr = sendPackage(post);
		logger.info("响应" + resultStr);
		return resultStr;
	}

	/*
	 * public static String post(String url, Map<String, String> paramsMap) {
	 * String resultStr = ""; //创建一个post请求 HttpPost post = new HttpPost(url);
	 * //解析params中的key Set<String> keyset = paramsMap.keySet(); //将参数传入list
	 * List<NameValuePair> parameters = new ArrayList<NameValuePair>(); for
	 * (String name : keyset) { parameters.add(new BasicNameValuePair(name,
	 * paramsMap.get(name))); } try { //将参数设置到post请求中 post.setEntity(new
	 * UrlEncodedFormEntity(parameters)); //生成发包工具 CloseableHttpClient
	 * httpClient = HttpClients.createDefault(); //发包并得到响应 CloseableHttpResponse
	 * response = httpClient.execute(post); //获得响应体 HttpEntity responseEntity =
	 * response.getEntity(); resultStr = EntityUtils.toString(responseEntity);
	 * logger.info("响应" + resultStr); } catch (Exception e) {
	 * e.printStackTrace(); } return resultStr; }
	 */

	/**
	 * post带请求头
	 * 
	 * @param url
	 * @param header
	 * @return
	 */
	public static String postByHeads(String url, Map<String, String> headers) {
		String result = "";
		HttpPost post = new HttpPost(url);
		// 设置请求头
		Set<String> headersKey = headers.keySet();
		for (String headkeysName : headersKey) {
			post.addHeader(headkeysName, headers.get(headkeysName));
		}
		result = sendPackage(post);
		// JSONUtil.JsonAnalysis(result, "card", "individual_card_id");
		logger.info("响应" + result);
		return result;
	}

	/**
	 * get带参带头请求
	 * 
	 * @param url
	 * @param paramsMap
	 * @param headers
	 * @return
	 */
	public static String get(String url, Map<String, String> paramsMap, Map<String, String> headers) {
		String resultStr = "";
		// 设置参数
		Set<String> keysets = paramsMap.keySet();
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String key : keysets) {
			parameters.add(new BasicNameValuePair(key, paramsMap.get(key)));
		}
		String paramStr = URLEncodedUtils.format(parameters, "utf-8");
		HttpGet get = new HttpGet(url + "?" + paramStr);
		// 设置头部信息
		Set<String> headersKey = headers.keySet();
		for (String headkeysName : headersKey) {
			get.addHeader(headkeysName, headers.get(headkeysName));
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity responseEntity = response.getEntity();
			resultStr = EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("响应" + resultStr);
		return resultStr;
	}

	public static String getUnParam(String url, Map<String, String> headers) {
		String resultStr = "";
		HttpGet get = new HttpGet(url);
		// 设置头部信息
		Set<String> headersKey = headers.keySet();
		for (String headkeysName : headersKey) {
			get.addHeader(headkeysName, headers.get(headkeysName));
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity responseEntity = response.getEntity();
			resultStr = EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("响应" + resultStr);
		return resultStr;
	}

	/**
	 * get带参请求
	 * 
	 * @param url       请求地址
	 * @param paramsMap 请求参数
	 * @return
	 */
	public static String getByParams(String url, Map<String, String> paramsMap) {
		String resultStr = "";
		Set<String> keysets = paramsMap.keySet();
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for (String key : keysets) {
			parameters.add(new BasicNameValuePair(key, paramsMap.get(key)));
		}
		String paramStr = URLEncodedUtils.format(parameters, "utf-8");
		HttpGet get = new HttpGet(url + "?" + paramStr);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity responseEntity = response.getEntity();
			resultStr = EntityUtils.toString(responseEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("响应" + resultStr);
		return resultStr;
	}

	/**
	 * get带头请求
	 * 
	 * @param url       请求地址
	 * @param paramsMap 请求参数
	 * @return
	 */
	public static String getByHeads(String url, Map<String, String> headers) {
		String resultStr = "";
		HttpGet get = new HttpGet();
		Set<String> headersKey = headers.keySet();
		for (String headkeysName : headersKey) {
			get.addHeader(headkeysName, headers.get(headkeysName));
		}
		resultStr = sendPackage(get);
		logger.info("响应" + resultStr);
		return resultStr;
	}

	/**
	 * 发包处理
	 * 
	 * @param url       请求地址
	 * @param paramsMap 请求参数
	 * @param type      请求类型
	 * @return
	 */
	public static String request(String type, String url, Map<String, String> paramsMap, Map<String, String> headers) {
		String resultStr = "";
		if ("post".equalsIgnoreCase(type)) {
			if (headers.size() != 0) {
				if (paramsMap == null) {
					resultStr = postUnParam(url, headers);
				} else {
					resultStr = post(url, paramsMap, headers);
				}
			} else if (paramsMap.size() != 0) {
				resultStr = postByParams(url, paramsMap);
			}
		} else if ("get".equalsIgnoreCase(type)) {
			if (headers.size() != 0) {
				if (paramsMap == null) {
					resultStr = getUnParam(url, headers);
				} else {
					resultStr = get(url, paramsMap, headers);
				}
			} else if (paramsMap.size() != 0) {
				resultStr = getByParams(url, paramsMap);
			}
		}
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
			// 获取请求结果的状态码
			int statusCode = response.getStatusLine().getStatusCode();
			// 重试机制
			int times = 1;
			while (statusCode > 400) {
				Thread.sleep(5000);
				System.out.println("重试第" + times + "次");
				sendPackage(request);
				times++;
				if (times > 3) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	/**
	 * get发包动作
	 * 
	 * @param post
	 * @return 发包结果
	 */
	/*
	 * private static String sendPackage(HttpGet get) { String result = ""; try
	 * { // 生成发包工具 CloseableHttpClient httpClient = HttpClients.createDefault();
	 * // 发包并得到响应 CloseableHttpResponse response = httpClient.execute(get); //
	 * 获得响应体 HttpEntity responseEntity = response.getEntity(); // 解析响应体 result =
	 * EntityUtils.toString(responseEntity); } catch (Exception e) {
	 * e.printStackTrace(); } return result; }
	 */

}
