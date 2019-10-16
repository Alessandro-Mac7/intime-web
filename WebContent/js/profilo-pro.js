$(document).ready(function() {

	initPage();
	
	$("#panelDati").hover( function(){
		$("#editMode").fadeToggle(200);
	});
	
	$('#editMode').on('click', function(){
		$('.editable').toggle();
		$('.inputMode').toggle();
	});
	
	$("#confirmEdit").on('click', function(){
		
		var data = getDataInput();
		openConfirmModal(data);
	});
	
	$("#modalConfirmButton").on('click', function(){
		var data = getDataInput();		
		editProfile(data);
		
	});
	
});

function initPage() {
	$("#editMode").hide();
	$('.inputMode').hide();
}

function getDataInput(){
	var obj = new Object();
	
	var indirizzo=$('input[name=indirizzo]').val();
	var telefono=$('input[name=telefono]').val();
	var sito=$('input[name=sito]').val();
	var professione=$('input[name=professione]').val();
	var descrizione=$('input[name=descrizione]').val();

	
	obj.indirizzo=indirizzo.trim();
	obj.sito=sito.trim();
	obj.professione=professione.trim();
	obj.telefono=telefono.trim();
	obj.descrizione=descrizione.trim();
	
	return obj;
}

function openConfirmModal(data){
	
	var body = "<p>I dati modificati sono: <p>";
	
	if(data.professione!="")
		body += "<p>" +"Professione: "  + data.professione + "</p>";
	if(data.indirizzo!="")
		body += "<p>" +"Indirizzo: "  +	data.indirizzo + "</p>";
	if(data.telefono!="")
		body += "<p>" +"Telefono: "  +	data.telefono + "</p>";
	if(data.sito!="")
		body += "<p>" +"Sito: "  +	data.sito + "</p>";
	
	$('#modalConfirm').on("shown.bs.modal", function () { 
		$(this).find('.modal-body').empty();			
		$(this).find('.modal-body').append(body);
	}).modal('show');

}

function editProfile(object){
	
	$.ajax({
		type:'post',
		url: 'ProfiloServlet?type=edit',
		data: {professione: object.professione, indirizzo:object.indirizzo,telefono:object.telefono,sito:object.sito,descrizione:object.descrizione},
		success: function(){
			$('.inputMode').hide();
			$('.editable').show();
			$('#successModifica').show();
		},

		error: function(){
	  }
	});
}