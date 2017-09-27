<%@ page language="java" isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<nav class="pagination-nav">
	<ul id="pagination" class="pagination-center">
		<c:if test="${currPage > 1 }">
			<li class="prev"><a href='?page=${currPage == 1 ? 1 : currPage - 1}'>&lt;</a></li>
		</c:if>
		<c:forEach begin="${beginPage}" end="${endPage < pages ? endPage : pages}" step="1" var="i">
			<li class="${i == currPage ? 'active': '' }"><a href="?page=${i}">${i }</a></li>
		</c:forEach>
		<c:if test="${currPage < pages }">
			<li class="next"><a href='?page=${currPage + 1}'>&gt;</a></li>
		</c:if>
	</ul>
	<script type="text/javascript">
		$(function(){
			var param = {};
			$('#searchForm input[name]').each(function(){
				param[$(this).attr('name')] = $(this).val();
			});
			
			if($.isPlainObject(param)){
				$('#pagination li a').each(function(){
					$(this).attr('href', $(this).attr('href') + '&' + $.param(param));
				});
			}
		})
	
	</script>
</nav>
