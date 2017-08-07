$(function(){
	
	
	
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	
	$('.header-search-select option[value=taobao]').prop('selected', 'selected');
	
	$('.header-search').searchForm({
		url: '/searchTicket',
		pagination: '#pagination',
		pageSize: 24,
		success: function(objects){
			$('#goods-list').initGoodsContainer(objects);
		}
	}).trigger('submit');
});