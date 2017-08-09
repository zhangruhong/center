$(function(){
	
	
	
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	
	$('.header-search-select option[value=site]').prop('selected', 'selected');
	
	$('#search-form').searchForm({
		url: '/search',
		pagination: '#pagination',
		pageSize: 24,
		success: function(objects){
			$('#goods-list').initGoodsContainer(objects);
		}
	}).trigger('submit');
	
	$('#search-goods').on('click', function(){
		if($(this).prev().prev().val() == 'site'){
			window.open(contextPath + '/v/search?searchName=' + $(this).prev('input').val(), '_self');
		}else{
			window.open(contextPath + '/v/searchTicket?searchName=' + $(this).prev('input').val(), '_blank');
		}
		
	});
});