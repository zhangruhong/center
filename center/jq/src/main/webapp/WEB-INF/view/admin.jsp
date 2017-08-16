<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>admin</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/admin/admin.js"></script>
</head>
<body>
	<button type="button" onclick="test('excel');">从excel采集</button>
	<button type="button" onclick="test('favorite');">从选聘库采集</button>
	<button type="button" onclick="test('hot');">从定向招商采集</button>
</body>
</html>