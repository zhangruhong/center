$(function(){
	
	var currentPage = 1;
	var pageSize = 24;
	var loading = false;
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	
	/**
	 * 初始化销量王
	 */
	var initTopSale = function(){
		
		$.ajax({
			url: '/m/index/getGoods',
			data: {pageSize: pageSize, page : currentPage},
		}).done(function(pagination){
			var $goodsList = $('#goods-list');
			$.each(pagination.rows, function(i, g){
				var $goodsContainer = $('<div class="col-xs-6 col-sm-3 goods-item"></div>').appendTo($goodsList);
				if(g.ticketLeft > 0){
					var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.mainImageUrl + ')" href="javascript: void(0);" target="_blank"></a>').appendTo($goodsContainer);
					var $goodsName = $('<div class="goods-name">' + g.name +'</div>').appendTo($goodsContainer);
					var $goodsOriginalPrice = $('<div class="goods-original-price">￥' + g.originalPrice.toFixed(2) +'</div>').appendTo($goodsContainer);
					var $goodsTicket = $('<div class="goods-ticket-container"></div>').append('<a class="goods-ticket" href="javascript: void(0);" target="_blank">' + g.ticketValue + '</a>').appendTo($goodsContainer);
					var $soldCount = $('<div class="goods-sold-count">月销量:<span class="num">' + g.soldCountPerMonth +'</span></div>').appendTo($goodsOriginalPrice);
					$goodsContainer.on('click', function(){
						$.ajax({url: '/m/index/getToken', data: {logo: null, title : g.name, itemUrl : g.ticketUrl}}).done(function(result){
							var $tokenModal = $('#tokenModal');
							$('#goods-image', $tokenModal).css({backgroundImage: 'url('+ g.mainImageUrl + ')'});
							$('#token-name', $tokenModal).text(g.name);
							$('#price', $tokenModal).text('￥' + g.originalPrice.toFixed(2));
							$('#ticket', $tokenModal).text(g.ticketValue);
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
			loading = false;
		});
	}
	
	copyToken = function(){
		var $tokenModal = $('#tokenModal');
	}
	
	$('#main').infinite().on('infinite', function(){
		if(loading){return;}
		loading = true;
		currentPage++;
        initTopSale();
	});
	
	$('#search').on('click', function(){
		window.open('/p/search?keyword=' + $('[name=keyword]').val(), '_self');
	});
	
	$('[name=keyword]').on('keypress', function(e){
		if(e.keyCode == 13){
			window.open('/p/search?keyword=' + $(this).val(), '_self');
		}
	});
	
	initTopSale();
	
	
});