<%@ page language="java" isELIgnored="false"
	contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<link href="<%=request.getContextPath()%>/image/favicon.ico"
	rel="shortcut icon" />
<title><sitemesh:write property='title' /></title>
<jsp:include page="/WEB-INF/include/refer-mobile.jsp"></jsp:include>
<sitemesh:write property='head' />
</head>
<body>

	<%-- <div class="nav-footer">
		<a class="nav-footer-item" href="<%=request.getContextPath()%>/"><i class="fa fa-home fa-lg"></i></a>
		<div class="nav-footer-item dropup">
			<a data-toggle="dropdown" data-target=""><i class="fa fa-th fa-lg"></i></a>
			<ul class="dropdown-menu">
				<li><a href="<%=request.getContextPath() %>/">abc</a></li>
				<li><a href="<%=request.getContextPath() %>/">ccc</a></li>
			</ul>
		</div>
		<a class="nav-footer-item" href="<%=request.getContextPath()%>/questions"><i class="fa fa-question-circle-o fa-lg"></i></a>
		<a class="nav-footer-item" href="<%=request.getContextPath()%>/m/user/home"><i class="fa fa-user fa-lg"></i></a>
		
	</div> --%>
	<sitemesh:write property='body' />
	<div id="tokenModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title"
						style="white-space: nowrap; text-overflow: ellipsis; color: #f50;">
						<i class="fa fa-ticket fa-lg"></i> 优惠券
					</h5>
				</div>
				<div class="modal-body">
					<div class="row"
						style="border-bottom: 1px dashed #ccc; padding: 15px 0;">
						<div class="col-xs-6">
							<div id="goods-image"
								style="height: 180px; background-position: center; background-size: cover; background-repeat: no-repeat;"></div>
						</div>
						<div class="col-xs-6" style="padding: 15px;">
							<p id="token-name"></p>
							<p>
								【在售价】<span id="price"></span>
							</p>
							<p>
								【优惠券】<span id="ticket"></span>
							</p>
						</div>
					</div>
					<input id="copyContent" class="form-control" style="margin-top: 20px;" value="123"/>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" type="button" id="copyBtn" data-clipboard-target="#copyContent">复制</button>
				</div>
			</div>
		</div>
	</div>
	<div class="toolbar-fixed-r-b">
		<div id="scrollToTop" class="toolbar-tool show">
			<i class="fa fa-chevron-up"></i>
		</div>
		<div id="callQQ" class="toolbar-tool show">
			<i class="fa fa-qq"></i>
		</div>
	</div>
</body>
</html>