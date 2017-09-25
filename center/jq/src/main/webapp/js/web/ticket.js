$(function(){
	
	
	
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	
	$('.header-search-select option[value=taobao]').prop('selected', 'selected');
	
	$('#search-goods').on('click', function(){
		if($(this).prev().prev().val() == 'site'){
			window.open(contextPath + '/v/search?keyword=' + $(this).prev('input').val(), '_blank');
		}else{
			window.open(contextPath + '/v/ticket?keyword=' + $(this).prev('input').val(), '_self');
		}
		
	});
});