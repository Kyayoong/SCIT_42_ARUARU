/**
 * 
 */
 
 window.addEventListener('scroll', function() {
	let value = window.scrollY;
	console.log("scrollY", value);
	
	if(value > 270 ) {
		document.getElementById("font1").style.animation="disappear 2s forwards";
		document.getElementById("font1content").style.animation="disappear 2s forwards";
		document.getElementById("btsection2").style.animation="disappear 2s forwards";
	} else {
		document.getElementById("font1").style.animation="slide 2s";
		document.getElementById("font1content").style.animation="slide 3s";
		document.getElementById("btsection2").style.animation="slide 4s";
	}
	
	if(value > 650) {
		document.getElementById("demo").style.animation="slide2 3s forwards";
	} else {
		document.getElementById("demo").style.animation="disappear2 2s forwards";
	}
	
	if(value > 1350) {
		document.getElementById("profile").style.animation="slide_profile 3s forwards";
	} else {
		document.getElementById("profile").style.animation="disappear_profile 3s forwards";
	}
});