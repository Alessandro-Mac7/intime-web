$(document).ready(function() {

	initPage();
	
	
	var element = $('#rating');
	var value = element.val();
	
	drawStar(value, element);

	$( "small" ).each(function() {	  
		  if( $( this ).hasClass("ratingStar")){
				var value =$( this ).text();
				drawStar(value, $( this ));
			}
	});

	$("#bodyNuovoAppuntamento").on('hide.bs.collapse', function() {
		var icon = $('#panelNuovoAppuntamento').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyNuovoAppuntamento").on('show.bs.collapse', function() {
		var icon = $('#panelNuovoAppuntamento').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});

	$("#bodyRecenti").on('hide.bs.collapse', function() {
		var icon = $('#panelRecenti').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyRecenti").on('show.bs.collapse', function() {
		var icon = $('#panelRecenti').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});

	$("#bodyOdierni").on('hide.bs.collapse', function() {
		var icon = $('#panelOdierni').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyOdierni").on('show.bs.collapse', function() {
		var icon = $('#panelOdierni').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});

	$("#bodyValutazioni").on('hide.bs.collapse', function() {
		var icon = $('#panelValutazioni').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyValutazioni").on('show.bs.collapse', function() {
		var icon = $('#panelValutazioni').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});
	
	$("#bodyValutazioniRecenti").on('hide.bs.collapse', function() {
		var icon = $('#panelValutazioniRecenti').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyValutazioniRecenti").on('show.bs.collapse', function() {
		var icon = $('#panelValutazioniRecenti').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});
	
	$("#myChart-wrap").on('hide.bs.collapse', function() {
		var icon = $('#panelChart').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#myChart-wrap").on('show.bs.collapse', function() {
		var icon = $('#panelChart').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});

	$('#nuovoAppuntamentoForm').submit(function(e) {

		var form = this;
		
		if (validaForm(form)) {
			creaAppuntamentoFormAjax(form);
		}
		
		e.preventDefault();

	});

	$('#reloadRecenti').on('click', function() {
		updateAppuntamentiRecenti();
	});
	
	$('#reloadOdierni').on('click', function() {
		updateAppuntamentiOdierni();
	});
	
	$('#reloadValutazioni').on('click', function() {
		updateValutazioni();
	});
	
	$('body').on('click','.open-modal-button', function(){
		
		var id = $(this).attr("id");
		showAppuntamento(id);
	});
	

});

function initPage() {
	$("#errorMessage").hide();
	$('#successAppuntamento').hide();
	$('.ajaxLoader').hide();
	$('[data-toggle="tooltip"]').tooltip();
	$('[data-toggle="collapse"]').tooltip();
	$('#bodyRecenti').animate({scrollTop: 350},30000);
	$('.collapse').collapse('show');
	$(".item:first").addClass("active");
	$('#carouselOdierni').carousel();
	$('#quote-carousel').carousel();
	$('#quote-carousel').find('.item:first').addClass("active");
	

}

function drawStar(value, element){
	
	element.empty();
	
	if (value == 0) {
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
	} else if (value == 1) {
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star "></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
	} else if (value == 2) {
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
	} else if (value == 3) {
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star"></span>');
		element.append('<span class="fa fa-star"></span>');
	} else if (value == 4) {
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star"></span>');
	} else if (value == 5) {
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
		element.append('<span class="fa fa-star checked"></span>');
	}
	
}

function updateAppuntamentiOdierni() {

	var dyamicItem = $("#panelOdierni").find('.dynamicItem');
	var ajaxLoader = $("#panelOdierni").find('.ajaxLoader');
	dyamicItem.empty();
	$("#panelOdierni").find('.empty').remove();
	
	$.ajax({
		type : 'post',
		url : 'DataServlet?makedata=odierni',

		beforeSend : function() {
			ajaxLoader.show();
			$("#carouselOdierni").hide();

		},

		complete : function() {
			ajaxLoader.hide();
			$("#carouselOdierni").show();
			$(".item:first").addClass("active");

		},

		success : function(result) {

			var item = "<div class='item'>";
			var col = "<div class='col-sm-offset-3 col-sm-6'>";
			var well = "<div class='well well-lg'>";
			var endCol = "</div>";
			var endItem = "</div>";
			var endWell = "</div>";
			var html = "";
				
			for (i in result) {
				html += item;
				html += col;
				html += well;
				html += "<p>" + result[i].descrizione + "</p>" + "<hr>";
				html += "<strong>" + result[i].data.day + 
						"-" + result[i].data.month + "-" + result[i].data.year
						+ "</strong> + <hr>";
				html += "<p>" + "Dalle " + result[i].ora_inizio.hour + ":"
						+ result[i].ora_inizio.minute;
				html += " a " + result[i].ora_fine.hour + ":"
						+ result[i].ora_fine.minute + "</p>";
				html += "<button class='btn btn-default open-modal-button dynamicElement' id=" + result[i].codice + "> Info </button>";
				html += endWell;
				html += endCol;
				html += endItem;
			}

			dyamicItem.append(html);
			
		}
	});
}

function updateAppuntamentiRecenti() {

	var list = $("#panelRecenti").find('.dynamicList');
	var ajaxLoader = $("#panelRecenti").find('.ajaxLoader');
	list.empty();
	$("#panelRecenti").find('.empty').remove();
	
	$.ajax({
		type : 'post',
		url : 'DataServlet?makedata=recenti',

		beforeSend : function() {
			ajaxLoader.show();
		},

		complete : function() {
			ajaxLoader.hide();
		},

		success : function(result) {

			var html = "";

			for (i in result) {
				html += "<button class='list-group-item list-group-item-action open-modal-button' id=" + result[i].codice +">";
				html += "<p>" + result[i].descrizione + "</p>";
				html += "<strong>" + result[i].data.day + "-"
						+ result[i].data.month + "-" + result[i].data.year
						+ "</strong>";
				html += "<p>" + "Dalle " + result[i].ora_inizio.hour + ":"
						+ result[i].ora_inizio.minute;
				html += " a " + result[i].ora_fine.hour + ":"
						+ result[i].ora_fine.minute + "</p>";
				html += "</button>";
			}

			list.append(html);
			
			
		}
	});
}

function updateValutazioni() {

	var list = $("#panelValutazioni").find('.dynamicList');
	var ajaxLoader = $("#panelValutazioni").find('.ajaxLoader');
	list.empty();
	$("#panelRecenti").find('.empty').remove();
	
	$.ajax({
		type : 'post',
		url : 'DataServlet?makedata=valutazioni',

		beforeSend : function() {
			ajaxLoader.show();
		},

		complete : function() {
			ajaxLoader.hide();
			
			$( "small" ).each(function() {	  
				  if( $( this ).hasClass("ratingStar") && $(this).parents('#bodyValutazioni').length > 0){
						var value =$( this ).text();
						drawStar(value, $( this ));
					}
			});
			
		},

		success : function(result) {

			var html = "";

			for (i in result) {
				html += "<button class='list-group-item list-group-item-action' id=" + result[i].codice +">";
				html += "<p>" + result[i].descrizione + "</p>";
				html += "<strong>" + result[i].data.day + "-"
						+ result[i].data.month + "-" + result[i].data.year
						+ "</strong>";
				html += "<p>" + "Rating: " + "<small class='ratingStar'>" + result[i].rating + "</small>" + "</p>";
				html += "<p>" + "Dal Cliente: " + result[i].mail_cliente + "</p>";
				html += "</button>";
			}

			list.append(html);
			

		}
	});
}

function isValidDate(date){
	if(date.toString()=='Invalid Date')
		return false;
	return true;
}

function validaForm(form) {

	var inizioStr = form['oraInizio'].value;
	var fineStr = form['oraFine'].value;
	var data = new Date(form['data'].value);
	var descrizione = form['descrizione'].value;
	var clienti = form['clienti'].value;
		
	if(inizioStr.trim()=="" || fineStr.trim()=="" || clienti.trim=="" || descrizione.trim()=="" || !isValidDate(data)){
		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-triangle'></i> Inserire i dati richiesti per creare un nuovo appuntamento!");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
	}
	
	var inizio = new moment(inizioStr, 'HH:mm');
	var fine = new moment(fineStr, 'HH:mm');
	var dataOdierna = new Date();
	var strOra = dataOdierna.getHours() + ":" + dataOdierna.getMinutes();
	var oraOdierna = new moment(strOra, 'HH:mm');
	
	
	if(data.getFullYear() < dataOdierna.getFullYear() ||
			(data.getMonth() < dataOdierna.getMonth() && data.getFullYear() == dataOdierna.getFullYear()) ||
			(data.getMonth() == dataOdierna.getMonth() && data.getFullYear() == dataOdierna.getFullYear() && data.getDate() < dataOdierna.getDate())){
		
		alert(data.getFullYear() +" "+ dataOdierna.getFullYear() +" "+
			data.getMonth() +" "+ dataOdierna.getMonth() +" "+
			data.getDate() +" "+ dataOdierna.getDate());
		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-triangle'></i> La data immessa non è valida!");
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
		$("#errorMessage").append("<i class='fa fa-exclamation-triangle'></i> L'ora di inizio non è valida!");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
	}
	if (inizio.hour() > fine.hour() ||
			(inizio.hour() == fine.hour() && inizio.minute() >= fine.minute())) {
		$("#errorMessage").empty();
		$("#errorMessage").append("<i class='fa fa-exclamation-triangle'></i> Gli orari immessi non sono validi!");
		$("#errorMessage").removeClass("shake");
		$("#errorMessage").show();
		$("#errorMessage").addClass("shake");
		return false;
	}
	
	return true;
}

function creaAppuntamentoFormAjax(form) {

	var ajaxLoader = $("#panelNuovoAppuntamento").find('.ajaxLoader');
	
	$.ajax({
		type : 'post',
		url : 'InserisciAppuntamentoServlet',
		data : $('#nuovoAppuntamentoForm').serialize(),
		
		beforeSend : function() {
			ajaxLoader.show();
			$("#nuovoAppuntamentoForm").hide();
		},

		complete : function() {
			ajaxLoader.hide();
			$("#nuovoAppuntamentoForm").show();

		},
		
		success : function() {

			$('#successAppuntamento').show();
			$("#errorMessage").hide();
			form.reset();
			updateAppuntamentiRecenti();
		},

		error : function() {
			$("#errorMessage").empty();
			$("#errorMessage").append("Inserire i dati richiesti per creare un nuovo appuntamento!");
			$("#errorMessage").removeClass("shake");
			$("#errorMessage").show();
			$("#errorMessage").addClass("shake");
		}
	});
}

function showAppuntamento(id){
	
	$.ajax({
		type:'get',
		url: 'AppuntamentoServlet?type=show',
		data: {codiceAppuntamento: id},
		contentType: "application/json",
		success: function(result){
			
			var appuntamento = result[0];
			var prenotati = result[1];
	//		alert(prenotati.length);
			
			var title = "Codice Appuntamento: " + appuntamento.codice;
			
			var dataOdierna = new Date();
			var tempo = moment(appuntamento.ora_inizio.hour +":"+appuntamento.ora_inizio.minute, "HH:mm");

			var ore = tempo.diff(dataOdierna, 'hour');
			var minuti = moment(tempo.diff(dataOdierna, 'HH:mm')).format('mm'); 
			var oreFine = appuntamento.ora_fine.hour;
			var minutiFine =  appuntamento.ora_fine.minute; 
			
			var stati = ["attivo", "in corso", "svolto"];
			var statoAttuale = stati[0];
		
			if(ore > 0){
				if(minuti > 0){
					var tempoMancante = ore + " ore e " + minuti + " minuti";
				} else{
					var tempoMancante = ore + " ore";
				}
				statoAttuale = stati[0];
				
			}
			else if(minuti > 0 && ore == 0){
				var tempoMancante = (minuti) + " minuti";
				statoAttuale = stati[0];
			}
			else if(oreFine > dataOdierna.getHours() || 
					(oreFine == dataOdierna.getHours() && minutiFine <= dataOdierna.getHours())){
				statoAttuale = stati[1];
			}
			else{
				statoAttuale = stati[2];
			}
			
			var body = "<p class='small'>" + "Descrizione:" + "</p>";
			body += "<p>" + appuntamento.descrizione + "</p>" + "<hr>";
			body += "<p class='small'>" + "Data:" + "</p>";
			body += "<strong>" + appuntamento.data.day + " - " + appuntamento.data.month + " - " + appuntamento.data.year + "</strong>"  + "<hr>" ;
			body += "<p class='small'>" + "Ora:" + "</p>";
			body += "<p>" + "Dalle " + appuntamento.ora_inizio.hour + ":" + appuntamento.ora_inizio.minute + " a " + appuntamento.ora_fine.hour + ":" + appuntamento.ora_fine.minute + "</p>"  + "<hr>";
			body += "<p class='small'>" + "Numero massimo di clienti:" + "</p>";
			body += "<p>" + appuntamento.numero_clienti + "</p>" + "<hr>";
			
			pullright = "<div class='pull-right'>"
			if(statoAttuale == stati[0]){
				pullright +=  "Stato: " + "<strong>" + statoAttuale + "</strong>" ;
				body += "<p class='small'>" + "Tempo mancante:" + "</p>";
				body += "<p>" + "Mancano " + "<strong>" + tempoMancante + "</strong>" + " per questo appuntamento." + "</p>";

			} else {
				pullright += "<strong>" + "Stato: " + statoAttuale + "</strong>" ;
			}
			
			 var footer = "<p>" +  "Clienti prenotati: " + "<strong>" + prenotati.length + "</strong>"+ "</p>";
			 
			 for(var i in prenotati){
				 footer += "<p>" + "<strong>" + prenotati[i].mail + "</strong>"+ "</p>";
			 } 
			 
			 pullright += "</div>";
			 var html = body;
			
			$('#modalAppuntamento').on("shown.bs.modal", function () { 
				$(this).find('.modal-title').empty();	 
				$(this).find('.modal-body').empty();	
				$(this).find('.modal-footer').empty();
				
				$(this).find('.modal-title').append(title + pullright);	           
				$(this).find('.modal-body').append(body);
				$(this).find('.modal-footer').append(footer);
					
			}).modal('show');
			
		}
	});

}
