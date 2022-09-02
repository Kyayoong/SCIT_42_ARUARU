package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Usage_history {

	
    int usage_num;				//이용내역 번호
    int reservation_num;		//예약내역 번호
    String usage_date;			//이용내역 날짜
    String usage_information;	//이용 정보
    String member_id;			//회원 아이디
    int restaurant_num;			//식당번호
    int reservation_success;	//실제갔는지확인
}
