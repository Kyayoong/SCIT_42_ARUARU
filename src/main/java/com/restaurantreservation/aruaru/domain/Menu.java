package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Menu {
	
	
	   int menu_num;					//메뉴번호
	   String menu_priceavr;			//1인당 평균 가격
	   String menu_name;				//메뉴이름
	   String restaurant_num;			//식당번호						
	   String menu_price;				//메뉴가격
	   String menu_originalfile;		//음식사진(오리지널)
	   String menu_savedfile;			//음식사진(저장)
	   String menu_content;				//음식상세설명
	   int menu_select;					//음식종류
	   int menu_suggestion;				//추천음식
}
