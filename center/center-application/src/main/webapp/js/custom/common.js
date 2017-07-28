$(function(){
	
	//顶部类目表
	var init = function(){
		//初始化顶部商品分类条
		$('.category-menu li').mouseleave(function(){
			$('.category-menu li.category-default').addClass('active');
		}).mouseenter(function(){
			$('.category-menu li.category-default').removeClass('active');
		}).trigger('mouseleave');
		
		
		var $goodsContainer = $('.goods-container');
		$(window).on('resize', function(){
			var singleWidth = 270;
			var parentWidth = $goodsContainer.parent().width();
			var colNumPerRow = Math.floor(parentWidth / singleWidth);
			var realMargin = (parentWidth - (singleWidth *  colNumPerRow ))/2;
			$('.section-content').each(function(index, value){
				$('.goods-container', $(this)).each(function(i,v){
					$(this).css({marginLeft: '10px'});
					if(i % colNumPerRow == 0){
						$(this).css({marginLeft: realMargin + 10 + 'px'});
					}
				});
				$('.pagination', $(this)).css({marginRight: realMargin + 10 + 'px'});
			});
			
		}).trigger('resize');
		
	}
	init();
});