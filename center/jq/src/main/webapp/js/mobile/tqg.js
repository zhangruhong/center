$(function(){
	
	var currentPage = 1;
	var pageSize = 24;
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	var $goodsList = $('#goods-list');
	
	/**
	 * 初始化
	 */
	var init = function(){
		
		$.ajax({
			url: '/m/getTqg',
			data: {page : currentPage, keyword : $('[name=keyword]').val()},
		}).done(function(objs){
			$.each(objs, function(i, g){
				var $goodsContainer = $('<div class="col-xs-6 col-sm-3 goods-item"></div>').appendTo($goodsList);
				var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.picUrl + ')" href="javascript: void(0);" target="_blank"></a>').appendTo($goodsContainer);
				var $goodsName = $('<div class="goods-name">' + g.title +'</div>').appendTo($goodsContainer);
				var $goodsOriginalPrice = $('<div class="goods-original-price" style="text-decoration: line-through">￥' + g.reservePrice +'</div>').appendTo($goodsContainer);
				var $goodsTicket = $('<div class="goods-ticket-container"></div>').append('<a class="goods-ticket" href="javascript: void(0);" target="_blank">￥' + parseFloat(g.zkFinalPrice).toFixed(2) + '</a>').appendTo($goodsContainer);
				var $soldCount = $('<div class="goods-sold-count">已抢购:<span class="num">' + g.soldNum +'</span></div>').appendTo($goodsOriginalPrice);
				$goodsContainer.on('click', function(){
					window.open(g.clickUrl, '_blank');
				});
			});
		});
	}
	
	$(window).scroll(function() {
        if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
        	currentPage++;
        	init();
        }
	});
	
	init();
	
});