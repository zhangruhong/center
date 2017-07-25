<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>时尚空间</title>
</head>
<body>
	<div class="container">
		<ul class="category-menu">
			<li class="active category-default"><a href="<%=request.getContextPath()%>">首页</a></li>
			<li><a class="<%=request.getContextPath() %>/clothes">男女服饰</a></li>
			<li><a class="<%=request.getContextPath() %>/clothes">母婴用品</a></li>
		</ul>
	</div>
	<div class="container-fluid">
		<div class="banner-container">
			<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner1.png); background-repeat: no-repeat;background-size: 100% 100%;"></div>
			<div class="banner"></div>
			<div class="banner"></div>
			<div class="banner"></div>
			<div class="banner"></div>
			<div class="banner-controller-container">
				<ul class="banner-controller">
					<li class="active"><a href="javascript: void(0);"></a></li>
					<li><a href="javascript: void(0);"></a></li>
					<li><a href="javascript: void(0);"></a></li>
					<li><a href="javascript: void(0);"></a></li>
					<li><a href="javascript: void(0);"></a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>