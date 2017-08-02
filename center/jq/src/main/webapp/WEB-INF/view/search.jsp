<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超高返利 - 万种高返超级券等你来抢</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/search.js"></script>
</head>
<body>
	<div class="container">
		<ul class="category-menu">
			<li class="${type == null || type == '' ? 'active category-default' : '' }"><a href="<%=request.getContextPath()%>/">首页</a></li>
			<li class="${type == 'highReturn' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/highReturn">超高返利</a></li>
			<li class="${type == 'superTicket' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/superTicket">超级券</a></li>
			<li class="${type == 'tenYuan' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/tenYuan">十元购</a></li>
			<li class=""><a href="<%=request.getContextPath() %>/tao-return">淘宝返利</a></li>
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
			<div class="section">
				<div class="section-search">
					<form id="search-form">
						<input type="hidden" name="categoryPid"/>
						<input type="hidden" name="categoryId"/>
						<input type="hidden" name="name"/>
						<input type="hidden" name="type" value="${type}"/>
						<ul class="main-category-bar">
							<li class="category-default active">全部</li>
							<c:forEach items="${topCategories}" var="c">
								<li data-id="${c.id}">${c.name }</li>
							</c:forEach>
						</ul>
						<ul class="sub-category-bar"></ul>
					</form>
				</div>
				<div class="section-content" id="goods-list">
					<nav class="data-pagination">
						<ul id="pagination" class="pagination"></ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
	    (function(win,doc){
	        var s = doc.createElement("script"), h = doc.getElementsByTagName("head")[0];
	        if (!win.alimamatk_show) {
	            s.charset = "gbk";
	            s.async = true;
	            s.src = "https://alimama.alicdn.com/tkapi.js";
	            h.insertBefore(s, h.firstChild);
	        };
	        var o = {
	            pid: "mm_30596893_34618551_122874993",/*推广单元ID，用于区分不同的推广渠道*/
	            appkey: "",/*通过TOP平台申请的appkey，设置后引导成交会关联appkey*/
	            unid: "",/*自定义统计字段*/
	            type: "click" /* click 组件的入口标志 （使用click组件必设）*/
	        };
	        win.alimamatk_onload = win.alimamatk_onload || [];
	        win.alimamatk_onload.push(o);
	    })(window,document);
	</script>
	<a data-type="6" data-tmpl="573x66" data-tmplid="140" data-style="2" data-border="0" biz-s_logo="1" biz-s_hot="1" href="#">搜索</a>
</body>
</html>