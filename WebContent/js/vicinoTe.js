var pos;
$(document).ready(function() {

	navigator.geolocation.getCurrentPosition(function(position) {
		pos = {
			lat : position.coords.latitude,
			lng : position.coords.longitude
		};
		
	});
	distanza();
});

function distanza(){
	var directionsService = new google.maps.DirectionsService();

	var request = {
		origin : pos, // a city, full address, landmark etc
		destination : '87012',
		travelMode : google.maps.DirectionsTravelMode.WALKING
	};

	directionsService.route(request, function(response, status) {
		alert("distanza "+response.routes[0].legs[0].distance.value); 
	});
}