$(function(){
	test = function(type){
		$.ajax({url: '/admin/collectGoods', data: {type : type}}).done(function(){
			swal({title: '采集完成', type: 'success'});
		});
	}
});