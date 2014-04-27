package com.wjy.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.jetty.util.UrlEncoded;

import com.jfinal.core.Controller;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.wjy.util.HttpTools;
import com.wjy.util.SecretPassword;

public class LoginSimulationController extends Controller {

	public void index() {
		renderJsp("/loginSimulation.jsp");
	}

	@SuppressWarnings("deprecation")
	public void singleUserTestOnce() {
		String sinaUsername = this.getPara("sinaUsername");
		String sinaPassword = this.getPara("sinaPassword");
		JSONObject result = null;
		String su = "";
		if (sinaUsername != null && sinaUsername.length() > 0) {
			su = sinaUsername.replace(" ", "");
			su = Base64.encode(UrlEncoded.encodeString(su, "UTF-8").getBytes());
			su = su.substring(0, su.length() - 1) + "lNDBxcS5jb20%3D";
		}

		// 登陆前请求地址
		String url = "http://login.sina.com.cn/sso/prelogin.php?entry=homepage&callback=pluginSSOController.preloginCallBack";
		url += "&su=" + su;// 加入用户名
		String content = "";// 返回内容
		JSONObject preLoginJson = new JSONObject();
		HttpClient client = new DefaultHttpClient();
		try {
			content = HttpTools.getRequest(client, url);// 获取请求内容

			// 将返回信息转换为json对象
			preLoginJson = JSONObject.fromObject(content.substring(
					content.indexOf("{"), content.length() - 1));

			// 获取登陆前相关数据
			String retcode = preLoginJson.get("retcode").toString();// 返回码
			String servertime = preLoginJson.get("servertime").toString();// 新浪服务器时间
			String nonce = preLoginJson.get("nonce").toString();// 加密用到的随机数
			String rsakv = preLoginJson.get("rsakv").toString();// 密钥
			String exectime = preLoginJson.get("exectime").toString();// 响应持续时间
			String pubkey = preLoginJson.get("pubkey").toString();// 公钥

			// 加密密码
			String sp = "";
			SecretPassword spTool = new SecretPassword();
			if (sinaPassword != null) {
				sp = spTool.encode(sinaPassword, servertime, nonce);

			}

			// 加密算法未实现，将帐号和密码写死
			su = "ODgzNjAzODAlNDBxcS5jb20=";
			sp = "04e0e7c7dca794aeb0a7d134c9cc023d96ad5a7fc09e5055ef4a50db8e2812c3100d98e35c3377e06023d7646998e5453d54b7b44d5f804c92676d2cfa53d2a47db1a028fafa6763b04bf645b2073fc06272b5ba015252c9c47c924e9f44e1887f1fbfd1a71517ea3a14b6f8d50ecc9f95d65ff6ac7857c5853e6d439cee73f4";

			// 准备登录
			String loginUrl = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.14)&_="
					+ servertime.substring(0, servertime.length() - 1) + "5251";
			// 放入参数
			List<NameValuePair> parms = new ArrayList<NameValuePair>();
			// parms.add(new BasicNameValuePair("entry", "homepage"));
			// parms.add(new BasicNameValuePair("geteway", "1"));
			// parms.add(new BasicNameValuePair("from", ""));
			// parms.add(new BasicNameValuePair("savestate", "0"));
			// parms.add(new BasicNameValuePair("useticket", "0"));
			// parms.add(new BasicNameValuePair("pagerefer", ""));
			// parms.add(new BasicNameValuePair("wsseretry",
			// "servertime_error"));
			// parms.add(new BasicNameValuePair("vsnf", "1"));
			// parms.add(new BasicNameValuePair("su", su));
			// parms.add(new BasicNameValuePair("service", "sso"));
			// parms.add(new BasicNameValuePair("servertime", servertime));
			// parms.add(new BasicNameValuePair("nonce", nonce));
			// parms.add(new BasicNameValuePair("pwencode", "rsa2"));
			// parms.add(new BasicNameValuePair("rsakv", rsakv));
			// parms.add(new BasicNameValuePair("sp", sp));
			// parms.add(new BasicNameValuePair("sr", "1366*768"));
			// parms.add(new BasicNameValuePair("encoding", "UTF-8"));
			// parms.add(new BasicNameValuePair("cdult", "3"));
			// parms.add(new BasicNameValuePair("domain", "sina.com.cn"));
			// parms.add(new BasicNameValuePair("prelt", "123"));
			// parms.add(new BasicNameValuePair("returntype", "TEXT"));
			parms.add(new BasicNameValuePair("cdult", "3"));
			parms.add(new BasicNameValuePair("domain", "sina.com.cn"));
			parms.add(new BasicNameValuePair("encoding", "UTF-8"));
			parms.add(new BasicNameValuePair("entry", "homepage"));
			parms.add(new BasicNameValuePair("form", ""));
			parms.add(new BasicNameValuePair("gateway", "1"));
			parms.add(new BasicNameValuePair("nonce", nonce));
			parms.add(new BasicNameValuePair("pagerefer", ""));
			parms.add(new BasicNameValuePair("prelt", "99"));
			parms.add(new BasicNameValuePair("pwencode", "rsa2"));
			parms.add(new BasicNameValuePair("rsakv", rsakv));
			parms.add(new BasicNameValuePair("savestate", "0"));
			parms.add(new BasicNameValuePair("servertime", servertime));
			parms.add(new BasicNameValuePair("service", "sso"));
			parms.add(new BasicNameValuePair(
					"sp",
					"5397d5aa0ef68b4ac7a5f0d0a63d67c1f2145fa7e3ebf4ca427ec21846178f90e4472fdc627e75f9e692292cb095b450dbd8fa5489846b978cdf0c66ae0c93b2faca1f3b768d9cedaa53955f7f992531a69e006cd30e3380858ac5a5f44af6107afbe4bbd9f7d0b89970d958759bc775f09c83c29ec018782e5adaade4da3c5a"));
			parms.add(new BasicNameValuePair("sr", "1366*768"));
			parms.add(new BasicNameValuePair("su", "ODgzNjAzODAlNDBxcS5jb20="));
			parms.add(new BasicNameValuePair("useticket", "0"));
			parms.add(new BasicNameValuePair("vsnf", "1"));
			parms.add(new BasicNameValuePair("returntype", "TEXT"));

			HttpClient clientLogin = new DefaultHttpClient();
			HttpPost post = new HttpPost(loginUrl);
			post.addHeader("Accept", "*/*");
			//post.addHeader("Accept-Encoding", "gzip,deflate,sdch");
			//post.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
			//post.addHeader("Connection", "keep-alive");
			//post.addHeader("Content-Length", "546");
			post.addHeader("Content-Type", "application/x-www-form-urlencoded");
			post.addHeader("Cookie", "U_TRS1=000000d4.1d7b35a7.53439aba.b10b218d; UOR=blog.sina.com.cn,blog.sina.com.cn,; SINAGLOBAL=183.129.254.212_1397009486.254998; vjuids=-dedefd6bd.14544411d5f.0.3ceb320d; SGUID=1397009490054_31929373; ArtiFSize=14; U_TRS2=000000d4.49e0329c.535b20ac.07dc1ba1; Apache=183.129.254.212_1398481069.53533; __utma=269849203.991888096.1398339974.1398339974.1398481077.2; __utmc=269849203; __utmz=269849203.1398339974.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic|utmctr=%E6%96%B0%E6%B5%AA; ULOGIN_IMG=gz-c682f315338dfcf543219ac51acf88418c91; ULV=1398482099933:12:12:6:183.129.254.212_1398481069.53533:1398481072485; SessionID=t553i2g2km587skafja5sfv1i4; sso_info=v02m4a4vY6DoLONo4CzjoOApY2DgbGckrmjm7a0pp2TtLSRtaWxmqWJkJSTkYiblaGXkqelj5aEtZOOlqGniZONhImTjYSJpq22j5OEwA==; SUB=AbUl4bVnKXC4MdexGLJeZezaXDGcwJKdwAA3TKOZQECZU8CBYMTXi0Irge6Ic1xGMEbes7OxFToaE5aZfezQhb1jnBFK6JrPgc%2F5UyO575laYeQc4kBvfm%2BMNysaYSg8DQpBBFMn47%2FKm%2Ba%2F8Uz2BUM%3D; SUBP=002A2c-gVlwEm1dAWxfgXELuuuLbW9UN74ocauoN7AuEV1qgRnz; rotatecount=2; vjlast=1398481076.1398482101.10; lxlrtst=1398504712_o; lxlrttp=1398504712");
			post.addHeader("Host", "login.sina.com.cn");
			post.addHeader("Origin", "http://www.sina.com.cn");
			post.addHeader("Referer", "http://www.sina.com.cn/");
			post.addHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");

			String loginContent = HttpTools.postRequest(clientLogin, loginUrl,
					parms, post);
			System.out.println("loginContent:" + loginContent);
			this.setAttr("loginInfoContent", loginContent);
			result = JSONObject.fromObject(loginContent.substring(loginContent
					.indexOf("{")));
			// result.put("responseInfo", loginContent);

		} catch (Exception e) {
			e.printStackTrace();
		}

		renderJson(result);
	}

}
