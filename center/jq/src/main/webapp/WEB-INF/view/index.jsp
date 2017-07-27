<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>时尚空间</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/index.js"></script>
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
			<div class="banner active" style="background-image: url(<%=request.getContextPath()%>/image/banner1.png);"><a href="javascript: void(0);"></a></div>
			<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner2.png);"><a href="javascript: void(0);"></a></div>
			<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner3.png);"><a href="javascript: void(0);"></a></div>
			<div class="banner" style="background-image: url(<%=request.getContextPath()%>/image/banner4.png);"><a href="javascript: void(0);"></a></div>
			<div class="banner-controller-container">
				<ul class="banner-controller">
					<li class="active"></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="intro-container">
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">超高返利</p>
						<p class="intro-desc">全场最高返利97%</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">返利购物</p>
						<p class="intro-desc">绑定支付宝，返利直接到账</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">全网精选</p>
						<p class="intro-desc">淘宝天猫优质精选商品</p>
					</div>
				</div>
				<div class="col-xs-6 col-sm-3 intro-item">
					<div class="intro-image"></div>
					<div class="intro-info">
						<p class="intro-title">无忧退货</p>
						<p class="intro-desc">7天无理由退货</p>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="section">
				<div class="section-title">
					<p class="main-title">销量王</p>
					<p class="sub-title">全网销量排行</p>
					<div class="op">
						<div class="tag">
							<a>查看更多></a>
						</div>
					</div>
				</div>
				<div class="section-content">
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="section">
				<div class="section-title">
					<ul class="section-title-tabs">
						<li class="title-tab active"><a href="#">超高返利</a></li>
						<li class="title-tab"><a href="#">十元店</a></li>
						<li class="title-tab"><a href="#">特卖商品</a></li>
					</ul>
				</div>
				<div class="section-content">
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
					<div class="goods-container">
						<a class="goods-image" style="background-image: url(http://img.alicdn.com/bao/uploaded/i3/TB1y9tRRpXXXXcoaXXXXXXXXXXX_!!0-item_pic.jpg)" href="https://s.click.taobao.com/HSjbdew" target="_blank"></a>
						<div class="goods-name">2017夏季新款韩版时尚夏装两件套女装夏天小清新雪纺上衣短裤套装</div>
						<div class="goods-original-price">￥55.00</div>
						<div class="goods-price">
							<div class="goods-off-price">3.11</div>
							<div class="goods-real-price">到手价<span class="num">51.99</span></div>
							<div class="goods-sold-count">月销量:<span class="num">9363</span></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>