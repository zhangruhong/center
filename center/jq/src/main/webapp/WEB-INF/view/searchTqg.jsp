<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超高返利 - 万种高返超级券等你来抢</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/searchTqg.js"></script>
</head>
<body>
	<div class="container">
		<ul class="category-menu">
			<li class="${type == null || type == '' ? 'active category-default' : '' }"><a href="<%=request.getContextPath()%>/">首页</a></li>
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
					<nav class="data-pagination">
						<ul id="pagination" class="pagination"></ul>
					</nav>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
		$('.pagination').bootstrapPaginator({
			total					:	4000,
			pageSize				:	40,
			currentPage				:	'${param.page}' ? '${param.page}' : 1,
	        numberOfPages			:	5,
	        bootstrapMajorVersion	:	3,
	        tooltipTitles			:	function (type, page, current) {
	            switch (type) {
	            	case 'first': return '首页';
	            	case 'prev': return '上一页';
	            	case 'next': return '下一页';
	            	case 'last': return '末页';
	            	case 'page': return '第' + page + '页';
	            }
	        },
	        pageUrl					:	function(type, page, current) {
	        	var queryString = '${pageContext.request.queryString}';
				var requestURI = '${requestScope["javax.servlet.forward.request_uri"]}';
				var updateQueryString = function(qs, k, v) {
					var result = '';
					if (qs == '') {
						result = (k + '=' + v);
		        	} else {
		        		var replaceIndex = -1;
		        		var array = qs.split('&');
		        		for (var i=0; i<array.length; i++) {
		        			if (new RegExp("^" + (k + '=')).test(array[i])) { replaceIndex = i; break; }
		        		}
		        		if (replaceIndex == -1) {
		        			result = qs + '&' + k + '=' + v;
		        		} else {
		        			array[i] = (k + '=' + v);
		        			result = array.join('&');
		        		}
		        	}
					return result;
				};
	        	return requestURI + '?' + updateQueryString(queryString, 'page', page);
	        }
		});
	});
	</script>
</body>
</html>