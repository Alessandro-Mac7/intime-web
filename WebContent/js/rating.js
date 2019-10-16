$(document).ready(function() {

});

function ratingStar() {
	
	var result = $('#rating').val();

	if (result == 0) {
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
	} else if (result == 1) {
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star "></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
	} else if (result == 2) {
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
	} else if (result == 3) {
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
	} else if (result == 4) {
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star"></span>');
	} else if (result == 5) {
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
		$('#rating').append('<span class="fa fa-star checked"></span>');
	}

}
