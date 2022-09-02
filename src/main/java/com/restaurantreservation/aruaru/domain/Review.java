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
}
