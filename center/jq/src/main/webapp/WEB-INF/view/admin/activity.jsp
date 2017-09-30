<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台管理</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/admin/activity.js"></script>
</head>
<body>
	<div class="wrapper wrapper-content ">
		<div class="ibox">
			<div class="ibox-title">
				<h5>活动管理</h5>
				<div class="ibox-tools">
                    <a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-wrench"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#">Config option 1</a></li>
                        <li><a href="#">Config option 2</a></li>
                    </ul>
                </div>
			</div>
			<div class="ibox-content">
				<form id="searchForm" class="form-inline">
					<div class="form-group">
						<input class="form-control" name="name" value="${param.name }"/>
					</div>
					<div class="form-group">
						<input class="form-control" name="startDate" value="${param.startDate }"/>
					</div>
					<div class="form-group">
						<input class="form-control" name="endDate" value="${param.endDate }"/>
					</div>
					<div class="form-group">
						<select class="form-control" name="status">
							<option value="">全部</option>
							<option value="1">有效</option>
							<option value="0">失效</option>
						</select>
					</div>
					<div class="form-group">
						<button type="submit" class="btn btn-primary">查询</button>
					</div>
				</form>
				<div class="hr-line-dashed"></div>
				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th width="20%">名称</th>
								<th width="10%">图片</th>
								<th width="10%">编码</th>
								<th width="10%">开始时间</th>
								<th width="10%">结束时间</th>
								<th width="10%">状态</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${objs }" var="g">
								<tr>
									<td>${g.name }</a></td>
									<td><img width="40" height="40" src=""/></td>
									<td>${g.code }</td>
									<td><fmt:formatDate value="${g.startDate }" pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${g.endDate }" pattern="yyyy-MM-dd"/></td>
									<td>${g.status ? '存活' : '失效' }</td>
									<td>
										<div class="btn-group btn-group-xs">
											<button type="button" class="btn btn-default"><i class="fa fa-list"></i></button>
										</div>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td id="showActivityBtn" class="clickable-td" colspan="7">+</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="9">
									<jsp:include page="pagination/pagination.jsp"/>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<div id="activityModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" style="white-space: nowrap; text-overflow: ellipsis; color: #f50;">
						<i class="fa fa-ticket fa-lg"></i> 活动
					</h5>
				</div>
				<div class="modal-body">
					<form id="activityForm" class="form-horizontal">
						<input name="id" type="hidden"/>
						<div class="form-group">
							<label class="control-label col-sm-2">名称</label>
							<div class="col-sm-8">
								<input name="name" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">图片</label>
							<div class="col-sm-8">
								<div class="file-upload"></div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">代码</label>
							<div class="col-sm-8">
								<input name="code" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">开始时间</label>
							<div class="col-sm-8">
								<input name="startDate" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">结束时间</label>
							<div class="col-sm-8">
								<input name="endDate" class="form-control"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2">状态</label>
							<div class="col-sm-8">
								<select name="status" class="form-control">
									<option value="1">有效</option>
									<option value="0">失效</option>
								</select>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" type="button" onclick="submit();">提交</button>
					<button class="btn btn-default" type="button" onclick="cancel();">取消</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>