$(document).ready(function() {

	initPage();
	initTable();

	$('.appuntamentoID').on('click', function(){
		
		var codice = $(this).attr("id");
		showAppuntamento(codice)
		
	});

	$('#editAppuntamento').on('click', function(){
		
		var codice = $("#codiceAppuntamento").text();
		if(codice.trim() != ""){
			$('#appuntamentoVisibile').toggle();
			$('#editFormAppuntamento').toggle();
		}
		
		$('#editFormAppuntamento').submit(function(e){
			
			var form = this;
			
			condition = validaModifiche(form);
			
			if(condition){
				editAppuntamento(form, codice);
			}
			
			e.preventDefault();
		});
	});
		
	$('#removeAppuntamento').on('click', function(){

		var codice = $("#codiceAppuntamento").text();

		if(codice.trim() != "")
			$('#confirmModal').modal('show');
		
		$('#confirmDelete').on('click', function(){
			removeAppuntamento(codice);
		});
	});
	
});


function initPage(){
	$('#editFormAppuntamento').hide();
	$("#errorMessage").hide();
	$('#successAppuntamento').hide();
	$('#warningAppuntamento').hide();
	$('.loaderAjaxPage').hide();
}

function getData(){
	
	var dataResult;
	var dataObject = [];
	$.ajax({
		type:'post',
		url: 'DataServlet?makedata=calendar',
		async: false,
		success: function(result){
			dataResult = result;
		}
	});
	
	for(var i in dataResult){
		
		var stringStart = dataResult[i].data.year + "-" + dataResult[i].data.month+"-" + dataResult[i].data.day;
		var stringStartTime = dataResult[i].ora_inizio.hour + ":" + dataResult[i].ora_inizio.minute;
		var stringStartTimeEnd = dataResult[i].ora_fine.hour + ":" + dataResult[i].ora_fine.minute;
		
		var startTime = moment(stringStart + " " + stringStartTime).format("DD-MM-YYYY HH:mm");
		var endTime = moment(stringStart + " " + stringStartTimeEnd).format("DD-MM-YYYY HH:mm");
		var id = dataResult[i].codice;
		var title = dataResult[i].descrizione;
	
		var obj = {};
		
		obj.start = startTime;
		obj.end = endTime;
		obj.id= id;
		obj.title = title;
		
		dataObject.push(obj);
	}
	
	return dataObject;

}

function initTable(){
	
	lang = {
			"emptyTable":     "Nessun appuntamento Disponibile",
		    "info":           "Mostra _START_ da _END_ a _TOTAL_ Appuntamenti",
		    "infoEmpty":      "Mostra 0 da 0 a 0 Appuntamenti",
		    "infoFiltered":   "(filtered from _MAX_ total entries)",
		    "infoPostFix":    "",
		    "thousands":      ",",
		    "lengthMenu":     "Mostra _MENU_ Appuntamenti",
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
	$('#tableEffettuati').DataTable({
		language: lang
		});

	$('#tableNonEffettuati').DataTable({
		language: lang
	});
}

function isValidDate(date){
	if(date.toString()=='Invalid Date')
		return false;
	return true;
}

function validaModifiche(form) {
	
	$("#errorMessage").hide();

	var oldInizio = $('#oraAppuntamento').text();
	var oldFine = $('#oraFineAppuntamento').text();
	var oldData = new Date($('#dataAppuntamento').text());
	var oldDescrizione = $('#descrizioneAppuntamento').text();
	var oldClienti = $('#clientiAppuntamento').text();
	

	var inizioStr = form['ora'].value;
	var fineStr = form['oraFine'].value;
	var data = new Date(form['data'].value);
	var descrizione = form['descrizione'].value;
	var clienti = form['clienti'].value;
	
	var warningMessage = " ";
	var empty = 5;
	
	if(inizioStr.trim() == oldInizio.trim() || inizioStr.trim()==""){
		warningMessage += "orario di inizio, ";
		empty--;
	}
	if(fineStr.trim() == oldFine.trim() || fineStr.trim()==""){
		warningMessage += "orario di fine, ";
		empty--;
	}
	if(clienti.trim() == oldClienti.trim() || clienti.trim()==""){
		warningMessage += "numero clienti, ";
		empty--;
	}	
	if(descrizione.trim() == oldDescrizione.trim() || descrizione.trim()==""){
		warningMessage += "descrizione, ";
		empty--;
	}
	if(moment(data).isSame(oldData) || !isValidDate(data)){
		warningMessage += "data, ";
		empty--;
	}
	
	warningMessage = warningMessage.slice(0,-2);

	
	if(empty == 0){
		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-circle'></i> Spiacente! Nulla da modificare");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
	}
	else if(empty<5){
		$("#warningMessage").empty();
		$("#warningMessage").append(warningMessage);
		$("#warningMessage");		
		$('#warningAppuntamento').show();
	}


	var inizio = new moment(inizioStr, 'HH:mm');
	var fine = new moment(fineStr, 'HH:mm');
	var dataOdierna = new Date();
	var strOra = dataOdierna.getHours() + ":" + dataOdierna.getMinutes();
	var oraOdierna = new moment(strOra, 'HH:mm');
	
	
	if(data.getFullYear() < dataOdierna.getFullYear() ||
			(data.getMonth() < dataOdierna.getMonth() && data.getFullYear() == dataOdierna.getFullYear()) ||
			(data.getMonth() == dataOdierna.getMonth() && data.getFullYear() == dataOdierna.getFullYear() && data.getDate() < dataOdierna.getDate())){

		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-circle'></i> La data immessa non è valida!");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
		
	}
	
	if(data.getFullYear() == dataOdierna.getFullYear() && 
			data.getMonth() == dataOdierna.getMonth() && 
			data.getDate() == dataOdierna.getDate() &&
			(inizio.hour() < oraOdierna.hour() || (
					inizio.hour() == oraOdierna.hour() && inizio.minute() <= oraOdierna.minute()))){
		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-circle'></i> L'ora di inizio non è valida!");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
	}
	if (inizio.hour() > fine.hour() ||
			(inizio.hour() == fine.hour() && inizio.minute() >= fine.minute())) {
		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-circle'></i> Gli orari immessi non sono validi!");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
	}

	return true;
}


function editAppuntamento(form, codice){
	
	var ajaxLoader = $('#ajaxLoader');
	var formData = $('#editFormAppuntamento').serializeArray();
	formData.push({name: 'codiceAppuntamento', value: codice});
	
	$.ajax({
		type:'post',
		url: 'AppuntamentoServlet?type=edit',
		data: formData,
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(result){
			$('#successAppuntamento').show();
			
			$("#errorMessage").hide();
			
			$("#" + result.codice).empty();
			$("#" + result.codice).append("<td class='text-center'>" + result.data.year + "-" + result.data.month + "-" + result.data.day + "</td>");
			$("#" + result.codice).append("<td class='text-center'>" + result.ora_inizio.hour + ":" + result.ora_inizio.minute  + "</td>" );
			$("#" + result.codice).append("<td class='text-center'>" + result.ora_fine.hour + ":" + result.ora_fine.minute  + "</td>" );
			$("#" + result.codice).append("<td class='text-center'>" + result.descrizione + "</td>");
			$("#" + result.codice).append("<td class='text-center'>" + result.numero_clienti  + "</td>" );					
			form.reset();

			$('#codiceAppuntamento').empty();
			$('#dataAppuntamento').empty();
			$('#oraAppuntamento').empty();
			$('#oraFineAppuntamento').empty();
			$('#descrizioneAppuntamento').empty();
			$('#clientiAppuntamento').empty();
			
			$('#codiceAppuntamento').append(result.codice);
			$('#dataAppuntamento').append(result.data.year + "-" + result.data.month + "-" + result.data.day);
			$('#oraAppuntamento').append(result.ora_inizio.hour + ":" + result.ora_inizio.minute);
			$('#oraFineAppuntamento').append(result.ora_fine.hour + ":" + result.ora_fine.minute);
			$('#descrizioneAppuntamento').append(result.descrizione);
			$('#clientiAppuntamento').append(result.numero_clienti);
			
			$('#editFormAppuntamento').hide();
			$('#appuntamentoVisibile').show();
			
		},

		error: function(){
			$("#errorMessage").empty();
			$("#errorMessage").append("<i class='fa fa-exclamation-circle'></i> Impossibile modificare l'appuntamento nei giorni e nelle ore scelte");
			$("#errorMessage").removeClass("shake");
			$("#errorMessage").show();
			$("#errorMessage").addClass("shake");
	  }
	});
}

function removeAppuntamento(codice){
	
	var ajaxLoader = $('#ajaxLoader');
	
	$.ajax({
		type:'post',
		url: 'AppuntamentoServlet?type=delete',
		data: {codiceAppuntamento: codice},
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(){
			$("#" + codice).remove();
			
			$('#codiceAppuntamento').empty();
			$('#dataAppuntamento').empty();
			$('#oraAppuntamento').empty();
			$('#oraFineAppuntamento').empty();
			$('#descrizioneAppuntamento').empty();
			$('#clientiAppuntamento').empty();
			$('#prenotazioniAppuntamento').empty();
		},

		error: function(){
			alert("error");
	  }
	});
}

function showAppuntamento(codice){
	
	var ajaxLoader = $('#ajaxLoader');
	$('#editFormAppuntamento').hide();
	$('#appuntamentoVisibile').show();

	if($(this).hasClass('effettuato')){
		$('#removeAppuntamento').hide();
		$('#editAppuntamento').hide();
	}
	else{
		$('#removeAppuntamento').show();
		$('#editAppuntamento').show();
	}
	
	$.ajax({
		type:'get',
		url: 'AppuntamentoServlet?type=show',
		data: {codiceAppuntamento: codice},
		contentType: "application/json",
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(result){

			
			$('#codiceAppuntamento').empty();
			$('#dataAppuntamento').empty();
			$('#oraAppuntamento').empty();
			$('#oraFineAppuntamento').empty();
			$('#descrizioneAppuntamento').empty();
			$('#clientiAppuntamento').empty();
			$('#prenotazioniAppuntamento').empty();
			
			
			$('#codiceAppuntamento').append(result[0].codice);
			$('#dataAppuntamento').append(result[0].data.year + "-" + result[0].data.month + "-" + result[0].data.day);
			$('#oraAppuntamento').append(result[0].ora_inizio.hour + ":" + result[0].ora_inizio.minute);
			$('#oraFineAppuntamento').append(result[0].ora_fine.hour + ":" + result[0].ora_fine.minute);
			$('#descrizioneAppuntamento').append(result[0].descrizione);
			$('#clientiAppuntamento').append(result[0].numero_clienti);
			
			var prenotati = result[1];
			var html = "";
			for(var i in prenotati){
				html+= prenotati[i].mail +", ";
			}
			html.slice(0,-2);
			$('#prenotazioniAppuntamento').append(html);
			
			$('#modalAppuntamento').modal();
			
		}
	});
}
