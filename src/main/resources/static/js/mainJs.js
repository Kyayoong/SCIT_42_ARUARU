/**
 * 
 */

window.addEventListener('scroll', function() {
	// value = window.scrollY(세로축 스크롤 위치 값)
	let value = window.scrollY;
	// scrollY값 확인(배포 때 지울 것)
	console.log("scrollY", value);

	if (value > 270) {
		//BOOK YOUR RESERVATION 스크롤 Y위치 270이상이면 2초 걸리면서 왼쪽으로 사라짐
		document.getElementById("font1").style.animation = "disappear 2s forwards";
		//원하시는 식당을 선택하세요  스크롤 Y위치 270이상이면 2초 걸리면서 왼쪽으로 사라짐
		document.getElementById("font1content").style.animation = "disappear 2s forwards";
		//예약버튼 스크롤 Y위치 270이상이면 2초 걸리면서 왼쪽으로 사라짐
		document.getElementById("btsection2").style.animation = "disappear 2s forwards";
	} else {
		//BOOK YOUR RESERVATION 스크롤 Y위치 2초 걸리면서 270이하이면 오른쪽으로 나타남
		document.getElementById("font1").style.animation = "slide 2s";
		//원하시는 식당을 선택하세요 스크롤 Y위치 3초 걸리면서 270이하이면 오른쪽으로 나타남
		document.getElementById("font1content").style.animation = "slide 3s";
		//예약버튼 스크롤 Y위치 270이하이면 4초 걸리면서 오른쪽으로 나타남
		document.getElementById("btsection2").style.animation = "slide 4s";
	}

	if (value > 650) {
		//식당추천 그림 Y위치 650이상이면 2초 걸리면서 나타남
		document.getElementById("demo").style.animation = "slide2 2s forwards";
		//식당예약란 그림 Y위치 650이상이면 2초 걸리면서 나타남
		document.getElementById("container2").style.animation = "slide2 2s forwards";
	} else {
		//식당추천 그림 Y위치 650이상이면 2초 걸리면서 사라짐
		document.getElementById("demo").style.animation = "disappear2 2s forwards";
		//식당예약란 그림 Y위치 650이상이면 2초 걸리면서 사라짐
		document.getElementById("container2").style.animation = "disappear2 2s forwards";
	}

	if (value > 1350) {
		// Y위치 1350이상이면 서비스 소개 글 3초 걸리면서 나타남
		document.getElementById("profile").style.animation = "slide_profile 3s forwards";
	} else {
		// Y위치 1350이상이면 서비스 소개 글 3초 걸리면서 사라짐
		document.getElementById("profile").style.animation = "disappear_profile 3s forwards";
	}
});