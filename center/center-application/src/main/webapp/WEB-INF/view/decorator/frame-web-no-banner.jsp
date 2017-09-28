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
<body class="no-banner">
	<div class="navbar navbar-default navbar-transparent">
		<div class="navbar-top">
			<div class="container">
				<div class="navbar-auth navbar-left ${context.user == null ? '' : 'hide' }">
					<a href="https://oauth.taobao.com/authorize?client_id=24567059&redirect_uri=http://www.vankeda.com/taobaoLogin&response_type=code&state=1" class="tao">
						<span class="tao-icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
						<span>淘宝账号登录</span>
					</a>
					<a href="javascript: void(0);" class="khd">
						<span class="khd_icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
						<span>手机号登录</span>
					</a>
				</div>
				<div class="navbar-auth navbar-left ${context.user != null ? '' : 'hide' }">
					<a href="javascript: void(0);" class="tao">
						<span class="tao-icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
					</a>
					<span>${context.user != null ?  context.user.username : '' }</span>
					
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
				<div class="intro-container">
					<div class="col-xs-3 intro-item">
						<div class="intro-image"></div>
						<div class="intro-info">
							<p class="intro-title">超多优惠券</p>
							<p class="intro-desc">满减活动应有尽有</p>
						</div>
					</div>
					<div class="col-xs-3 intro-item">
						<div class="intro-image"></div>
						<div class="intro-info">
							<p class="intro-title">淘抢购</p>
							<p class="intro-desc">全场商品低至1折</p>
						</div>
					</div>
					<div class="col-xs-3 intro-item">
						<div class="intro-image"></div>
						<div class="intro-info">
							<p class="intro-title">全网精选</p>
							<p class="intro-desc">淘宝天猫优质精选商品</p>
						</div>
					</div>
					<div class="col-xs-3 intro-item">
						<div class="intro-image"></div>
						<div class="intro-info">
							<p class="intro-title">购物包邮</p>
							<p class="intro-desc">站内商品全场包邮</p>
						</div>
					</div>
				</div>
			       
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