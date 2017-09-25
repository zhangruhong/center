<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no" />
<link href="<%=request.getContextPath() %>/image/favicon.ico" rel="shortcut icon" />
<title><sitemesh:write property='title' /></title>
<jsp:include page="/WEB-INF/include/refer-mobile.jsp"></jsp:include>
<sitemesh:write property='head' />
</head>
<body>
	<sitemesh:write property='body' />
	<div id="tokenModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" style="white-space: nowrap; text-overflow: ellipsis;color: #f50;"><i class="fa fa-ticket fa-lg"></i> 优惠券</h5>
				</div>
				<div class="modal-body">
					<div class="row" style="border-bottom: 1px dashed #ccc;padding: 15px 0;">
						<div class="col-xs-6">
							<div id="goods-image" style="height: 180px;background-position: center; background-size: cover;background-repeat: no-repeat;"></div>
						</div>
						<div class="col-xs-6" style="padding: 15px;">
							<p id="token-name"></p>
							<p>【在售价】<span id="price"></span></p>
							<p>【优惠券】<span id="ticket"></span></p>
						</div>
					</div>
					<p style="margin-top: 20px;font-size: 16px;font-family: '宋体'">复制这条消息，<span id="token"></span>，打开【手机淘宝】即可购买</p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" type="button" id="copyBtn" onclick="copyToken();" data-clipboard-text="123">复制</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>