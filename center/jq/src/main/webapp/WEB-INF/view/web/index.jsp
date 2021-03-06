<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超高返利 - 万种高返超级券等你来抢</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/web/index.js"></script>
</head>
<body>
	<div class="container">
		<ul class="category-menu">
			<li class="active category-default"><a href="<%=request.getContextPath()%>/">首页</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/superTicket">超级券</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/tenYuan">十元购</a></li>
			<li><a href="<%=request.getContextPath() %>/v/tqg">淘抢购</a></li>
		</ul>
	</div>
	<div class="container-fluid">
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
		<div class="container">
			<div class="intro-container">
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">超多优惠券</p>
						<p class="intro-desc">满减活动应有尽有</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">淘抢购</p>
						<p class="intro-desc">全场商品低至1折</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">全网精选</p>
						<p class="intro-desc">淘宝天猫优质精选商品</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">购物包邮</p>
						<p class="intro-desc">站内商品全场包邮</p>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="section">
				<div class="section-title">
					<p class="main-title">销量王</p>
					<p class="sub-title">全网销量排行</p>
					<div class="op">
						<div class="tag">
							<a href="<%=request.getContextPath()%>/v/top">查看更多></a>
						</div>
					</div>
				</div>
				<div class="section-content" id="top-sale-list">
					<nav class="data-pagination hide">
						<ul id="top-pagination" class="pagination"></ul>
					</nav>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="section">
				<div class="section-title">
					<ul class="section-title-tabs" id="type-tabs">
						<c:forEach items="${categories }" var="c" varStatus="s">
							<li class="title-tab ${s.index == 0 ? 'active' : '' }" data-type="${c.id }"><a href="javascript: void(0);">${c.name }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="section-content">
					<div id="goods-list"></div>
					<nav class="data-pagination">
						<ul id="pagination" class="pagination"></ul>
					</nav>
				</div>
			</div>
		</div>
		<form id="top-sale-search-form" class="hide"></form>
		<form id="search-form" class="hide">
			<input type="hidden" name="categoryPid" value="${categories.get(0).id }">
		</form>
	</div>
</body>
</html>