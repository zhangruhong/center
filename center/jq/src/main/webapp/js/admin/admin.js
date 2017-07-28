$(function(){
	test = function(){
		$.ajax({url: '/admin/collectGoods'}).done(function(){
			swal({title: '采集完成', type: 'success'});
		});
	}
});