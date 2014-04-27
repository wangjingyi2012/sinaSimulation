package com.wjy.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.wjy.controller.CommentSimulationController;
import com.wjy.controller.CommonController;
import com.wjy.controller.LoginSimulationController;
import com.wjy.controller.VoteSimulationController;

public class InitConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants arg0) {
		arg0.setDevMode(true);
	}

	@Override
	public void configHandler(Handlers arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configInterceptor(Interceptors arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configPlugin(Plugins arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
		me.add("/loginSimulation", LoginSimulationController.class);
		me.add("/commentSimulation", CommentSimulationController.class);
		me.add("/voteSimulation", VoteSimulationController.class);
	}

	public static void main(String[] args) {
		JFinal.start("WebRoot", 8081, "/", 5);
	}

}
