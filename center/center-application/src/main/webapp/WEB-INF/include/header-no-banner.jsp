<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-default navbar-transparent">
	<div class="navbar-top">
		<div class="container">
			<div class="navbar-header">
				<div class="navbar-auth navbar-left">
					<a href="https://oauth.taobao.com/authorize?client_id=24566675&redirect_uri=http://www.vankeda.com/taobaoLogin&response_type=code&state=1" class="tao">
						<span class="tao-icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
						<span>淘宝账号登录</span>
					</a>
					<a href="javascript: void(0);" class="khd">
						<span class="khd_icon" style="background-image: url(<%=request.getContextPath()%>/image/alicdn.png);background-repeat: no-repeat;"></span>
						<span>手机号登录</span>
					</a>
				</div>
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-menus" aria-expanded="false">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			    </button>
			</div>
		    <div class="collapse navbar-collapse" id="navbar-menus">
				 <ul class="navbar-link">
				 	<li><a href="">新手教程</a></li>
				 	<li><a href="">关注微信</a></li>
				 	<li><a href="">联系客服</a></li>
		      	</ul>
		    </div>
		    
		</div>
	</div>
	<div class="container-fluid">
		<div class="container" style="position: relative;">
			<div class="navbar-header">
			      <div class="navbar-left">
		      		 <p class="brand">
		      		 	<img class="navbar-logo" src="<%=request.getContextPath() %>/image/logo.png">
		      		 </p>
			      </div>
			</div>
			<div class="intro-container">
				<div class="col-xs-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">超高返利</p>
						<p class="intro-desc">全场最高返利97%</p>
					</div>
				</div>
				<div class="col-xs-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">返利购物</p>
						<p class="intro-desc">绑定支付宝，返利直接到账</p>
					</div>
				</div>
				<div class="col-xs-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">全网精选</p>
						<p class="intro-desc">淘宝天猫优质精选商品</p>
					</div>
				</div>
				<div class="col-xs-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">无忧退货</p>
						<p class="intro-desc">7天无理由退货</p>
					</div>
				</div>
			</div>
		       
	    </div>
	</div>
</div>