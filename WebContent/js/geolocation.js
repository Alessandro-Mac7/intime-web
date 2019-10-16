var map, infoWindow;
var address;
function initMap() {
	//geocoder serve per trasformare l'indirizzo in coordinate
	var geocoder = new google.maps.Geocoder();
	$.ajax({
		type : 'post',
		async : false,
		url : 'ProfiloServlet?type=posizione',
		contentType : "application/json",
		success : function(result) {
			address = result;
			//alert(address);
		}
	});
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var latitude = results[0].geometry.location.lat();
			var longitude = results[0].geometry.location.lng();
			map = new google.maps.Map(document.getElementById('map'), {
				center : {
					lat : latitude,
					lng : longitude
				},
				zoom : 17
			});
			var marker = new google.maps.Marker({
				position : {
					lat : latitude,
					lng : longitude
				},
				map : map,
			});
		}
	});
}