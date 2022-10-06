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
	String restaurant_sectors; 		// 업종선택
	String restaurant_comment; 		// 상세설명
	String restaurant_phone; 		// 가게번호
	double restaurant_grade; 		// 평점
	String registration_date;		// 등록일
	String restaurant_address1; 	// 도로명
	String restaurant_address2; 	// 상세주고
	String restaurant_addressinfo; 	// 오는길 설명
	String menu_priceavr;			// 1인당 평균 가격
	int restaurant_people;			// 수용인원
	int certification; 				// 허가 여부
}
