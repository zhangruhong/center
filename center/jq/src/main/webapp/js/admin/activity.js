$(function(){
	var showActivityModal = function(activityId){
		if(!activityId){
			$('#activityForm').resetForm();
			$('file-upload').empty();
			$('#activityModal').modal('show');
		}else{
			$.ajax({url: '/admin/activity/' + activityId}).done(function(activity){
				$('#activityForm').fillForm(activity);
			});
		}
	}
	
	$('#showActivityBtn').on('click', function(){
		showActivityModal();
	});
	
	$('.file-upload').fileUpload({inputName: 'mainImageUrl'});
	
	submit = function(){
		$('#activityForm').trigger('submit');
	}
});