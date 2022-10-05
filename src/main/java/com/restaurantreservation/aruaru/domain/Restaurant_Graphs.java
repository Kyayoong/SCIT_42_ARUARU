package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Restaurant_Graphs {
	String dates;		 // 데이터의 날짜
	int reservation_cnt; // 예약 개수
	int review_cnt;		 // 리뷰 개수
	int zzim_cnt;		 //	찜 개수
}
