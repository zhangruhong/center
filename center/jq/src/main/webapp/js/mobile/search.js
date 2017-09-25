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
			url: '/m/ticket/getCoupons',
			data: {page : currentPage, keyword : $('[name=keyword]').val()},
		}).done(function(objs){
			$.each(objs, function(i, g){
				var $goodsContainer = $('<div class="col-xs-6 col-sm-3 goods-item"></div>').appendTo($goodsList);
				if(g.couponRemainCount > 0){
					var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.pictUrl + ')" href="javascript: void(0);" target="_blank"></a>').appendTo($goodsContainer);
					var $goodsName = $('<div class="goods-name">' + g.title +'</div>').appendTo($goodsContainer);
					var $goodsOriginalPrice = $('<div class="goods-original-price">￥' + g.zkFinalPrice +'</div>').appendTo($goodsContainer);
					var $goodsTicket = $('<div class="goods-ticket-container"></div>').append('<a class="goods-ticket" href="javascript: void(0);" target="_blank">' + g.couponInfo + '</a>').appendTo($goodsContainer);
					var $soldCount = $('<div class="goods-sold-count">月销量:<span class="num">' + g.volume +'</span></div>').appendTo($goodsOriginalPrice);
					$goodsContainer.on('click', function(){
						$.ajax({url: '/m/index/getToken', data: {logo: null, title : g.title, itemUrl : g.couponClickUrl}}).done(function(result){
							var $tokenModal = $('#tokenModal');
							$('#goods-image', $tokenModal).css({backgroundImage: 'url('+ g.pictUrl + ')'});
							$('#token-name', $tokenModal).text(g.title);
							$('#price', $tokenModal).text('￥' + g.zkFinalPrice);
							$('#ticket', $tokenModal).text(g.couponInfo);
							$('#token', $tokenModal).text(result.model);
							$('#copyBtn', $tokenModal).remove();
							var $copyBtn = $('<button class="btn btn-default" type="button" id="copyBtn" onclick="copyToken();" data-clipboard-text="' + result.model + '">复制</button>').appendTo($('.modal-footer', $tokenModal));
							$tokenModal.modal('show');
							var clipboard = new Clipboard(document.getElementById('copyBtn'));
							clipboard.on('success', function(e) {
							    window.open('taobao://');
							    e.clearSelection();
							});
						});
					});
				}
			});
		});
	}
	
	copyToken = function(){
		var $tokenModal = $('#tokenModal');
	}
	
	$(window).scroll(function() {
        if ($(document).scrollTop() >= $(document).height() - $(window).height()) {
        	currentPage++;
        	init();
        }
	});
	
	init();
	
	$('#search').on('click', function(){
		$goodsList.empty();
		init();
	});
	
	$('[name=keyword]').on('keypress', function(e){
		if(e.keyCode == 13){
			$goodsList.empty();
			init();
		}
	});
});