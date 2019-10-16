$(document).ready(function() {

	initPage();

	$( "small" ).each(function() {	  
		  if( $( this ).hasClass("ratingStar")){
				var value =$( this ).text();
				drawStar(value, $( this ));
			}
	});


	$("#bodyPrenotazioniEffettuate").on('hide.bs.collapse', function() {
		var icon = $('#panelPrenotazioniEffettuate').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyPrenotazioniEffettuate").on('show.bs.collapse', function() {
		var icon = $('#panelPrenotazioniEffettuate').find('.fa-plus');
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

	$("#bodyDaValutare").on('hide.bs.collapse', function() {
		var icon = $('#panelDaValutare').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyDaValutare").on('show.bs.collapse', function() {
		var icon = $('#panelDaValutare').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});
	
	$("#panelValutazioni").on('hide.bs.collapse', function() {
		var icon = $('#bodyValutazioni').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#panelValutazioni").on('show.bs.collapse', function() {
		var icon = $('#bodyValutazioni').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});
	
	$("#bodyChart").on('hide.bs.collapse', function() {
		var icon = $('#panelChart').find('.fa-minus');
		icon.removeClass('fa-minus');
		icon.addClass('fa-plus');
	});

	$("#bodyChart").on('show.bs.collapse', function() {
		var icon = $('#panelChart').find('.fa-plus');
		icon.removeClass('fa-plus');
		icon.addClass('fa-minus');
	});

	$('body').on('click','.open-modal-button', function(){
		
		var id = $(this).attr("id");
		showPrenotazione(id);
	});
	
	$('body').on('click','.open-modal-button-insert', function(){
		
		var id = $(this).attr("id");
		
		$('#modalValutazione').modal('show');
		
		$('#nuovaValutazioneForm').submit(function(e){
			var form = this;

			valutaAppuntamento(form, id);
			e.preventDefault();
		});
		
	});
	
	$('body').on('click','.removeAppuntamento', function(){
		
		var id = $(this).attr("id");
		
		$('#confirmModal').modal('show');
		
		
		$('#confirmDelete').on('click', function(){
			
			var motivazione = $("#motivazioneAnnullamento").val();
			removePrenotazione(id, motivazione);
			
		});
	});

});

function initPage() {
	$('.ajaxLoader').hide();
	$('.loaderAjaxPage').hide();
	$('#successDeletePrenotazione').hide();
	$('#successValutazione').hide();
	$("#errorMessage").hide();
	$('#removeAppuntamento').hide();
	$('[data-toggle="tooltip"]').tooltip();
	$('[data-toggle="collapse"]').tooltip();
	$('.collapse').collapse('show');
	$(".item:first").addClass("active");
	$('#carouselOdierni').carousel();
	$('#quote-carousel').find('.item:first').addClass("active");
	$('#quote-carousel').carousel();


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

function showPrenotazione(codice){
	
	var ajaxLoader = $('#ajaxLoader');
	
	$.ajax({
		type:'get',
		url: 'PrenotazioneClienteServlet?type=show',
		data: {codiceAppuntamento: codice},
		contentType: "application/json",
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(result){
			
			var appuntamento = result[0];
			var prenotati = result[1];
			
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
			body += "<p class='small'>" + "Numero di clienti prenotati:" + "</p>";
			body += "<p>" + prenotati  + " clienti  su " + appuntamento.numero_clienti + " prenotati</p>" + "<hr>";
			
			pullright = "<div class='pull-right'>"
			if(statoAttuale == stati[0]){
				pullright +=  "Stato: " + "<strong>" + statoAttuale + "</strong>" ;
				body += "<p class='small'>" + "Tempo mancante:" + "</p>";
				body += "<p>" + "Mancano " + "<strong>" + tempoMancante + "</strong>" + " per questo appuntamento." + "</p>";

			} else {
				pullright += "<strong>" + "Stato: " + statoAttuale + "</strong>" ;
			}
			
			 var footer = "<button class='btn btn-danger removeAppuntamento' id='" +appuntamento.codice +"'>Cancella prenotazione</button>";
			 
			 pullright += "</div>";
			 var html = body;
			
			$('#genericModal').on("shown.bs.modal", function () { 
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

function removePrenotazione(codice, descrizione) {
	
	var ajaxLoader = $('#ajaxLoader');

	$.ajax({
		type:'post',
		url: 'PrenotazioneClienteServlet?type=delete',
		data: {codiceAppuntamento: codice, messaggio:descrizione },
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(){
			$('#confirmModal').modal('hide');
			$('#genericModal').modal('hide');
			$('#bodyPrenotazioniEffettuate').find("#" + codice).remove();
			$('#successDeletePrenotazione').show();

		},

		error: function(){
			alert("errore");
		}
	});
}

function valutaAppuntamento(form, codice){
	
	var ajaxLoader = $('#ajaxLoader');
	var formData = $('#nuovaValutazioneForm').serializeArray();
	formData.push({name: 'codiceAppuntamento', value: codice});
	
	$.ajax({
		type:'post',
		url: 'PrenotazioneClienteServlet?type=new-valutazione',
		data: formData,
		beforeSend : function() {
			ajaxLoader.addClass('loaderAjaxPage');
		},

		complete : function() {
			ajaxLoader.removeClass('loaderAjaxPage');
		},
		success: function(result){
			$("#errorMessage").hide();
			$('#bodyDaValutare').find("#" + codice).remove();			
			$('#successValutazione').show();
			$('#modalValutazione').modal('hide');

			form.reset();
		},

		error: function(){
			$("#errorMessage").removeClass("shake");
			$("#errorMessage").show();
			$("#errorMessage").addClass("shake");
	  }
	});
	
}