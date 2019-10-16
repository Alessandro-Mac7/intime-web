
$(document).ready(function() {
	
	$('#successDeletePrenotazione').hide();
	
	$('#removeAppuntamento').hide();
	
	
	$('.prenotazioneID').on('click', function(){
			
		if($(this).hasClass('effettuato')){
		}

		var codice = $(this).attr("id");
		
		$.ajax({
			type:'get',
			url: 'PrenotazioneClienteServlet?type=show',
			data: {codiceAppuntamento: codice},
			contentType: "application/json",
			success: function(result){
	
				
				$('#codiceAppuntamento').empty();
				$('#dataAppuntamento').empty();
				$('#oraAppuntamento').empty();
				$('#oraFineAppuntamento').empty();
				$('#descrizioneAppuntamento').empty();
						
				$('#codiceAppuntamento').append(result.codice);
				$('#dataAppuntamento').append(result.data.year + "-" + result.data.month + "-" + result.data.day);
				$('#oraAppuntamento').append(result.ora_inizio.hour + ":" + result.ora_inizio.minute);
				$('#oraFineAppuntamento').append(result.ora_fine.hour + ":" + result.ora_fine.minute);
				$('#descrizioneAppuntamento').append(result.descrizione);

			}
		});
		
	});
		
	$('#removeAppuntamento').on('click', function(){

		var codice = $("#codiceAppuntamento").text();
		
		if(codice.trim() != "")
			$('#confirmModal').modal('show');
		
		
		
		$('#confirmDelete').on('click', function(){
			$.ajax({
				type:'post',
				url: 'PrenotazioneClienteServlet?type=delete',
				data: {codiceAppuntamento: codice},
				success: function(){
					$('#confirmModal').modal('hide');
					$("#" + codice).remove();
					
					$('#codiceAppuntamento').empty();
					$('#dataAppuntamento').empty();
					$('#oraAppuntamento').empty();
					$('#oraFineAppuntamento').empty();
					$('#descrizioneAppuntamento').empty();

					$('#successDeletePrenotazione').show();

				},

				error: function(){
					
			  }
			});
		});
			
			
		
		
	});
});
