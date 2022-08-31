/**
 * @fileoverview: KakaoMap 외부 API의 Javascript
 * @author: SoungJun Cho
 * @since: 2022.08.31
 */
/**-----------------------------------------------
 * 카카오 맵 테스트용
 * div id="map"을 불러올 때, HTML DOM 형식으로 불러와야한다.
 * 때문에 배열형식의 맨 앞 index를 참조하여 가져온다.
 * 
 * 1. 지도에 표시할 위치의 주소를 좌표로 변환
 * 2. 해당 좌표를 중심으로 지도를 호출
 * 3. 해당 좌표에 마커 올리기
 * 4. 해당 마커에 이벤트 걸기
 *------------------------------------------------*/
  
//맵 구현할 HTML상의 위치
let container = document.getElementById('map');


/**-----------------------------------------
 * 1. 지도위에 표시할 위치의 주소를 좌표로 변환
 *-----------------------------------------*/
//주소 -> 좌표 기능을 제공해주는 API 기능
let geocoder = new kakao.maps.services.Geocoder();

//검색하는 함수 선언
let callback = function(result, status) {
    //service가 잘 작동되고 있을 때.
	if (status === kakao.maps.services.Status.OK) {
	    console.log(result);
	    console.log(result[0].x);
	    console.log(result[0].y);
	 	
	 	/**---------------------------------------------
	 	* 2. 주소로 얻어낸 좌표 위치를 중심으로 지도를 그리기
	 	*---------------------------------------------*/
		let options = {
		    //중심좌표
		    center: new kakao.maps.LatLng(result[0].y, result[0].x),
		    //확대level
		    level: 3,
		};
		
		//맵 그리기
		let map = new kakao.maps.Map(container, options);
		
		//맵 추가기능: 스카이뷰 <-> 일반지도 버튼
		let mapTypeControl = new kakao.maps.MapTypeControl();
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
		
		//맵 추가기능: 줌인, 아웃 버튼
		let zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		/**----------------------
		 * 3.지도 위에 마커 올리기
		 *----------------------*/
		// 마커 생성 위치
		let markerPosition  = new kakao.maps.LatLng(result[0].y, result[0].x); 
		// 마커 이미지 경로
		let imageSrc = '/aruaru/images/map_flag_icon.png';
		// 마커 사이즈
		let imageSize = new kakao.maps.Size(31, 35);
		// 마커 이미지 위치 조정
		let imagePoint = new kakao.maps.Point(10, 38);
		// 마커의 클릭 또는 마우스오버 가능한 영역의 모양
		let markerForm = "poly";
		// 마커의 클릭 또는 마우스오버 가능한 영역을 표현하는 좌표값
		let markerRange = "1,20,1,9,5,2,10,0,21,0,27,3,30,9,30,20,17,33,14,33";
		
		// 마커 이미지 설정
		let icon = new kakao.maps.MarkerImage(
		    imageSrc,
		    imageSize,
		    imagePoint,
		    markerForm,
		    markerRange
		); 
	
		// 마커 생성
		let marker = new kakao.maps.Marker({
			//마커의 위치
		    position: markerPosition,
		    //마커 이미지
		    image: icon
		});
		// 맵에 마커 올리기
		marker.setMap(map);
		
		/**---------------------
         * 4. 마커에 이벤트 걸기
         *---------------------*/
		kakao.maps.event.addListener(marker, 'mouseover', function() {
		    console.log('marker hover!');
		});
	}
}

//받아온 주소 정보를 여기에 넣는다.(코엑스 주소)
let addr = "서울특별시 강남구 영동대로 513";
//주소를 넣어 callback함수 호출
geocoder.addressSearch(addr, callback);

