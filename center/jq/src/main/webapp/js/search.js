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
});