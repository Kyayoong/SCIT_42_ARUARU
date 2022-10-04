package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin_Graphs {
	int date_id;		// 데이터 번호
	String dates;		// 데이터의 날짜
	int users_cnt;		// 일반유저 가입한 숫자
	int restaurant_cnt;  // 레스토랑멤버 승인된 숫자
	int allreview_cnt;	// 사이트 전체 리뷰 숫자
	int visit_cnt;		// 일일 방문자 숫자 - 홈화면 로드 될때, 쿠키에 방문인수 true --> 카운트 안올라감
	
	//데이터 +할 변수들만 1세팅, 나머지는 0세팅하여 sql에서 한번에 올릴 수 있게 한다.
	public Admin_Graphs(int users_cnt, int restaurant_cnt, int visit_cnt) {
		super();
		this.users_cnt = users_cnt;
		this.restaurant_cnt = restaurant_cnt;
		this.visit_cnt = visit_cnt;
	}
	
	//비고... 한 가게만 특정해서 리뷰수 측정하는 건, 따로 테이블 만들어서 가게넘버 + 날짜 + 카운터 만들어야할듯.
}
