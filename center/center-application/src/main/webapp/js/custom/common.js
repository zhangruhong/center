$(function(){
	
	//顶部类目表
	var init = function(){
		//初始化顶部商品分类条
		$('.category-menu li').mouseleave(function(){
			$('.category-menu li.category-default').addClass('active');
		}).mouseenter(function(){
			$('.category-menu li.category-default').removeClass('active');
		}).trigger('mouseleave');
	}
	
});