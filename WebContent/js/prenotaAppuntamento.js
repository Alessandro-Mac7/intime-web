$(document).ready(function() {
	
	initPage();

	$('.appuntamentoID').on('click', function(){
		var ajaxLoader = $('#ajaxLoader');

		var codice = $(this).attr("id");
		$('#confirmModal').modal('show');
				
		$('#confirmPrenota').on('click', function(){
			$.ajax({
				type:'post',
				url: 'PrenotaAppuntamentoServlet',
				data: {codiceAppuntamento: codice},
				beforeSend : function() {
					ajaxLoader.addClass('loaderAjaxPage');
				},

				complete : function() {
					ajaxLoader.removeClass('loaderAjaxPage');
				},
				success: function(){
					$('#confirmModal').modal('hide');
					$("#" + codice).remove();
					$('#successPrenotazione').show();

				},

				error: function(){
					window.location.replace("http://localhost:8080/inTime/login");
				}
			});
		});
	});
	
   $("#inputData").on("keyup", function() {
	     var value = $(this).val().toLowerCase();
	     $("#appuntamentiTable tr").filter(function() {
	       $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	     });
	});
   
   $('#list-tab a').on('click', function (e) {
	   e.preventDefault();
	   
	   var $lis = $('.list-group a');
	   
	   $(this).tab('show');
	   
	   $lis.removeClass("active");
	   $(this).addClass("active");
	   
	 });

});


function initPage(){
	$('#successPrenotazione').hide();
}





