package com.wjy.util;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;

public class HttpTools {
	
	HttpGet get;
	HttpPost post;

	/**
	 * 
	 * 正常GET方式HTTP请求
	 * 
	 * @param client
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * 
	 * @throws IOException
	 */

	public static String getRequest(HttpClient client, String url)
			throws ClientProtocolException, IOException {

		HttpGet get = new HttpGet(url);
		get.addHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
		// get.addHeader(&quot;Referer&quot;,
		// &quot;http://2013.weibo.com/&quot;);

		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, "GBK");

		// System.out.println(content);
		/* EntityUtils.consume(entity); */

		return content;

	}

	/**
	 * 正常POST方式HTTP请求
	 * 
	 * @param client
	 * @param url
	 * @param parms
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */

	public static String postRequest(HttpClient client, String url,
			List<NameValuePair> parms,HttpPost post) throws ClientProtocolException,
			IOException {
		//HttpPost post = post1;
//		post.addHeader("Accept", "*/*");
//		post.addHeader("Accept-Encoding", "gzip,deflate,sdch");
//		post.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
//		post.addHeader("Connection", "keep-alive");
//		post.addHeader("Content-Length", "546");
//		post.addHeader("Content-Type", "application/x-www-form-urlencoded");
//		post.addHeader("Cookie", "U_TRS1=000000d4.1d7b35a7.53439aba.b10b218d; UOR=blog.sina.com.cn,blog.sina.com.cn,; SINAGLOBAL=183.129.254.212_1397009486.254998; vjuids=-dedefd6bd.14544411d5f.0.3ceb320d; SGUID=1397009490054_31929373; ArtiFSize=14; U_TRS2=000000d4.49e0329c.535b20ac.07dc1ba1; Apache=183.129.254.212_1398481069.53533; __utma=269849203.991888096.1398339974.1398339974.1398481077.2; __utmc=269849203; __utmz=269849203.1398339974.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E6%96%B0%E6%B5%AA; ULOGIN_IMG=gz-c682f315338dfcf543219ac51acf88418c91; ULV=1398482099933:12:12:6:183.129.254.212_1398481069.53533:1398481072485; SessionID=t553i2g2km587skafja5sfv1i4; sso_info=v02m4a4vY6DoLONo4CzjoOApY2DgbGckrmjm7a0pp2TtLSRtaWxmqWJkJSTkYiblaGXkqelj5aEtZOOlqGniZONhImTjYSJpq22j5OEwA==; SUB=AbUl4bVnKXC4MdexGLJeZezaXDGcwJKdwAA3TKOZQECZU8CBYMTXi0Irge6Ic1xGMEbes7OxFToaE5aZfezQhb1jnBFK6JrPgc%2F5UyO575laYeQc4kBvfm%2BMNysaYSg8DQpBBFMn47%2FKm%2Ba%2F8Uz2BUM%3D; SUBP=002A2c-gVlwEm1dAWxfgXELuuuLbW9UN74ocauoN7AuEV1qgRnz; rotatecount=2; vjlast=1398481076.1398482101.10; lxlrtst=1398504712_o; lxlrttp=1398504712");
//		post.addHeader("Host", "login.sina.com.cn");
//		post.addHeader("Origin", "http://www.sina.com.cn");
//		post.addHeader("Referer", "http://www.sina.com.cn/");
//		post.addHeader(
//				"User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
//

		UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(parms,
				"UTF-8");

		post.setEntity(postEntity);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		String content = EntityUtils.toString(entity, "GBK");

		/* EntityUtils.consume(entity); */

		return content;

	}

	public HttpGet getGet() {
		return get;
	}

	public void setGet(HttpGet get) {
		this.get = get;
	}

	public HttpPost getPost() {
		return post;
	}

	public void setPost(HttpPost post) {
		this.post = post;
	}
	
	

}
