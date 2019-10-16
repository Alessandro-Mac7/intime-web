$(document).ready(function() {
	initMap();
});

function initMap() {
	var map;
	var address;
	// geocoder serve per trasformare l'indirizzo in coordinate
	var geocoder = new google.maps.Geocoder();
	address = $('#minimappa').text();
	geocoder.geocode({
		'address' : address
	}, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var latitude = results[0].geometry.location.lat();
			var longitude = results[0].geometry.location.lng();
			map = new google.maps.Map(document.getElementById('minimappa'), {
				center : {
					lat : latitude,
					lng : longitude
				},
				zoom : 16
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