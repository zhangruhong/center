<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超高返利 - 万种高返超级券等你来抢</title>
<script type="text/javascript">
	type = '${type}';
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/web/search.js"></script>
</head>
<body>
	<div class="container">
		<ul class="category-menu">
			<li class="${type == null || type == '' ? 'active category-default' : '' }"><a href="<%=request.getContextPath()%>/">首页</a></li>
			<li class="${type == 'superTicket' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/superTicket">超级券</a></li>
			<li class="${type == 'tenYuan' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/tenYuan">十元购</a></li>
			<li class=""><a href="<%=request.getContextPath() %>/v/tqg">淘抢购</a></li>
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
						<input type="hidden" name="name" value="${param.keyword }"/>
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
				<div class="section-content" id="goods-list"></div>
				<nav class="data-pagination">
					<ul id="pagination" class="pagination"></ul>
				</nav>
			</div>
		</div>
	</div>
	
</body>
</html>