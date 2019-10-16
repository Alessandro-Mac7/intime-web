$(document).ready(function() {
	var sugg = [];
	$.ajax({
		type : 'get',
		url : 'AutocompleteSearchServlet',
		success : function(result) {
			for ( var r in result) {
				sugg.push(result[r]);
			}
		}
	});
	$("#ricerca").autocomplete({
		maxResults: 5,
		source : function(request, response) {
	        var results = $.ui.autocomplete.filter(sugg, request.term);
	        response(results.slice(0, this.options.maxResults));
	    },
		minLenght:1
	});
});