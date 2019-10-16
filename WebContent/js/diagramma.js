var stat;
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : 'DataServlet?makedata=chart',
		async : true,
		success : function(result) {
			stat = result;
			disegna();
		}
	});
});

function disegna() {
	if (stat[0] == 0 && stat[1] == 0 && stat[2] == 0 && stat[3] == 0
			&& stat[4] == 0) {
		var ctxD = document.getElementById("myChart").getContext('2d');
		var myLineChart = new Chart(ctxD, {
			type : 'doughnut',
			data : {
				labels : [ "Nessuna Recensione" ],
				datasets : [ {
					data : [100],
					backgroundColor : [ "#F7464A", "#46BFBD", "#FDB45C",
							"#949FB1", "#4D5360" ],
					hoverBackgroundColor : [ "#FF5A5E", "#5AD3D1", "#FFC870",
							"#A8B3C5", "#616774" ]
				} ]
			},
			options : {
				responsive : true
			}
		});
	} else {
		var ctxD = document.getElementById("myChart").getContext('2d');
		var myLineChart = new Chart(ctxD, {
			type : 'doughnut',
			data : {
				labels : [ "1 Stella", "2 Stelle", "3 Stelle", "4 Stelle",
						"5 Stelle" ],
				datasets : [ {
					data : stat,
					backgroundColor : [ "#F7464A", "#46BFBD", "#FDB45C",
							"#949FB1", "#4D5360" ],
					hoverBackgroundColor : [ "#FF5A5E", "#5AD3D1", "#FFC870",
							"#A8B3C5", "#616774" ]
				} ]
			},
			options : {
				responsive : true
			}
		});
	}
}

/*
 * google.charts.load('current', { 'packages' : [ 'corechart' ] });
 * google.charts.setOnLoadCallback(init);
 * 
 * var stat;
 * 
 * function init() { $.ajax({ type : 'post', url : 'DataServlet?makedata=chart',
 * async : false, success : function(result) { stat = result; // alert("success " +
 * result); } }); // alert("dati " + stat); drawChart(); };
 * 
 * function drawChart() { if (stat[0] == 0 && stat[1] == 0 && stat[2] == 0 &&
 * stat[3] == 0 && stat[4] == 0) { var data =
 * google.visualization.arrayToDataTable([ [ 'Rating', 'Percentuale' ], [
 * 'Nessun Punteggio', 100 ] ]); } else { var data =
 * google.visualization.arrayToDataTable([ [ 'Rating', 'Percentuale' ], [ '5
 * Stelle', stat[4] ], [ '4 Stelle', stat[3] ], [ '3 Stelle', stat[2] ], [ '2
 * Stelle', stat[1] ], [ '1 Stella', stat[0] ] ]); } var options = { width :
 * '100%', height : '100%', backgroundColor: { fill:'transparent' } };
 * 
 * var chart = new google.visualization.PieChart(document
 * .getElementById('myChart')); chart.draw(data, options); }
 */