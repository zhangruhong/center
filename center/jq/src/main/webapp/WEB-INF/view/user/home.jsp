<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>admin</title>
<style type="text/css">
	.fanli{margin-top:5px; overflow: hidden;_zoom:1;}
	.fanli-left{width:271px; background:#fff; float:left; border:#dfdfdf solid 1px;}
	.fanli-left-head{width:84px; height:84px; margin:14px auto 22px; position:relative; display:block}
	.left-head-a{ position:absolute; top:0; left:0; z-index:2;}
	.left-head-vip{position:absolute; z-index:3; bottom:0; right:-7px;}
	.left-head-img1{position:absolute; z-index:2; left:0; top:0;width:84px; height:84px; background:url(http://www.taofen8.com/images/fanli/fanli_w84.png) no-repeat;_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=http://www.taofen8.com/images/fanli/fanli_w84.png); }
	.left-head-img2{position:absolute; z-index:1; left:0; top:0;}
	.change-head{width:84px; height:84px; background:url(http://www.taofen8.com/images/fanli/fl_w84.png) no-repeat;_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=http://www.taofen8.com/images/fanli/fl_w84.png); position:absolute; left:0; top:0; z-index:2; display:none;}
	.fanli-left-info{color:#383838; position:relative; z-index:3;}
	.fanli-left-info dd{overflow:hidden;_zoom:1; height:22px; line-height:22px; padding-left:68px;}
	.fanli-left-info-dd .let-spac3{color:#9e9e9e; float:left;letter-spacing:1px;}
	.left-info-dd-div{float:left; width:85px; height:20px;-o-text-overflow: ellipsis;display: block;text-overflow:ellipsis;overflow:hidden;white-space:nowrap; color:#000; font-weight:bold;}
	.fanli-left-info dd a{color:#666; float:left;}
	.fanli-left-info .line{color:#e1e1e1; float:left; margin:0 5px;}
	.fanli-left-ul{list-style-type:none; margin-left:66px; position:relative; z-index:0; padding-bottom:30px;padding-left: 0px;}
	.fanli-left-ul li{ margin-top:30px; height:21px; line-height:21px;}
	.fanli-left-ul li img{float:left; margin-top:-1px;}
	.fanli-left-ul li a{color:#414141; font-size:13px; margin-left:15px; outline:none;}
	.fanli-left-ul .fanli-left-red{color:#f83a7a;}
	.fanli-right{float:right; width:755px;}
	.fanli-right-top{background:#fff; border:#dfdfdf solid 1px; width:753px; overflow:hidden;}
	.right-top-dl{height:115px; padding:18px 0; width:758px; overflow:hidden;_zoom:1; text-align:center; color:#666;}
	.right-top-left{width:188px; height:115px; border-right:#e1e1e1 solid 1px;float:left; color:#9e9e9e; }
	.top-left-d1{margin-top:10px;}
	.top-left-d1 span{color:#f83a7a; padding-right:3px; font-size:30px; font-family:"å¾®è½¯é›…é»‘";}
	.top-left-d3{color:#000; margin-top:20px;}
	.top-left-d3 a:hover{color:#f83a7a;}
	.fanli-alter{width:100%; height:48px; line-height:48px; line-height:48px; border-top:#c5c5c5 dashed 1px;}
	.fanli-alter .alter-box{float:left; width:49%; _width:49%; color:#9e9e9e; height:48px; overflow:hidden;}
	.alter-box i{width:27px; height:27px; display:block; float:left; _display:inline; margin:9px 0 0 50px;}
	.alter-box-zfb{background:url(http://www.taofen8.com/images/fanli/zfb_w27_2.png) no-repeat;_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=http://www.taofen8.com/images/fanli/zfb_w27_2.png);}
	.alter-box-cell{background:url(http://www.taofen8.com/images/fanli/pho_w27_2.png) no-repeat;_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=http://www.taofen8.com/images/fanli/pho_w27_2.png);}
	.alter-box span{color:#9e9e9e; float:left; margin-left:15px;}
	.alter-box a{color:#f53e7b; float:left; margin-left:10px; text-decoration:underline;}
	.fanli-box{border:#dfdfdf solid 1px; margin-top:10px;}
	
	
	.my-fanli{background:#fff; font-family:"å¾®è½¯é›…é»‘";}
	.fanli-right .h41{ height:41px; line-height:41px;}
	.fanli-right .h47{ height:47px; line-height:47px;}
	.my-fanli-h2{float:left; font-size:17px; color:#404040; margin-left:14px; _display:inline;}
	.my-fanli a{float:right; margin-right:8px; font-family:"å®‹ä½“";}
	.fanli-right .h41 .fl-tb-all{color:#a7a7a7; font-weight:bold;}
	.fanli-right .h47 .fl-tb-help{font-size:14px; color:#acacac;}
	.table_bor{border-top:#dfdfdf solid 1px;}
	.my-fanli-ul{list-style-type:none;}
	.my-fanli-ul li{_overflow:hidden;}
	.fanli-ul-h35{height:35px; line-height:35px; background:#f9f9f9; padding:0 15px; color:#959595;}
	.ul-h35-s1{display:block; float:left; width:250px;}
	.ul-h35-s2{float:left;}
	.ul-h35-s3{float:right;}
	.fanli-ul-order{background:#f8f8f8; height:120px; color:#666; text-align:center;}
	.ul-order-img{float: left;height:90px; width:90px; font-size:0; border:#f0f0f0 solid 1px; position:relative; background:#fff; display:inline-block;}
	.fanli-td{height:112px; text-align:right; width:102px;padding-left: 15px;}
	.ul-order-img img{max-width:90px; max-height:90px;}
	.ul-order-name,.ul-sc-name{width:170px; padding:0 15px; line-height:18px; text-align:left;}
	.ul-order-name a{color:#333;}
	.ul-order-name a:hover{color:#f83a7a;}
	.ul-order-pri{width:165px;}
	.ul-order-pri a{text-decoration:underline; color:#333;}
	.ul-order-jfb{width:auto; padding-left: 50px; text-align: left;}
	.size13{color:#f83a7a; font-size:13px;}
	.ul-order-time{width:100px; padding:0 16px; line-height:18px;text-align: left; }
	.ul-order-time span{display:block; color:#333; font-size:12px;}
	.ul-order-p{height:18px; margin-top:3px; font-size:0;}
	.ul-order-h16{display:inline-block; height:16px; line-height:16px; padding:0 3px; border:#666 solid 1px; cursor:pointer; font-size:12px; margin:0 2px;}
	.ul-order-label{position:absolute; top:-1px; right:-1px; width:55px; height:20px;}
	.color-red{color:#f83a7a; padding-left:5px; text-decoration:underline;}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/admin/admin.js"></script>
</head>
<body>
	<div class="container" style="border-bottom: 2px solid black;margin-bottom: 20px;">
		<ul class="category-menu">
			<li class="active category-default"><a href="<%=request.getContextPath()%>/">首页</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/highReturn">超高返利</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/superTicket">超级券</a></li>
			<li><a href="<%=request.getContextPath() %>/v/search/tenYuan">十元购</a></li>
			<li><a href="<%=request.getContextPath() %>/v/introduce">淘宝返利</a></li>
		</ul>
	</div>
	<div class="container">
		<div class="fanli-left" style="height: 1161px;">
	   		<!--头像-->
	        <div class="fanli-left-head">
	        	<a class="left-head-a">
	        		<div class="left-head-img1"></div>
	        		<img src="http://img.taofen8.com/show/image/01130401default9999999999999999999999999.png_100x100.png" width="84" height="84" alt="头像" class="left-head-img2">
	            </a>
	            <a href="http://www.taofen8.com/showUserLevel" class="left-head-vip">
	                <img src="../images/user_level/pic_1.png" class="png_bg" width="27" height="28" alt="vip1">
	            </a>
	        </div>
	        <dl class="fanli-left-info">
	        	<dd class="fanli-left-info-dd">
	            	<span class="let-spac3">淘宝账号：</span>
	                <div class="left-info-dd-div">w403202153x1</div>
	            </dd>
	        </dl>
	     	<ul class="fanli-left-ul">
	       		<li>
	                <img src="http://www.taofen8.com/images/fanli/fl_fanli2.png" class="grey_w21 png_bg" width="21" height="21">
	                <a class="fanli-left-red" href="http://www.taofen8.com/myfanli">我的返利首页</a>
	            </li>
	       		<li>
	               <img src="http://www.taofen8.com/images/fanli/fl_taobao1.png" class="grey_w21 png_bg" width="21" height="21">
	               <a class="" href="http://www.taofen8.com/taobaoFanli">我的淘宝订单</a>
	           </li>
	           <li>
	           	<img src="http://www.taofen8.com/images/fanli/fl_shangc1.png" class="grey_w21 png_bg" width="21" height="21">
	               <a class="" href="http://www.taofen8.com/mallFanli">我的商城订单</a>
	         	</li>
	            <li>
	                <img src="https://img.alicdn.com/imgextra/i1/2296013456/TB298rrr4xmpuFjSZFNXXXrRXXa_!!2296013456.png" class="grey_w21 png_bg" width="21" height="21">
	                <a href="http://www.taofen8.com/mnw/login?redirectUrl=https%3a%2f%2fwww.manaowan.com%2fUser%2fuserCenter.html%3ffrom%3dtaofen8" target="_blank">我的理财</a>
	            </li>
	         	<li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_haihu1.png" class="grey_w21 png_bg" width="21" height="21">
	             	<a href="http://www.taofen8.com/haihu/login?jump={%22pk%22:%223wOrder%22}" target="_blank">我的海狐</a>
	            </li>
	         	<li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_like1.jpg" class="grey_w21 png_bg" width="21" height="21">
	               <a class="" href="http://www.taofen8.com/myLike">我的喜欢</a>
	         	</li>
	         	<li>
	            	<img src="http://www.taofen8.com/images/fanli/fl_appeal1.png" class="grey_w21 png_bg" width="21" height="21">
	                <a class="" href="http://www.taofen8.com/myComplain">我的申诉</a>
	          	</li>
	          	<li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_jf1.png" class="grey_w21 png_bg" width="21" height="21">
	             	<a id="nv_my_jf" style="float:left;" class="" href="http://www.taofen8.com/myJiFenAll-1">我的积分</a>
	             	<img src="https://img.alicdn.com/imgextra/i2/2296013456/TB2CHJihFXXXXaLXpXXXXXXXXXX_!!2296013456.png" class="Lfloat" style="margin:3px 0 0 7px;">
	           	</li>
	           	<li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_mypocket1.png" class="grey_w21 png_bg" width="21" height="21">
	             	<a class="" href="http://www.taofen8.com/myHongbao">我的红包</a>
	           	</li>
	           	<li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_fenzhuan1.png" class="grey_w21 png_bg" width="21" height="21">
	             	<a id="nv_my_fenzhuan" class="" href="http://www.taofen8.com/queryAdRecord">我的粉赚</a>
	           	</li>
	           	<li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_yaoj1.png" class="grey_w21 png_bg" width="21" height="21">
	             	<a class="" href="http://www.taofen8.com/shake">我的摇奖</a>
	           	</li>
	           	<li>
	             	<img src="http://www.taofen8.com/images/fanli/fl_liulan1.png" class="grey_w21 png_bg" width="21" height="21">
	                <a class="" href="http://www.taofen8.com/recordFanli">已浏览过的商品</a>
	          	</li>
	          	<li>
	             	<img src="http://www.taofen8.com/images/fanli/fl_xiaoxi1.png" class="grey_w21 png_bg" width="21" height="21">
	                <a class="" href="http://www.taofen8.com/noticeList">消息中心</a>
	          	</li>
	          	<li>
	             	<img src="http://www.taofen8.com/images/fanli/fl_vip1.png" class="grey_w21 png_bg" width="21" height="21">
	                <a class="" href="http://www.taofen8.com/showUserLevel">会员特权</a>
	          	</li>
	            <li>
	           		<img src="http://www.taofen8.com/images/fanli/fl_yaoq1.png" class="grey_w21 png_bg" width="21" height="21">
	             	<a class="" href="http://www.taofen8.com/invite" target="_blank">我的邀请</a>
	           	</li>
	     	</ul>
	   </div>
	</div>
</body>
</html>