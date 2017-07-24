<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<!-- #####################################################javascript库############################################################# -->
<!-- jquery -->
<script src="<%=request.getContextPath()%>/js/vendor/jquery-3.1.1/jquery-3.1.1.min.js"></script>
<!-- jquery-ui -->
<link href="<%=request.getContextPath()%>/js/vendor/jquery-ui-1.12.0/jquery-ui.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/jquery-ui-1.12.0/jquery-ui.min.js"></script>
<!-- jquery-cookie -->
<script src="<%=request.getContextPath()%>/js/vendor/jquery-cookie-1.4.1/jquery.cookie.js"></script>
<!-- jquery-file-upload -->
<link href="<%=request.getContextPath()%>/js/vendor/jquery-file-upload-9.18.0/css/jquery.fileupload.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/jquery-file-upload-9.18.0/js/jquery.iframe-transport.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/jquery-file-upload-9.18.0/js/jquery.fileupload.js"></script>

<!-- bootstrap -->
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<!-- bootstrap-validator -->
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-validator-0.5.3/css/bootstrapValidator.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-validator-0.5.3/js/bootstrapValidator.min.js"></script>
<!-- bootstrap-select -->
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-select-2.0.0-beta1/css/bootstrap-select.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-select-2.0.0-beta1/js/bootstrap-select.js"></script>
<!-- bootstrap-multiselect -->
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-multiselect-2.0/css/bootstrap-multiselect.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-multiselect-2.0/js/bootstrap-multiselect.js"></script>
<!-- bootstrap-paginator -->
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-paginator-1.0.2/bootstrap-paginator.js"></script>
<!-- bootstrap-datepicker -->
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-datepicker-1.6.4/css/bootstrap-datepicker3.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-datepicker-1.6.4/js/bootstrap-datepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-datepicker-1.6.4/locales/bootstrap-datepicker.zh-CN.min.js"></script>
<link href="<%=request.getContextPath()%>/js/vendor/bootstrap-datetimepicker-2.4.4/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-datetimepicker-2.4.4/js/bootstrap-datetimepicker.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/bootstrap-datetimepicker-2.4.4/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<!-- 表格 footable -->
<link href="<%=request.getContextPath()%>/js/vendor/footable-3.1.4/footable.bootstrap.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/footable-3.1.4/footable.js"></script>

<!-- 树 jstree -->
<link href="<%=request.getContextPath()%>/js/vendor/jstree-3.3.2/themes/default/style.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/jstree-3.3.2/jstree.min.js"></script>

<!-- 图标 font-awesome -->
<link href="<%=request.getContextPath()%>/js/vendor/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" />

<!-- 图标 echarts -->
<script src="<%=request.getContextPath()%>/js/vendor/echarts-3.4/echarts.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/echarts-3.4/echarts-liquidfill.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/echarts-3.4/theme/macarons.js"></script>
<!-- 图表 d3 -->
<script src="<%=request.getContextPath()%>/js/vendor/d3-3.5.17/d3.js"></script>

<!-- 页面加载动画 pace -->
<script src="<%=request.getContextPath()%>/js/vendor/pace-1.0.2/pace.min.js"></script>

<!-- 图片预览 fancyBox  -->
<link href="<%=request.getContextPath()%>/js/vendor/fancyBox-2.1.5/jquery.fancybox.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/fancyBox-2.1.5/jquery.fancybox.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/fancyBox-2.1.5/jquery.fancybox.pack.js"></script>

<!-- 折叠菜单 metisMenu -->
<link href="<%=request.getContextPath()%>/js/vendor/metismenu-2.6.2/metisMenu.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/metismenu-2.6.2/metisMenu.min.js"></script>

<!-- 弹出提示 sweetalert -->
<link href="<%=request.getContextPath()%>/js/vendor/sweetalert-1.1.3/sweetalert.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/sweetalert-1.1.3/sweetalert-dev.js"></script>

<!-- 按钮加载动画 -->
<link href="<%=request.getContextPath()%>/js/vendor/ladda-1.0.0/ladda-themeless.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/ladda-1.0.0/spin.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/ladda-1.0.0/ladda.min.js"></script>
<script src="<%=request.getContextPath()%>/js/vendor/ladda-1.0.0/ladda.jquery.min.js"></script>

<!-- 通知 toastr -->
<link href="<%=request.getContextPath()%>/js/vendor/toastr-2.1.3/toastr.min.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/toastr-2.1.3/toastr.min.js"></script>

<!-- 滚动 slimScroll -->
<script src="<%=request.getContextPath()%>/js/vendor/slimScroll-1.3.8/jquery.slimscroll.min.js"></script>

<!-- 选项框 iCheck -->
<link href="<%=request.getContextPath()%>/js/vendor/iCheck-1.0.2/css/custom.css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/js/vendor/iCheck-1.0.2/icheck.min.js"></script>

<!-- sockjs -->
<script src="<%=request.getContextPath()%>/js/vendor/sockjs-1.1.4/sockjs.js"></script>

<!-- 自定义的库 -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script src="<%=request.getContextPath()%>/js/custom/utils/util.js"></script>
<script src="<%=request.getContextPath()%>/js/custom/utils/date.js"></script>
<script src="<%=request.getContextPath()%>/js/custom/utils/websocket.js"></script>
<script src="<%=request.getContextPath()%>/js/custom/common.js"></script>


