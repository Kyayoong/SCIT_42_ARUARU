package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Restaurant_file {
	int restaurant_file_num;						//파일번호
 	String restaurant_originalfile; 				//가게 사진파일(오리지날)
    String restaurant_savedfile;  	         		//가게 사진파일(저장)
    int restaurant_num;								//식당번호
}
