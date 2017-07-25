$(function(){
	
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
		}
	});
	$('.banner-container').initBanner();
	
});