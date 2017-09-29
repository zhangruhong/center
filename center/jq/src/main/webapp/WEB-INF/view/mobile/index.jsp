<%@ page language="java" isELIgnored="false"
	contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超级券 - 百万张超级券等你来抢</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mobile/index.js"></script>
</head>
<body>
	<div class="weui-tab">
		<div class="weui-tab__bd">
			<div id="main" class="weui-tab__bd-item weui-tab__bd-item--active">
				<nav class="navbar navbar-orange navbar-fixed-top">
					<!-- 标题 -->
					<div class="navbar-header">
						<a class="navbar-brand" href="#">
							<img alt="万客达" src="<%=request.getContextPath()%>/image/logo-white.png" width="120" height="36">
						</a>
						<form class="navbar-form navbar-right">
							<div class="form-group">
								<div class="input-group">
									<input name="keyword" type="text" class="form-control" placeholder="淘宝商品关键字">
									<span id="search" class="input-group-addon"><i class="fa fa-search"></i></span>
								</div>
							</div>
						</form>
					</div>
				</nav>
				
				<!-- banner -->
				<div class="container-fluid" style="margin-top: 55px;">
					<div class="banner-container">
						<div class="banner active" style="background-image: url(<%=request.getContextPath()%>/image/banner1.png);"><a href="javascript: void(0);"></a></div>
						<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner2.png);"><a href="javascript: void(0);"></a></div>
						<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner3.png);"><a href="javascript: void(0);"></a></div>
						<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner4.png);"><a href="javascript: void(0);"></a></div>
						<div class="banner-controller-container">
							<ul class="banner-controller">
								<li class="active"></li>
								<li></li>
								<li></li>
								<li></li>
							</ul>
						</div>
					</div>
				</div>
				
				<div class="container-fluid">
					<div class="menu-container">
						<div class="row">
							<div class="col-xs-3 menu-item" data-href="<%=request.getContextPath() %>/">
								<div class="menu-image"></div>
								<p class="menu-name">首页</p>
							</div>
							<div class="col-xs-3 menu-item" data-href="<%=request.getContextPath() %>/p/ticket">
								<div class="menu-image"></div>
								<p class="menu-title">超级券</p>
							</div>
							<div class="col-xs-3 menu-item" data-href="<%=request.getContextPath() %>/p/tenyuan">
								<div class="menu-image"></div>
								<p class="menu-title">十元购</p>
							</div>
							<div class="col-xs-3 menu-item" data-href="<%=request.getContextPath() %>/p/tqg">
								<div class="menu-image"></div>
								<p class="menu-title">淘抢购</p>
							</div>
						</div>
					</div>
				</div>
				
				<div class="container-fluid">
					<div class="goods-container">
						<div class="row" id="goods-list"></div>
					</div>
				</div>
				
				<div class="weui-loadmore">
				  	<i class="weui-loading"></i>
				  	<span class="weui-loadmore__tips">正在加载</span>
				</div>
			</div>
			<div id="category" class="weui-tab__bd-item">
				<div class="weui-grids">
					<nav class="navbar navbar-orange navbar-fixed-top">
						<!-- 标题 -->
						<div class="navbar-header">
							<a class="navbar-brand" href="javascript:;">
								<img alt="万客达" src="<%=request.getContextPath()%>/image/logo-white.png" width="120" height="36">
							</a>
						</div>
					</nav>
					<div style="width: 100%;margin-top: 55px;"></div>
					<c:forEach items="${categories }" var="c">
						<a href="javascript: loadByCategory('${c.id }', true)" class="weui-grid js_grid">
					    	<div class="weui-grid__icon">
					      		<img src="<%=request.getContextPath() %>/image/${c.name }.png" alt="">
					    	</div>
					    	<p class="weui-grid__label">
					      		${c.name }
					    	</p>
					  	</a>				
					</c:forEach>
				</div>
				<div class="container-fluid">
					<div class="goods-container">
						<div class="row" id="goods-list-category"></div>
					</div>
				</div>
			</div>
			<div id="lookup" class="weui-tab__bd-item">
				lookup
			</div>
			<div id="mine" class="weui-tab__bd-item">
				mine
			</div>
		</div>
		<div class="weui-tabbar">
			<a href="#main" class="weui-tabbar__item weui-bar__item--on">
				<div class="weui-tabbar__icon">
					<i class="fa fa-home"></i>
				</div>
				<p class="weui-tabbar__label">首页</p>
			</a> 
			<a href="#category" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<i class="fa fa-th"></i>
				</div>
				<p class="weui-tabbar__label">类目</p>
			</a> 
			<a href="#lookup" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<i class="fa fa-search"></i>
				</div>
				<p class="weui-tabbar__label">发现</p>
			</a> 
			<a href="#mine" class="weui-tabbar__item">
				<div class="weui-tabbar__icon">
					<i class="fa fa-user"></i>
				</div>
				<p class="weui-tabbar__label">我</p>
			</a>
		</div>
	</div>
</body>