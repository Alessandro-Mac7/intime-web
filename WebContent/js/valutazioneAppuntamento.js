$(document).ready(function() {
	


	$('#formVisibile').hide();
	
	$('.valutazioneID').on('click', function(){

		var codice = $(this).attr("id");
		$('#formVisibile').show();		
		$('#codiceAppuntamentoValutabile').empty();
		$('#codiceAppuntamentoValutabile').append(codice);
	});
	
	$('#nuovaValutazioneForm').submit(function(e){
	
		
		var form = this;
		var codice = $('#codiceAppuntamentoValutabile').text();
		var formData = $('#nuovaValutazioneForm').serializeArray();
		formData.push({name: 'codiceAppuntamento', value: codice});
		
		$.ajax({
			type:'post',
			url: 'PrenotazioneClienteServlet?type=new-valutazione',
			data: formData,
			success: function(result){
				$("#errorMessage").hide();

				$('#successValutazione').show();
				$("#" + result.codice).remove();
				
				form.reset();
			},

			error: function(){
				$("#errorMessage").removeClass("shake");
				$("#errorMessage").show();
				$("#errorMessage").addClass("shake");
		  }
		});
		
		e.preventDefault();
	});

});
