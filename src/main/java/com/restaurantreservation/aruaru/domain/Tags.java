package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Tags {
	
	
    int tags_num;				//태그 번호
    int restaurant_num;			//식당번호
    String tags_name;			//태그 이름
    String tags_sector;			//태그 종류
}
