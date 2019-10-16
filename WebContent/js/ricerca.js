$(document).ready(function() {
	
	
	createData();
	
	$('#formFilter').on('click', function(){
		var formResults = $("#professione").val();
		alert(formResults);
		filterByProfessione(formResults);
		
	});


});

var data = [];
var dataFilter = [];

function createData(){
	
	var query = $('#ricerca').val();
	
	$.ajax({
		type:'post',
		url: 'DataServlet?makedata=search',
		data: {queryString: query},
		success: function(result){
			data = result;
		}
	});
}


function filterByProfessione(professione){
	
	for(i in data){
		if(data[i].professione == professione){
			dataFilter.push(data[i]);
		}
	};
	
}

function filterByProvincia(){
	alert(data.length);
	for(i in data){
		alert(data[i].nome);
	};
	
}

function filterByProfessioneProvincia(){
	alert(data.length);
	for(i in data){
		alert(data[i].nome);
	};
	
}

