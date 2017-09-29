$(function(){
	
	var currentIndexPage = 1;
	var currentCategoryPage = 1;
	var pageSize = 24;
	var mainLoading = false;
	var categoryLoading = false;
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	
	/**
	 * 初始化销量王
	 */
	
	var buildGoodsList = function(list, $goodsList){
		$.each(list, function(i, g){
			if(g.ticketLeft > 0){
				var $goodsContainer = $('<div class="col-xs-6 col-sm-3 goods-item"></div>').appendTo($goodsList);
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
						$('#copyContent', $tokenModal).val('复制这条消息，' + result.model + '，打开【手机淘宝】即可购买');
						$('#copyBtn', $tokenModal).remove();
						var $copyBtn = $('<button class="btn btn-default" type="button" id="copyBtn" data-clipboard-target="#copyContent">复制</button>').appendTo($('.modal-footer', $tokenModal));
						$tokenModal.modal('show');
						var clipboard = new Clipboard(document.getElementById('copyBtn'));
						clipboard.on('success', function(e) {
						    window.open('taobao://');
						    e.clearSelection();
						});
						
						clipboard.on('error', function(e) {
						    alert('请选择“拷贝”进行复制!')
//							$("#copyContent").trigger('taphold');
							
						});
					});
				});
			}
		});
	}
	
	var initTopSale = function(){
		$.ajax({url: '/m/index/getGoods',data: {pageSize: pageSize, page : currentIndexPage}}).done(function(pagination){
			var $goodsList = $('#goods-list');
			buildGoodsList(pagination.rows, $goodsList);
			mainLoading = false;
		});
	}
	
	loadByCategory = function(categoryPid, refresh){
		var $goodsList = $('#goods-list-category');
		if(refresh){
			currentCategoryPage = 1;
			$goodsList.empty();
		}
		$.ajax({url: '/m/index/loadByCategory', data: {categoryPid : categoryPid, pageSize: pageSize, page : currentCategoryPage}}).done(function(pagination){
			buildGoodsList(pagination.rows, $goodsList);
			categoryLoading = false;
		});
	}
	
	$('#main').infinite(100).on('infinite', function(){
		if(mainLoading){return;}
		mainLoading = true;
		currentIndexPage++;
        initTopSale();
	});
	
	$('#category').infinite(100).on('infinite', function(){
		if(categoryLoading){return;}
		categoryLoading = true;
		currentCategoryPage++;
		loadByCategory();
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
	loadByCategory();
	
});