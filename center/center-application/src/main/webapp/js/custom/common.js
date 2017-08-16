$(function(){
	
	/* 通用扩展方法 */
	$.fn.extend({
		initUser: function(){
			if(code){
				$.ajax({url: ''})
			}
		},
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
				var $goodsContainer = $('<div class="goods-container"></div>').appendTo($goodsList);
				if(g.ticketLeft == 0){
					var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.mainImageUrl + ')" href="' + g.tbkLongUrl + '" target="_blank"></a>').appendTo($goodsContainer);
					var $goodsName = $('<div class="goods-name">' + g.name +'</div>').appendTo($goodsContainer);
					var $goodsOriginalPrice = $('<div class="goods-original-price">￥' + g.originalPrice.toFixed(2) +'</div>').appendTo($goodsContainer);
					var $soldCount = $('<div class="goods-sold-count">月销量:<span class="num">' + g.soldCountPerMonth +'</span></div>').appendTo($goodsOriginalPrice);
				}else{
					var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.mainImageUrl + ')" href="' + g.ticketUrl + '" target="_blank"></a>').appendTo($goodsContainer);
					var $goodsName = $('<div class="goods-name">' + g.name +'</div>').appendTo($goodsContainer);
					var $goodsOriginalPrice = $('<div class="goods-original-price">￥' + g.originalPrice.toFixed(2) +'</div>').appendTo($goodsContainer);
					var $goodsTicket = $('<div class="goods-ticket-container"></div>').append('<a class="goods-ticket" href="' + g.ticketUrl + '" target="_blank">' + g.ticketValue + '</a>').appendTo($goodsContainer);
					var $soldCount = $('<div class="goods-sold-count">月销量:<span class="num">' + g.soldCountPerMonth +'</span></div>').appendTo($goodsOriginalPrice);
				}
				
			});
			$(window).trigger('resize');
		},
		initTqgContainer: function(){
			$.ajax({url: '/search/searchTqg'}).done(function(objects){
				var $goodsList = $(this);
				$('.goods-container', $goodsList).remove();
				$.each(objects.rows, function(i, g){
					var $goodsContainer = $('<div class="goods-container"></div>').prependTo($goodsList);
					var $goodsImage = $('<a class="goods-image" style="background-image: url(' + g.picUrl + ')" href="' + g.clickUrl + '" target="_blank"></a>').appendTo($goodsContainer);
					var $goodsName = $('<div class="goods-name">' + g.title +'</div>').appendTo($goodsContainer);
					var $goodsOriginalPrice = $('<div class="goods-original-price">￥' + g.reservePrice.toFixed(2) +'</div>').appendTo($goodsContainer);
					var $goodsPrice = $('<div class="goods-price"></div>').appendTo($goodsContainer);
					var $goodsOffPrice = $('<div class="goods-off-price">' + (g.reservePrice - g.zkFinalPrice).toFixed(2)  + '</div>').appendTo($goodsPrice);
					var $goodsRealPrice = $('<div class="goods-real-price">到手价:<span class="num">' + g.zkFinalPrice.toFixed(2) +'</span></div>').appendTo($goodsPrice);
					var $soldCount = $('<div class="goods-sold-count">月销量:<span class="num">' + g.soldNum +'</span></div>').appendTo($goodsPrice);
					
				});
				$(window).trigger('resize');
			});
		},
		initType: function(){
			var cookieType = $.cookie('type');
			if(cookieType){
				$('[name=categoryPid]', this).val(cookieType);
				$('#type-tabs > li').removeClass('active');
				$('#type-tabs > li[data-type=' + cookieType +']').addClass('active');
			}else{
				$('[name=categoryPid]', this).val($('#type-tabs > li:first-child').data('type'));
			}
		}
	});
	
	//顶部类目表
	var init = function(){
		//初始化顶部tabs
		$('.category-menu li').mouseleave(function(){
			$('.category-menu li.category-default').addClass('active');
		}).mouseenter(function(){
			$('.category-menu li.category-default').removeClass('active'); 
		}).trigger('mouseleave');
		
		/**
		 * 初始化分类信息
		 */
		$('.main-category-bar li').mouseleave(function(){
			$('.main-category-bar li.category-default').addClass('active');
		}).mouseenter(function(){
			$('.main-category-bar li.category-default').removeClass('active');
		}).on('click', function(){
			$(this).not('.active').addClass('category-default active').siblings('li').removeClass('category-default').removeClass('active');
			var id = $(this).data('id');
			if(id && $(this).hasClass('active')){
				$('[name=categoryPid]').val(id);
				$('[name=categoryId]').val('');
				var $subC = $('.sub-category-bar').empty();
				$('<li class="active">全部</li>').appendTo($subC);
				$.ajax({url: '/search/getChildrenCategory', data: {id : id}}).done(function(children){
					$.each(children, function(i, c){
						$('<li data-id="' + c.id + '">' + c.name + '</li>').appendTo($subC);
					});
					$('.sub-category-bar li').on('click', function(){
						$(this).addClass('active').siblings('li.active').removeClass('active');
						$('[name=categoryId]').val($(this).data('id'));
						$('#search-form').trigger('submit');
					}).trigger('mouseleave');
				});
			} else{
				$('.sub-category-bar').empty();
				$('[name=categoryPid]').val('');
				$('[name=categoryId]').val('');
			}
			$('#search-form').trigger('submit');
		}).trigger('mouseleave');
		
		/**
		 * 初始化商品展示区居中
		 */
		$(window).on('resize', function(){
			$('.section-content').each(function(index, value){
				var singleWidth = 270;
				var parentWidth = $(this).width();
				var colNumPerRow = Math.floor(parentWidth / singleWidth);
				var realMargin = (parentWidth - (singleWidth *  colNumPerRow ))/2;
				$('.goods-container', $(this)).each(function(i,v){
					$(this).css({marginLeft: '10px'});
					if(i % colNumPerRow == 0){
						$(this).css({marginLeft: realMargin + 10 + 'px'});
					}
				});
				$('.pagination', $(this)).css({marginRight: realMargin + 10 + 'px'});
			});
			
		});
		
		/**
		 * 阻止enter键刷新页面
		 */
		$('input').on('keypress', function(e){
			if(e.keyCode == 13){
				return false;
			}
		})
		
	}
	init();
});