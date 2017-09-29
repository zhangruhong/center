$(function(){
	var showActivityModal = function(activityId){
		if(!activityId){
			$('#activityModal').modal('show');
		}
	}
	
	$('#showActivityBtn').on('click', function(){
		showActivityModal();
	});
	
	$('.file-upload').fileUpload();
});