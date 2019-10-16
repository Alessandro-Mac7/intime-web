var label = [];
var data = [];
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : 'DataServlet?makedata=statisticheCliente',
		async : true,
		success : function(result) {
			for (i in result) {
				label.push(i);
				data.push(parseInt(result[i]));
			}
			disegna();
		}
	});

});

function disegna() {
	if (data == "") {
		var ctxD = document.getElementById("doughnutChart").getContext('2d');
		var myLineChart = new Chart(ctxD, {
			type : 'doughnut',
			data : {
				labels : [ "Nessun Appuntamento" ],
				datasets : [ {
					data : [ 100 ],
					backgroundColor : [ rCol() ],
					hoverBackgroundColor : [ rCol() ]
				} ]
			},
			options : {
				responsive : true
			}
		});
	} else {
		var ctxD = document.getElementById("doughnutChart").getContext('2d');
		var myLineChart = new Chart(ctxD, {
			type : 'doughnut',
			data : {
				labels : label,
				datasets : [ {
					data : data,
					backgroundColor : [ rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol() ],
					hoverBackgroundColor : [ rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol() ]
				} ]
			},
			options : {
				responsive : true
			}
		});
	}
}

function rCol() {
	var letters = '0123456789ABCDEF'.split('');
	var color = '#';
	for (var i = 0; i < 6; i++) {
		color += letters[Math.floor(Math.random() * 16)];
	}
	return color;
}