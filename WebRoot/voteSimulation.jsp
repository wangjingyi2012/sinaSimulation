<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
	
	//开始测试
	$('#startTest').click(function(){

		//发送至后台
		$.post('/voteSimulation/votePost',
				function(data){		
					 var c;
					 c = 'msg：'+data.result.status.msg+"<br>"; 
					 c +='code：' + data.result.status.code+'<br>';
					 c +='language：' + data.result.language+'<br>';
					 c +='encoding：' + data.result.encoding+'<br>';
					 c +='content：' + data.result.content+'<br>';
					 c +='nick：' + data.result.user.nick+'<br>';
					 c +='user.id：' + data.result.user.id+'<br>';
					 c +='time：' + data.result.time+'<br>';
					 c +='id：' + data.result.id+'<br>';
					 //c +='<br>'+data.result+'<br>';
							 
					 $('#voteInfo').append(c);
				},
		'json');
		return false;
	});
});
	

</script>

<title>新浪新闻评论点赞模拟</title>
</head>
<body>

	
	<div class='container'>
	
	<br><br>
	
	<ul class="nav nav-tabs">
	  <li><a href="/">首页</a></li>
	  <li><a href="/loginSimulation">登录模拟</a></li>
	  <li class="active"><a href="#">点赞模拟</a></li>
	  <li><a href="#">评论模拟</a></li>
	</ul>

		<div class='row'>
			<div class='col-md-8 col-md-offset-2'>
				<br><br>
				<p>点赞的时候无需登录，无论登陆与否，在刷新评论界面后又可以对已经赞过的评论继续点赞，发送参数也相同。</p>
				<p>测试的新闻地址为http://comment5.news.sina.com.cn/comment/skin/default.html?channel=gn&newsid=1-1-30018976</p>
				<p>评论中琥珀Ambre_[河南安阳]的评论。</p>
				<br><br>
				  <button id='startTest' type="submit" class="btn btn-default">开始点赞测试</button>
			</div>
		</div>
		
		<div class='row'>
			<div class='col-md-8 col-md-offset-1'>
				<div>
					<br><br>
					<p class='lead'> 返回信息： </p>
					<p>${voteInfoContent }</p>
					<p>${result.status }</p>
					<p>${result.language }</p>
					<p>${result.endoding }</p>
					<p>${result.content }</p>
					<p>${result.user }</p>
					<p>${result.time }</p>
					<p id="voteInfo"></p>
				</div>
			</div>
		</div>


	</div>
</body>
</html>