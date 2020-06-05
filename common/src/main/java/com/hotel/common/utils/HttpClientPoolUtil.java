package com.hotel.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
	* @ClassName: HttpClientPoolUtil 
	* @Description: 链接池
	* @author lisc 
	* @date 2019年7月9日 上午11:35:26 
	* @version V1.0 
 */
public class HttpClientPoolUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientPoolUtil.class);
	public static PoolingHttpClientConnectionManager cm = null;
	public static CloseableHttpClient httpClient = null;
	/** * 默认content 类型 */
	private static final String DEFAULT_CONTENT_TYPE = "application/json;charset=UTF-8";
	/** * 默认请求超时时间30s */
	private static final int DEFAUL_TTIME_OUT = 15000;
	private static final int count = 32;
	private static final int totalCount = 1000;
	private static final int Http_Default_Keep_Time =15000;

	/** * 初始化连接池 */
	public static synchronized void initPools() {
		if (httpClient == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setDefaultMaxPerRoute(count);
			cm.setMaxTotal(totalCount);
			httpClient = HttpClients.custom().setKeepAliveStrategy(defaultStrategy).setConnectionManager(cm).build();
		}
	}

	/**
	 *  Http connection keepAlive 设置 
	 */
	public static ConnectionKeepAliveStrategy defaultStrategy = new ConnectionKeepAliveStrategy() {
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
			int keepTime = Http_Default_Keep_Time;
			while (it.hasNext()) {
				HeaderElement he = it.nextElement();
				String param = he.getName();
				String value = he.getValue();
				if (value != null && param.equalsIgnoreCase("timeout")) {
					try {
						return Long.parseLong(value) * 1000;
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("format KeepAlive timeout exception, exception:" + e.toString());
					}
				}
			}
			return keepTime * 1000;
		}
	};

	public static CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
		return cm;
	}

	/**
	 * 执行http post请求 
	 * 默认采用Content-Type：application/json，Accept：application/json
	 * @param uri 请求地址 
	 * @param data 请求数据 
	 * @return
	 */
	public static String execute(String uri, String data) {
		long startTime = System.currentTimeMillis();
		HttpEntity httpEntity = null;
		HttpEntityEnclosingRequestBase method = null;
		String responseBody = "";
		try {
			if (httpClient == null) {
				initPools();
			}
			method = (HttpEntityEnclosingRequestBase) getRequest(uri, HttpPost.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
			StringEntity stringEntity = new StringEntity(data, "UTF-8");
			stringEntity.setContentType("application/json");
			method.setEntity(stringEntity);
			HttpContext context = HttpClientContext.create();
			CloseableHttpResponse httpResponse = httpClient.execute(method, context);
			httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				responseBody = EntityUtils.toString(httpEntity, "UTF-8");
			}
			logger.info("发起请求" + uri + ",请求参数" + data);
		} catch (Exception e) {
			if (method != null) {
				method.abort();
			}
			e.printStackTrace();
			logger.error("execute post request exception, url:" + uri + ", exception:" + e.toString()
					+ ", cost time(ms):" + (System.currentTimeMillis() - startTime));
		} finally {
			if (httpEntity != null) {
				try {
					EntityUtils.consumeQuietly(httpEntity);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("close response exception, url:" + uri + ", exception:" + e.toString()
							+ ", cost time(ms):" + (System.currentTimeMillis() - startTime));
				}
			}
		}
		logger.info(uri + "请求返回：" + responseBody);
		return responseBody;
	}

	/**
	 * 执行http post请求
	 * 默认采用Content-Type：application/json，Accept：application/json
	 * @param uri 请求地址
	 * @param data 请求数据
	 * @return
	 */
	public static String httpPost(String uri, String data, String contentType) {
		long startTime = System.currentTimeMillis();
		HttpEntity httpEntity = null;
		HttpEntityEnclosingRequestBase method = null;
		String responseBody = "";
		try {
			if (httpClient == null) {
				initPools();
			}
			method = (HttpEntityEnclosingRequestBase) getRequest(uri, HttpPost.METHOD_NAME, contentType, 0);
			StringEntity stringEntity = new StringEntity(data, "UTF-8");
			stringEntity.setContentType(contentType);
			method.setEntity(stringEntity);
			HttpContext context = HttpClientContext.create();
			CloseableHttpResponse httpResponse = httpClient.execute(method, context);
			httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				responseBody = EntityUtils.toString(httpEntity, "UTF-8");
			}
			logger.info("发起请求" + uri + ",请求参数" + data);
		} catch (Exception e) {
			if (method != null) {
				method.abort();
			}
			e.printStackTrace();
			logger.error("execute post request exception, url:" + uri + ", exception:" + e.toString()
					+ ", cost time(ms):" + (System.currentTimeMillis() - startTime));
		} finally {
			if (httpEntity != null) {
				try {
					EntityUtils.consumeQuietly(httpEntity);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("close response exception, url:" + uri + ", exception:" + e.toString()
							+ ", cost time(ms):" + (System.currentTimeMillis() - startTime));
				}
			}
		}
		logger.info(uri + "请求返回：" + responseBody);
		return responseBody;
	}


	/**
	 * * 创建请求
	 * @param uri 请求url 
	 * @param methodName 请求的方法类型
	* 	@param contentType contentType类型 
	*  @param timeout 超时时间 
	* @param @return    入参
	* @return HttpRequestBase    返回类型
	* @author lisc 
	* @throws
	* @date 2019年7月9日 上午11:37:00 
	* @version V1.0   
	 */
	public static HttpRequestBase getRequest(String uri, String methodName, String contentType, int timeout) {
		if (httpClient == null) {
			initPools();
		}
		HttpRequestBase method = null;
		if (timeout <= 0) {
			timeout = DEFAUL_TTIME_OUT;
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000)
				.setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000)
				.setExpectContinueEnabled(false).build();
		if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodName)) {
			method = new HttpPut(uri);
		} else if (HttpPost.METHOD_NAME.equalsIgnoreCase(methodName)) {
			method = new HttpPost(uri);
		} else if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodName)) {
			method = new HttpGet(uri);
		} else {
			method = new HttpPost(uri);
		}
		if (StringUtils.isBlank(contentType)) {
			contentType = DEFAULT_CONTENT_TYPE;
		}
		method.addHeader("Content-Type", contentType);
		method.addHeader("Accept", contentType);
		method.setConfig(requestConfig);
		return method;
	}

	/** 
	 * 执行GET 请求 
	 *  @param uri 
	 *  @return
	 */
	public static String httpGet(String uri, Map<String, Object> params) {

		if (params != null) {
			StringBuilder stringBuilder = new StringBuilder();
			Iterator<String> iterator = params.keySet().iterator();
			String key;
			while (iterator.hasNext()) {
				key = iterator.next();
				Object val = params.get(key);
				if (val instanceof List) {
					List v = (List) val;
					for (Object o : v) {
						stringBuilder.append(key).append("=").append(o.toString()).append("&");
					}
				} else {
					stringBuilder.append(key).append("=").append(val.toString()).append("&");
				}
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			uri = uri + "?" + stringBuilder.toString();
			logger.info("url:{}", uri);
		}

		long startTime = System.currentTimeMillis();
		HttpEntity httpEntity = null;
		HttpRequestBase method = null;
		String responseBody = "";
		try {
			if (httpClient == null) {
				initPools();
			}
			method = getRequest(uri, HttpGet.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
			HttpContext context = HttpClientContext.create();
			CloseableHttpResponse httpResponse = httpClient.execute(method, context);
			httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				responseBody = EntityUtils.toString(httpEntity, "UTF-8");
				logger.info("请求URL: " + uri + "+ 返回状态码：" + httpResponse.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			if (method != null) {
				method.abort();
			}
			e.printStackTrace();
			logger.error("execute get request exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):"
					+ (System.currentTimeMillis() - startTime));
		} finally {
			if (httpEntity != null) {
				try {
					EntityUtils.consumeQuietly(httpEntity);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("close response exception, url:" + uri + ", exception:" + e.toString()
							+ ",cost time(ms):" + (System.currentTimeMillis() - startTime));
				}
			}
		}
		return responseBody;
	}
}