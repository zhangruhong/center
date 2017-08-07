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
			<li class="${type == 'superTicket' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/superTicket">超级券</a></li>
			<li class="${type == 'tenYuan' ? 'active category-default' : '' }"><a href="<%=request.getContextPath() %>/v/search/tenYuan">十元购</a></li>
			<li class=""><a href="<%=request.getContextPath() %>/v/introduce">淘宝返利</a></li>
		</ul>
	</div>
	
	<div class="container-fluid">
		<div class="intro-detail-container">
			<div class="container">
				<form class="tao-search-form">
			         <div class="form-group">
			         	 <div class="input-group">
		          	     	<input type="text" class="form-control" placeholder="搜索淘宝天猫商品标题或关键字开始购物">
		          	     	<span id="search-goods" class="input-group-addon">立即搜索</span>
		          	     </div>
			         </div>
		      	</form>
		      	<div class="intro-step" style="background-image: url(<%=request.getContextPath() %>/image/step1.png)"></div>
		      	<div class="intro-step" style="background-image: url(<%=request.getContextPath() %>/image/step2.png)"></div>
		      	<div class="intro-step" style="background-image: url(<%=request.getContextPath() %>/image/step3.png)"></div>
	      	</div>
		</div>
	</div>
</body>
</html>