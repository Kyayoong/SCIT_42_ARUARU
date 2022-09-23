package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Holiday {

	
	 int holidays_num;				//휴무일 번호
	 String holidays;				//휴무일
	 int restaurant_num;			//식당번호
	 String holiday_date;			//휴무날짜
}
