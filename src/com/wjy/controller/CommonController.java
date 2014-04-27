package com.wjy.controller;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;

public class CommonController extends Controller {

	public void index() {
		renderJsp("/index.jsp");
	}

	public void getTestCount() {
		String username = "utmcmd=organic|utmctr=%E6%96%B0%E6%B5%AA";
		String password = "v02m4a4vY6DoLONo4CzjoOApY2DgbGckrmjm7a0pp2TtLSRtaWxmqWJkJSTkYiblaGXkqe";
		JSONObject result = new JSONObject();
		result.put("serverInfo", "success");
		result.put("username", username);
		result.put("password", password);
		renderJson(result);
	}

}
