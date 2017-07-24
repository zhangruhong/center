<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><sitemesh:write property='title' /></title>
<jsp:include page="/WEB-INF/include/refer.jsp"></jsp:include>
<sitemesh:write property='head' />
</head>
<body>
	<jsp:include page="/WEB-INF/include/header.jsp"></jsp:include>
	<div id="content-wrapper" class="container-fluid">
		<sitemesh:write property='body' />
	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>
</body>
</html>