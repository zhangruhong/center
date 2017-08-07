$(function(){
	
	var off = 0.7;
	
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	/* 初始化显示那个类型的内容*/
	$('#search-form').initType();
	
	/**
	 * 初始化销量王
	 */
	var initTopSale = function(){
		$('#top-sale-search-form').searchForm({
			url: '/index/getGoods',
			pagination: '#top-pagination',
			pageSize: 12,
			success: function(objects){
				$('#top-sale-list').initGoodsContainer(objects);
			}
		}).trigger('submit');
	}
	
	/**
	 * 初始化商品列表
	 */
	var initGoods = function(){
		$('#search-form').searchForm({
			url: '/index/getGoods',
			pagination: '#pagination',
			pageSize: 24,
			success: function(objects){
				$('#goods-list').initGoodsContainer(objects);
			}
		}).trigger('submit');
	}
	
	
	initGoods();
	initTopSale();
	
	$('#type-tabs > li').on('click', function(){
		$.cookie('type', $(this).data('type'), {expires: 1});
		$(this).addClass('active').siblings('li.active').removeClass('active');
		$('#search-form').find('[name=categoryPid]').val($(this).data('type')).trigger('submit');
	});
	
	/* 初始化 搜索框*/
	$('#search-goods').on('click', function(){
		if($(this).prev('select').val() == 'site'){
			window.open(contextPath + '/v/search?searchName=' + $(this).prev('input').val(), '_blank');
		}else{
			window.open(contextPath + '/v/searchTicket?searchName=' + $(this).prev('input').val(), '_blank');
		}
		
	});
	
});