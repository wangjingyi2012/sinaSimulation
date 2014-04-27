<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" href="/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/bootstrap-theme.min.css">

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		
		//获取测试帐号和密码
		$('#getTestCount').click(function(){
			$.get("/getTestCount",function(data){
				if(data.serverInfo == 'success'){
					$('#sinaUsername').attr('type','password');	
					$('#sinaUsername').val(data.username);	
					$('#sinaPassword').val(data.password);
				}else{
					alert('获取失败，请重试或填入自己的帐号密码');
				}
				
			},"json");
		});
		
		//开始测试
		$('#startTest').click(function(){
			var sinaUsername = $('#sinaUsername').val();
			var sinaPassword = $('#sinaPassword').val();

			//发送至后台
			$.post('/loginSimulation/singleUserTestOnce',
					{sinaUsername:sinaUsername,
					 sinaPassword:sinaPassword},
					function(data){		
						 var c;
						 if(data.retcode != 0){
							 c = '返回码：'+data.retcode+"<br>"; 
							 c +='失败原因：' + data.reason+'<br>';
						 }
						 					 
						 $('#loginInfo').append(c);
					},
			'json');
			return false;
		});
	});
</script>

<title>新浪登录 评论 点赞模拟 - 首页</title>
</head>
<body>

	<div class='container'>
	
	<br><br>
	
	<ul class="nav nav-tabs">
	  <li><a href="/">首页</a></li>
	  <li class="active"><a href="#">登录模拟</a></li>
	  <li><a href="/voteSimulation">点赞模拟</a></li>
	  <li><a href="#">评论模拟</a></li>
	</ul>

		<div class='row'>
			<div class='col-md-8 col-md-offset-2'>
				<br><br>
				<p>输入测试的帐号和密码，也可以<a id='getTestCount' href='#'>获取</a>一个测试帐号</p>
				<br><br>
				<form role="form">
				  <div class="form-group">
				    <label for="sinaUsername">帐号</label>
				    <input type="text" class="form-control" id="sinaUsername" name='sinaUsername' placeholder="填入新浪网的帐号">
				  </div>
				  <div class="form-group">
				    <label for="sinaPassword">密码</label>
				    <input type="password" class="form-control" id="sinaPassword" name='sinaPassword' placeholder="填入密码">
				  </div>
				  <button type="reset" class="btn btn-default">重置</button>
				  <button id='startTest' type="submit" class="btn btn-default">开始登录测试</button>
				</form>
			</div>
		</div>
		
		<div class='row'>
			<div class='col-md-8 col-md-offset-1'>
				<div>
					<br><br>
					<p class='lead'> 登录信息： </p>
					<p>${loginInfoContent }</p>
					<p id="loginInfo"></p>
				</div>
			</div>
		</div>


	</div>

</body>
</html>