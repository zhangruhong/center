<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!-- #####################################################javascript库############################################################# -->
<script type="text/javascript">
	contextPath = '<%=request.getContextPath()%>'
</script>
<!-- jquery -->
<!-- 引入 jQuery 库 -->
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>

<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-3.3.7/js/bootstrap.min.js"></script>

<!-- font-awesome -->
<link href="<%=request.getContextPath()%>/js/vendor/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<!-- zeroClipboard -->
<script src="<%=request.getContextPath()%>/js/vendor/clipboard/clipboard.js"></script>

<!-- 自定义的库 -->
<script src="<%=request.getContextPath()%>/js/custom/jquery-form/jquery.form.js"></script>
<script src="<%=request.getContextPath()%>/js/custom/jquery-ajax/jquery.ajax.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style-mobile.css" />

<script src="<%=request.getContextPath()%>/js/custom/common.js"></script>
<script type="text/javascript">
	$(function(){
		$('.menu-item').each(function(){
			var href = $(this).data('href');
			$(this).on('click', function(){
				window.open(href, '_self');
			});
		})
	});
</script>


