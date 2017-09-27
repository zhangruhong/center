<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>后台管理</title>
</head>
<body>
	<div class="wrapper wrapper-content ">
		<div class="ibox">
			<div class="ibox-title">
				<h5>商品管理</h5>
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
						<input class="form-control" name="lowerPrice" value="${param.lowerPrice }"/>
					</div>
					<div class="form-group">
						<input class="form-control" name="higherPrice" value="${param.higherPrice }"/>
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
								<th width="10%">ID</th>
								<th width="20%">名称</th>
								<th width="10%">图片</th>
								<th width="10%">原价</th>
								<th width="10%">佣金比率</th>
								<th width="10%">佣金</th>
								<th width="10%">月销量</th>
								<th width="10%">状态</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${objs }" var="g">
								<tr>
									<td>${g.id }</td>
									<td><a href="${g.ticketUrl }">${g.name }</a></td>
									<td><img width="40" height="40" src="${g.mainImageUrl }"/></td>
									<td><fmt:formatNumber value="${g.originalPrice }" pattern="0.00"/></td>
									<td><fmt:formatNumber value="${g.incomingRate * 100 }" pattern="0.00"/>%</td>
									<td><fmt:formatNumber value="${g.incoming }" pattern="0.00"/></td>
									<td>${g.soldCountPerMonth }</td>
									<td>${g.status ? '存活' : '失效' }</td>
									<td>
										<div class="btn-group btn-group-xs">
											<button type="button" class="btn btn-default"><i class="fa fa-list"></i></button>
										</div>
									</td>
								</tr>
							</c:forEach>
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
</body>
</html>