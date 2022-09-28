package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Reservation {
	
	
    int reservation_num;		//예약 번호
    String member_id;			//회원아이디
    int restaurant_num;			//식당번호
    String restaurant_name;		//식당이름
    int reservation_people;		//예약인수
    String registration_date;	//등록일
    String reservation_date;	//예약일
    String reservation_hours;	//예약시간
    String request;				//요청사항
    int reservation_success;
}
