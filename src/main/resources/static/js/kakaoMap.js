/**
 * @fileoverview: KakaoMap 외부 API의 Javascript
 * @author: SoungJun Cho
 * @since: 2022.08.31
 */


/**-----------------------------------------------
 * 카카오 맵 테스트용
 * div id="map"을 불러올 때, HTML DOM 형식으로 불러와야한다.
 * 때문에 배열형식의 맨 앞 index를 참조하여 가져온다.
 *------------------------------------------------*/
let container = document.getElementById('map');
let options = {
    center: new kakao.maps.LatLng(33.450701, 126.570667),
    level: 3,
};

let map = new kakao.maps.Map(container, options);

/**--------------------------
 * 스카이뷰 <-> 일반지도 버튼
 *--------------------------*/
var mapTypeControl = new kakao.maps.MapTypeControl();
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

/**--------------------
 * 줌인, 아웃 버튼
 *--------------------*/
let zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);



/**---------------
 * 마커 관련
 *---------------*/
// 마커 생성 위치
let markerPosition  = new kakao.maps.LatLng(33.450701, 126.570667);

// 마커 생성
var marker = new kakao.maps.Marker({
	//마커의 위치
    position: markerPosition,
    //마커 이미지
    image: icon
});
// 맵에 마커 올리기
marker.setMap(map);

// 마커에 이벤트 걸기
kakao.maps.event.addListener(marker, 'mouseover', function() {
    alert('marker hover!');
});

