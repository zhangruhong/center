$(function(){
	
	var off = 0.7;
	
	$.fn.extend({
		initBanner : function(){
			if($(this)[0].tagName === 'DIV'){
				var $bannerContainer = $(this);
				var $bannerController = $('.banner-controller', $bannerContainer);
				var bannerLength = $('li', $bannerController).length;
				
				tickTimer = setInterval(function(){
					var index = $('.active', $bannerController).index();
					if(index != bannerLength -1){
						$('.active', $bannerController).removeClass('active').next('li').addClass('active');
						$('.banner.active', $bannerContainer).removeClass('active').next('.banner').addClass('active');
					}else{
						$('.active', $bannerController).removeClass('active').parent().find('li:first-child').addClass('active');
						$('.banner.active', $bannerContainer).removeClass('active').parent().find('.banner:first-child').addClass('active');
					}
				}, 5000);
				
				$bannerController.on('mouseenter', function(e){
					var _this = this;
					$('li', $(_this)).on('click', function(){
						$('li.active', $(_this)).removeClass('active');
						$(this).addClass('active');
						var index = $(this).index();
						$('.banner', $bannerContainer).removeClass('active').eq(index).addClass('active');
					});
				});
			}
		},
		initGoodsContainer : function(objects){
			var $goodsList = $(this);
			$('.goods-container', $goodsList).remove();
			$.each(objects, function(i, g){
				var $goodsContainer = $('<div class="goods-container"></div>').prependTo($goodsList);
				var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.mainImageUrl + ')" href="' + g.tbkShortUrl + '" target="_blank"></a>').appendTo($goodsContainer);
				var $goodsName = $('<div class="goods-name">' + g.name +'</div>').appendTo($goodsContainer);
				var $goodsOriginalPrice = $('<div class="goods-original-price">￥' + g.originalPrice.toFixed(2) +'</div>').appendTo($goodsContainer);
				var $goodsPrice = $('<div class="goods-price"></div>').appendTo($goodsContainer);
				var $goodsOffPrice = $('<div class="goods-off-price">' + (g.incoming * 0.7).toFixed(2)  + '</div>').appendTo($goodsPrice);
				var $goodsRealPrice = $('<div class="goods-real-price">到手价:<span class="num">' + (g.originalPrice - g.incoming * 0.7).toFixed(2) +'</span></div>').appendTo($goodsPrice);
				var $soldCount = $('<div class="goods-sold-count">月销量:<span class="num">' + g.soldCountPerMonth +'</span></div>').appendTo($goodsPrice);
			});
			$(window).trigger('resize');
		},
		initType: function(){
			var cookieType = $.cookie('type');
			if(cookieType){
				$('[name=type]', this).val(cookieType);
			}else{
				$('[name=type]', this).val('highReturn');
			}
		}
	});
	/* 品牌/活动展示banner*/
	$('.banner-container').initBanner();
	/* 初始化显示那个类型的内容*/
	$('#search-form').initType();
	
	/**
	 * 初始化销量王
	 */
	var initTopSale = function(){
		$('#top-sale-search-form').searchForm({
			url: '/index/getGoodsByTopSale',
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
		$('#search-form').find('[name=type]').val($(this).data('type')).trigger('submit');
	});
	
});