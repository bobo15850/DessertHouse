$(document).ready(function() {

	$("#nav_days").hide();

	$(function() {
		$(window).scroll(function() {
			if ($(this).scrollTop() > 100) {
				$('#nav_days').fadeIn();
			} else {
				$('#nav_days').fadeOut();
			}
			var offsets=[];
			var aList=$('#nav_days a');
			for(var i=0;i<aList.size();i++){
				if($(this).scrollTop()>=$($(aList.get(i)).attr('href')).offset().top){
					changeAListColor('#0089dc',i);
				}
			}

		});

		// scroll body to 0px on click 
		$('#nav_days a').click(function() {
			$('body,html').animate({
				scrollTop : $($(this).attr("href")).offset().top
			}, 800);
			return false;
		});
	});
});

function changeAListColor(color,n){
	var aList=$('#nav_days a');
	for(var i=0;i<aList.size();i++){
		if(n==i){
			$(aList.get(i)).css('color',color);
		}else{
			$(aList.get(i)).css('color','');
		}
	}
}