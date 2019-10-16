$(document).ready(function() {
	
	initFullPage();

});

function initFullPage(){
	$('#fullpage')
	.fullpage(
			{
				anchors : ['intro', 'inTime', 'vantaggiProfessionista', 'vantaggiCliente'],
				paddingTop : '60px',
				responsiveWidth : 768,
				responsiveWidth : 468,
				verticalCentered: false,
				loopBottom: true,
				css3: true,
				navigation: true,
				scrollingSpeed: 500,
				autoScrolling: true,
				scrollBar: false,
				easing: 'easeInOutCubic',
				easingcss3: 'ease',
				fadingEffect: true,

				onLeave : function(index, nextIndex,
						direction) {

					if (index == 1
							&& direction == 'down') {
						$('#description').addClass(
								'fadeInUp');
					}

					if (index == 2
							&& direction == 'down') {
						$('#proHead').addClass(
								'fadeInUp');
						$('#proBody').addClass(
								'fadeInUp');
					}

					if (index == 3
							&& direction == 'down') {
						$('#clientHead').addClass(
								'fadeInUp');
						$('#clientBody').addClass(
								'fadeInUp');
					}

				}

			});

}