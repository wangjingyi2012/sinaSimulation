package com.wjy.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;
import com.wjy.util.HttpTools;

public class VoteSimulationController extends Controller {

	public void index() {
		renderJsp("/voteSimulation.jsp");
	}

	@SuppressWarnings("deprecation")
	public void votePost() {
		String content = "";// 返回内容
		JSONObject result = new JSONObject();
		HttpClient client = new DefaultHttpClient();

		String url = "http://comment5.news.sina.com.cn/cmnt/vote";

		try {
			List<NameValuePair> parms = new ArrayList<NameValuePair>();
			parms.add(new BasicNameValuePair("channel", "gn"));
			parms.add(new BasicNameValuePair("newsid", "1-1-30018976"));
			parms.add(new BasicNameValuePair("parent",
					"535C8E13-7F000001-ABC7C4BF-898-8F6"));
			parms.add(new BasicNameValuePair("format", "js"));
			parms.add(new BasicNameValuePair("vote", "1"));
			parms.add(new BasicNameValuePair("callback",
					"function+%28o%29%7B%7D"));
			parms.add(new BasicNameValuePair("domain", "sina.com.cn"));

			HttpPost post = new HttpPost(url);
			post.addHeader("Accept",
					"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded");
			post.addHeader(
					"Cookie",
					"U_TRS1=000000d4.1d7b35a7.53439aba.b10b218d; UOR=blog.sina.com.cn,blog.sina.com.cn,; SINAGLOBAL=183.129.254.212_1397009486.254998; vjuids=-dedefd6bd.14544411d5f.0.3ceb320d; SGUID=1397009490054_31929373; lxlrtst=1398504712_o; SUB=AWaoPWgARLPLxz03gDXW5kgRKEW8%2B015SaKJ7Bb%2FclOkDaHHII3ykiXf%2BuT74S8KZR3m3gruBG11S6qMf9vcta%2Fbn%2BM2vJBxS%2BmqtJI1m%2Bnu1nlprA5SeknVyQ3iTJ4UPKMEo3pKor%2BAGACthL785lY%3D; SUBP=002A2c-gVlwEm1uAWxfgXELuuu1xVxBxALi0tg8MKsScBb9WL_5z9Z9; sso_info=v02m6alo5qztLiOg4y2jIOMuIyCmbWalpC9jKOAsY2DgLWNg5i2jLDAwA==; U_TRS2=000000d4.3ae2fb2.535c9355.1753b446; Apache=183.129.254.212_1398575956.267114; vjlast=1398575964; __utma=269849203.991888096.1398339974.1398481077.1398575965.3; __utmb=269849203.1.10.1398575965; __utmc=269849203; __utmz=269849203.1398339974.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E6%96%B0%E6%B5%AA; directAd=true; rotatecount=2; ULV=1398576861916:14:14:2:183.129.254.212_1398575956.267114:1398575961200; ArtiFSize=14; USRMDE16=usrmdins4827; ULOGIN_IMG=gz-5c6b8e24626b2a958044fe18e275f3edbc59; FSINAGLOBAL=183.129.254.212_1397009486.254998; lxlrttp=1398560790");
			post.addHeader("Host", "comment5.news.sina.com.cn");
			post.addHeader("Origin", "http://comment5.news.sina.com.cn");
			post.addHeader(
					"Referer",
					"http://comment5.news.sina.com.cn/comment/skin/default.html?channel=gn&newsid=1-1-30017384");
			post.addHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36");

			content = HttpTools.postRequest(client, url, parms, post);
			System.out.println(content);
			result = JSONObject.fromObject(content.substring(content
					.indexOf("{")));
			System.out.println(result.getString("result"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setAttr("voteInfoContent", content);
		this.setAttr("result", result);
		renderJson(result);
		//renderJsp("/voteSimulation.jsp");
	}

}
