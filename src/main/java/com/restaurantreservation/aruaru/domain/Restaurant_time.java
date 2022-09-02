package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Restaurant_time {
	
	
    String opentime;				//오픈시간
    String closetime;				//영업종료시간
    String breaktime;				//브레이크 타임
    String business_days;			//영업일
    int restaurant_num;				//식당번호

}
