<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-default navbar-transparent">
	<div class="navbar-top">
		<div class="container">
			<div class="navbar-header">
				<div class="navbar-auth navbar-left">
					<a href="https://oauth.taobao.com/authorize?client_id=24566675&redirect_uri=http://www.vankeda.com&response_type=code&state=1" class="tao">
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
			<form class="search-form">
		         <div class="form-group">
		         	 <div class="input-group">
		         	 	<select class="search-erea">
			         	 	<option value="taobao">淘宝搜索</option>
			         	 	<option value="site">站内搜索</option>
			         	 </select>
	          	     	<input type="text" class="form-control" placeholder="搜索淘宝天猫商品标题或关键字开始购物">
	          	     	<span id="search-goods" class="input-group-addon">搜索</span>
	          	     </div>
		         </div>
	      	</form>
		       
	    </div>
	</div>
</div>