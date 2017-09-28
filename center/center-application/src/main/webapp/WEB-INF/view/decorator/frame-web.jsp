<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<link href="<%=request.getContextPath() %>/image/favicon.ico" rel="shortcut icon" />
<title><sitemesh:write property='title' /></title>
<jsp:include page="/WEB-INF/include/refer-web.jsp"></jsp:include>
<sitemesh:write property='head' />
</head>
<body>
	<div class="navbar navbar-default navbar-transparent">
		<div class="navbar-top">
			<div class="container">
				<div class="navbar-header">
					<div class="navbar-auth navbar-left ${context.user == null ? '' : 'hide' }">
						<a href="https://oauth.taobao.com/authorize?client_id=24567059&redirect_uri=http://www.vankeda.com/taobaoLogin&response_type=code&state=1" class="tao">
							<span class="tao-icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
							<span>淘宝账号登录</span>
						</a>
						<a href="<%=request.getContextPath() %>/login" class="khd">
							<span class="khd_icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
							<span>账号登录</span>
						</a>
					</div>
					<div class="navbar-auth navbar-left ${context.user != null ? '' : 'hide' }">
						<a href="javascript: void(0);" class="tao">
							<span>欢迎您！</span>
							<span>${context.user != null ?  context.user.username : '' }</span>
						</a>
						<a href="<%=request.getContextPath()%>/logout">退出</a>
					</div>
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menus" aria-expanded="false">
				        <span class="sr-only">Toggle navigation</span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				        <span class="icon-bar"></span>
				    </button>
				</div>
			    <div class="collapse navbar-collapse" id="navbar-menus">
					 <ul class="navbar-link">
					 	<li><a href="">新手教程</a></li>
					 	<li><a href="">关注微信</a></li>
					 	<li><a href="">联系客服</a></li>
			      	</ul>
			    </div>
			    
			</div>
		</div>
		<div class="container-fluid">
			<div class="container" style="position: relative;">
				<div class="navbar-header">
				      <div class="navbar-left">
			      		 <p class="brand">
			      		 	<img class="navbar-logo" src="<%=request.getContextPath() %>/image/logo.png">
			      		 </p>
				      </div>
				</div>
				<form class="header-search">
	         	 	<select class="header-search-select component">
	         	 		<option value="site">站内搜索</option>
	         	 		<option value="taobao">淘宝搜索</option>
	         	 	</select>
	       	     	<input type="text" class="form-input component" name="title" placeholder="搜索淘宝天猫商品标题或关键字开始购物" value="${param.keyword }">
	       	     	<span id="search-goods" class="span-search component">搜索</span>
		      	</form>
			       
		    </div>
		</div>
	</div>
	<div id="content-wrapper" class="container-fluid">
		<sitemesh:write property='body' />
	</div>
	<div class="container-fluid">
		<div class="footer-c">
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<p class="title">关于我们</p>
						<div class="link">
							<p class="item"><a href="javascript: void(0)">关于万客达</a></p>
						</div>
					</div>
					<div class="col-sm-4">
						<p class="title">关注微信</p>
						<p class="qrcode">
							<img src="/image/weixinQR.jpg" width="120" height="120">
						</p>
					</div>
					<div class="col-sm-4">
						<p class="title">App下载</p>
						<div class="link">
							<p class="item"><a href="javascript: void(0)">App下载</a></p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>