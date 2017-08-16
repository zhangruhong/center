<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超高返利 - 万种高返超级券等你来抢</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/tqg.js"></script>
</head>
<body>
	<div class="container">
		<ul class="category-menu">
			<li><a href="<%=request.getContextPath()%>/">首页</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/superTicket">超级券</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/tenYuan">十元购</a></li>
			<li class="active category-default"><a href="<%=request.getContextPath() %>/v/tqg">淘抢购</a></li>
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
				<div class="section-content">
					<div id="goods-list">
						<c:forEach items="${objs }" var="g">
							<div class="goods-container">
								<a class="goods-image" style="background-image: url(${g.picUrl})" href="${g.clickUrl}" target="_blank"></a>
								<div class="goods-name">${g.title }</div>
								<div class="goods-original-price">￥<fmt:formatNumber value="${g.reservePrice}" pattern="0.00"/></div>
								<div class="goods-price">
									<div class="goods-off-price"><fmt:formatNumber value="${g.reservePrice - g.zkFinalPrice}" pattern="0.00"/></div>
									<div class="goods-real-price">到手价:<span class="num">${g.zkFinalPrice }</span></div>
								</div>
							</div>
						</c:forEach>
					</div>
					<nav class="pagination-nav">
						<ul id="pagination" class="pagination-center">
							<c:if test="${currPage > 1 }">
								<li class="prev"><a href='?page=${currPage == 1 ? 1 : currPage - 1}'>&lt;</a></li>
							</c:if>
							<c:forEach begin="${beginPage}" end="${endPage}" step="1" var="i">
								<li class="${i == currPage ? 'active': '' }"><a href="?page=${i}">${i }</a></li>
							</c:forEach>
							<c:if test="${currPage < totalPageSize }">
								<li class="next"><a href='?page=${currPage + 1}'>&gt;</a></li>
							</c:if>
						</ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
</body>
</html>