$(function(){
	
	$('.category-menu li').mouseleave(function(){
		$('.category-menu li.category-default').addClass('active');
	}).mouseenter(function(){
		$('.category-menu li.category-default').removeClass('active');
	}).trigger('mouseleave');
	
});