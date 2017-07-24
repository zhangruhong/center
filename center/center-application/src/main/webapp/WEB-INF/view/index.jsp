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
			<li class="active"><a href="<%=request.getContextPath()%>">首页</a></li>
			<li><a class="<%=request.getContextPath() %>/clothes">男女服饰</a></li>
			<li><a class="<%=request.getContextPath() %>/clothes">母婴用品</a></li>
		</ul>
	</div>
</body>
</html>