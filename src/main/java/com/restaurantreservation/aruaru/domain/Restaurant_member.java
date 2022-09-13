package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Restaurant_member {
	
	
	int restaurant_num;				// 가게번호
	String member_id; 				// 회원아이디
	String restaurant_name; 		// 가게이름
	String restaurant_ename; 		// 가게영어 이름
	String restaurant_sectors; 		// 업종선택
	String restaurant_comment; 		// 상세설명
	String restaurant_phone; 		// 가게번호
	int restaurant_grade; 			// 평점
	String registration_date;		// 등록일
	String restaurant_location; 	// 가게 위치(gps적 요소)
	String restaurant_address; 		// 가게 실제주소
	int certification; 				// 허가 여부
}
