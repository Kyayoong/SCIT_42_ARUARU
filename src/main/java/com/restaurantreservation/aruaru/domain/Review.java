package com.restaurantreservation.aruaru.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Review {
	int review_num;				//리뷰번호
    String member_id;			//회원아이디
    int restaurant_num;			//식당번호									
    int usage_num;				//이용내역번호
    String title;				//제목
    String contents;			//내용
    int grade;					//평점
    String review_originalfile;	//리뷰사진(오리지널)
    String review_savedfile;	//리뷰사진(저장)
    String review_suggestion;	//리뷰추천
    String reviewed_date;		//리뷰 작성일 테이블 수정 필요
    String member_nickname;		//맴버네임
    String reservation_date;	//예약날자
    int member_num;				//맴버넘버

    public Review(String member_id, int restaurant_num, int usage_num, String title, String contents, int grade) {
		super();
		this.member_id = member_id;
		this.restaurant_num = restaurant_num;
		this.usage_num = usage_num;
		this.title = title;
		this.contents = contents;
		this.grade = grade;
	}
}




