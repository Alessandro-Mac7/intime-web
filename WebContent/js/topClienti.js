var label = [];
var data = [];
$(document).ready(function() {
	$.ajax({
		type : 'post',
		url : 'DataServlet?makedata=topClienti',
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
		var ctxD = document.getElementById("topClienti").getContext('2d');
		ctxD.canvas.width = 300;
		ctxD.canvas.height = 300;
		ctx.canvas.originalwidth = ctx.canvas.width;
		ctx.canvas.originalheight = ctx.canvas.height;
		var myLineChart = new Chart(ctxD, {
			type : 'doughnut',
			data : {
				labels : [ "Nessun Cliente" ],
				datasets : [ {
					data : [ 100 ],
					backgroundColor : [ rCol() ],
					hoverBackgroundColor : [ rCol() ]
				} ]
			},
			options : {
				responsive : true,
			    maintainAspectRatio: true
			}
		});
	
	} else {
		var ctxD = document.getElementById("topClienti").getContext('2d');
		ctxD.canvas.width = 300;
		ctxD.canvas.height = 300;
		var myLineChart = new Chart(ctxD, {
			type : 'pie',
			data : {
				labels : label,
				datasets : [ {
					data : data,
					backgroundColor : [ rCol(), rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol() ],
					hoverBackgroundColor : [ rCol(), rCol(), rCol(), rCol(),
							rCol(), rCol(), rCol(), rCol(), rCol(), rCol() ]
				} ]
			},
			options : {
				responsive : true,
			    maintainAspectRatio: true

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