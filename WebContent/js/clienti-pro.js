$(document).ready(function() {

	initPage();
	initTable();

	$('.clienteID').on('click', function(){
		
		var mail_cliente = $(this).attr("id");
		showPrenotazioniValutazioniCliente(mail_cliente)
		
	});
		
});


function initPage(){
	$('.loaderAjaxPage').hide();
}

function initTable(){
	
	lang = {
			"emptyTable":     "Nessun cliente presente!",
		    "info":           "Mostra _START_ da _END_ a _TOTAL_ Clienti",
		    "infoEmpty":      "Mostra 0 da 0 a 0 Clienti",
		    "infoFiltered":   "(filtered from _MAX_ total entries)",
		    "infoPostFix":    "",
		    "thousands":      ",",
		    "lengthMenu":     "Mostra _MENU_ Clienti",
		    "loadingRecords": "Caricamento...",
		    "processing":     "Refresh...",
		    "search":         "Cerca:",
		    "zeroRecords":    "Nessun risultato trovato",
		    "paginate": {
		        "first":      "Prima",
		        "last":       "Ultima",
		        "next":       "Prossima",
		        "previous":   "Precedente"
		    },
		    "aria": {
		        "sortAscending":  ": attiva per ordinare le tabelle in modo crescente",
		        "sortDescending": ": attiva per ordinare le tabelle in modo decrescente"
		    }	
	}
	$('#clientiTable').DataTable({
		language: lang
		});
}

function showPrenotazioniValutazioniCliente(mail_cliente){
	
	var ajaxLoader = $('#ajaxLoader');
	var listPrenotazioni = $("#prenotazioniAppuntamento").find('.dynamicList');
	var listValutazioni = $("#valutazioniAppuntamento").find('.dynamicList');
	
	$.ajax({
		type:'post',
		url: 'ClientiServlet?type=show',
		data: {mail: mail_cliente},
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(result){
			
			prenotazioni = result[0];
			valutazioni = result[1];

			var htmlPrenotazioni = "";

			for (i in prenotazioni) {
				htmlPrenotazioni += "<button class='list-group-item list-group-item-action>";
				htmlPrenotazioni += "<p>" + prenotazioni[i].descrizione + "</p>";
				htmlPrenotazioni += "<strong>" + prenotazioni[i].data.day + "-"
						+ prenotazioni[i].data.month + "-" + prenotazioni[i].data.year
						+ "</strong>";
				htmlPrenotazioni += "<p>" + "Dalle " + prenotazioni[i].ora_inizio.hour + ":"
						+ prenotazioni[i].ora_inizio.minute;
				htmlPrenotazioni += " a " + prenotazioni[i].ora_fine.hour + ":"
						+ prenotazioni[i].ora_fine.minute + "</p>";
				htmlPrenotazioni += "</button>";
			}
			
			var htmlValutazioni = "";

			for (i in valutazioni) {
				htmlValutazioni += "<button class='list-group-item list-group-item-action'>";
				htmlValutazioni += "<p>" + valutazioni[i].descrizione + "</p>";
				htmlValutazioni += "<strong>" + valutazioni[i].data.day + "-"
						+ valutazioni[i].data.month + "-" + valutazioni[i].data.year
						+ "</strong>";
				htmlValutazioni += "<p>" + "Rating: " + "<small class='ratingStar'>" + valutazioni[i].rating + "</small>" + "</p>";
				htmlValutazioni += "<p>" + "Dal Cliente: " + valutazioni[i].mail_cliente + "</p>";
				htmlValutazioni += "</button>";
		}
			
			
			
			$('#modalAppuntamento').on("shown.bs.modal", function () {
				listPrenotazioni.empty();
				listValutazioni.empty();
				listPrenotazioni.append(htmlPrenotazioni);
				listValutazioni.append(htmlValutazioni);
					
			}).modal('show');
			
		}
	});
}
