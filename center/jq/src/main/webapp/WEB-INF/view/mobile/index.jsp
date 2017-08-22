<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>万客达超级券 - 百万张超级券等你来抢</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/mobile/index.js"></script>
</head>
<body>
	<div data-role="page" id="pageone">
	  	<div data-role="header" data-position="fixed">
		  	<a href="#" class="ui-btn ui-icon-bars ui-btn-icon-left ui-btn-icon-notext ui-nodisc-icon ui-alt-icon">主页</a>
		  	<h1>万客达</h1>
		  	<a href="#search" data-rel="popup" class="ui-btn ui-icon-search ui-btn-icon-left ui-btn-icon-notext ui-nodisc-icon ui-alt-icon">搜索</a>
		  	<div data-role="popup" id="search" class="ui-content" >
			  	<input name="keyword" class="form-group" />
			</div>
	  	</div>
	
	  	<div data-role="main" class="ui-content">
	  		<p>欢迎！</p>
	  	</div>
	
		
	  	<div data-role="footer" style="text-align: center;" data-position="fixed">
		  	<div data-role="navbar">
				<ul>
					<li><a href="#" data-icon="home"></a></li>
					<li><a href="#" data-icon="home"></a></li>
					<li><a href="#" data-icon="home"></a></li>
				</ul>
			</div>
	  	</div>
	</div>
</body>