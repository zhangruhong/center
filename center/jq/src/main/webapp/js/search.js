$(function(){
	
	if(window.opener && window.opener.searchName){
		$('[name=name]').val(window.opener.searchName);
	}
	
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	
	$('#search-form').searchForm({
		url: '/search/searchInTicket',
		pagination: '#pagination',
		pageSize: 24,
		success: function(objects){
			$('#goods-list').initGoodsContainer(objects);
		}
	}).trigger('submit');
});