<%@ page language="java" isELIgnored="false"
	contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<link href="<%=request.getContextPath() %>/image/favicon.ico" rel="shortcut icon" />
<title><sitemesh:write property='title' /></title>
<jsp:include page="/WEB-INF/include/refer-admin.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		load = function(page){
			window.open('/admin/' + page, '_self');
		}
	    $('.collapse-link').on('click', function () {
	        var ibox = $(this).closest('div.ibox');
	        var button = $(this).find('i');
	        var content = ibox.children('.ibox-content');
	        content.slideToggle(200);
	        button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
	        ibox.toggleClass('').toggleClass('border-bottom');
	        setTimeout(function () {
	            ibox.resize();
	            ibox.find('[id^=map-]').resize();
	        }, 50);
	    });

	    $('.close-link').on('click', function () {
	        var content = $(this).closest('div.ibox');
	        content.remove();
	    });

	    $('.fullscreen-link').on('click', function () {
	        var ibox = $(this).closest('div.ibox');
	        var button = $(this).find('i');
	        $('body').toggleClass('fullscreen-ibox-mode');
	        button.toggleClass('fa-expand').toggleClass('fa-compress');
	        ibox.toggleClass('fullscreen');
	        setTimeout(function () {
	            $(window).trigger('resize');
	        }, 100);
	    });
	});
</script>
<sitemesh:write property='head' />
</head>
<body class="md-skin">
	<nav class="navbar navbar-orange">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">
					<img src="<%=request.getContextPath() %>/image/logo-white.png" width="100" height="30">
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="menus">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="javascript: load('goods');">商品管理 <span class="sr-only">(current)</span></a></li>
					<li><a href="javascript: load('activity');">活动管理</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">用户管理 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="javascript: load('user');">用户列表</a></li>
							<li><a href="javascript: load('point');">积分查询</a></li>
							<li><a href="javascript: load('exchange');">礼品兑换</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<sitemesh:write property='body' />
	</div>
</body>
</html>